package huce.fit.mvvmpattern.views.fragments.home.itemPopular;

public class Popular {
    //    set ảnh từ drawable nên cần resouceId
    private int resourceId;
    private String trackName;
    private String artistName;

    public Popular(int resourceId, String trackName, String artistName) {
        this.resourceId = resourceId;
        this.trackName = trackName;
        this.artistName = artistName;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
