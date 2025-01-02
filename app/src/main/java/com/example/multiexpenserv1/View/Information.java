package com.example.multiexpenserv1.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multiexpenserv1.Controller.BalanceController;
import com.example.multiexpenserv1.NotificationSettingsActivity;
import com.example.multiexpenserv1.R;

public class Information extends AppCompatActivity {

    private TextView currentBalance;
    private TextView fullname;
    private  String fname;
    private  String lname;
    private TextView Notification_Settings;
    private BalanceController balanceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        currentBalance = findViewById(R.id.tv_current_balance);
        fullname = findViewById(R.id.tv_user_name);
        Notification_Settings = findViewById(R.id.tv_notifications_label);

        balanceController = new BalanceController(this);
        currentBalance.setText(balanceController.getCurrentBalance() + " VND");

        fname = balanceController.getFirstName();
        lname = balanceController.getLastName();
        fullname.setText(fname + " " + lname);

        //Information Activity
        Notification_Settings.setOnClickListener(v -> startActivity(new Intent(Information.this, NotificationSettingsActivity.class)));
    }
}

