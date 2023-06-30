package com.example.appepicnovels.models;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;

import java.sql.Time;
import java.time.LocalDateTime;

public class Comment {
    private String id;
    private String userId;
    private String content;
    private String storyId;
    private Timestamp timestamp;

    public Comment(String id, String userId, String content, String storyId) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.storyId = storyId;
        this.timestamp = Timestamp.now();
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
