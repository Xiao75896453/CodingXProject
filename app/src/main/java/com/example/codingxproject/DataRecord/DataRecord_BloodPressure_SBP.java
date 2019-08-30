package com.example.codingxproject.DataRecord;

import androidx.appcompat.app.AppCompatActivity;

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

public class DataRecord_BloodPressure_SBP extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.datarecord.extra.REPLY";
    //public static final int nowValue = "com.example.datarecord.extra.REPLY";
    private TextView tvTime;
    private Button bTimeChange ;
    private  static final int REQUEST_CODE =1 ;
    private NumberPicker mNumberPicker;
    final static Calendar currentCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_record_blood_pressure_sbp);
        String currentHour, currentMin;
        currentHour = setTimeForm(currentCalendar.get(Calendar.HOUR_OF_DAY));
        currentMin = setTimeForm(currentCalendar.get(Calendar.MINUTE));
        String currentTime = currentHour + ":" + currentMin;

        final TextView tvCurrentTime = (TextView) findViewById(R.id.tvTime);
        tvCurrentTime.setText(currentTime);

        Button resetTime = (Button) findViewById(R.id.bChangeTime);
        resetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(DataRecord_BloodPressure_SBP.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMin) {
                        String hour = setTimeForm(selectedHour);
                        String min = setTimeForm(selectedMin);
                        tvCurrentTime.setText(hour+":"+min);
                    }
                }, currentCalendar.get(Calendar.HOUR_OF_DAY), currentCalendar.get(Calendar.MINUTE),true).show();
            }
        });

//        tvTime= (TextView)findViewById(R.id.tvTime);
//        bTimeChange = (Button)findViewById(R.id.tvChangeTime);
//        Intent intent_SBP = getIntent();
//        startActivityForResult(intent_SBP,REQUEST_CODE);
//        bTimeChange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //預設為系統時間
//                final Calendar c = Calendar.getInstance();
//                int hour = c.get(Calendar.HOUR_OF_DAY);
//                int minute = c.get(Calendar.MINUTE);
//                //抓現在時間
//                new TimePickerDialog(DataRecord_BloodPressure_SBP.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int houreOfDay, int minute) {
//                        tvTime.setText("現在時間是" + houreOfDay + ":" + minute);
//                    }
//                }, hour,minute,true).show();
//            }
//        });
//        //設定numberpicker
//        mNumberPicker = (NumberPicker)findViewById(R.id.numberpicker_SBP);
//        mNumberPicker.setMinValue(100);
//        mNumberPicker.setMaxValue(150);
//        mNumberPicker.setValue(120);
//        int nowValue = mNumberPicker.getValue();
//        mNumberPicker.setOnValueChangedListener(numberpicker_SBP_OnValueChange);
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        switch(resultCode){
//            case REQUEST_CODE:
//                //int result = data.getIntExtra("bloodsugar");
//                break;
//        }
//    }
//
//    public void returnReply(View view){
//        int reply = mNumberPicker.getValue();
//        Intent replyIntent = new Intent();
//        replyIntent.putExtra(EXTRA_REPLY,reply);
//        setResult(RESULT_OK, replyIntent);
//        finish();
//    }
//    private NumberPicker.OnValueChangeListener numberpicker_SBP_OnValueChange = new NumberPicker.OnValueChangeListener() {
//        @Override
//        public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
//            int nowValue = mNumberPicker.getValue();
//        }
//    };

    public static String setTimeForm(int currentTime) {
        if (Integer.toString(currentTime).length()==1) {
            return "0" + Integer.toString(currentTime);
        } else {
            return Integer.toString(currentTime);
        }
    }
}
