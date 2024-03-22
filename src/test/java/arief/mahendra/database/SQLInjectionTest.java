package arief.mahendra.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjectionTest {
    @Test
    void testExecuteSelect() throws SQLException {
        Connection connection = ConnectionUtil.getHikariDataSource().getConnection();
        Statement statement = connection.createStatement();

        String username = "arief'; --";
        String password = "salah";

        String sql = "select * from admin where username = '" +
                username +"' and password = '" + password + "';";

        System.out.println(sql);

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            String name = resultSet.getString("username");
            System.out.println("success login" + name);
        }

        System.out.println("gagal login");

        resultSet.close();
        statement.close();
        connection.close();
    }
}
