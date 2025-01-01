package com.example.multiexpenserv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class Congratulations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);

        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.goalachieved);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Congratulations.this, My_Goals.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }, 3000);
    }
}