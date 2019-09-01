package com.example.codingxproject.MainFunctionsWithBottomBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.codingxproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageActivity extends AppCompatActivity {

    private  PeriodDrugFragment mPeriodDrugFragment;
    private AddOrViewDataFragment mDataFragment;
    private SetAlarmFragment mSetAlarmFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        BottomNavigationView btmNavView=(BottomNavigationView)findViewById(R.id.navbBottomMenu);

        init();
        btmNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
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
    }

    private void init() { //initialize screen
        mDataFragment=new AddOrViewDataFragment();
        mPeriodDrugFragment=new PeriodDrugFragment();
        mSetAlarmFragment=new SetAlarmFragment();
        FragmentTransaction beginTransaction=getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.id.frameMainFunctions,mPeriodDrugFragment).add(R.id.frameMainFunctions,mDataFragment).add(R.id.frameMainFunctions,mSetAlarmFragment);
        beginTransaction.hide(mSetAlarmFragment).hide(mDataFragment).hide(mPeriodDrugFragment);
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
        showNav(R.id.iconViewPeriodDrug);
    }

    private void showNav(int nav) {
        FragmentTransaction beginTransaction=getSupportFragmentManager().beginTransaction();
        switch (nav){
            case R.id.iconViewPeriodDrug:
                beginTransaction.hide(mDataFragment).hide(mSetAlarmFragment);
                beginTransaction.show(mPeriodDrugFragment);
                beginTransaction.commit();
                break;
            case R.id.iconAddBodyRecord:
                beginTransaction.hide(mPeriodDrugFragment).hide(mSetAlarmFragment);
                beginTransaction.show(mDataFragment);
                beginTransaction.commit();
                break;
            case  R.id.iconSetAlarm:
                beginTransaction.hide(mDataFragment).hide(mPeriodDrugFragment);
                beginTransaction.show(mSetAlarmFragment);
                beginTransaction.commit();
                break;
        }

    }
}
