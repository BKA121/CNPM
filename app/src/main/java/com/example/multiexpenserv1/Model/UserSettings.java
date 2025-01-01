package com.example.multiexpenserv1.Model;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSettings {
    private static final String PREFERENCE_NAME = "PREFERENCE";
    private SharedPreferences sharedPreferences;

    public UserSettings(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }
    public void saveUserSettings(String mIncome, String cBalance, String eIncome) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("FirstTimeInstalled", "Yes");
        editor.putString("Monthly_Income", mIncome);
        editor.putString("Current_Balance", cBalance);
        editor.putString("Extra_Income", eIncome);
        editor.apply();
    }

    public String getMonthlyIncome() {
        return sharedPreferences.getString("Monthly_Income", "");
    }

    public String getCurrentBalance() {
        return sharedPreferences.getString("Current_Balance", "");
    }

    public String getExtraIncome() {
        return sharedPreferences.getString("Extra_Income", "");
    }

}
