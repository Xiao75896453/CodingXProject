package com.example.codingxproject.MainFunctionsWithBottomBar;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.codingxproject.DailyDrugInfoAndAlarmSetting.ItemAdapter;
import com.example.codingxproject.DailyDrugInfoAndAlarmSetting.NotificationSetting;
import com.example.codingxproject.DataRecord.DataRecord_BloodPressure_DBP;
import com.example.codingxproject.R;
import com.example.codingxproject.SetTimeActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SetAlarmFragment extends Fragment {

    private ArrayList<String> data = new ArrayList<String>();
    final static Calendar timeCalendar = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_set_alarm,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ListView lv = (ListView) getView().findViewById(R.id.lvAlarmList);
        generateListContent();
        lv.setAdapter(new MyListAdapter(getContext(), R.layout.activity_item_adapter, data));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openSetTime();
            }
        });
    }
    public void openSetTime(){
        Intent intent = new Intent(getActivity(), SetTimeActivity.class);
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
//            ViewHolder mainViewholder= null;

            if(convertView==null){
                LayoutInflater inflater =  LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.period = (TextView) convertView.findViewById(R.id.tvPeriodTitle);
                viewHolder.time = (Button) convertView.findViewById(R.id.tvPeriodTime);
                viewHolder.open = (Switch) convertView.findViewById(R.id.sbTimeSwitch);

                convertView.setTag(viewHolder);
            }
            final ViewHolder mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    openSetTime();
                    new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMin) {
                            String hour = setTimeForm(selectedHour);
                            String min = setTimeForm(selectedMin);
                            mainViewholder.time.setText(hour+":"+min);
                        }
                    }, timeCalendar.get(Calendar.HOUR_OF_DAY), timeCalendar.get(Calendar.MINUTE),true).show();
                }
            });
            mainViewholder.period.setText(getItem(position));

            return convertView;
        }
        public void openSetTime(){
            Intent intent = new Intent(getActivity(), SetTimeActivity.class);
            startActivity(intent);
        }
    }
    public class ViewHolder{
        TextView period;
        Button time;
        Switch open;
    }
    public static String setTimeForm(int currentTime) {
        if (Integer.toString(currentTime).length() == 1) {
            return "0" + Integer.toString(currentTime);
        } else {
            return Integer.toString(currentTime);
        }
    }
}
