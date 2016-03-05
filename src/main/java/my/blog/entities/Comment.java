package my.blog.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by mohamed on 07/09/15.
 */
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private Date date;
    @Column
    private String content;
    @Column
    private String author;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    private List<Reply> replyList;

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

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }
}
