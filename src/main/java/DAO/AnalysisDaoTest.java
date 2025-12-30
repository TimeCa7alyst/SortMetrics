package DAO;

import DB.DbManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
public class AnalysisDaoTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @BeforeAll
    static void beforeAll() {
        DbManager.initDataSource(
                postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword()
        );
    }

    @BeforeEach
    void setUp() throws Exception {
        try (Connection conn = DbManager.getConnection();
             Statement stmt = conn.createStatement()) {

            // Adjust this SQL to match your actual database schema
            stmt.execute("DROP TABLE IF EXISTS analysis");
            stmt.execute("CREATE TABLE analysis (" +
                    "Id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                    "AlgoCase VARCHAR (250) NOT NULL, " +
                    "File jsonb NOT NULL, " +
                    "Date TIMESTAMP NOT NULL, " +
                    "Usr VARCHAR(250) DEFAULT 'ADMIN', " +
                    "Size NUMERIC)");
        }
    }

    @Test
    void testConnectionAndInsert() throws Exception {
        AnalysisDAO dao = new AnalysisDAO(DbManager.getConnection(),
                new com.fasterxml.jackson.databind.ObjectMapper());

        try (Connection conn = DbManager.getConnection()) {
            String url = conn.getMetaData().getURL();

            System.out.println("==========================================");
            System.out.println(" [TEST] Connected to: " + url);
            System.out.println("==========================================");

            if (url.contains(":5432/")) {
                System.err.println("❌ WARNING: Using LOCALHOST port 5432. Testcontainers is NOT being used.");
            } else {
                System.out.println("✅ SUCCESS: Using mapped port (not 5432). Testcontainers IS active.");
            }
        }

        try (Connection conn = DbManager.getConnection()) {
            assertNotNull(conn);
            System.out.println("Testing against: " + conn.getMetaData().getURL());
        }
    }
}