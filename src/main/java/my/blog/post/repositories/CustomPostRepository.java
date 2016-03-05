package my.blog.post.repositories;

import my.blog.entities.Post;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by mohamed on 3/2/16.
 */
public interface CustomPostRepository {
    List<Post> findTop3Posts();
}
