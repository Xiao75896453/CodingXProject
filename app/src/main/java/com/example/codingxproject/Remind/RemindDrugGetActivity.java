package com.example.codingxproject.Remind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.codingxproject.R;

import java.util.Calendar;

public class RemindDrugGetActivity extends AppCompatActivity {

    public static final Calendar currentTime= Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_drug_get);

        int currentHour=currentTime.get(Calendar.HOUR_OF_DAY);
        int currentMinute=currentTime.get(Calendar.MINUTE);



    }
}
