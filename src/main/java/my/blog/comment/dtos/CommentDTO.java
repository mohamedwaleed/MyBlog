package my.blog.comment.dtos;

import java.util.Date;

/**
 * Created by mohamed on 3/5/16.
 */
public class CommentDTO {
    private Integer id;
    private Date date;
    private String content;
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
