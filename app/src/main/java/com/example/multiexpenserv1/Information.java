package com.example.multiexpenserv1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Information extends AppCompatActivity {

    private TextView currentBalance;
    private String Original_Balance;
    private TextView fullname;
    private  String fname;
    private  String lname;
    private TextView Notification_Settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        currentBalance = findViewById(R.id.tv_current_balance);
        fullname = findViewById(R.id.tv_user_name);
        Notification_Settings=findViewById(R.id.tv_notifications_label);

        SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        Original_Balance = sharedPreferences.getString("Current_Balance", "");
        currentBalance.setText(Original_Balance + " VND");

        fname = sharedPreferences.getString("First_Name","");
        lname = sharedPreferences.getString("Last_Name","");
        fullname.setText(fname + " " + lname);

        //Information Activity
        Notification_Settings.setOnClickListener(v -> startActivity(new Intent(Information.this, NotificationSettingsActivity.class)));
    }
}

