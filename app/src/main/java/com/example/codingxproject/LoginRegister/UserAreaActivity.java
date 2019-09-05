package com.example.codingxproject.LoginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codingxproject.MainFunctionsWithBottomBar.HomePageActivity;
import com.example.codingxproject.R;
import com.example.codingxproject.Splash;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final TextView welcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
//        final EditText etAge=(EditText) findViewById(R.id.etAge);
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
//        String userName = intent.getStringExtra("username");
        String message = name + "\nWelcome!!";
        welcomeMsg.setText(message); //Debug
        Animation myanim = AnimationUtils.loadAnimation(UserAreaActivity.this, R.anim.fade_in);
        welcomeMsg.startAnimation(myanim);
        final Intent aftersplash = new Intent(UserAreaActivity.this, HomePageActivity.class);
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
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
