package appLayer;

import java.io.InputStream;

public class Post {

    private String title;
    private String picturePath;
    private String username;

    public Post(String title, String picturePath) {
        this.title = title;
        this.picturePath = picturePath;
    }

    public Post(String title, String picturePath, String username) {
        this.title = title;
        this.picturePath = picturePath;
        this.username = username;
    }

    public Post() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}