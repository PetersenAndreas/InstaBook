package appLayer;


public class Post {

    private String title;
    private String picturePath;
    private int userid;

    public Post(String title, String picturePath) {
        this.title = title;
        this.picturePath = picturePath;
    }

    public Post(String title, String picturePath, int userid) {
        this.title = title;
        this.picturePath = picturePath;
        this.userid = userid;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}