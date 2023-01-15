package pl.camp.it.forum.database.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.forum.database.IPostDAO;
import pl.camp.it.forum.model.Post;
import pl.camp.it.forum.sequence.IPostIdSequence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDB implements IPostDAO {

    @Autowired
    IPostIdSequence postIdSequence;
    private final List<Post> posts = new ArrayList<>();

    public PostDB(@Autowired IPostIdSequence postIdSequence) {
        this.postIdSequence = postIdSequence;
        this.posts.add(new Post(this.postIdSequence.getId(),
                LocalDateTime.now(),
                1,
                "plotki są codziennym zdarzeniem ludzkosci.",
                1));

        this.posts.add(new Post(this.postIdSequence.getId(),
                LocalDateTime.now(),
                1,
                "bla bla bla.",
                2));

        this.posts.add(new Post(this.postIdSequence.getId(),
                LocalDateTime.now(),
                2,
                "bla bla bla la bla.",
                2));
        this.posts.add(new Post(this.postIdSequence.getId(),
                LocalDateTime.now(),
                2,
                "bla bla bla la blafg.",
                3));
    }


    @Override
    public List<Post> getPost() {
        return posts;
    }

    @Override
    public void addPost(Post post, int titleId, int userId) {
        post.setId(this.postIdSequence.getId());
        post.setDate(LocalDateTime.now());
        post.setTitleId(titleId);
        post.setUserId(userId);
    this.posts.add(post);
    }

    @Override
    public void editPost(Post post) {

    }

    @Override
    public List<Post> getPostByTitle(String title) {
       /* List<Post> postsByTitle = new ArrayList<>();
        for(Post post : this.posts){
            if(Topic.getTitle().equals(title)){
                postsByTitle.add(post);
            }
        }*/
        return /*postsByTitle*/ null;
    }

    @Override
    public List<Post> getPostByTitleId(int titleId) {
        List<Post> postList = new ArrayList<>();

        for (Post post : this.posts) {
            if (post.getTitleId() == titleId) {
                postList.add(post);
            }
        }
        return postList;
    }

    @Override
    public void addFirstPost(Post post, int userId, int titleId) {
    post.setId(postIdSequence.getId());
    post.setUserId(userId);
    post.setDate(LocalDateTime.now());
    post.setTitleId(titleId);
    this.posts.add(post);
    }
}
