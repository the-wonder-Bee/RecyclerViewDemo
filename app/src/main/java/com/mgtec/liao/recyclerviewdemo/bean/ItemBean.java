package com.mgtec.liao.recyclerviewdemo.bean;

public class ItemBean {

    private int imgId;
    private String msg;
    private boolean highLight = false;

    public boolean isHighLight() {
        return highLight;
    }

    public void setHighLight(boolean highLight) {
        this.highLight = highLight;
    }

    public ItemBean(int imgId, String msg) {
        this.imgId = imgId;
        this.msg = msg;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
