package com.example.codingxproject.MainFunctionsWithBottomBar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.codingxproject.AlarmReceiver;
import com.example.codingxproject.MainActivity;
import com.example.codingxproject.R;
import com.example.codingxproject.SetTimeActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Intent.FLAG_RECEIVER_FOREGROUND;

public class SetAlarmFragment extends Fragment {

    private ArrayList<String> data = new ArrayList<String>();
    final static Calendar timeCalendar = Calendar.getInstance();
    Calendar myCalendar = Calendar.getInstance();
    Calendar calSet = (Calendar) myCalendar.clone();

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
                    new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMin) {

                            String hour = setTimeForm(selectedHour);
                            String minute = setTimeForm(selectedMin);
                            mainViewholder.time.setText(hour+":"+minute);
                            calSet.set(Calendar.HOUR_OF_DAY, selectedHour);
                            calSet.set(Calendar.MINUTE, selectedMin);

                            if(calSet.compareTo(myCalendar) <= 0) {
                                //Today Set time passed, count to tomorrow
                                calSet.add(Calendar.DATE, 1);
                            }

                        }
                    }, timeCalendar.get(Calendar.HOUR_OF_DAY), timeCalendar.get(Calendar.MINUTE),true).show();

                }

            });
            mainViewholder.period.setText(getItem(position));

            mainViewholder.open.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (mainViewholder.open.isChecked()) {
                        Intent intent = new Intent(SetAlarmFragment.this.getActivity(), AlarmReceiver.class);

                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext().getApplicationContext(), 0, intent, 0);
                        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                        //alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + hour1 + minute1, pendingIntent);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), pendingIntent);
                        //Toast.makeText(this, "Alarm set in " + second + " mili seconds", Toast.LENGTH_LONG).show();
                        Toast.makeText(getActivity().getApplicationContext(), " 提醒開啟", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent intent = new Intent(SetAlarmFragment.this.getActivity(), AlarmReceiver.class);
                        intent.setFlags(FLAG_RECEIVER_FOREGROUND);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext().getApplicationContext(), 0, intent, 0);
                        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                        alarmManager.cancel(pendingIntent);
                        Toast.makeText(getActivity().getApplicationContext(), " 提醒關閉 ", Toast.LENGTH_LONG).show();
                    }
                }
            });
            return convertView;
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