package com.example.multiexpenserv1.Model;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSettings {

    private static final String PREFERENCE_NAME = "PREFERENCE";
    private final SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public UserSettings(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }
    public void saveUserSettings(String mIncome, String cBalance, String eIncome) {
        editor.putString("FirstTimeInstalled", "Yes");
        editor.putString("Monthly_Income", mIncome);
        editor.putString("Current_Balance", cBalance);
        editor.putString("Extra_Income", eIncome);
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
    public String getCurrentBalance() {
        return sharedPreferences.getString("Current_Balance", "");
    }
    public void updateBalance(int updatedBalance) {
        editor.putString("Current_Balance", String.valueOf(updatedBalance));
        editor.apply();
    }

}
