package com.example.codingxproject.DailyDrugInfoAndAlarmSetting;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.example.codingxproject.R;
import com.example.codingxproject.SetTimeActivity;

import java.util.ArrayList;
import java.util.List;

public class NotificationSetting extends AppCompatActivity {

    private ArrayList<String> data = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_setting);

        ListView lv = (ListView) findViewById(R.id.lvAlarmList);
        generateListContent();
        lv.setAdapter(new MyListAdapter(this, R.layout.activity_item_adapter, data));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openSetTime();
            }
        });
    }
    public void openSetTime(){
        Intent intent = new Intent(this, SetTimeActivity.class);
        startActivity(intent);
    }

    private void generateListContent() {
        data.add("睡醒");
        data.add("早餐");
        data.add("午餐");
        data.add("晚餐");
        data.add("睡前");
    }



    public class MyListAdapter extends ArrayAdapter<String>{

        private int layout;
        private List<String> mObjects;
        private MyListAdapter(Context context, int resource, List<String> objects){
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            ViewHolder mainViewholder= null;

            if(convertView==null){
                LayoutInflater inflater =  LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.period = (TextView) convertView.findViewById(R.id.tvPeriodTitle);
                viewHolder.time = (Button) convertView.findViewById(R.id.tvPeriodTime);
                viewHolder.open = (Switch) convertView.findViewById(R.id.sbTimeSwitch);

                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openSetTime();
                }
            });
            mainViewholder.period.setText(getItem(position));

            return convertView;
        }
        public void openSetTime(){
            Intent intent = new Intent(NotificationSetting.this, SetTimeActivity.class);
            startActivity(intent);
        }
    }
    public class ViewHolder{
        TextView period;
        Button time;
        Switch open;
    }

}