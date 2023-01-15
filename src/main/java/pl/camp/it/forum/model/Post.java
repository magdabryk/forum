package pl.camp.it.forum.model;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private LocalDateTime date;
    private int userId;
    private String content;
    private int titleId;


    public Post(int id, LocalDateTime date, int userId,  String content, int titleId) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.content = content;
        this.titleId = titleId;
    }

    public Post(String content){
        this.content = content;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
