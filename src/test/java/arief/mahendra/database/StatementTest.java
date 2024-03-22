package arief.mahendra.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {

    @Test
    void testCreateStatement() throws SQLException {
        Connection connection = ConnectionUtil.getHikariDataSource().getConnection();
        Statement statement = connection.createStatement();

        statement.close();
        connection.close();
    }

    @Test
    void testExecuteUpdate() throws SQLException {
        Connection connection = ConnectionUtil.getHikariDataSource().getConnection();
        Statement statement = connection.createStatement();

        var sql = """
                INSERT INTO customers(id, name, email) 
                VALUES ('2', 'eko', 'eko@mail.com');
                """;

        int rowAffected = statement.executeUpdate(sql);

        System.out.println("terdapat " + rowAffected + " row yang di execute ke tabel customers");

        statement.close();
        connection.close();

    }

    @Test
    void testExecuteDelete() throws SQLException {
        Connection connection = ConnectionUtil.getHikariDataSource().getConnection();
        Statement statement = connection.createStatement();

        var sql = """
                DELETE FROM customers WHERE id = '1';
                """;

        int rowAffected = statement.executeUpdate(sql);

        System.out.println("terdapat " + rowAffected + " row yang di execute ke tabel customers");

        statement.close();
        connection.close();

    }

    @Test
    void testExecuteSelect() throws SQLException {
        Connection connection = ConnectionUtil.getHikariDataSource().getConnection();
        Statement statement = connection.createStatement();

        var sql = """
                SELECT * FROM customers
                """;

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("terdapat " + resultSet + " row yang di execute ke tabel customers");

        resultSet.close();
        statement.close();
        connection.close();
    }
}
