package my.blog.authentication.Services;

import my.blog.authentication.repository.UserRepository;
import my.blog.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mohamed on 24/09/15.
 */
@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User loadUserByUsername(String username){
        List<User> users = userRepository.findByUsername(username);
        if(users == null || users.size() == 0 ){
            return null;
        }
        return users.get(0);
    }
    public User getLoggedInUser(){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loggedInUser;
    }
}
