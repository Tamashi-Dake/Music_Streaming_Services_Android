package huce.fit.mvvmpattern.views.fragments.home.itemArtist;

public class Artist {

    //    set ảnh từ drawable nên cần resourceId
    private String imageUrl;
    private String title;

    public Artist(String imageUrl, String title) {
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
