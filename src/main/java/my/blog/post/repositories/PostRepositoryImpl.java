package my.blog.post.repositories;

import com.mysema.query.jpa.impl.JPAQuery;
import my.blog.entities.Post;
import my.blog.entities.QPost;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by mohamed on 3/2/16.
 */
@Repository
public class PostRepositoryImpl implements CustomPostRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Post> findTop3Posts() {
        JPAQuery query = new JPAQuery(entityManager);
        QPost qPost = QPost.post;

        List<Post> posts = query.from(qPost)
                .orderBy(qPost.date.desc())
                .limit(3)
                .list(qPost);

        return posts;
    }
}
