package huce.fit.mvvmpattern.model;

import java.io.Serializable;

public class SongApi implements Serializable {
    private String id;
    private String name;
    private int playedTime;
    private String linkSong;
    private String linkPicture;

    public SongApi() {
    }

    public SongApi(String id, String name, int playedTime, String linkSong, String linkPicture) {
        this.id = id;
        this.name = name;
        this.playedTime = playedTime;
        this.linkSong = linkSong;
        this.linkPicture = linkPicture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayedTime() {
        return playedTime;
    }

    public void setPlayedTime(int playedTime) {
        this.playedTime = playedTime;
    }

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }

    public String getLinkPicture() {
        return linkPicture;
    }

    public void setLinkPicture(String linkPicture) {
        this.linkPicture = linkPicture;
    }
}
