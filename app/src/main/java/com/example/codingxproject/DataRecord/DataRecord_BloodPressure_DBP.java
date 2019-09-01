package com.example.codingxproject.DataRecord;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.codingxproject.R;

import java.util.Calendar;

public class DataRecord_BloodPressure_DBP extends AppCompatActivity {

    final static Calendar currentCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_record_blood_pressure_dbp);
        final TextView tvCurrentTime = (TextView) findViewById(R.id.tvCurrentTime);
        final NumberPicker mNumberPicker = (NumberPicker)findViewById(R.id.numberpicker_DBP);
        final Button bConfirmDBP=(Button)findViewById(R.id.bConfirm_DBP);
        final TextView tvDBP=(TextView)findViewById(R.id.tvTitle_DBP);
        String currentHour, currentMin;
        currentHour = setTimeForm(currentCalendar.get(Calendar.HOUR_OF_DAY));
        currentMin = setTimeForm(currentCalendar.get(Calendar.MINUTE));
        String currentTime = currentHour + ":" + currentMin;


        tvCurrentTime.setText(currentTime);

        Button resetTime = (Button) findViewById(R.id.bChangeTime);
        resetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(DataRecord_BloodPressure_DBP.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMin) {
                        String hour = setTimeForm(selectedHour);
                        String min = setTimeForm(selectedMin);
                        tvCurrentTime.setText(hour+":"+min);
                    }
                }, currentCalendar.get(Calendar.HOUR_OF_DAY), currentCalendar.get(Calendar.MINUTE),true).show();
            }
        });
        mNumberPicker.setMaxValue(100);
        mNumberPicker.setMinValue(40);
        mNumberPicker.setValue(80);
        mNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                mNumberPicker.setValue(newVal);
                tvDBP.setText("舒張壓值："+Integer.toString(mNumberPicker.getValue())+"mm-Hg");
            }
        });

        bConfirmDBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mNumberPicker.getValue()>90){
                    AlertDialog.Builder build=new AlertDialog.Builder(DataRecord_BloodPressure_DBP.this);
                    build.setMessage("舒張壓太高，請立即就醫，或服用指示藥物!!").setPositiveButton("我知道了",null).create().show();
                }else if(mNumberPicker.getValue()<60){
                    AlertDialog.Builder build=new AlertDialog.Builder(DataRecord_BloodPressure_DBP.this);
                    build.setMessage("舒張壓太低，請立即就醫，或服用指示藥物!!").setPositiveButton("我知道了",null).create().show();
                }
                Intent intent=new Intent(DataRecord_BloodPressure_DBP.this,DataRecord_BloodSugar.class);
                startActivity(intent);
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
