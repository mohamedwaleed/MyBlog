package my.blog.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mohamed on 24/09/15.
 */
@Entity
@Table(name = "comment")
@Data
public class Reply {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private Date date;
    @Column
    private String content;
    @Column
    private String author;
}
