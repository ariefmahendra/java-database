package arief.mahendra.database.repository;

import arief.mahendra.database.ConnectionUtil;
import arief.mahendra.database.entity.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository{


    @Override
    public void insert(Comment comment){
        try (Connection connection = ConnectionUtil.getHikariDataSource().getConnection()){
            String sql = """
                insert into comments(email, comment)
                values(?,?)
                """;
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, comment.getEmail());
                preparedStatement.setString(2, comment.getComment());
                preparedStatement.executeUpdate();

                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()){
                        comment.setId(resultSet.getInt(1));
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comment findById(Integer id) {
        try (Connection connection = ConnectionUtil.getHikariDataSource().getConnection()){
            String sql = "select * from comments where id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()){
                        return new Comment(
                                resultSet.getInt(1),
                                resultSet.getString("email"),
                                resultSet.getString("comment")
                        );
                    } else {
                        return null;
                    }
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> findAll() {
        try (Connection connection = ConnectionUtil.getHikariDataSource().getConnection()){

            String sql = "select * from comments";
            try(Statement statement = connection.createStatement()) {
                try(ResultSet resultSet = statement.executeQuery(sql)) {
                    List<Comment> comments = new ArrayList<>();

                    while (resultSet.next()){
                         comments.add(new Comment(
                            resultSet.getInt(1),
                            resultSet.getString("email"),
                            resultSet.getString("comment")
                         ));
                    }

                    return comments;
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> findAllByEmail(String email) {
        try (Connection connection = ConnectionUtil.getHikariDataSource().getConnection()){

            String sql = "select * from comments where email = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<Comment> comments = new ArrayList<>();

                    while (resultSet.next()){
                        comments.add(new Comment(
                           resultSet.getInt(1),
                           resultSet.getString("email"),
                           resultSet.getString("comment")
                        ));
                    }

                    return comments;
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
