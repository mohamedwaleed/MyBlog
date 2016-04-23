package my.blog.authentication.repositories;

import my.blog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mohamed on 4/22/16.
 */
public interface RoleRepository extends JpaRepository<Role,Integer>{

    Role findByName(String authority);
}
