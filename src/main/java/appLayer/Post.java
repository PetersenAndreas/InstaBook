package appLayer;

import java.io.InputStream;

public class Post {

    private String title;
    private InputStream file;

    public Post(String title, InputStream file) {
        this.title = title;
        this.file = file;
    }

    public Post() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }
}