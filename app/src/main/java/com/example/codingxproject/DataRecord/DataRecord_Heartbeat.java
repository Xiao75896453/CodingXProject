package com.example.codingxproject.DataRecord;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.codingxproject.MainFunctionsWithBottomBar.HomePageActivity;
import com.example.codingxproject.R;

import java.util.Calendar;

public class DataRecord_Heartbeat extends AppCompatActivity {

    final static Calendar currentCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_record_heartbeat);
        final TextView tvCurrentTime = (TextView) findViewById(R.id.tvTime);
        final NumberPicker mNumberPicker = (NumberPicker)findViewById(R.id.numberpicker_heartbeat);
        final Button bConfirmHeartbeat=(Button)findViewById(R.id.bConfirm_heartbeat);
        final TextView tvHeartbeat=(TextView)findViewById(R.id.tvTitle_Heartbeat);
        String currentHour, currentMin;
        currentHour = setTimeForm(currentCalendar.get(Calendar.HOUR_OF_DAY));
        currentMin = setTimeForm(currentCalendar.get(Calendar.MINUTE));
        String currentTime = currentHour + ":" + currentMin;

        tvCurrentTime.setText(currentTime);

        Button resetTime = (Button) findViewById(R.id.bChangeTime);
        resetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(DataRecord_Heartbeat.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMin) {
                        String hour = setTimeForm(selectedHour);
                        String min = setTimeForm(selectedMin);
                        tvCurrentTime.setText(hour+":"+min);
                    }
                }, currentCalendar.get(Calendar.HOUR_OF_DAY), currentCalendar.get(Calendar.MINUTE),true).show();
            }
        });
        mNumberPicker.setMaxValue(120);
        mNumberPicker.setMinValue(30);
        mNumberPicker.setValue(72);
        mNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                mNumberPicker.setValue(newVal);
                tvHeartbeat.setText("心跳數："+Integer.toString(mNumberPicker.getValue())+"次/分鐘");
            }
        });

        final DialogInterface.OnClickListener dialogListener=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(DataRecord_Heartbeat.this, HomePageActivity.class);
                startActivity(intent);
            }
        };

        bConfirmHeartbeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mNumberPicker.getValue()>100){
                    AlertDialog.Builder build=new AlertDialog.Builder(DataRecord_Heartbeat.this);
                    build.setMessage("心跳太快，請立即就醫，或服用指示藥物!!").setPositiveButton("我知道了", dialogListener).create().show();
                }else if(mNumberPicker.getValue()<40){
                    AlertDialog.Builder build=new AlertDialog.Builder(DataRecord_Heartbeat.this);
                    build.setMessage("心跳數過低，請立即就醫，或服用指示藥物!!").setPositiveButton("我知道了", dialogListener).create().show();
                }else{
                    Intent intent=new Intent(DataRecord_Heartbeat.this,HomePageActivity.class);
                    startActivity(intent);
                }

                //紀錄資訊，傳出資訊
//                final int outputVal= mNumberPicker.getValue();
//                Intent intent_SBP = getIntent();
//                startActivityForResult(intent_SBP,REQUEST_CODE);
            }
        });

    }

    public static String setTimeForm(int currentTime) {
        if (Integer.toString(currentTime).length() == 1) {
            return "0" + Integer.toString(currentTime);
        } else {
            return Integer.toString(currentTime);
        }
    }
}
