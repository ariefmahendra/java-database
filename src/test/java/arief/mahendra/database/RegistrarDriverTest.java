package arief.mahendra.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class RegistrarDriverTest {

    @BeforeAll
    static void setUp() {
        try {
            Driver postgresDriver = new org.postgresql.Driver();
            DriverManager.registerDriver(postgresDriver);
        } catch (SQLException exception) {
            Assertions.fail(exception);
        }
    }

    @Test
    void testCreateConnection() {
        String url = "jdbc:postgresql://localhost:5432/belajar_java_db";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "Makananku123");
        props.setProperty("ssl", "false");


        try {
            Connection connection = DriverManager.getConnection(url, props);
            System.out.println("success connect to database postgresql");
            connection.close();
            System.out.println("success close connection database");
        } catch (SQLException e){
            Assertions.fail(e);
        }
    }
}
