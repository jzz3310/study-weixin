package com.jzz.entity;

/**
 * @author:jzz
 * @date:2020/6/27
 */
public class Music {
    private String title;
    private String description;
    private String musicURL;
    private String hQMusicUrl;
    private String thumbMediald;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }

    public String getThumbMediald() {
        return thumbMediald;
    }

    public void setThumbMediald(String thumbMediald) {
        this.thumbMediald = thumbMediald;
    }

    public Music(String title, String description, String musicURL, String hQMusicUrl, String thumbMediald) {
        this.title = title;
        this.description = description;
        this.musicURL = musicURL;
        this.hQMusicUrl = hQMusicUrl;
        this.thumbMediald = thumbMediald;
    }
}
