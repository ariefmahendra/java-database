package arief.mahendra.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {

    @Test
    void testTransactionCommit() throws SQLException {
        Connection connection = ConnectionUtil.getHikariDataSource().getConnection();
        connection.setAutoCommit(false);
        String sql = """
                insert into comments(email, comment) values(?, ?)
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < 100; i++) {
            preparedStatement.setString(1, "juan@mail.com");
            preparedStatement.setString(2, "hello guys");
            preparedStatement.executeUpdate();
        }

        preparedStatement.close();

        connection.commit();
        connection.close();
    }

    @Test
    void testTransactionRollback() throws SQLException {
        Connection connection = ConnectionUtil.getHikariDataSource().getConnection();
        connection.setAutoCommit(false);
        String sql = """
                insert into comments(email, comment) values(?, ?)
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < 100; i++) {
            preparedStatement.setString(1, "juan@mail.com");
            preparedStatement.setString(2, "hello guys");
            preparedStatement.executeUpdate();
        }

        preparedStatement.close();

        connection.rollback();
        connection.close();
    }
}
