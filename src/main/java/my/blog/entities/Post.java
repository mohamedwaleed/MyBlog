package my.blog.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by mohamed on 07/09/15.
 */
@Entity
@Table(name = "post")
@Data
public class Post {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String title;
    @Column
    private Date date;
    @Column
    private String content;
    @Column
    private String author;
    @Column
    private String image;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post_id")
    private List<Comment> commentList;
}
