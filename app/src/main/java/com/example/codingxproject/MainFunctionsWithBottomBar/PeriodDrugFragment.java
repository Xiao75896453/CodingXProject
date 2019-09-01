package com.example.codingxproject.MainFunctionsWithBottomBar;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codingxproject.R;


public class PeriodDrugFragment extends Fragment {

    private ScrollView mMorningSV, mBreakfastSV, mLunchSV, mDinnerSV, mNightSV;
    private Button btmMorning, btmBreakfast, btmLunch, btmDinner, btmNight;
    private ConstraintLayout coverText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_period_drug, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMorningSV = (ScrollView) getView().findViewById(R.id.MorningScrollView);
        mBreakfastSV = (ScrollView) getView().findViewById(R.id.BreakfastScrollView);
        mLunchSV = (ScrollView) getView().findViewById(R.id.LunchScrollview);
        mDinnerSV = (ScrollView) getView().findViewById(R.id.DinnerScrollView);
        mNightSV = (ScrollView) getView().findViewById(R.id.NightScrollVIEW);
        btmMorning = (Button) getView().findViewById(R.id.morning_btm);
        btmBreakfast = (Button) getView().findViewById(R.id.breakfast_btm);
        btmLunch = (Button) getView().findViewById(R.id.lunch_btm);
        btmDinner = (Button) getView().findViewById(R.id.dinner_btm);
        btmNight = (Button) getView().findViewById(R.id.night_btm);
        coverText = (ConstraintLayout) getView().findViewById(R.id.cover);

        btmMorning.setBackgroundColor(Color.GREEN);
        btmBreakfast.setBackgroundColor(Color.GREEN);
        btmLunch.setBackgroundColor(Color.GREEN);
        btmDinner.setBackgroundColor(Color.GREEN);
        btmNight.setBackgroundColor(Color.GREEN);

        View.OnClickListener listener=new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.morning_btm){
//                    Toast.makeText(getActivity(),"morningBtmClicked",Toast.LENGTH_LONG).show();
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
                }else if(view.getId()==R.id.breakfast_btm){
//                    Toast.makeText(getActivity(),"breakfastBtmClicked",Toast.LENGTH_LONG).show();
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
                }else if(view.getId()==R.id.lunch_btm){
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
                }else if(view.getId()==R.id.dinner_btm){
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
                }else if(view.getId()==R.id.night_btm){
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
        };
        btmMorning.setOnClickListener(listener);
        btmBreakfast.setOnClickListener(listener);
        btmLunch.setOnClickListener(listener);
        btmDinner.setOnClickListener(listener);
        btmNight.setOnClickListener(listener);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}

