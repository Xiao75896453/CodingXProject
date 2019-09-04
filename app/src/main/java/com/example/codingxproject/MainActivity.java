package com.example.codingxproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.codingxproject.DailyDrugInfoAndAlarmSetting.NotificationSetting;
import com.example.codingxproject.DataRecord.DataRecord_BloodPressure_DBP;
import com.example.codingxproject.DataRecord.DataRecord_BloodPressure_SBP;
import com.example.codingxproject.DataRecord.DataRecord_BloodSugar;
import com.example.codingxproject.DetailDrugMessage.DrugsInfoActivity;
import com.example.codingxproject.LoginRegister.LoginActivity;
import com.example.codingxproject.MainFunctionsWithBottomBar.HomePageActivity;
import com.example.codingxproject.Remind.RemindDrugGetActivity;
import com.example.codingxproject.Remind.RemindRecordActivity;
import com.example.codingxproject.Remind.RemindTakeMedActivity;

public class MainActivity extends AppCompatActivity {

    public Intent mainIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bSetDrugTime = (Button) findViewById(R.id.bSetDrugTime);
        Button bLoginAndRegister = (Button) findViewById(R.id.bLoginRegister);
        Button bRecordBloodPressureDBP = (Button) findViewById(R.id.bRecordBloodPressureDBP);
        Button bRecordBloodPressureSBP = (Button) findViewById(R.id.bRecordBloodPressureSBP);
        Button bRecordBloodSugar = (Button) findViewById(R.id.bRecordBloodSugar);
//        Button bDrugTimePage = (Button) findViewById(R.id.bToDrugTimePage);
        Button bRemindDrugTaking = (Button) findViewById(R.id.bToRemindDrugGet);
        Button bRemindRecord = (Button) findViewById(R.id.bToRemindRecord);
        Button bRemindTakeMed = (Button) findViewById(R.id.bToRemindTakeMed);
//        Button bSetTime = (Button) findViewById(R.id.bToSetTime);
        Button bDetailDrugList = (Button) findViewById(R.id.bToDetailDrugList);
        Button bDateReview = (Button) findViewById(R.id.bDataReview);
        Button bHomePage=(Button)findViewById(R.id.bHomePage);

        Button.OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (view.getId() == R.id.bRecordBloodPressureDBP) {
                    intent = new Intent(MainActivity.this, DataRecord_BloodPressure_DBP.class);
                } else if (view.getId() == R.id.bRecordBloodPressureSBP) {
                    intent = new Intent(MainActivity.this, DataRecord_BloodPressure_SBP.class);
                } else if (view.getId() == R.id.bRecordBloodSugar) {
                    intent = new Intent(MainActivity.this, DataRecord_BloodSugar.class);
                } else if (view.getId() == R.id.bToRemindDrugGet) {
                    intent = new Intent(MainActivity.this, RemindDrugGetActivity.class);
                } else if (view.getId() == R.id.bToRemindRecord) {
                    intent = new Intent(MainActivity.this, RemindRecordActivity.class);
                } else if (view.getId() == R.id.bToRemindTakeMed) {
                    intent = new Intent(MainActivity.this, RemindTakeMedActivity.class);
                } else if (view.getId() == R.id.bToDetailDrugList) {
                    intent = new Intent(MainActivity.this, DrugsInfoActivity.class);
                } else if (view.getId() == R.id.bLoginRegister) {
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                } else if (view.getId() == R.id.bDataReview) {
                    intent = new Intent(MainActivity.this, DataReview.class);
                } else if (view.getId() == R.id.bSetDrugTime) {
                    intent = new Intent(MainActivity.this, NotificationSetting.class);
                }else if(view.getId()==R.id.bHomePage){
                    intent = new Intent(MainActivity.this, HomePageActivity.class);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(intent);
            }
        };
        bHomePage.setOnClickListener(listener);
        bSetDrugTime.setOnClickListener(listener);
        bLoginAndRegister.setOnClickListener(listener);
        bRecordBloodPressureDBP.setOnClickListener(listener);
        bRecordBloodPressureSBP.setOnClickListener(listener);
        bRecordBloodSugar.setOnClickListener(listener);
//        bDrugTimePage.setOnClickListener(listener);
        bRemindDrugTaking.setOnClickListener(listener);
        bRemindRecord.setOnClickListener(listener);
        bRemindTakeMed.setOnClickListener(listener);
//        bSetTime.setOnClickListener(listener);
        bDetailDrugList.setOnClickListener(listener);
        bDateReview.setOnClickListener(listener);
    }


}
