package com.example.multiexpenserv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.multiexpenserv1.View.MainActivityView;

public class SplashScreenv1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screenv1);

        // Sử dụng Handler để thay thế Timer
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenv1.this, MainActivityView.class));
            finish(); // Đóng màn hình splash
        }, 1500);
    }
}