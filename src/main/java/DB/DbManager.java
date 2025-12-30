package DB;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbManager {

    private static HikariDataSource dataSource;

    private static final boolean DEMO_MODE = true;

    public static void initDataSource(String jdbcUrl, String username, String password) {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }

        HikariConfig config = new HikariConfig();

        if (DEMO_MODE) {
            System.out.println("🐳 STARTING TESTCONTAINERS (Docker)... Please wait...");

            config.setJdbcUrl("jdbc:tc:postgresql:16:///apii_ado2");
            config.setUsername("test");
            config.setPassword("test");

            config.setDriverClassName("org.testcontainers.jdbc.ContainerDatabaseDriver");
        } else {
            config.setJdbcUrl(jdbcUrl);
            config.setUsername(username);
            config.setPassword(password);
        }

        config.setMaximumPoolSize(10);

        dataSource = new HikariDataSource(config);

        if (DEMO_MODE) {
            createTable();
        }
    }

    private static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Analysis (" +
                "    Id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                "    Algorithm VARCHAR(250) NOT NULL," +
                "    AlgoCase VARCHAR(250) NOT NULL," +
                "    File jsonb NOT NULL," +
                "    Date TIMESTAMP NOT NULL," +
                "    Usr VARCHAR(250) DEFAULT 'ADMIN'," +
                "    Size NUMERIC" +
                ")";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'Analysis' created in Docker container.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {

        if (dataSource == null) {
            System.out.println("Initializing default local connection...");
            initDataSource("jdbc:postgresql://localhost:5432/algoritmos", "root", "25339439");
        }

        return dataSource.getConnection();
    }
}