package my.blog.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by mohamed on 31/08/15.
 */
@Entity
@Data
@Table(name = "role")
public class Role implements GrantedAuthority{
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    @Override
    public String getAuthority() {
        return name;
    }
}
