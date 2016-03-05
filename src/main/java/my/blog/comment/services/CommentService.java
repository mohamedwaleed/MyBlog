package my.blog.comment.services;

import my.blog.comment.commands.CommentCommand;
import my.blog.comment.dtos.CommentDTO;
import my.blog.comment.repositories.CommentRepository;
import my.blog.common.services.MappingService;
import my.blog.entities.Comment;
import my.blog.entities.Post;
import my.blog.post.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mohamed on 3/5/16.
 */
@Service
public class CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MappingService mappingService;

    public List<Comment> addComment(CommentCommand commentCommand,Integer postId){
        Post post = postRepository.findOne(postId);
        if (post == null){
            return null;
        }
        List<Comment>comments = post.getCommentList();
        Comment comment = mappingService.map(commentCommand, Comment.class);
        comments.add(comment);
        Post p = postRepository.save(post);
        return p.getCommentList();
    }
    public Boolean deleteComment(Integer commentId){
        Comment comment = commentRepository.findOne(commentId);
        if (comment == null){
            return false;
        }
        commentRepository.delete(commentId);
        return true;
    }

}
