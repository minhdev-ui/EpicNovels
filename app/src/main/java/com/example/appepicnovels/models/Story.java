package com.example.appepicnovels.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Story implements Serializable {
    private String id;
    private String name;
    private String chap;
    private String linkImg;
    private List<Rating> ratingStar;

    private String description;

    private String status;
    private Double totalRate;

    public Story(String id, String name, String chap, String linkImg, String description, String status, List<Rating> ratingStar, Double totalRate) {
        this.id = id;
        this.name= name;
        this.chap = chap;
        this.linkImg = linkImg;
        this.description= description;
        this.status = status;
        this.ratingStar = ratingStar;
        this.totalRate = totalRate;
    }

    public Double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(Double totalRate) {
        this.totalRate = totalRate;
    }

    public List<Rating> getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(List<Rating> ratingStar) {
        this.ratingStar = ratingStar;
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
