package arief.mahendra.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetTest {
    @Test
    void testExecuteSelect() throws SQLException {
        Connection connection = ConnectionUtil.getHikariDataSource().getConnection();
        Statement statement = connection.createStatement();

        var sql = """
                SELECT * FROM customers
                """;

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");

            System.out.println("id : " + id + " || Name : " + name + " || Email : " + email);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
