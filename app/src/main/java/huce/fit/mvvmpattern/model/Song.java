package huce.fit.mvvmpattern.model;

import java.io.Serializable;

public class Song implements Serializable {
    //    set ảnh từ drawable nên cần resourceId
    private String image;
    private String trackName;
    private String artistName;
    

    public Song(String image, String trackName, String artistName) {
        this.image = image;
        this.trackName = trackName;
        this.artistName = artistName;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
