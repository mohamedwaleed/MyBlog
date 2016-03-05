package my.blog.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by mohamed on 31/08/15.
 */
@Entity
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
