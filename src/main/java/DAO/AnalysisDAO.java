package DAO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.entities.Analysis;
import org.postgresql.util.PGobject;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnalysisDAO {

    private final Connection connection;
    private final ObjectMapper mapper;

    public AnalysisDAO(Connection connection, ObjectMapper mapper) {
        this.connection = connection;
        this.mapper = mapper;
    }

    public Analysis create(Analysis obj) throws SQLException {

        String sql = "INSERT INTO \"analysis\" (\"algorithm\", \"file\", \"date\", \"usr\", \"size\") " +
                "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = null;

        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            PGobject pGobject = new PGobject();
            pGobject.setType("jsonb");
            pGobject.setValue(obj.getJsonFile().toString());

            LocalDateTime actualDate = (obj.getDate() != null) ? obj.getDate() : LocalDateTime.now();

            statement.setString(1, obj.getAlgorithm());
            statement.setObject(2, pGobject);
            statement.setTimestamp(3, Timestamp.valueOf(actualDate));
            statement.setString(4, "ADMIN");
            statement.setBigDecimal(5, obj.getSize());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected <= 0) {
                throw new RuntimeException("Failed to create object");
            }

            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Integer newId = rs.getInt(1);

                return new Analysis.Builder(
                        newId,
                        obj.getAlgorithm(),
                        obj.getJsonFile(),
                        actualDate,
                        obj.getUser(),
                        obj.getSize())
                        .build();
            }
            throw new SQLException("Failed to create Analysis");
        } finally {
            rs.close();
            statement.close();
        }
    }

    public List<Analysis> findAll() throws SQLException {
        List<Analysis> analyses = new ArrayList<Analysis>();
        String sql = "SELECT * FROM \"analysis\"";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {

                String fileString = resultSet.getString("file");
                JsonNode jsonNode = null;
                if (fileString != null) {
                    jsonNode = mapper.readTree(fileString);
                }

                Analysis obj = new Analysis.Builder(
                        resultSet.getInt("id"),
                        resultSet.getString("algorithm"),
                        jsonNode,
                        resultSet.getTimestamp("date").toLocalDateTime(),
                        resultSet.getString("usr"),
                        resultSet.getBigDecimal("size"))
                        .build();

                analyses.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException("No analysis found", e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return analyses;
    }

    public Analysis findById(Integer id) throws SQLException, JsonProcessingException {

        String sql = "SELECT * FROM \"analysis\" WHERE id = ?";

        ResultSet resultSet = null;

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (!resultSet.next())
                throw new RuntimeException("Analysis not found");

            String fileString = resultSet.getString("file");
            JsonNode jsonNode = null;
            if (fileString != null) {
                jsonNode = mapper.readTree(fileString);
            }
            return new Analysis.Builder(
                    resultSet.getInt("id"),
                    resultSet.getString("algorithm"),
                    jsonNode,
                    resultSet.getTimestamp("date").toLocalDateTime(),
                    resultSet.getString("usr"),
                    resultSet.getBigDecimal("size"))
                    .build();

        } finally {
            if (resultSet != null && statement != null) {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM \"analysis\" WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error excluding analysis");
            throw new RuntimeException(e);
        }
    }
}
