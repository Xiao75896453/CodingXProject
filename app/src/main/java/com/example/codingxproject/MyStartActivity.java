package com.example.codingxproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.codingxproject.Remind.RemindTakeMedActivity;

public class MyStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_start);

    }

    public void start()
    {
        //startActivity(new Intent(MyStartActivity.this, RemindTakeMedActivity.class));
    }
}
