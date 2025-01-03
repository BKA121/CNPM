package com.example.multiexpenserv1.View.FlashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import com.example.multiexpenserv1.Controller.Home.My_Goals_C;
import com.example.multiexpenserv1.R;

public class Congratulations_V extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);

        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.goalachieved);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Congratulations_V.this, My_Goals_C.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }, 3000);
    }
}