package arief.mahendra.database;

import arief.mahendra.database.entity.Comment;
import arief.mahendra.database.repository.CommentRepository;
import arief.mahendra.database.repository.CommentRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class RepositoryTest {

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentRepositoryImpl();
    }

    @Test
    void testInsert() throws SQLException {
        Comment comment = new Comment("arief@mail.com", "hi arief");
        commentRepository.insert(comment);

        Assertions.assertNotNull(comment.getId());
    }

    @Test
    void testFindById() {
        Comment comment = commentRepository.findById(3004);

        Assertions.assertNotNull(comment);

        System.out.println(comment.getId());
        System.out.println(comment.getEmail());
        System.out.println(comment.getComment());

        Comment commentNotFound = commentRepository.findById(1000000);
        Assertions.assertNull(commentNotFound);
    }

    @Test
    void testFindAll() {
        List<Comment> comments = commentRepository.findAll();

        Assertions.assertNotNull(comments);
        System.out.println(comments.size());
    }

    @Test
    void testFindAllByEmail() {
        List<Comment> allByEmail = commentRepository.findAllByEmail("arief@mail.com");

        Assertions.assertNotNull(allByEmail);
        System.out.println(allByEmail.size());
    }
}
