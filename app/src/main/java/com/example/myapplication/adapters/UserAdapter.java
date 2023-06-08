package com.example.myapplication.adapters;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.models.User;

public class UserAdapter {
    private static LoginActivity loginActivity; // Thêm biến static LoginActivity

    // Setter để thiết lập LoginActivity
    public static void setLoginActivity(LoginActivity activity) {
        loginActivity = activity;
    }

    public static void login(String username, String password) {
        // Kiểm tra đăng nhập
        if (username.equals("admin") && password.equals("123")) {
            User user = new User(username, password);
            // Gọi phương thức thành công đăng nhập từ View
            if (loginActivity != null) {
                loginActivity.onLoginSuccess(user);
            }
        } else {
            // Gọi phương thức thất bại đăng nhập từ View
            if (loginActivity != null) {
                loginActivity.onLoginFailure();
            }
        }
    }
}

