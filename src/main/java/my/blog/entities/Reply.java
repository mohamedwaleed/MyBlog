package my.blog.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mohamed on 24/09/15.
 */
@Entity
@Table(name = "reply")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
