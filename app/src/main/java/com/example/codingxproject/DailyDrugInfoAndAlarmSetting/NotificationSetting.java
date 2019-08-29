package com.example.codingxproject.DailyDrugInfoAndAlarmSetting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

import com.example.codingxproject.R;

public class NotificationSetting extends AppCompatActivity {

    ListView myListView;
    String[] items;

    String[] alarms;

    String[] descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_setting);

        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);
        items = res.getStringArray(R.array.items);

        alarms = res.getStringArray(R.array.alarms);
        descriptions = res.getStringArray(R.array.descriptions);

        ItemAdapter itemAdapter = new ItemAdapter(this, items,alarms,descriptions);
        myListView.setAdapter(itemAdapter);



    }
}
