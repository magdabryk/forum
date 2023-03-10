package pl.camp.it.forum.services;

import pl.camp.it.forum.model.Post;
import java.util.List;
import java.util.Optional;

public interface IPostService {
    List<Post> getPost();
    List<Post> getPostByTitleId(int titleID);
    void addPost(Post post, int titleId, int userId);
    Optional<Post> getPostById(int id);
    void editPost(Post post, int id);
    void removePost(int id);
    Optional<Post> getLastPostByTitleId(int titleId);
}
