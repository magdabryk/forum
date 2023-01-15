package pl.camp.it.forum.database;

import pl.camp.it.forum.model.Post;

import java.util.List;

public interface IPostDAO {
    List<Post> getPost();
    void addPost(Post post, int titleId, int userId);
    void editPost(Post post);
    List<Post> getPostByTitle(String title);
    List<Post> getPostByTitleId(int id);
    void addFirstPost(Post post, int userId, int titleId);

}
