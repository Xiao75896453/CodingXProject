package com.example.codingxproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Intent mainIntent = new Intent();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bLoginAndRegister=(Button)findViewById(R.id.bLoginRegister);
        Button bRecordBloodPressureDBP = (Button) findViewById(R.id.bRecordBloodPressureDBP);
        Button bRecordBloodPressureSBP = (Button) findViewById(R.id.bRecordBloodPressureSBP);
        Button bRecordBloodSugar=(Button)findViewById(R.id.bRecordBloodSugar);
        Button bDrugTimePage = (Button) findViewById(R.id.bToDrugTimePage);
        Button bRemindDrugTaking = (Button) findViewById(R.id.bToRemindDrugTaking);
        Button bRemindRecord = (Button) findViewById(R.id.bToRemindRecord);
        Button bRemindTakeMed = (Button) findViewById(R.id.bToRemindTakeMed);
        Button bSetTime = (Button) findViewById(R.id.bToSetTime);
        Button bDetailDrugList=(Button) findViewById(R.id.bToDetailDrugList);
        final Button bDateReview=(Button)findViewById(R.id.bDataReview);

//    public void launch_data_review(View view) {
//        Log.d("btm", "to Data Review Button clicked!");
//        Intent intent = new Intent(this, DataReview.class);
//        startActivity(intent);
//    }

        Button.OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (view.getId() == R.id.bRecordBloodPressureDBP) {
                    intent = new Intent(MainActivity.this, DataRecord_BloodPressure_DBP.class);
                }else if(view.getId()==R.id.bRecordBloodPressureSBP){
                    intent = new Intent(MainActivity.this, DataRecord_BloodPressure_SBP.class);
                }else if(view.getId()==R.id.bRecordBloodSugar){
                    intent = new Intent(MainActivity.this, DataRecord_BloodSugar.class);
                }else if (view.getId() == R.id.bToDrugTimePage) {
                    intent = new Intent(MainActivity.this, DrugTimePageActivity.class);
                }else if (view.getId() == R.id.bToRemindDrugTaking) {
                    intent = new Intent(MainActivity.this, RemindDrugTakingActivity.class);
                }else if (view.getId() == R.id.bToRemindRecord) {
                    intent = new Intent(MainActivity.this, RemindRecordActivity.class);
                }else if (view.getId() == R.id.bToRemindTakeMed) {
                    intent = new Intent(MainActivity.this, RemindTakeMedActivity.class);
                }else if (view.getId() == R.id.bToSetTime) {
                    intent = new Intent(MainActivity.this, SetTimeActivity.class);
                }else if (view.getId() == R.id.bToDetailDrugList) {
                    intent = new Intent(MainActivity.this, DrugsInfoActivity.class);
                }else if(view.getId()==R.id.bLoginRegister){
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                }else if(view.getId()==R.id.bDataReview){
                    intent = new Intent(MainActivity.this, DataReview.class);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(intent);
            }
        };
        bLoginAndRegister.setOnClickListener(listener);
        bRecordBloodPressureDBP.setOnClickListener(listener);
        bRecordBloodPressureSBP.setOnClickListener(listener);
        bRecordBloodSugar.setOnClickListener(listener);
        bDrugTimePage.setOnClickListener(listener);
        bRemindDrugTaking.setOnClickListener(listener);
        bRemindRecord.setOnClickListener(listener);
        bRemindTakeMed.setOnClickListener(listener);
        bSetTime.setOnClickListener(listener);
        bDetailDrugList.setOnClickListener(listener);
        bDateReview.setOnClickListener(listener);
    }


}
