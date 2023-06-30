package com.example.appepicnovels.interfaces;

import com.example.appepicnovels.models.User;

public interface UserCallback {
    void onUserLoaded(User user);
    void onFailure(String error);
}
