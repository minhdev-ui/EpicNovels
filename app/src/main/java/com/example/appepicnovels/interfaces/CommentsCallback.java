package com.example.appepicnovels.interfaces;

import com.example.appepicnovels.models.Comment;

import java.util.ArrayList;

public interface CommentsCallback {
    void onCommentsLoaded(ArrayList<Comment> comments);
}
