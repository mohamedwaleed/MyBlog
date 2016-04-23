package my.blog.common.commands;

import lombok.Data;

/**
 * Created by mohamed on 4/23/16.
 */
@Data
public class EmailCommand {
    private String name;
    private String email;
    private String message;
}
