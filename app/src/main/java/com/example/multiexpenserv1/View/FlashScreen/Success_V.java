package com.example.multiexpenserv1.View.FlashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import com.example.multiexpenserv1.R;
import com.example.multiexpenserv1.View.Home.Home_V;

public class Success_V extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.success);
        mediaPlayer.start();

        // Chuyển sang màn hình Home_V sau 3 giây
        new Handler().postDelayed(() -> {
            startActivity(new Intent(Success_V.this, Home_V.class));
            finish();
        }, 3000);
    }
}