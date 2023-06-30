package com.example.appepicnovels.interfaces;

import com.example.appepicnovels.models.Story;

import java.util.ArrayList;

public interface StoriesCallback {
    void onStoriesLoad(ArrayList<Story> stories);

}
