package com.example.codingxproject.MainFunctionsWithBottomBar;

import android.content.Context;
import android.content.Intent;
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

import com.example.codingxproject.DetailDrugMessage.DetailDrugInfoActivity;
import com.example.codingxproject.DetailDrugMessage.DrugsInfoActivity;
import com.example.codingxproject.R;


public class PeriodDrugFragment extends Fragment {

    private ScrollView mMorningSV, mBreakfastSV, mLunchSV, mDinnerSV, mNightSV;
    private Button btmMorning, btmBreakfast, btmLunch, btmDinner, btmNight,bTotalDrug;
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
        bTotalDrug=(Button) getView().findViewById(R.id.bTotalDrug);
        coverText = (ConstraintLayout) getView().findViewById(R.id.cover);

        setButtonToGreen();

        View.OnClickListener listener=new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.morning_btm){
//                  Toast.makeText(getActivity(),"morningBtmClicked",Toast.LENGTH_LONG).show();
                    setVisibilityGone();
                    setButtonToGreen();
                    mMorningSV.setVisibility(View.VISIBLE);
                    setButtonClickedUI(btmMorning);

                }else if(view.getId()==R.id.breakfast_btm){
//                    Toast.makeText(getActivity(),"breakfastBtmClicked",Toast.LENGTH_LONG).show();
                    setVisibilityGone();
                    setButtonToGreen();
                    mBreakfastSV.setVisibility(View.VISIBLE);
                    setButtonClickedUI(btmBreakfast);
                }else if(view.getId()==R.id.lunch_btm){
                    setVisibilityGone();
                    setButtonToGreen();
                    mLunchSV.setVisibility(View.VISIBLE);
                    setButtonClickedUI(btmLunch);
                }else if(view.getId()==R.id.dinner_btm){
                    setVisibilityGone();
                    setButtonToGreen();
                    mDinnerSV.setVisibility(View.VISIBLE);
                    setButtonClickedUI(btmDinner);
                }else if(view.getId()==R.id.night_btm){
                    setVisibilityGone();
                    setButtonToGreen();
                    mNightSV.setVisibility(View.VISIBLE);
                    setButtonClickedUI(btmNight);
                }else if(view.getId()==R.id.bTotalDrug){
                    startActivity(new Intent(getActivity(), DrugsInfoActivity.class));
                }
            }
        };
        btmMorning.setOnClickListener(listener);
        btmBreakfast.setOnClickListener(listener);
        btmLunch.setOnClickListener(listener);
        btmDinner.setOnClickListener(listener);
        btmNight.setOnClickListener(listener);
        bTotalDrug.setOnClickListener(listener);
    }

    public void setVisibilityGone(){
        coverText.setVisibility(View.GONE);
        mMorningSV.setVisibility(View.GONE);
        mBreakfastSV.setVisibility(View.GONE);
        mLunchSV.setVisibility(View.GONE);
        mDinnerSV.setVisibility(View.GONE);
        mNightSV.setVisibility(View.GONE);
    }

    public void setButtonToGreen(){
        setButton(btmMorning);
        setButton(btmBreakfast);
        setButton(btmLunch);
        setButton(btmDinner);
        setButton(btmNight);
    }
    public void setButton(Button b){
//        b.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        b.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_button));
//        b.setTextColor(getResources().getColor(R.color.white));
        b.setTextColor(getResources().getColor(R.color.Gray));
    }
    public void setButtonClickedUI(Button b){
        b.setBackgroundColor(getResources().getColor(R.color.transparent));
        b.setTextColor(getResources().getColor(R.color.Gray));
    }
}

