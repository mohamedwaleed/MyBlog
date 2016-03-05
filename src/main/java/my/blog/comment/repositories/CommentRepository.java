package my.blog.comment.repositories;

import my.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mohamed on 3/5/16.
 */
public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
