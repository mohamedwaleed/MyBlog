package my.blog.post.repositories;

import my.blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mohamed on 2/29/16.
 */
public interface PostRepository extends JpaRepository<Post,Integer>,CustomPostRepository{

}
