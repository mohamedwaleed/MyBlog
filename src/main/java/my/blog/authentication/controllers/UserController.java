package my.blog.authentication.controllers;

import my.blog.authentication.Dtos.UserDTO;
import my.blog.authentication.commands.UserCommand;
import my.blog.authentication.services.UserService;
import my.blog.authentication.commands.AdminPanelCommand;
import my.blog.entities.User;
import my.blog.utilities.CredentialsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mohamed on 24/09/15.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CredentialsUtils credentialsUtils;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/getLoggedInUser")
    public User getLoggednUser(){
        return userService.getLoggedInUser();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/confirmAdminPanelPassword" , method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Boolean> confirmAdminPanelPassword(@RequestBody AdminPanelCommand adminPanelCommand){
        String username = adminPanelCommand.getUsername();
        String password = credentialsUtils.encodePassword(adminPanelCommand.getPassword());
        User user = userService.loadUserByUsername(username);
        if(user == null){
            return new ResponseEntity<Boolean>(false,HttpStatus.FORBIDDEN);
        }
        if(!user.getPassword().equals(password)){
            return new ResponseEntity<Boolean>(false,HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserDTO createNewUser(@RequestBody UserCommand userCommand){
        return userService.createNewUser(userCommand);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET)
    public Boolean isUserExist(@RequestParam(value = "username") String username){
        return userService.isUserExist(username);
    }
}
