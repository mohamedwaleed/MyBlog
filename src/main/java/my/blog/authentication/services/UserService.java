package my.blog.authentication.services;

import my.blog.authentication.Dtos.UserDTO;
import my.blog.authentication.commands.UserCommand;
import my.blog.authentication.repositories.RoleRepository;
import my.blog.authentication.repositories.UserRepository;
import my.blog.common.services.MappingService;
import my.blog.entities.Role;
import my.blog.entities.User;
import my.blog.utilities.CredentialsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 24/09/15.
 */
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MappingService mappingService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CredentialsUtils credentialsUtils;

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

    public UserDTO createNewUser(UserCommand userCommand){
        User user = mappingService.map(userCommand, User.class);
        if(user != null){
            List<User> users = userRepository.findByUsername(user.getUsername());
            if(users == null || users.size() > 0)
                throw new IllegalStateException();
        }else {
            throw new IllegalStateException();
        }

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setAuthorities(roles);
        user.setPassword(credentialsUtils.encodePassword(user.getPassword()));
        User savedUser = userRepository.save(user);
        return mappingService.map(savedUser,UserDTO.class);
    }
    public UserDTO getUserByUsername(String username){
        List<User> users = userRepository.findByUsername(username);
        User user = users.get(0);
        return mappingService.map(user,UserDTO.class);
    }

    public Boolean isUserExist(String username) {
        List<User> users = userRepository.findByUsername(username);
        if(users == null || users.size() == 0){
            return false;
        }
        return true;
    }
}
