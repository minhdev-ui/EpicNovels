package com.example.appepicnovels.models;

import java.io.Serializable;

public class Chapter implements Serializable {
    private String id;
    private String name;
    private String content;
    private String storyId;
    private Long views;

    public Chapter(String id, String name, String content, String storyId, Long views) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.storyId = storyId;
        this.views = views;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }
}
