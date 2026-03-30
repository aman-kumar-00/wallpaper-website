package model;


public class Wallpaper {

    private int id;
    private String title;
    private String image_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return image_url;
    }

    public void setImagePath(String image_url) {
        this.image_url = image_url;
    }
}
