package com.example.codingxproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Intent mainIntent = new Intent();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bDataRecord = (Button) findViewById(R.id.bToDataRecord);
        Button bDrugTimePage = (Button) findViewById(R.id.bToDrugTimePage);
        Button bRemindDrugTaking = (Button) findViewById(R.id.bToRemindDrugTaking);
        Button bRemindRecord = (Button) findViewById(R.id.bToRemindRecord);
        Button bRemindTakeMed = (Button) findViewById(R.id.bToRemindTakeMed);
        Button bSetTime = (Button) findViewById(R.id.bToSetTime);

        Button.OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (view.getId() == R.id.bToDataRecord) {
                    intent = new Intent(MainActivity.this, DataRecord.class);
                }
                if (view.getId() == R.id.bToDrugTimePage) {
                    intent = new Intent(MainActivity.this, DrugTimePage.class);
                }
                if (view.getId() == R.id.bToRemindDrugTaking) {
                    intent = new Intent(MainActivity.this, RemindDrugTaking.class);
                }
                if (view.getId() == R.id.bToRemindRecord) {
                    intent = new Intent(MainActivity.this, RemindRecord.class);
                }
                if (view.getId() == R.id.bToRemindTakeMed) {
                    intent = new Intent(MainActivity.this, RemindTakeMed.class);
                }
                if (view.getId() == R.id.bToSetTime) {
                    intent = new Intent(MainActivity.this, SetTime.class);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(intent);
            }
        };

        bDataRecord.setOnClickListener(listener);
        bDrugTimePage.setOnClickListener(listener);
        bRemindDrugTaking.setOnClickListener(listener);
        bRemindRecord.setOnClickListener(listener);
        bRemindTakeMed.setOnClickListener(listener);
        bSetTime.setOnClickListener(listener);
    }


}
