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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.codingxproject.R;

import java.util.Calendar;

public class DataRecord_BloodSugar_Activity extends AppCompatActivity {

    final static Calendar currentCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_record_blood_sugar);

        final TextView tvCurrentTime = (TextView) findViewById(R.id.tvCurrentTime);
        Button resetTime = (Button) findViewById(R.id.bChangeTime);
        final CustomNumberPicker mNumberPicker = (CustomNumberPicker)findViewById(R.id.numberpicker_bloodsugar);
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
                new TimePickerDialog(DataRecord_BloodSugar_Activity.this, new TimePickerDialog.OnTimeSetListener() {
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
                tvBloodSugar.setText("血糖值："+Integer.toString(mNumberPicker.getValue()-1)+"mm/dL");
            }
        });

        final DialogInterface.OnClickListener dialogListener=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish_activity(mNumberPicker);
            }
        };

        bConfirmBloodSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((rbAC_bloodsugar.isChecked()==false)&&(rbPC_bloodsugar.isChecked()==false)){
                    AlertDialog.Builder build=new AlertDialog.Builder(DataRecord_BloodSugar_Activity.this);
                    build.setMessage("請選擇餐前或餐後。").setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {}
                    }).create().show();
                }else if(rbAC_bloodsugar.isChecked()&&mNumberPicker.getValue()>110){
                    AlertDialog.Builder build=new AlertDialog.Builder(DataRecord_BloodSugar_Activity.this);
                    build.setMessage("血糖過高，請立即就醫，或服用指示藥物!!").setPositiveButton("我知道了",dialogListener).create().show();
                }else if(mNumberPicker.getValue()<70){
                    AlertDialog.Builder build=new AlertDialog.Builder(DataRecord_BloodSugar_Activity.this);
                    build.setMessage("血糖太低，請立即就醫，或服用指示藥物!!").setPositiveButton("我知道了",dialogListener).create().show();
                }else{
                    finish_activity(mNumberPicker);
                }
            }
        });
    }

    private void finish_activity(CustomNumberPicker mNumberPicker)
    {
        Intent intent = new Intent();
        //把返回數據存入Intent
        intent.putExtra("HbA1c_result", mNumberPicker.getValue());
        //設置返回數據
        DataRecord_BloodSugar_Activity.this.setResult(RESULT_OK, intent);
        //關閉Activity
        DataRecord_BloodSugar_Activity.this.finish();
    }

    public static String setTimeForm(int currentTime) {
        if (Integer.toString(currentTime).length()==1) {
            return "0" + Integer.toString(currentTime);
        } else {
            return Integer.toString(currentTime);
        }
    }
}
