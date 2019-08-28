package com.example.codingxproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launch_drug_time(View view) {
        Log.d("btm", "to DrugTime Button clicked!");
        Intent intent = new Intent(this, drug_time_page.class);
        startActivity(intent);
    }

    public void launch_timer(View view) {
        Log.d("btm", "to Timer Button clicked!");
        Intent intent = new Intent(this, set_time.class);
        startActivity(intent);
    }
}
