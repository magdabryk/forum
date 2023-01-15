package pl.camp.it.forum.database;

import pl.camp.it.forum.model.Post;

import java.util.List;
import java.util.Optional;

public interface IPostDAO {
    List<Post> getPost();
    void addPost(Post post, int titleId, int userId);
    void editPost(Post post);
    void removePost(int id);
    List<Post> getPostByTitle(String title);
    List<Post> getPostByTitleId(int titleId);
    void removePostByTitleId(int titleId);
    Optional<Post> getPostById(int id);

}
