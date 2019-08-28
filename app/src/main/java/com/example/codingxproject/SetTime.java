package com.example.codingxproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.NumberPicker;

public class SetTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.picker);
        //final String[] numbers = getResources().getStringArray(R.array.numbers);
        Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(numbers.length - 1);
        //numberPicker.setDisplayedValues(numbers);
        numberPicker.setValue(3); // 設定預設位置
        numberPicker.setWrapSelectorWheel(false); // 是否循環顯示
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 不可編輯
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            }
        });
    }
}
