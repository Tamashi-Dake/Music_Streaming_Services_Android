package huce.fit.mvvmpattern.model;

import java.io.Serializable;

public class Song implements Serializable {
    //    set ảnh từ drawable nên cần resourceId
    private String id;
    private String image;
    private String trackName;
    private String artistName;
    private String linkSong;

    public Song() {
    }

    public Song(String id, String image, String trackName, String artistName, String linkSong) {
        this.id = id;
        this.image = image;
        this.trackName = trackName;
        this.artistName = artistName;
        this.linkSong = linkSong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }
}
