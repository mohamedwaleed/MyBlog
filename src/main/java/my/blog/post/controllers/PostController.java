package my.blog.post.controllers;

import my.blog.comment.commands.CommentCommand;
import my.blog.comment.services.CommentService;
import my.blog.common.commands.PagedCommonResponse;
import my.blog.entities.Comment;
import my.blog.post.commands.PostCommand;
import my.blog.post.dtos.PostDTO;
import my.blog.post.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mohamed on 2/29/16.
 */
@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;


    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody ResponseEntity<Boolean> createPost(@RequestBody PostCommand postCommand){
        HttpStatus status = HttpStatus.OK;
        try {
            postService.createPost(postCommand);
        }catch (IllegalStateException e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Boolean>(status);
    }
    @RequestMapping(value = "/{postId}",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<PostDTO> getPost(@PathVariable(value = "postId") Integer postId){
        PostDTO post = postService.getPost(postId);
        HttpStatus status = HttpStatus.OK;
        if(post == null){
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(post,status);
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<PostDTO>> listAllPosts(){
        List<PostDTO> posts = postService.listAllPosts();
        HttpStatus status = HttpStatus.OK;
        if(posts == null){
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(posts,status);
    }
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody ResponseEntity<PagedCommonResponse<PostDTO>> getPagablePosts(@RequestParam(value = "pageNum") Integer pageNum,@RequestParam(value = "pageSize") Integer pageSize){
        PagedCommonResponse<PostDTO> posts = postService.getPagablePosts(pageNum, pageSize);
        HttpStatus status = HttpStatus.OK;
        if(posts == null){
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(posts,status);
    }
    @RequestMapping(value = "/{postId}",method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody ResponseEntity<Boolean> deletePost(@PathVariable(value = "postId") Integer postId){
        Boolean deleteted = postService.deletePost(postId);
        HttpStatus status = HttpStatus.OK;
        if(deleteted == false){
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(deleteted,status);
    }
    @RequestMapping(value = "/{postId}",method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody ResponseEntity<Boolean> updatePost(@PathVariable(value = "postId") Integer postId,@RequestBody PostCommand postCommand){
        Boolean updated = postService.updatePost(postId, postCommand);
        HttpStatus status = HttpStatus.OK;
        if(updated == false){
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(updated,status);
    }
    @RequestMapping(value = "/list3",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<PostDTO>> listMostRecent3Posts(){
        List<PostDTO> posts = postService.getMost3RecentPosts();
        HttpStatus status = HttpStatus.OK;
        if(posts == null){
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(posts,status);
    }


    @RequestMapping(value = "/{postId}/comment",method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody ResponseEntity<List<Comment>> addComment(@PathVariable(value = "postId") Integer postId ,@RequestBody CommentCommand commentCommand){
        List<Comment> comments = commentService.addComment(commentCommand, postId);
        HttpStatus status = HttpStatus.OK;
        if(comments == null || comments.size() == 0){
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(comments,status);
    }

    @RequestMapping(value = "/{postId}/comment/{commentId}",method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody ResponseEntity<Boolean> deleteComment(@PathVariable(value = "postId") Integer postId ,@PathVariable(value = "commentId") Integer commentId){
        Boolean deleted = commentService.deleteComment(commentId);
        HttpStatus status = HttpStatus.OK;
        if(!deleted){
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(deleted,status);
    }
}
