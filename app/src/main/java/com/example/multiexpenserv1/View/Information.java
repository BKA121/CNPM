package com.example.multiexpenserv1.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.multiexpenserv1.Controller.BalanceController;
import com.example.multiexpenserv1.R;

public class Information extends AppCompatActivity {

    private TextView currentBalance;
    private TextView fullname;
    private String fname;
    private String lname;
    private TextView Notification_Settings;
    private TextView logout;
    private BalanceController balanceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        currentBalance = findViewById(R.id.tv_current_balance);
        fullname = findViewById(R.id.tv_user_name);
        Notification_Settings = findViewById(R.id.tv_notifications_label);
        logout = findViewById(R.id.logout);
        balanceController = new BalanceController(this);
        currentBalance.setText(balanceController.getCurrentBalance() + " VND");

        fname = balanceController.getFirstName();
        lname = balanceController.getLastName();
        fullname.setText(fname + " " + lname);

        //Information Activity
        logout.setOnClickListener(v -> new AlertDialog.Builder(Information.this)
                .setTitle("Xác nhận đăng xuất")
                .setMessage("Bạn có chắc chắn muốn đăng xuất không?")
                .setPositiveButton("Đồng ý", (dialog, which) -> {
                    // Xóa tất cả thông tin người dùng trong SharedPreferences
                    SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear(); // Xóa thông tin người dùng
                    editor.apply();

                    // Xóa thông tin "FirstTimeInstalled" để buộc quay lại màn hình đăng nhập
                    SharedPreferences.Editor prefEditor = preferences.edit();
                    prefEditor.remove("FirstTimeInstalled");
                    prefEditor.apply();

                    // Chuyển đến màn hình đăng nhập (MainActivity) và xóa hết các Activity trước đó trong ngăn xếp
                    Intent intent = new Intent(Information.this, MainActivityView.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa hết các activity cũ
                    startActivity(intent);
                    finish(); // Đảm bảo không quay lại màn hình thông tin
                })
                .setNegativeButton("Hủy", null)
                .show());
        Notification_Settings.setOnClickListener(v -> startActivity(new Intent(Information.this, NotificationSettingsActivity.class)));
    }
}

