package huce.fit.mvvmpattern.model;

import java.io.Serializable;

public class Song implements Serializable {
    //    set ảnh từ drawable nên cần resourceId
    private int resourceId;
    private String trackName;
    private String artistName;
    

    public Song(int resourceId, String trackName, String artistName) {
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
