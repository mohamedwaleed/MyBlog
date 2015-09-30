package my.blog.authentication.controllers;

import my.blog.authentication.Services.UserService;
import my.blog.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mohamed on 24/09/15.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/getLoggedInUser")
    public User getLoggednUser(){
        return userService.getLoggedInUser();
    }

}
