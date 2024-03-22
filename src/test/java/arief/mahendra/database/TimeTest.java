package arief.mahendra.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class TimeTest {
    @Test
    void testInsertTime() throws SQLException {

        Connection connection = ConnectionUtil.getHikariDataSource().getConnection();
        var sql = """
                insert into sample_time(sample_date, sample_time, sample_timestamp)
                values(?,?,?)
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setDate(1, new Date(System.currentTimeMillis()));
        preparedStatement.setTime(2, new Time(System.currentTimeMillis()));
        preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Test
    void testSelectTime() throws SQLException {

        Connection connection = ConnectionUtil.getHikariDataSource().getConnection();
        var sql = """
                select * from sample_time
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            System.out.println("id : " + resultSet.getInt(1));
            System.out.println("date : " + resultSet.getDate("sample_date"));
            System.out.println("time : " + resultSet.getTime("sample_time"));
            System.out.println("timestamp : " + resultSet.getTimestamp("sample_timestamp"));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
