package com.example.multiexpenserv1.Model;

import android.content.Context;
import android.content.SharedPreferences;

public class Login {
    private SharedPreferences sharedPreferences;

    public Login(Context context) {
        sharedPreferences = context.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
    }

    // Lưu thông tin người dùng vào SharedPreferences
    public void saveUserInfo(String firstName, String lastName, String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("First_Name", firstName);
        editor.putString("Last_Name", lastName);
        editor.putString("email", email);
        editor.putString("Coins", "0");
        editor.putInt("Day", -1);
        editor.apply();
    }

    // Kiểm tra lần cài đặt đầu tiên
    public boolean isFirstTimeInstalled() {
        String firstTime = sharedPreferences.getString("FirstTimeInstalled", "");
        return firstTime.equals("Yes");
    }
}
