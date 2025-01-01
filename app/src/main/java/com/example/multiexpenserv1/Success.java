package com.example.multiexpenserv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import com.example.multiexpenserv1.View.Home;

public class Success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.success);
        mediaPlayer.start();

        // Chuyển sang màn hình Home sau 3 giây
        new Handler().postDelayed(() -> {
            startActivity(new Intent(Success.this, Home.class));
            finish();
        }, 3000);
    }
}