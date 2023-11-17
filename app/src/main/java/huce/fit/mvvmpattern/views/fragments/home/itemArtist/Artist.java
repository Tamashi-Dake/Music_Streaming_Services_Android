package huce.fit.mvvmpattern.views.fragments.home.itemArtist;

public class Artist {

    //    set ảnh từ drawable nên cần resourceId
    private int resourceId;
    private String title;

    public Artist(int resourceId, String title) {
        this.resourceId = resourceId;
        this.title = title;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
