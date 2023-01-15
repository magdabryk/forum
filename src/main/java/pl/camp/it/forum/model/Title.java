package pl.camp.it.forum.model;


import java.time.LocalDateTime;
import java.util.List;

public class Title {
    private int id;
    private String name;
    private List<Post> postList;
    private LocalDateTime date;
    private int userId;

    public Title(int id, String name, List<Post> postList, LocalDateTime date, int userId) {
        this.id = id;
        this.name = name;
        this.postList = postList;
        this.date = date;
        this.userId = userId;
    }

    public Title(String name) {
        this.name = name;
    }

    public Title() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
