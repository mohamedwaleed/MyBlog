package my.blog.authentication.commands;

import lombok.Data;

@Data
public class UserCommand {
    private String username;
    private String password;
    private String email;
    private String gender;
}

