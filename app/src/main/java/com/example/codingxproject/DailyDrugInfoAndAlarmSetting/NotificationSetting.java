package com.example.codingxproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

public class NotificationSetting extends AppCompatActivity {

    ListView myListView;
    String[] items;

    String[] alarms;

    String[] descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);
        items = res.getStringArray(R.array.items);

        alarms = res.getStringArray(R.array.alarms);

        descriptions = res.getStringArray(R.array.descriptions);



        ItemAdapter itemAdapter = new ItemAdapter(this, items,alarms,descriptions);
        myListView.setAdapter(itemAdapter);



    }
}
