package appLayer;

import java.io.InputStream;

public class Post {

    private String title;
    private InputStream file;
    private String base64Image;
    private byte[] image;

    public byte[] getImage() {
        return this.image;
    }


    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

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