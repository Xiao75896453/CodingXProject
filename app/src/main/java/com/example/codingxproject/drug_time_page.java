package com.example.codingxproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

public class drug_time_page extends AppCompatActivity {

    private TableLayout mMorningTable;
    private TableLayout mBreakfastTable;
    private TableLayout mLunchTable;
    private TableLayout mDinnerTable;
    private TableLayout mNightTable;
    private Button btmMorning;
    private Button btmBreakfast;
    private Button btmLunch;
    private Button btmDinner;
    private Button btmNight;
    private ConstraintLayout coverText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_time_page);
        Intent intent = getIntent();
        mMorningTable = findViewById(R.id.morning_table);
        mBreakfastTable = findViewById(R.id.breakfast_table);
        mLunchTable = findViewById(R.id.lunch_table);
        mDinnerTable = findViewById(R.id.dinner_table);
        mNightTable = findViewById(R.id.night_table);

        btmMorning = findViewById(R.id.morning_btm);
        btmBreakfast = findViewById(R.id.breakfast_btm);
        btmLunch = findViewById(R.id.lunch_btm);
        btmDinner = findViewById(R.id.dinner_btm);
        btmNight = findViewById(R.id.night_btm);

        coverText = findViewById(R.id.cover);

        btmMorning.setBackgroundColor(Color.GREEN);
        btmBreakfast.setBackgroundColor(Color.GREEN);
        btmLunch.setBackgroundColor(Color.GREEN);
        btmDinner.setBackgroundColor(Color.GREEN);
        btmNight.setBackgroundColor(Color.GREEN);
    }

    public void showMorning(View view) {
        coverText.setVisibility(View.GONE);
        mMorningTable.setVisibility(View.VISIBLE);
        mBreakfastTable.setVisibility(View.GONE);
        mLunchTable.setVisibility(View.GONE);
        mDinnerTable.setVisibility(View.GONE);
        mNightTable.setVisibility(View.GONE);
        btmMorning.setBackgroundColor(Color.WHITE);
        btmBreakfast.setBackgroundColor(Color.GREEN);
        btmLunch.setBackgroundColor(Color.GREEN);
        btmDinner.setBackgroundColor(Color.GREEN);
        btmNight.setBackgroundColor(Color.GREEN);
    }

    public void showBreakfast(View view) {
        coverText.setVisibility(View.GONE);
        mMorningTable.setVisibility(View.GONE);
        mBreakfastTable.setVisibility(View.VISIBLE);
        mLunchTable.setVisibility(View.GONE);
        mDinnerTable.setVisibility(View.GONE);
        mNightTable.setVisibility(View.GONE);
        btmMorning.setBackgroundColor(Color.GREEN);
        btmBreakfast.setBackgroundColor(Color.WHITE);
        btmLunch.setBackgroundColor(Color.GREEN);
        btmDinner.setBackgroundColor(Color.GREEN);
        btmNight.setBackgroundColor(Color.GREEN);
    }

    public void showLunch(View view) {
        coverText.setVisibility(View.GONE);
        mMorningTable.setVisibility(View.GONE);
        mBreakfastTable.setVisibility(View.GONE);
        mLunchTable.setVisibility(View.VISIBLE);
        mDinnerTable.setVisibility(View.GONE);
        mNightTable.setVisibility(View.GONE);
        btmMorning.setBackgroundColor(Color.GREEN);
        btmBreakfast.setBackgroundColor(Color.GREEN);
        btmLunch.setBackgroundColor(Color.WHITE);
        btmDinner.setBackgroundColor(Color.GREEN);
        btmNight.setBackgroundColor(Color.GREEN);
    }

    public void showDinner(View view) {
        coverText.setVisibility(View.GONE);
        mMorningTable.setVisibility(View.GONE);
        mBreakfastTable.setVisibility(View.GONE);
        mLunchTable.setVisibility(View.GONE);
        mDinnerTable.setVisibility(View.VISIBLE);
        mNightTable.setVisibility(View.GONE);
        btmMorning.setBackgroundColor(Color.GREEN);
        btmBreakfast.setBackgroundColor(Color.GREEN);
        btmLunch.setBackgroundColor(Color.GREEN);
        btmDinner.setBackgroundColor(Color.WHITE);
        btmNight.setBackgroundColor(Color.GREEN);
    }

    public void showNiight(View view) {
        coverText.setVisibility(View.GONE);
        mMorningTable.setVisibility(View.GONE);
        mBreakfastTable.setVisibility(View.GONE);
        mLunchTable.setVisibility(View.GONE);
        mDinnerTable.setVisibility(View.GONE);
        mNightTable.setVisibility(View.VISIBLE);
        btmMorning.setBackgroundColor(Color.GREEN);
        btmBreakfast.setBackgroundColor(Color.GREEN);
        btmLunch.setBackgroundColor(Color.GREEN);
        btmDinner.setBackgroundColor(Color.GREEN);
        btmNight.setBackgroundColor(Color.WHITE);
    }
}
