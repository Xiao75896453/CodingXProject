package com.example.codingxproject.DataRecord;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.codingxproject.R;

import java.util.Calendar;

public class DataRecord_BloodSugar extends AppCompatActivity {

    final static Calendar currentCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_record_blood_sugar);
        final TextView tvCurrentTime = (TextView) findViewById(R.id.tvCurrentTime);
        Button resetTime = (Button) findViewById(R.id.bChangeTime);
        final NumberPicker mNumberPicker = (NumberPicker)findViewById(R.id.numberpicker_bloodsugar);
        final Button bConfirmBloodSugar=(Button)findViewById(R.id.bConfirm_bloodsugar);
        final TextView tvBloodSugar=(TextView)findViewById(R.id.tvTitle_bloodsugar);
        final RadioButton rbAC_bloodsugar=(RadioButton) findViewById(R.id.rbAC_bloodsugar);
        final RadioButton rbPC_bloodsugar=(RadioButton)findViewById(R.id.rbPC_bloodsugar);
        String currentHour, currentMin;
        currentHour = setTimeForm(currentCalendar.get(Calendar.HOUR_OF_DAY));
        currentMin = setTimeForm(currentCalendar.get(Calendar.MINUTE));
        String currentTime = currentHour + ":" + currentMin;

        tvCurrentTime.setText(currentTime);

        resetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(DataRecord_BloodSugar.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMin) {
                        String hour = setTimeForm(selectedHour);
                        String min = setTimeForm(selectedMin);
                        tvCurrentTime.setText(hour+":"+min);
                    }
                }, currentCalendar.get(Calendar.HOUR_OF_DAY), currentCalendar.get(Calendar.MINUTE),true).show();
            }
        });

        mNumberPicker.setMaxValue(1000);
        mNumberPicker.setMinValue(50);
        mNumberPicker.setValue(80);
        mNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                mNumberPicker.setValue(newVal);
                tvBloodSugar.setText("血糖值："+Integer.toString(mNumberPicker.getValue())+"mm/dL");
            }
        });

        bConfirmBloodSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbAC_bloodsugar.isChecked()&&rbAC_bloodsugar.isChecked()==false){
                    AlertDialog.Builder build=new AlertDialog.Builder(DataRecord_BloodSugar.this);
                    build.setMessage("請選擇餐前或餐後。").setPositiveButton("我知道了",null).create().show();
                }else if(rbAC_bloodsugar.isChecked()&&mNumberPicker.getValue()>126){
                    AlertDialog.Builder build=new AlertDialog.Builder(DataRecord_BloodSugar.this);
                    build.setMessage("血糖過高，請立即就醫，或服用指示藥物!!").setPositiveButton("我知道了",null).create().show();
                }else if(rbPC_bloodsugar.isChecked()&&mNumberPicker.getValue()>180){
                    AlertDialog.Builder build=new AlertDialog.Builder(DataRecord_BloodSugar.this);
                    build.setMessage("血糖過高，請立即就醫，或服用指示藥物!!").setPositiveButton("我知道了",null).create().show();
                }else if(mNumberPicker.getValue()<70){
                    AlertDialog.Builder build=new AlertDialog.Builder(DataRecord_BloodSugar.this);
                    build.setMessage("血糖太低，請立即就醫，或服用指示藥物!!").setPositiveButton("我知道了",null).create().show();
                }

                //紀錄資訊，傳出資訊
//                final int outputVal= mNumberPicker.getValue();
//                Intent intent_SBP = getIntent();
//                startActivityForResult(intent_SBP,REQUEST_CODE);
            }
        });

    }

    public static String setTimeForm(int currentTime) {
        if (Integer.toString(currentTime).length()==1) {
            return "0" + Integer.toString(currentTime);
        } else {
            return Integer.toString(currentTime);
        }
    }
}
