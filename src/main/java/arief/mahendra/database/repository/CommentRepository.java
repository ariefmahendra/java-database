package arief.mahendra.database.repository;

import arief.mahendra.database.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentRepository {
    void insert(Comment comment) throws SQLException;

    Comment findById(Integer id);

    List<Comment> findAll();

    List<Comment> findAllByEmail(String email);
}
