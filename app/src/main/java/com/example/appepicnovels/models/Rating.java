package com.example.appepicnovels.models;

public class Rating {
    private String userId;
    private String storyId;
    private float star;

    public Rating(String userId, String storyId, float star) {
        this.userId = userId;
        this.storyId = storyId;
        this.star = star;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }
}
