package huce.fit.mvvmpattern.views.fragments.library.itemPlaylist;

import java.io.Serializable;

public class Playlist implements Serializable {

//    set ảnh từ drawable nên cần resourceId
    private String imageUrl;
private String title;

    public Playlist(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
