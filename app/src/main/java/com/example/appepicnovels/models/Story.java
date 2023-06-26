package com.example.appepicnovels.models;

public class Story {
    private String name;
    private String chap;
    private String linkImg;

    public Story(String name, String chap, String linkImg) {
        this.name= name;
        this.chap = chap;
        this.linkImg = linkImg;
    }

    public String getName() {
        return this.name;
    }

    public String getChap() {
        return this.chap;
    }

    public String getLinkImg() {
        return this.linkImg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChap(String chap) {
        this.chap = chap;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }
}
