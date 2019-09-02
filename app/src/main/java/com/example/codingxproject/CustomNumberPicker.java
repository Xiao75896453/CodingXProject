package com.example.codingxproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;

public class CustomNumberPicker extends NumberPicker
    {
        public CustomNumberPicker(Context context)
        {
            super(context);
        }

        public CustomNumberPicker(Context context, AttributeSet attrs)
        {
            super(context, attrs);
        }

        @Override
        public void addView(View child)
        {
            super.addView(child);
            updateView(child);
        }

        @Override
        public void addView(View child, ViewGroup.LayoutParams params)
        {
            super.addView(child, params);
            updateView(child);
        }

        @Override
        public void addView(View child, int index, ViewGroup.LayoutParams params)
        {
            super.addView(child, index, params);
            updateView(child);
        }

        private void updateView(View child)
        {
            if (child instanceof EditText)
            {
                ((EditText) child).setTextSize(72);
                //            ((EditText) view).setTextColor(Color.parseColor("#333333"));
            }
        }
    }
