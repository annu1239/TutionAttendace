package com.uuu.tutionattendace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mp= MediaPlayer.create(this,R.raw.r_android);//for song
        mp.start();
        //if i want ki song play ho only for 5 sec or some amount of time use postDelayed() which exists in handler class

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mp.isPlaying())
                {
                    mp.stop();}
                Intent intent=new Intent(getApplicationContext(),StudentProfile.class);
                startActivity(intent);
                finish();//taaki back button press krne pe dubaara splash screen pe na jaaye

            }
        }, 5000);

//two types of intents explicit->defined by user
    }

    }

