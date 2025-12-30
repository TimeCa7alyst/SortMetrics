package DB;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbManager {

    private static HikariDataSource dataSource;

    public static void initDataSource(String jdbcUrl, String username, String password) {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(10);

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {

        if (dataSource == null) {
            System.out.println("Initializing default local connection...");
            initDataSource("jdbc:postgresql://localhost:5432/algoritmos", "root", "25339439");
        }

        return dataSource.getConnection();
    }
}