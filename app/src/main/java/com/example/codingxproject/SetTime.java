package com.example.codingxproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.NumberPicker;

public class SetTime extends AppCompatActivity {

    private int hour;
    private  int ten_min;
    private  int one_min;
    int minutes = ten_min*10+one_min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
//
//        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.picker);
//        numberPicker.setMinValue(0);
//        numberPicker.setMaxValue(numbers.length - 1);
//        numberPicker.setValue(3); // 設定預設位置
//        numberPicker.setWrapSelectorWheel(true); // 是否循環顯示
//        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 不可編輯
//        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                Log.d("set_time" , Integer.toString(newVal));
//            }
//        });
//
//
//        NumberPicker hoursNumberPicker = (NumberPicker) findViewById(R.id.hoursPicker);
//        hoursNumberPicker.setMinValue(0);
//        hoursNumberPicker.setMaxValue(9);
//        hoursNumberPicker.setValue(0); // 設定預設位置
//        hoursNumberPicker.setWrapSelectorWheel(false); // 是否循環顯示
//        hoursNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 不可編輯
//        hoursNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                hour = newVal;
//                Log.d("set_time" , hour+" : "+minutes);
//            }
//        });
//
//        NumberPicker tenMinutesNumberPicker = (NumberPicker) findViewById(R.id.tenMinutesPicker);
//        tenMinutesNumberPicker.setMinValue(0);
//        tenMinutesNumberPicker.setMaxValue(9);
//        tenMinutesNumberPicker.setValue(3); // 設定預設位置
//        tenMinutesNumberPicker.setWrapSelectorWheel(true); // 是否循環顯示
//        tenMinutesNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 不可編輯
//        tenMinutesNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                ten_min = newVal;
//                Log.d("set_time" , hour+" : "+minutes);
//            }
//        });
//
//        NumberPicker oneMinuteNumberPicker = (NumberPicker) findViewById(R.id.oneMinutePicker);
//        oneMinuteNumberPicker.setMinValue(0);
//        oneMinuteNumberPicker.setMaxValue(9);
//        oneMinuteNumberPicker.setValue(0); // 設定預設位置
//        oneMinuteNumberPicker.setWrapSelectorWheel(true); // 是否循環顯示
//        oneMinuteNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 不可編輯
//        oneMinuteNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                one_min = newVal;
//                Log.d("set_time" , hour+" : "+minutes);
//            }
//        });
    }
}
