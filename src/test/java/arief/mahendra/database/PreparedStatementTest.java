package arief.mahendra.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementTest {

    @Test
    void testPreparedStatement() throws SQLException {
        Connection connection = ConnectionUtil.getHikariDataSource().getConnection();
        String sql = """
                select  * from admin where username = ? and password = ?
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "arief");
        preparedStatement.setString(2, "makananku");

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            System.out.println("login success with, " + username + password);
        } else{
            System.out.println("login gagal");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
