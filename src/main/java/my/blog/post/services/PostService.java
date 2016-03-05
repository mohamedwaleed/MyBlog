package my.blog.post.services;

import my.blog.common.PageViewModel;
import my.blog.common.commands.PagedCommonResponse;
import my.blog.common.services.MappingService;
import my.blog.entities.Post;
import my.blog.post.commands.PostCommand;
import my.blog.post.dtos.PostDTO;
import my.blog.post.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 2/29/16.
 */
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MappingService mappingService;

    public void createPost(PostCommand postCommand) throws IllegalStateException{
        Post newPost = mappingService.map(postCommand, Post.class);
        Post savedPost = postRepository.save(newPost);

        if(savedPost == null) {
            throw new IllegalStateException();
        }
    }

    public PostDTO getPost(Integer postId) {
        Post post = postRepository.findOne(postId);
        PostDTO postDTO = mappingService.map(post, PostDTO.class);
        return postDTO;
    }

    public List<PostDTO> listAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOList = new ArrayList<>();
        mappingService.map(posts, PostDTO.class, postDTOList);
        return postDTOList;
    }

    public PagedCommonResponse<PostDTO> getPagablePosts(Integer pageNum, Integer size) {
        Page<Post> postPage = postRepository.findAll(new PageRequest(pageNum, size));
        PageViewModel<PostDTO> pageViewModel = mappingService.mapPage(postPage,PostDTO.class);
        return mappingService.createPagedCommonResponse(pageViewModel, PostDTO.class);
    }

    public Boolean deletePost(Integer postId) {
        Post p = postRepository.findOne(postId);
        if (p == null){
            return false;
        }
        postRepository.delete(postId);
        return true;
    }

    public Boolean updatePost(Integer postId, PostCommand postCommand) {
        Post p = postRepository.findOne(postId);
        if (p == null){
            return false;
        }
        mapPostCommandToPost(postCommand, p);
        postRepository.save(p);
        return true;
    }
    public List<PostDTO> getMost3RecentPosts() {
        List<Post> posts = postRepository.findTop3Posts();
        List<PostDTO> postDTOs = new ArrayList<>();
        mappingService.map(posts,PostDTO.class,postDTOs);
        return postDTOs;
    }
    private void mapPostCommandToPost(PostCommand postCommand, Post p) {
        p.setTitle(postCommand.getTitle());
        p.setContent(postCommand.getContent());
        p.setAuthor(postCommand.getAuthor());
        p.setImage(postCommand.getImage());
        p.setDate(postCommand.getDate());
        p.setCommentList(postCommand.getCommentList());
    }
}
