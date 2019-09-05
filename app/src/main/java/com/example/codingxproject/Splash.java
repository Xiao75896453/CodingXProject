package com.example.codingxproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.codingxproject.LoginRegister.LoginActivity;
import com.example.codingxproject.MainFunctionsWithBottomBar.HomePageActivity;

public class Splash extends AppCompatActivity {

    private Button logo;
    private TextView appDescription;
    private LinearLayout appEnter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.appLogo);
        appDescription=findViewById(R.id.appDescription);
//        appEnter=findViewById(R.id.appEnter);
        Animation myanim = AnimationUtils.loadAnimation(Splash.this, R.anim.fade_in);
        appDescription.startAnimation(myanim);
        logo.startAnimation(myanim);
        final Intent aftersplash = new Intent(Splash.this, LoginActivity.class);
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(aftersplash);
                    finish();
                }
            }
        };
        timer.start();
    }
}
