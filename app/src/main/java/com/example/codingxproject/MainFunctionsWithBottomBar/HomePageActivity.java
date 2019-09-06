package com.example.codingxproject.MainFunctionsWithBottomBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.codingxproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageActivity extends AppCompatActivity {

    public PeriodDrugFragment mPeriodDrugFragment;
    public AddOrViewDataFragment mDataFragment;
    public SetAlarmFragment mSetAlarmFragment;
    public DetailDrugInfoFragment mDetailDrugInfoFragment;
    public FragmentTransaction beginTransaction ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        BottomNavigationView btmNavView = (BottomNavigationView) findViewById(R.id.navbBottomMenu);

        init();
        btmNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.iconViewPeriodDrug:
                        showNav(R.id.iconViewPeriodDrug);
                        break;
                    case R.id.iconAddBodyRecord:
                        showNav(R.id.iconAddBodyRecord);
                        break;
                    case R.id.iconSetAlarm:
                        showNav(R.id.iconSetAlarm);
                        break;
                }
                return true;
            }
        });


        try {
            if (getIntent().getStringExtra("chooseFragment").equals("1")) {
                showNav(R.id.iconViewPeriodDrug);
            } else if (getIntent().getStringExtra("chooseFragment").equals("2")) {
                showNav(R.id.iconAddBodyRecord);
            } else if (getIntent().getStringExtra("chooseFragment").equals("3")) {
                showNav(R.id.iconSetAlarm);
            }
        } catch (Exception e) {
            Log.d("intent message", "no intent string");
        }


    }

    private void init() { //initialize screen
        mDataFragment = new AddOrViewDataFragment();
        mPeriodDrugFragment = new PeriodDrugFragment();
        mSetAlarmFragment = new SetAlarmFragment();
        mDetailDrugInfoFragment=new DetailDrugInfoFragment();
//        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.id.frameMainFunctions, mPeriodDrugFragment).add(R.id.frameMainFunctions, mDataFragment).add(R.id.frameMainFunctions, mSetAlarmFragment).add(R.id.frameMainFunctions,mDetailDrugInfoFragment);
        beginTransaction.hide(mSetAlarmFragment).hide(mDataFragment).hide(mPeriodDrugFragment).hide(mDetailDrugInfoFragment);
        beginTransaction.addToBackStack(null);
//        beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        beginTransaction.commit();
        showNav(R.id.iconViewPeriodDrug);
    }

    public void showNav(int nav) {
//        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction = getSupportFragmentManager().beginTransaction();
        switch (nav) {
            case R.id.iconViewPeriodDrug:
                beginTransaction.hide(mDataFragment).hide(mSetAlarmFragment).hide(mDetailDrugInfoFragment);
                beginTransaction.show(mPeriodDrugFragment);
                beginTransaction.commit();
                break;
            case R.id.iconAddBodyRecord:
                beginTransaction.hide(mPeriodDrugFragment).hide(mSetAlarmFragment).hide(mDetailDrugInfoFragment);
                beginTransaction.show(mDataFragment);
                beginTransaction.commit();
                break;
            case R.id.iconSetAlarm:
                beginTransaction.hide(mDataFragment).hide(mPeriodDrugFragment).hide(mDetailDrugInfoFragment);
                beginTransaction.show(mSetAlarmFragment);
                beginTransaction.commit();
                break;
            case 0:
                beginTransaction.hide(mDataFragment).hide(mPeriodDrugFragment).hide(mSetAlarmFragment);
                beginTransaction.show(mDetailDrugInfoFragment);
                beginTransaction.commit();
                break;
        }

    }
}
