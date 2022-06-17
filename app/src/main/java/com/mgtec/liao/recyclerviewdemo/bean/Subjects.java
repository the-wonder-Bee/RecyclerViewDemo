package com.mgtec.liao.recyclerviewdemo.bean;

public class Subjects {

    private String episodesInfo;
    private String rate;
    private String coverX;
    private String coverY;
    private String title;
    private String url;
    private boolean playable;
    private String cover;
    private String id;
    private boolean isNew;
    private boolean highLight;

    public boolean isPlayable() {
        return playable;
    }

    public boolean isHighLight() {
        return highLight;
    }

    public void setHighLight(boolean highLight) {
        this.highLight = highLight;
    }

    public String getEpisodesInfo() {
        return episodesInfo;
    }

    public void setEpisodesInfo(String episodesInfo) {
        this.episodesInfo = episodesInfo;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCoverX() {
        return coverX;
    }

    public void setCoverX(String coverX) {
        this.coverX = coverX;
    }

    public String getCoverY() {
        return coverY;
    }

    public void setCoverY(String coverY) {
        this.coverY = coverY;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
