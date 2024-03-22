package arief.mahendra.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {

    @Test
    void testBatchCreateStatement() throws SQLException {
        Connection connection = ConnectionUtil.getHikariDataSource().getConnection();
        String sql = """
                insert into comments(email, comment) values('arief@mail.com', 'hi')
                """;

        // using create statement
        Statement statement = connection.createStatement();
        for (int i = 0; i < 1000; i++) {
            statement.addBatch(sql);
        }

        // execute batch, bahkan cuma memakan waktu 1 detik
        statement.executeBatch();

        statement.close();
        connection.close();
    }

    @Test
    void testBatchPreparedStatement() throws SQLException {
        Connection connection = ConnectionUtil.getHikariDataSource().getConnection();
        String sql = """
                insert into comments(email, comment) values(?, ?)
                """;

        // using prepared statement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < 1000; i++) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, "juan@mail.com");
            preparedStatement.setString(2, "hello guys");
            preparedStatement.addBatch();
        }

        // execute batch, bahkan cuma memakan waktu 1 detik
        preparedStatement.executeBatch();

        preparedStatement.close();
        connection.close();
    }
}
