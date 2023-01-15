package pl.camp.it.forum.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.forum.database.IPostDAO;
import pl.camp.it.forum.model.Post;
import pl.camp.it.forum.services.IPostService;


import java.util.List;


@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    IPostDAO postDAO;

    @Override
    public List<Post> getPost() {
      return this.postDAO.getPost();
    }

    @Override
    public List<Post> getPostByTitleId(int titleId) {
        return this.postDAO.getPostByTitleId(titleId);
    }

    @Override
    public void addPost(Post post, int titleId, int userId) {

        this.postDAO.addPost(post, titleId, userId);
    }
}
