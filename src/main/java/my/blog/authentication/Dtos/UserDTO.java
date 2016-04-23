package my.blog.authentication.Dtos;

import lombok.Data;

/**
 * Created by mohamed on 4/22/16.
 */
@Data
public class UserDTO {
    private Long id;

    private String username;

    private String password;

    private String email;

    private String gender;
}
