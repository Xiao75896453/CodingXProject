package com.example.codingxproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class DataRecord_BloodPressure_SBP extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.datarecord.extra.REPLY";
    //public static final int nowValue = "com.example.datarecord.extra.REPLY";
    private TextView time;
    private Button timechange ;
    private  static final int REQUEST_CODE =1 ;
    private NumberPicker mNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_record__blood_pressure__sbp);
        time= (TextView)findViewById(R.id.time);
        timechange = (Button)findViewById(R.id.timechange);
        Intent intent_SBP = getIntent();
        startActivityForResult(intent_SBP,REQUEST_CODE);
        timechange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //預設為系統時間
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                //抓現在時間
                new TimePickerDialog(DataRecord_BloodPressure_SBP.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int houreOfDay, int minute) {
                        time.setText("現在時間是" + houreOfDay + ":" + minute);
                    }
                }, hour,minute,true).show();
            }
        });
        //設定numberpicker
        mNumberPicker = (NumberPicker)findViewById(R.id.numberpicker_SBP);
        mNumberPicker.setMinValue(100);
        mNumberPicker.setMaxValue(150);
        mNumberPicker.setValue(120);
        int nowValue = mNumberPicker.getValue();
        mNumberPicker.setOnValueChangedListener(numberpicker_SBP_OnValueChange);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(resultCode){
            case REQUEST_CODE:
                //int result = data.getIntExtra("bloodsugar");
                break;
        }
    }

    public void returnReply(View view){
        int reply = mNumberPicker.getValue();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY,reply);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
    private NumberPicker.OnValueChangeListener numberpicker_SBP_OnValueChange = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
            int nowValue = mNumberPicker.getValue();
        }
    };
}
