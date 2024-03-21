package arief.mahendra.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {

    @Test
    void testHikariCP() {
        HikariDataSource hikariDataSource = ConnectionUtil.getHikariDataSource();
        try {
            Connection connection = hikariDataSource.getConnection();
            System.out.println("connected to postgresql database");

            // jika kita clone, artinya kita mengembalikan connection ke pool
            connection.close();
            System.out.println("close database is done");
        }catch (SQLException e){
            Assertions.fail(e);
        }
    }
}
