package pl.camp.it.forum.database.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.forum.database.IPostDAO;
import pl.camp.it.forum.exceptions.NotEnoughtPostException;
import pl.camp.it.forum.model.Post;
import pl.camp.it.forum.sequence.IPostIdSequence;

import java.time.LocalDateTime;
import java.util.*;


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
        Optional<Post> postBox = this.getPostById(post.getId());
        if (postBox.isPresent() && postBox.get().getId() == post.getId()) {
            postBox.get().setContent(post.getContent());
        }
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
    public void removePost(int id) {
        Optional<Post> postBox = getPostById(id);
        List<Post> postList = new ArrayList<>();
        if (postBox.isPresent() && getPostByTitleId(postBox.get().getTitleId()).size() >= 2) {
            this.posts.remove(postBox.get());
        }
    }

    @Override
    public void removePostByTitleId(int titleId) {
        List<Post> postList = getPostByTitleId(titleId);
        Iterator<Post> iterator = this.posts.iterator();
        while (iterator.hasNext()) {
            Post postFromDB = iterator.next();
            if (postFromDB.getTitleId() == titleId) {
                iterator.remove();
            }
        }
    }

    @Override
    public Optional<Post> getPostById(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return Optional.of(post);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Post> getLastPostByTitleId(int titleId) {
        return Optional.empty();
    }
}
