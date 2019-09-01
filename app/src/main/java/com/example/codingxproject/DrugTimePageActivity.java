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

    private ScrollView mMorningSV,mBreakfastSV,mLunchSV,mDinnerSV,mNightSV;
    private Button btmMorning,btmBreakfast,btmLunch,btmDinner,btmNight;
    private ConstraintLayout coverText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_time_page);

        mMorningSV= findViewById(R.id.MorningScrollView);
        mBreakfastSV= findViewById(R.id.BreakfastScrollView);
        mLunchSV= findViewById(R.id.LunchScrollview);
        mDinnerSV= findViewById(R.id.DinnerScrollView);
        mNightSV= findViewById(R.id.NightScrollVIEW);
        btmMorning= findViewById(R.id.morning_btm);
        btmBreakfast= findViewById(R.id.breakfast_btm);
        btmLunch= findViewById(R.id.lunch_btm);
        btmDinner= findViewById(R.id.dinner_btm);
        btmNight= findViewById(R.id.night_btm);
        coverText = findViewById(R.id.cover);

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
