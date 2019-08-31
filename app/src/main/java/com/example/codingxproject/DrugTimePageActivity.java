package com.example.codingxproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;

public class DrugTimePageActivity extends AppCompatActivity {

    private ScrollView mMorningSV= findViewById(R.id.MorningScrollView);;
    private ScrollView mBreakfastSV= findViewById(R.id.BreakfastScrollView);
    private ScrollView mLunchSV= findViewById(R.id.LunchScrollview);
    private ScrollView mDinnerSV= findViewById(R.id.DinnerScrollView);
    private ScrollView mNightSV= findViewById(R.id.NightScrollVIEW);
    private Button btmMorning= findViewById(R.id.morning_btm);
    private Button btmBreakfast= findViewById(R.id.breakfast_btm);
    private Button btmLunch= findViewById(R.id.lunch_btm);
    private Button btmDinner= findViewById(R.id.dinner_btm);
    private Button btmNight= findViewById(R.id.night_btm);
    private ConstraintLayout coverText = findViewById(R.id.cover);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_time_page);

        btmMorning.setBackgroundColor(Color.GREEN);
        btmBreakfast.setBackgroundColor(Color.GREEN);
        btmLunch.setBackgroundColor(Color.GREEN);
        btmDinner.setBackgroundColor(Color.GREEN);
        btmNight.setBackgroundColor(Color.GREEN);
    }

    public void showMorning(View view) {
        coverText.setVisibility(View.GONE);
        mMorningSV.setVisibility(View.VISIBLE);
        mBreakfastSV.setVisibility(View.GONE);
        mLunchSV.setVisibility(View.GONE);
        mDinnerSV.setVisibility(View.GONE);
        mNightSV.setVisibility(View.GONE);
        btmMorning.setBackgroundColor(Color.WHITE);
        btmBreakfast.setBackgroundColor(Color.GREEN);
        btmLunch.setBackgroundColor(Color.GREEN);
        btmDinner.setBackgroundColor(Color.GREEN);
        btmNight.setBackgroundColor(Color.GREEN);
    }

    public void showBreakfast(View view) {
        coverText.setVisibility(View.GONE);
        mMorningSV.setVisibility(View.GONE);
        mBreakfastSV.setVisibility(View.VISIBLE);
        mLunchSV.setVisibility(View.GONE);
        mDinnerSV.setVisibility(View.GONE);
        mNightSV.setVisibility(View.GONE);
        btmMorning.setBackgroundColor(Color.GREEN);
        btmBreakfast.setBackgroundColor(Color.WHITE);
        btmLunch.setBackgroundColor(Color.GREEN);
        btmDinner.setBackgroundColor(Color.GREEN);
        btmNight.setBackgroundColor(Color.GREEN);
    }

    public void showLunch(View view) {
        coverText.setVisibility(View.GONE);
        mMorningSV.setVisibility(View.GONE);
        mBreakfastSV.setVisibility(View.GONE);
        mLunchSV.setVisibility(View.VISIBLE);
        mDinnerSV.setVisibility(View.GONE);
        mNightSV.setVisibility(View.GONE);
        btmMorning.setBackgroundColor(Color.GREEN);
        btmBreakfast.setBackgroundColor(Color.GREEN);
        btmLunch.setBackgroundColor(Color.WHITE);
        btmDinner.setBackgroundColor(Color.GREEN);
        btmNight.setBackgroundColor(Color.GREEN);
    }

    public void showDinner(View view) {
        coverText.setVisibility(View.GONE);
        mMorningSV.setVisibility(View.GONE);
        mBreakfastSV.setVisibility(View.GONE);
        mLunchSV.setVisibility(View.GONE);
        mDinnerSV.setVisibility(View.VISIBLE);
        mNightSV.setVisibility(View.GONE);
        btmMorning.setBackgroundColor(Color.GREEN);
        btmBreakfast.setBackgroundColor(Color.GREEN);
        btmLunch.setBackgroundColor(Color.GREEN);
        btmDinner.setBackgroundColor(Color.WHITE);
        btmNight.setBackgroundColor(Color.GREEN);
    }

    public void showNight(View view) {
        coverText.setVisibility(View.GONE);
        mMorningSV.setVisibility(View.GONE);
        mBreakfastSV.setVisibility(View.GONE);
        mLunchSV.setVisibility(View.GONE);
        mDinnerSV.setVisibility(View.GONE);
        mNightSV.setVisibility(View.VISIBLE);
        btmMorning.setBackgroundColor(Color.GREEN);
        btmBreakfast.setBackgroundColor(Color.GREEN);
        btmLunch.setBackgroundColor(Color.GREEN);
        btmDinner.setBackgroundColor(Color.GREEN);
        btmNight.setBackgroundColor(Color.WHITE);
    }
}
