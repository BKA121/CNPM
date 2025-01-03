package com.example.multiexpenserv1.View.FlashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.multiexpenserv1.R;
import com.example.multiexpenserv1.View.Login.Login_V;

public class SplashScreenv1_V extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screenv1);

        // Sử dụng Handler để thay thế Timer
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenv1_V.this, Login_V.class));
            finish(); // Đóng màn hình splash
        }, 1500);
    }
}