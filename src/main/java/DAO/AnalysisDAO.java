package DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.entities.Analysis;
import org.postgresql.util.PGobject;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class AnalysisDAO {

    private final Connection connection;

    public AnalysisDAO(Connection connection, ObjectMapper mapper) {
        this.connection = connection;
        this.mapper = mapper;
    }

    public Analysis create(Analysis obj) throws SQLException {

        String sql = "INSERT INTO \"analysis\" (\"algorithm\", \"file\", \"date\", \"usr\", \"size\") " +
                "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

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

        try (ResultSet rs = statement.getGeneratedKeys()) {
            if (rs.next()) {
                Integer newId = rs.getInt(1);

                return new Analysis.Builder(
                        newId,
                        obj.getAlgorithm(),
                        obj.getJsonFile(),
                        obj.getDate(),
                        "ADMIN",
                        obj.getSize())
                        .build();
            }
        }
        throw new SQLException("Failed to create Analysis");
    }

    public List<Analysis> findAll(Integer id) {

    }

    public List<Analysis> findById(Integer id) {

    }

    public void delete(Integer id) {

    }
}
