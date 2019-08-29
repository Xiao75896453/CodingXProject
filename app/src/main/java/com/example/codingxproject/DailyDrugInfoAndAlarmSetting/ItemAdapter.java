package com.example.codingxproject.DailyDrugInfoAndAlarmSetting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.codingxproject.R;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] items;
    String[] alarms;
    String[] descriptions;


    public ItemAdapter(Context c, String[] i, String[] a, String[] d) {
        items = i;
        alarms = a;
        descriptions = d;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final View v = mInflater.inflate(R.layout.activity_item_adapter, null);
        final TextView nameTextView = (TextView) v.findViewById(R.id.periodTextView);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.timeTextView);
        TextView alarmTextView = (TextView) v.findViewById(R.id.switchButton);

        String name = items[i];
        String desc = descriptions[i];
        String alarm = alarms[i];


        nameTextView.setText(name);
        descriptionTextView.setText(desc);
        alarmTextView.setText(alarm);

        return v;
    }
}