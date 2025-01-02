package com.example.multiexpenserv1.Model;

import android.content.Context;
import android.content.SharedPreferences;

public class Login {
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Login(Context context) {
        sharedPreferences = context.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    // Lưu thông tin người dùng vào SharedPreferences
    public void saveUserInfo(String firstName, String lastName, String email) {
        editor.putString("First_Name", firstName);
        editor.putString("Last_Name", lastName);
        editor.putString("email", email);
        editor.putString("Coins", "0");
        editor.putInt("Day", -1);
        editor.apply();
    }
    public String getFirstName() {
        return sharedPreferences.getString("First_Name", "");
    }
    public String getLastName() {
        return sharedPreferences.getString("Last_Name", "");
    }
    public String getEmail() {
        return sharedPreferences.getString("email", "");
    }
    // Kiểm tra lần cài đặt đầu tiên
    public boolean isFirstTimeInstalled() {
        String firstTime = sharedPreferences.getString("FirstTimeInstalled", "");
        return firstTime.equals("Yes");
    }
}
