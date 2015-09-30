package my.blog.authentication.repository;

import my.blog.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mohamed on 24/09/15.
 */
public interface UserRepository extends CrudRepository<User,Long> {
     List<User> findByUsername(String username);
}
