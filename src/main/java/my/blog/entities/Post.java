package my.blog.entities;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by mohamed on 07/09/15.
 */
@Entity
@Table(name = "post")
public class Post  {

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
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private List<Comment> commentList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
