package com.ecommerce.shoopingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout container;
    Button btnShowLogin,btnLogin;
    VideoView videoBackground;
    TextInputLayout textInputLayoutUserName, textInputLayoutPassword;
    Animation slide_up;
    Boolean flag =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.relative_container);
        videoBackground = findViewById(R.id.video_backgroud);
        btnShowLogin = findViewById(R.id.btnShowLogin);
        btnLogin = findViewById(R.id.btnLogin);
        textInputLayoutUserName = findViewById(R.id.inputLayoutUserName);
        textInputLayoutPassword = findViewById(R.id.inputLayoutPassword);
        btnShowLogin.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        slide_up = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
        init();
        videoBackground.setMediaController(null);
        videoBackground.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.shoop);
        videoBackground.start();

        videoBackground.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
    }

    public void init(){
        textInputLayoutUserName.setVisibility(View.INVISIBLE);
        textInputLayoutPassword.setVisibility(View.INVISIBLE);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)container.getLayoutParams();
        layoutParams.height=150;
        container.setLayoutParams(layoutParams);
        container.startAnimation(slide_up);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnShowLogin:
                if(flag == true){
                    textInputLayoutUserName.setVisibility(View.VISIBLE);
                    textInputLayoutPassword.setVisibility(View.VISIBLE);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)container.getLayoutParams();
                    layoutParams.height = 1300;
                    container.setLayoutParams(layoutParams);
                    container.startAnimation(slide_up);
                    flag = false;
                }else{
                    flag = true;
                    init();
                }
                break;
            case R.id.btnLogin:
                startActivity(new Intent(MainActivity.this, LobbyActivity.class));;
                break;
        }
    }
}