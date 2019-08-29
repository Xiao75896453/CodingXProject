package com.example.codingxproject.LoginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codingxproject.R;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final TextView welcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        final TextView etUserName = (TextView) findViewById(R.id.etUserName);
//        final EditText etAge=(EditText) findViewById(R.id.etAge);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String userName = intent.getStringExtra("username");

        String message = name + "welcome to your area";
        welcomeMsg.setText(message); //Debug
        etUserName.setText(userName);

    }
}
