package com.example.appepicnovels.models;

import java.io.Serializable;

public class Story implements Serializable {
    private String id;
    private String name;
    private String chap;
    private String linkImg;

    private String description;

    private String status;

    public Story(String id, String name, String chap, String linkImg, String description, String status) {
        this.id = id;
        this.name= name;
        this.chap = chap;
        this.linkImg = linkImg;
        this.description= description;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }

    public String getStatus() {
        return this.status;
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
    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.name = status;
    }
}
