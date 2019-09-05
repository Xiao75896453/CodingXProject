package com.example.codingxproject.MainFunctionsWithBottomBar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.codingxproject.DataRecord.DataRecord_BloodPressure_DBP;
import com.example.codingxproject.DataRecord.DataRecord_BloodPressure_SBP;
import com.example.codingxproject.DataRecord.DataRecord_BloodSugar;
import com.example.codingxproject.DataRecord.DataRecord_Heartbeat;
import com.example.codingxproject.DataReview;
import com.example.codingxproject.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;


public class AddOrViewDataFragment extends Fragment {

    private int SBP_result = 0;
    private int DBP_result = 0;
    private int pulse_result = 0;

    private int infinity = -1000;
    private boolean is_choose_HbA1c = true;
    private boolean is_choose_BP = false;
    private boolean is_choose_pulse = false;

    private String[] week = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    private String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
    private int current = 0;
    private int current_month = 9 - 1;
    private int current_day = 5 - 1;
    private int current_date = 19 - 1;
    private int[] current_day_interval = new int[4];  //0~3陣列元素為各項目 ; 其儲存值為0~3(早上，中午，傍晚，晚上)  //128行初始化
    private int current_chart_position = 0;  //0:HbA1c ; 1:BP
    private int current_period_position = 0;  //0:day ; 1:week ; 2:month ; 3:6months

    private LineChartView lineChartView;

    private int HbA1c_reference_max = 110;
    private int HbA1c_reference_min = 70;
    private int SBP_reference_max = 129;
    private int SBP_reference_min = 100;
    private int DBP_reference_max = 85;
    private int DBP_reference_min = 66;
    private int pulse_reference_max = 80;
    private int pulse_reference_min = 60;


    //HbA1c_day_interval
    private int[] yAxisData_HbA1c_day_interval = {83, 120, 100, 70};
    //SBP_day_interval
    private int[] yAxisData_SBP_day_interval = {110, 170, 111, 150};
    //DBP_day_interval
    private int[] yAxisData_DBP_day_interval = {83, 91, 84, 97};
    //pulse_day_interval
    private int[] yAxisData_pulse_day_interval = {83, 91, 84, 97};


    //HbA1c_week
    private int[] yAxisData_HbA1c_week = {80, 81, 82, 83, 84, 85, 85};
    //SBP_week
    private int[] yAxisData_SBP_week = {109, 102, 110, 111, 210, 115, 114};
    //DBP_week
    private int[] yAxisData_DBP_week = {80, 81, 82, 83, 84, -9, 85};
    //pulse_week
    private int[] yAxisData_pulse_week = {65,65,66,65,64,67,70};

    //HbA1c_month
    private int[] yAxisData_HbA1c_month = {80, 82, 83, 82, 81, 83, 83, 85, 83, 81, 80, 83, 89, 82, 84, 82, 65, 72, 76, 85, 75, 80, 81, 82, 83, 84, 85, 85, 84, 85};
    //SBP_month
    private int[] yAxisData_SBP_month = {113, 115, 114, 113, 113, 116, 110, 112, 110, 114, 104, 111, 111, 110, 111, 111, 112, 114, 112, 113, 110, 111, 115, 117, 111, 104, 105, 105, 108, 114, 115};
    //DBP_month
    private int[] yAxisData_DBP_month = {83, 85, 84, 83, 83, 86, 85, 82, 83, 83, 82, 82, 85, 83, 81, 82, 82, 81, 80, 82, 82, 85, 81, 82, 85, 84, 83, 83, 80, 84, 85};
    //pulse_month
    private int[] yAxisData_pulse_month = {65,67,68,65,65,64,64,65,62,65,69,69,65,70,72,75,93,75,72,69,72,68,67,64,65,62,65,67,65,65};

    //HbA1c_6months
    private int[] yAxisData_HbA1c_6months = {83, 85, 84, 83, 83, 86};
    //SBP_6months
    private int[] yAxisData_SBP_6months = {110, 110, 111, 114, 104, 104};
    //DBP_6months
    private int[] yAxisData_DBP_6months = {83, 85, 84, 83, 83, 86};
    //pulse_6months
    private int[] yAxisData_pulse_6months = {66,67,65,65,64,65};

    public Button bHbAlc,bBP,bPulse,bAddData;

    private LinearLayout cover,chart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_data,container,false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cover=getView().findViewById(R.id.cover);
        chart=getView().findViewById(R.id.chart_layout);

        for(int i=0;i<4;i++)
            current_day_interval[i] = 2;

            Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        current_date = Integer.parseInt(formatter.format(date))-1;
Log.e("current_date",Integer.toString(current_date));

        SimpleDateFormat formatterday = new SimpleDateFormat("EEE");
        for(int i=0;i<7;i++)
            if(formatterday.format(date).equals(week[i]))
                current_day = i;
Log.e("current_day",formatterday.format(date));

        bHbAlc=getView().findViewById(R.id.HbA1c);
        bBP=getView().findViewById(R.id.BP);
        bPulse=getView().findViewById(R.id.pulse);
        bAddData=getView().findViewById(R.id.add_data);

        setAllButtonColor();
        //setButtonClickedColor(bHbAlc);

        Spinner spinner = null;
        spinner = set_spinner(spinner);
        spinner_listener(spinner);

        //initial chart view
        set_chart(0, 0);  //set_chart_BP(0) alternately

        //button listener
        Button.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) getView().findViewById(R.id.add_data);

                if (view.getId() == R.id.HbA1c) {
                    cover.setVisibility(View.GONE);
                    chart.setVisibility(View.VISIBLE);
                    setAllButtonColor();
                    setButtonClickedColor(bHbAlc);
                    is_choose_HbA1c = true;
                    is_choose_BP = false;
                    is_choose_pulse = false;
                    current_chart_position = 0;
                    set_chart(current_chart_position, current_period_position);
                    button.setText("新增血糖數據");

                }

                else if (view.getId() == R.id.BP) {
                    cover.setVisibility(View.GONE);
                    chart.setVisibility(View.VISIBLE);
                    setAllButtonColor();
                    setButtonClickedColor(bBP);
                    is_choose_HbA1c = false;
                    is_choose_BP = true;
                    is_choose_pulse = false;
                    current_chart_position = 1;
                    set_chart(current_chart_position, current_period_position);
                    button.setText("新增血壓、脈搏數據");
                }

                else if (view.getId() == R.id.pulse) {
                    cover.setVisibility(View.GONE);
                    chart.setVisibility(View.VISIBLE);
                    setAllButtonColor();
                    setButtonClickedColor(bPulse);
                    is_choose_HbA1c = false;
                    is_choose_BP = false;
                    is_choose_pulse = true;
                    current_chart_position = 2;
                    set_chart(current_chart_position, current_period_position);
                    button.setText("新增血壓、脈搏數據");
                }

                else if (view.getId() == R.id.add_data) {
                    if (is_choose_HbA1c == true && current_day_interval[0] <= 3)
                        startActivityForResult(new Intent(getActivity(), DataRecord_BloodSugar.class), 0);
                    else if ((is_choose_BP == true || is_choose_pulse == true) && current_day_interval[1] <= 3) {
                        startActivityForResult(new Intent(getActivity(), DataRecord_Heartbeat.class), 3);
                        startActivityForResult(new Intent(getActivity(), DataRecord_BloodPressure_DBP.class), 2);
                        startActivityForResult(new Intent(getActivity(), DataRecord_BloodPressure_SBP.class), 1);
                    }
                }
            }
        };
        getView().findViewById(R.id.HbA1c).setOnClickListener(listener);
        getView().findViewById(R.id.BP).setOnClickListener(listener);
        getView().findViewById(R.id.pulse).setOnClickListener(listener);
        getView().findViewById(R.id.add_data).setOnClickListener(listener);
    }

    public void setAllButtonColor(){
        bHbAlc.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_button));
        bHbAlc.setTextColor(getResources().getColor(R.color.white));
        bBP.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_button));
        bBP.setTextColor(getResources().getColor(R.color.white));
        bPulse.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_button));
        bPulse.setTextColor(getResources().getColor(R.color.white));
        bAddData.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_button));
        bAddData.setTextColor(getResources().getColor(R.color.white));
    }

    public void setButtonClickedColor(Button b){
//        b.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_button));
        b.setBackgroundColor(getResources().getColor(R.color.transparent));
        b.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    public void add_data(int requestCode, int result, int[] yAxisData)
    {
        yAxisData[current_day_interval[requestCode]] = result;
        current_day_interval[requestCode]++;
    }

    public void average_data()
    {
        int[] yAxisData;
        if (current_day_interval[0] > 3 && current_day_interval[1] > 3 && current_day_interval[2] > 3 && current_day_interval[3] > 3)
        {
            yAxisData = yAxisData_HbA1c_day_interval;
            yAxisData_HbA1c_week[current_day] = (yAxisData[0] + yAxisData[1] + yAxisData[2] + yAxisData[3]) / 4;
            yAxisData_HbA1c_month[current_date] = yAxisData_HbA1c_week[current_day];

            yAxisData = yAxisData_SBP_day_interval;
            yAxisData_SBP_week[current_day] = (yAxisData[0] + yAxisData[1] + yAxisData[2] + yAxisData[3]) / 4;
            yAxisData_SBP_month[current_date] = yAxisData_SBP_week[current_day];

            yAxisData = yAxisData_DBP_day_interval;
            yAxisData_DBP_week[current_day] = (yAxisData[0] + yAxisData[1] + yAxisData[2] + yAxisData[3]) / 4;
            yAxisData_DBP_month[current_date] = yAxisData_DBP_week[current_day];

            yAxisData = yAxisData_pulse_day_interval;
            yAxisData_pulse_week[current_day] = (yAxisData[0] + yAxisData[1] + yAxisData[2] + yAxisData[3]) / 4;
            yAxisData_pulse_month[current_date] = yAxisData_pulse_week[current_day];

            current_day++;
            current_date++;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0) {
            //HbA1c輸入數據
            int HbA1c_result = data.getExtras().getInt("HbA1c_result");  //得到新Activity關閉後返回的數據

            //加數據進圖表
            add_data(requestCode,HbA1c_result,yAxisData_HbA1c_day_interval);

            //一天結束時
            average_data();
        }
        else if(requestCode == 1)
        {
            //SBP輸入數據
            SBP_result = data.getExtras().getInt("SBP_result");  //得到新Activity關閉後返回的數據

            //加數據進圖表
            add_data(requestCode,SBP_result,yAxisData_SBP_day_interval);

            //一天結束時
            average_data();
        }

        else if(requestCode == 2)
        {
            //DBP輸入數據
            DBP_result = data.getExtras().getInt("DBP_result");  //得到新Activity關閉後返回的數據

            //加數據進圖表
            add_data(requestCode,DBP_result,yAxisData_DBP_day_interval);

            //一天結束時
            average_data();
        }

        else if(requestCode == 3)
        {
            //pulse輸入數據
            pulse_result = data.getExtras().getInt("pulse_result");  //得到新Activity關閉後返回的數據

            //加數據進圖表
            add_data(requestCode,pulse_result,yAxisData_pulse_day_interval);

            //一天結束時
            average_data();
        }

        set_chart(current_chart_position,current_period_position);

        Log.e("SBP",Integer.toString(SBP_result));
        Log.e("DBP",Integer.toString(DBP_result));
        Log.e("pulse",Integer.toString(pulse_result));

    }

    public Spinner set_spinner(Spinner sp) {
        Spinner spinner = sp;
        spinner = (Spinner) getView().findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> period_list = ArrayAdapter.createFromResource(getActivity(),
                R.array.choose_period,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(period_list);
        return spinner;
    }

    private void spinner_listener(Spinner spinner) {
        final String[] spinner_list = {"按 天 檢視", "按 週 檢視", "按 月 檢視", "按 半年 檢視"};
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), "click " + spinner_list[position], Toast.LENGTH_SHORT).show();
                if (position == 0)  //指標:一天
                {
                    current_period_position = 0;
                } else if (position == 1)  //指標:一週
                {
                    current_period_position = 1;
                } else if (position == 2)  //指標:一月
                {
                    current_period_position = 2;
                } else if (position == 3)  //指標:6個月
                {
                    current_period_position = 3;
                }

                set_chart(current_chart_position, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public int[] set_yAxis_reference_max(int position, int max) {
        int xAxisNumber = 0;
        if (position == 0)
            xAxisNumber = 4;
        else if (position == 1)
            xAxisNumber = 7;
        else if (position == 2)
            xAxisNumber = 31;
        else if (position == 3)
            xAxisNumber = 6;
        //參考值上限
        int[] reference_max = new int[xAxisNumber];
        for (int i = 0; i < xAxisNumber; i++)
            reference_max[i] = max;

        return reference_max;
    }

    public int[] set_yAxis_reference_min(int position, int min) {
        int xAxisNumber = 0;
        if (position == 0)
            xAxisNumber = 4;
        else if (position == 1)
            xAxisNumber = 7;
        else if (position == 2)
            xAxisNumber = 31;
        else if (position == 3)
            xAxisNumber = 6;
        //參考值上限
        int[] reference_min = new int[xAxisNumber];
        for (int i = 0; i < xAxisNumber; i++)
            reference_min[i] = min;

        return reference_min;
    }

    public void set_chart(int chart_index, int position)  //chat_index: 0:HbA1c,1:BP ; position為spinner的period_list的指標
    {
        //chart_index = 0
        int[] yAxisData_HbA1c = null;  //HbA1c折線
        int[] yAxisData_HbA1c_reference_max = set_yAxis_reference_max(position, HbA1c_reference_max);  //HbA1c參考值上限
        int[] yAxisData_HbA1c_reference_min = set_yAxis_reference_min(position, HbA1c_reference_min);  //HbA1c參考值下限
        int[] yAxisData_HbA1c_warning = null;  //HbA1c危險值

        //chart_index = 1
        int[] yAxisData_SBP = null;  //SBP折線
        int[] yAxisData_SBP_reference_max = set_yAxis_reference_max(position, SBP_reference_max);  //SBP參考值上限
        int[] yAxisData_SBP_reference_min = set_yAxis_reference_min(position, SBP_reference_min);  //SBP參考值下限
        int[] yAxisData_SBP_warning = null;  //SBP危險值

        int[] yAxisData_DBP = null;  //DBP折線
        int[] yAxisData_DBP_reference_max = set_yAxis_reference_max(position, DBP_reference_max);  //DBP參考值上限
        int[] yAxisData_DBP_reference_min = set_yAxis_reference_min(position, DBP_reference_min);  //DBP參考值下限
        int[] yAxisData_DBP_warning = null;  //DBP危險值

        //chart_index = 2
        int[] yAxisData_pulse = null;  //pulse折線
        int[] yAxisData_pulse_reference_max = set_yAxis_reference_max(position, pulse_reference_max);  //pulse參考值上限
        int[] yAxisData_pulse_reference_min = set_yAxis_reference_min(position, pulse_reference_min);  //pulse參考值下限
        int[] yAxisData_pulse_warning = null;  //pulse危險值

        String[] xAxisData = null;  //座標軸顯示值

        int yAxisData_max = 0;
        int yAxisData_min = 0;

        if (position == 0) {

            String[] xAxisData_day_interval = {"morning", "noon", "evening", "night"};
            xAxisData = xAxisData_day_interval;


            if(chart_index == 0) {
                current = current_day_interval[0];

                //HbA1c_day_interval
                yAxisData_HbA1c = yAxisData_HbA1c_day_interval;
                //HbA1c warning
                int[] yAxisData_HbA1c_warning_day_interval = new int[4];
                for (int i = 0; i < 4; i++)
                    if ((yAxisData_HbA1c_day_interval[i] > HbA1c_reference_max || yAxisData_HbA1c_day_interval[i] < HbA1c_reference_min) && i < current)
                        yAxisData_HbA1c_warning_day_interval[i] = yAxisData_HbA1c_day_interval[i];
                    else
                        yAxisData_HbA1c_warning_day_interval[i] = infinity;  //還沒紀錄的點
                yAxisData_HbA1c_warning = yAxisData_HbA1c_warning_day_interval;
            }

            else if(chart_index == 1) {
                current = current_day_interval[1];

                //SBP_day_interval
                yAxisData_SBP = yAxisData_SBP_day_interval;
                //SBP warning
                int[] yAxisData_SBP_warning_day_interval = new int[4];
                for (int i = 0; i < 4; i++)
                    if ((yAxisData_SBP_day_interval[i] > SBP_reference_max || yAxisData_SBP_day_interval[i] < SBP_reference_min) && i < current)
                        yAxisData_SBP_warning_day_interval[i] = yAxisData_SBP_day_interval[i];
                    else
                        yAxisData_SBP_warning_day_interval[i] = infinity;
                yAxisData_SBP_warning = yAxisData_SBP_warning_day_interval;


                //DBP_day_interval
                yAxisData_DBP = yAxisData_DBP_day_interval;
                //DBP warning
                int[] yAxisData_DBP_warning_day_interval = new int[4];
                for (int i = 0; i < 4; i++)
                    if ((yAxisData_DBP_day_interval[i] > DBP_reference_max || yAxisData_DBP_day_interval[i] < DBP_reference_min) && i < current)
                        yAxisData_DBP_warning_day_interval[i] = yAxisData_DBP_day_interval[i];
                    else
                        yAxisData_DBP_warning_day_interval[i] = infinity;
                yAxisData_DBP_warning = yAxisData_DBP_warning_day_interval;
            }

            else if(chart_index == 2) {
                current = current_day_interval[3];

                //pulse_day_interval
                yAxisData_pulse = yAxisData_pulse_day_interval;
                //pulse warning
                int[] yAxisData_pulse_warning_day_interval = new int[4];
                for (int i = 0; i < 4; i++)
                    if ((yAxisData_pulse_day_interval[i] > pulse_reference_max || yAxisData_pulse_day_interval[i] < pulse_reference_min) && i < current)
                        yAxisData_pulse_warning_day_interval[i] = yAxisData_pulse_day_interval[i];
                    else
                        yAxisData_pulse_warning_day_interval[i] = infinity;  //還沒紀錄的點
                yAxisData_pulse_warning = yAxisData_pulse_warning_day_interval;
            }
        }

        if (position == 1) {
            current = current_day;
            xAxisData = week;

            //HbA1c_week
            yAxisData_HbA1c = yAxisData_HbA1c_week;
            //HbA1c warning
            int[] yAxisData_HbA1c_warning_week = new int[7];
            for (int i = 0; i < 7; i++)
                if ((yAxisData_HbA1c_week[i] > HbA1c_reference_max || yAxisData_HbA1c_week[i] < HbA1c_reference_min) && i < current)
                    yAxisData_HbA1c_warning_week[i] = yAxisData_HbA1c_week[i];
                else
                    yAxisData_HbA1c_warning_week[i] = infinity;
            yAxisData_HbA1c_warning = yAxisData_HbA1c_warning_week;


            //SBP_week
            yAxisData_SBP = yAxisData_SBP_week;
            //SBP warning
            int[] yAxisData_warning_week = new int[7];
            for (int i = 0; i < 7; i++)
                if ((yAxisData_SBP_week[i] > SBP_reference_max || yAxisData_SBP_week[i] < SBP_reference_min) && i < current)
                    yAxisData_warning_week[i] = yAxisData_SBP_week[i];
                else
                    yAxisData_warning_week[i] = infinity;
            yAxisData_SBP_warning = yAxisData_warning_week;


            //DBP_week
            yAxisData_DBP = yAxisData_DBP_week;
            //DBP warning
            int[] yAxisData_DBP_warning_week = new int[7];
            for (int i = 0; i < 7; i++)
                if ((yAxisData_DBP_week[i] > DBP_reference_max || yAxisData_DBP_week[i] < DBP_reference_min) && i < current)
                    yAxisData_DBP_warning_week[i] = yAxisData_DBP_week[i];
                else
                    yAxisData_DBP_warning_week[i] = infinity;
            yAxisData_DBP_warning = yAxisData_DBP_warning_week;


            //pulse_week
            yAxisData_pulse = yAxisData_pulse_week;
            //HbA1c warning
            int[] yAxisData_pulse_warning_week = new int[7];
            for (int i = 0; i < 7; i++)
                if ((yAxisData_pulse_week[i] > pulse_reference_max || yAxisData_pulse_week[i] < pulse_reference_min) && i < current)
                    yAxisData_pulse_warning_week[i] = yAxisData_pulse_week[i];
                else
                    yAxisData_pulse_warning_week[i] = infinity;
            yAxisData_pulse_warning = yAxisData_pulse_warning_week;
        }

        if (position == 2) {
            current = current_date;


            String[] xAxisData_month = new String[30];
            for (int i = 0; i < 30; i++)
                xAxisData_month[i] = Integer.toString(i + 1);
            xAxisData = xAxisData_month;


            //HbA1c_month
            yAxisData_HbA1c = yAxisData_HbA1c_month;
            //HbA1c warning
            int[] yAxisData_HbA1c_warning_month = new int[30];
            for (int i = 0; i < 30; i++)
                if ((yAxisData_HbA1c_month[i] > HbA1c_reference_max || yAxisData_HbA1c_month[i] < HbA1c_reference_min) && i < current)
                    yAxisData_HbA1c_warning_month[i] = yAxisData_HbA1c_month[i];
                else
                    yAxisData_HbA1c_warning_month[i] = infinity;
            yAxisData_HbA1c_warning = yAxisData_HbA1c_warning_month;


            //SBP_month
            yAxisData_SBP = yAxisData_SBP_month;
            //SBP warning
            int[] yAxisData_warning_month = new int[31];
            for (int i = 0; i < 31; i++)
                if ((yAxisData_SBP_month[i] > SBP_reference_max || yAxisData_SBP_month[i] < SBP_reference_min) && i < current)
                    yAxisData_warning_month[i] = yAxisData_SBP_month[i];
                else
                    yAxisData_warning_month[i] = infinity;
            yAxisData_SBP_warning = yAxisData_warning_month;


            //DBP_month
            yAxisData_DBP = yAxisData_DBP_month;
            //DBP warning
            int[] yAxisData_DBP_warning_month = new int[31];
            for (int i = 0; i < 31; i++)
                if ((yAxisData_DBP_month[i] > DBP_reference_max || yAxisData_DBP_month[i] < DBP_reference_min) && i < current)
                    yAxisData_DBP_warning_month[i] = yAxisData_DBP_month[i];
                else
                    yAxisData_DBP_warning_month[i] = infinity;
            yAxisData_DBP_warning = yAxisData_DBP_warning_month;


            //pulse_month
            yAxisData_pulse = yAxisData_pulse_month;
            //HbA1c warning
            int[] yAxisData_pulse_warning_month = new int[30];
            for (int i = 0; i < 30; i++)
                if ((yAxisData_pulse_month[i] > pulse_reference_max || yAxisData_pulse_month[i] < pulse_reference_min) && i < current)
                    yAxisData_pulse_warning_month[i] = yAxisData_pulse_month[i];
                else
                    yAxisData_pulse_warning_month[i] = infinity;
            yAxisData_pulse_warning = yAxisData_pulse_warning_month;
        }

        if (position == 3) {
            current = 6;


            //調整為當月起的前6月
            String[] xAxisData_6months = new String[6];
            int month_pos = current_month - 5;
            for (int i = 0; i < 6; i++) {
                if (month_pos < 0)
                    month_pos += 12;
                else if (month_pos >= 12)
                    month_pos -= 12;
                xAxisData_6months[i] = month[month_pos];
                month_pos++;
            }
            xAxisData = xAxisData_6months;


            //HbA1c_6months
            yAxisData_HbA1c = yAxisData_HbA1c_6months;
            //HbA1c warning
            int[] yAxisData_HbA1c_warning_6months = new int[6];
            for (int i = 0; i < 6; i++)
                if (yAxisData_HbA1c_6months[i] > HbA1c_reference_max || yAxisData_HbA1c_6months[i] < HbA1c_reference_min)
                    yAxisData_HbA1c_warning_6months[i] = yAxisData_HbA1c_6months[i];
                else
                    yAxisData_HbA1c_warning_6months[i] = infinity;
            yAxisData_HbA1c_warning = yAxisData_HbA1c_warning_6months;


            //SBP_6months
            yAxisData_SBP = yAxisData_SBP_6months;
            //SBP warning
            int[] yAxisData_warning_6months = new int[6];
            for (int i = 0; i < 6; i++)
                if (yAxisData_SBP_6months[i] > SBP_reference_max || yAxisData_SBP_6months[i] < SBP_reference_min)
                    yAxisData_warning_6months[i] = yAxisData_SBP_6months[i];
                else
                    yAxisData_warning_6months[i] = infinity;
            yAxisData_SBP_warning = yAxisData_warning_6months;


            //DBP_6months
            yAxisData_DBP = yAxisData_DBP_6months;
            //DBP warning
            int[] yAxisData_DBP_warning_6months = new int[6];
            for (int i = 0; i < 6; i++)
                if (yAxisData_DBP_6months[i] > DBP_reference_max || yAxisData_DBP_6months[i] < DBP_reference_min)
                    yAxisData_DBP_warning_6months[i] = yAxisData_DBP_6months[i];
                else
                    yAxisData_DBP_warning_6months[i] = infinity;
            yAxisData_DBP_warning = yAxisData_DBP_warning_6months;


            //pulse_6months
            yAxisData_pulse = yAxisData_pulse_6months;
            //HbA1c warning
            int[] yAxisData_pulse_warning_6months = new int[6];
            for (int i = 0; i < 6; i++)
                if (yAxisData_pulse_6months[i] > pulse_reference_max || yAxisData_pulse_6months[i] < pulse_reference_min)
                    yAxisData_pulse_warning_6months[i] = yAxisData_pulse_6months[i];
                else
                    yAxisData_pulse_warning_6months[i] = infinity;
            yAxisData_pulse_warning = yAxisData_pulse_warning_6months;
        }

        lineChartView = getView().findViewById(R.id.chart);

        //將陣列中的String儲存在X軸顯示
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        for (int i = 0; i < xAxisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(xAxisData[i]));
        }

        //將陣列裡的y值儲存在yAxisValues
        List<PointValue> yAxisValues_item1 = new ArrayList<PointValue>();
        List<PointValue> yAxisValues_item1_reference_max = new ArrayList<PointValue>();
        List<PointValue> yAxisValues_item1_reference_min = new ArrayList<PointValue>();
        List<PointValue> yAxisValues_item1_warning = new ArrayList<PointValue>();

        List<PointValue> yAxisValues_item2 = new ArrayList<PointValue>();
        List<PointValue> yAxisValues_item2_reference_max = new ArrayList<PointValue>();
        List<PointValue> yAxisValues_item2_reference_min = new ArrayList<PointValue>();
        List<PointValue> yAxisValues_item2_warning = new ArrayList<PointValue>();

        if (chart_index == 0)  //HbA1c chart
        {
            for (int i = 0; i < current; i++)
                yAxisValues_item1.add(new PointValue(i, yAxisData_HbA1c[i]));
            for (int i = 0; i < yAxisData_HbA1c_reference_max.length; i++)
                yAxisValues_item1_reference_max.add(new PointValue(i, yAxisData_HbA1c_reference_max[i]));
            for (int i = 0; i < yAxisData_HbA1c_reference_min.length; i++)
                yAxisValues_item1_reference_min.add(new PointValue(i, yAxisData_HbA1c_reference_min[i]));
            for (int i = 0; i < xAxisData.length; i++)
                yAxisValues_item1_warning.add(new PointValue(i, yAxisData_HbA1c_warning[i]));
        }

        else if (chart_index == 1)  //BP chart
        {
            //SBP
            for (int i = 0; i < current; i++)
                yAxisValues_item1.add(new PointValue(i, yAxisData_SBP[i]));
            for (int i = 0; i < yAxisData_SBP_reference_max.length; i++)
                yAxisValues_item1_reference_max.add(new PointValue(i, yAxisData_SBP_reference_max[i]));
            for (int i = 0; i < yAxisData_SBP_reference_min.length; i++)
                yAxisValues_item1_reference_min.add(new PointValue(i, yAxisData_SBP_reference_min[i]));
            for (int i = 0; i < xAxisData.length; i++)
                yAxisValues_item1_warning.add(new PointValue(i, yAxisData_SBP_warning[i]));

            //DBP
            for (int i = 0; i < current; i++)
                yAxisValues_item2.add(new PointValue(i, yAxisData_DBP[i]));
            for (int i = 0; i < yAxisData_DBP_reference_max.length; i++)
                yAxisValues_item2_reference_max.add(new PointValue(i, yAxisData_DBP_reference_max[i]));
            for (int i = 0; i < yAxisData_DBP_reference_min.length; i++)
                yAxisValues_item2_reference_min.add(new PointValue(i, yAxisData_DBP_reference_min[i]));
            for (int i = 0; i < xAxisData.length; i++)
                yAxisValues_item2_warning.add(new PointValue(i, yAxisData_DBP_warning[i]));
        }

        else if (chart_index == 2)  //pulse chart
        {
            //pulse
            for (int i = 0; i < current; i++)
                yAxisValues_item1.add(new PointValue(i, yAxisData_pulse[i]));
            for (int i = 0; i < yAxisData_pulse_reference_max.length; i++)
                yAxisValues_item1_reference_max.add(new PointValue(i, yAxisData_pulse_reference_max[i]));
            for (int i = 0; i < yAxisData_pulse_reference_min.length; i++)
                yAxisValues_item1_reference_min.add(new PointValue(i, yAxisData_pulse_reference_min[i]));
            for (int i = 0; i < xAxisData.length; i++)
                yAxisValues_item1_warning.add(new PointValue(i, yAxisData_pulse_warning[i]));
        }
        //多條線集合
        List<Line> lines = new ArrayList<Line>();

        //宣告新線並作客製化
        Line line_item1 = null;
        Line line_item1_reference_max = null;
        Line line_item1_reference_min = null;
        Line line_item1_warning = null;
        Line line_item2 = null;
        Line line_item2_reference_max = null;
        Line line_item2_reference_min = null;
        Line line_item2_warning = null;

        if (chart_index == 0)  //HbA1c chart
        {
            //HbA1c折線
            line_item1 = new Line(yAxisValues_item1).setColor(Color.parseColor("#0000FF"));
            line_item1.setCubic(true);  //平滑曲線
            line_item1.setHasLabelsOnlyForSelected(true);

            //HbA1c參考值上限
            line_item1_reference_max = new Line(yAxisValues_item1_reference_max).setColor(Color.parseColor("#B7B7B7"));
            line_item1_reference_max.setFilled(false);
            line_item1_reference_max.setHasPoints(false);

            //HbA1c參考值下限
            line_item1_reference_min = new Line(yAxisValues_item1_reference_min).setColor(Color.parseColor("#B7B7B7"));
            line_item1_reference_min.setFilled(false);
            line_item1_reference_min.setHasPoints(false);

            //HbA1c危險值
            line_item1_warning = new Line(yAxisValues_item1_warning).setColor(Color.parseColor("#FF0000"));
            line_item1_warning.setHasPoints(true);
            line_item1_warning.setHasLabels(true);
            line_item1_warning.setHasLines(false);
            line_item1_warning.setFilled(false);
            line_item1_warning.setHasPoints(true);
        }

        else if (chart_index == 1)  //BP chart
        {
            //SBP折線
            line_item1 = new Line(yAxisValues_item1).setColor(Color.parseColor("#9C27B0"));
            line_item1.setCubic(true);  //平滑曲線
            line_item1.setHasLabelsOnlyForSelected(true);
            //SBP參考值上限
            line_item1_reference_max = new Line(yAxisValues_item1_reference_max).setColor(Color.parseColor("#B7B7B7"));
            line_item1_reference_max.setFilled(false);
            line_item1_reference_max.setHasPoints(false);
            //SBP參考值下限
            line_item1_reference_min = new Line(yAxisValues_item1_reference_min).setColor(Color.parseColor("#B7B7B7"));
            line_item1_reference_min.setFilled(false);
            line_item1_reference_min.setHasPoints(false);
            //SBP危險值
            line_item1_warning = new Line(yAxisValues_item1_warning).setColor(Color.parseColor("#FF0000"));
            line_item1_warning.setHasPoints(true);
            line_item1_warning.setHasLabels(true);
            line_item1_warning.setHasLines(false);
            line_item1_warning.setFilled(false);
            line_item1_warning.setHasPoints(true);

            //DBP折線
            line_item2 = new Line(yAxisValues_item2).setColor(Color.parseColor("#32CC32"));
            line_item2.setCubic(true);  //平滑曲線
            line_item2.setHasLabelsOnlyForSelected(true);
            //DBP參考值上限
            line_item2_reference_max = new Line(yAxisValues_item2_reference_max).setColor(Color.parseColor("#B7B7B7"));
            line_item2_reference_max.setFilled(false);
            line_item2_reference_max.setHasPoints(false);
            //DBP參考值下限
            line_item2_reference_min = new Line(yAxisValues_item2_reference_min).setColor(Color.parseColor("#B7B7B7"));
            line_item2_reference_min.setFilled(false);
            line_item2_reference_min.setHasPoints(false);
            //DBP危險值
            line_item2_warning = new Line(yAxisValues_item2_warning).setColor(Color.parseColor("#FF0000"));
            line_item2_warning.setHasPoints(true);
            line_item2_warning.setHasLabels(true);
            line_item2_warning.setHasLines(false);
            line_item2_warning.setFilled(false);
            line_item2_warning.setHasPoints(true);
        }

        else if (chart_index == 2)  //pulse chart
        {
            //pulse折線
            line_item1 = new Line(yAxisValues_item1).setColor(Color.parseColor("#808000"));
            line_item1.setCubic(true);  //平滑曲線
            line_item1.setHasLabelsOnlyForSelected(true);

            //pulse參考值上限
            line_item1_reference_max = new Line(yAxisValues_item1_reference_max).setColor(Color.parseColor("#B7B7B7"));
            line_item1_reference_max.setFilled(false);
            line_item1_reference_max.setHasPoints(false);

            //pulse參考值下限
            line_item1_reference_min = new Line(yAxisValues_item1_reference_min).setColor(Color.parseColor("#B7B7B7"));
            line_item1_reference_min.setFilled(false);
            line_item1_reference_min.setHasPoints(false);

            //pulse危險值
            line_item1_warning = new Line(yAxisValues_item1_warning).setColor(Color.parseColor("#FF0000"));
            line_item1_warning.setHasPoints(true);
            line_item1_warning.setHasLabels(true);
            line_item1_warning.setHasLines(false);
            line_item1_warning.setFilled(false);
            line_item1_warning.setHasPoints(true);
        }

        //將線加入lines(線集合)
        if (chart_index == 0)  //HbA1c chart
        {
            lines.add(line_item1);
            lines.add(line_item1_reference_max);
            lines.add(line_item1_reference_min);
            lines.add(line_item1_warning);
        }

        else if (chart_index == 1)  //BP chart
        {
            lines.add(line_item1);
            lines.add(line_item1_reference_max);
            lines.add(line_item1_reference_min);
            lines.add(line_item1_warning);

            lines.add(line_item2);
            lines.add(line_item2_reference_max);
            lines.add(line_item2_reference_min);
            lines.add(line_item2_warning);
        }

        else if (chart_index == 2)  //pulse chart
        {
            lines.add(line_item1);
            lines.add(line_item1_reference_max);
            lines.add(line_item1_reference_min);
            lines.add(line_item1_warning);
        }

        LineChartData data = new LineChartData();
        data.setLines(lines);

        lineChartView.setLineChartData(data);

        //X座標軸
        Axis xAxis = new Axis();
        xAxis.setValues(axisValues);
        xAxis.setTextSize(16);
        xAxis.setTextColor(Color.parseColor("#03A9F4"));
        data.setAxisXBottom(xAxis);

        //Y座標軸
        Axis yAxis = new Axis();
        yAxis.setName("mm-Hg");
        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);


        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());

        //縱向排版
        if (chart_index == 0) {
            for (int i = 0; i < current; i++) {
                if (i == 0) {
                    yAxisData_max = yAxisData_HbA1c[i];
                    yAxisData_min = yAxisData_HbA1c[i];
                }
                if (yAxisData_HbA1c[i] >= yAxisData_max)
                    yAxisData_max = yAxisData_HbA1c[i];
                if (yAxisData_HbA1c[i] <= yAxisData_min)
                    yAxisData_min = yAxisData_HbA1c[i];
            }
            if (yAxisData_max >= 150)
                viewport.top = yAxisData_max + 50;
            else
                viewport.top = 150;  //default
            if (yAxisData_min <= 0)
                viewport.bottom = yAxisData_min - 50;
            else
                viewport.bottom = -10;  //default
            Log.e("yAxisData_max", Integer.toString(yAxisData_max) + "");
            Log.e("yAxisData_min", Integer.toString(yAxisData_min) + "");
        }

        else if (chart_index == 1) {
            for (int i = 0; i < yAxisData_SBP.length; i++) {
                if (i == 0) {
                    yAxisData_max = yAxisData_SBP[i];
                    yAxisData_min = yAxisData_SBP[i];
                }
                if (yAxisData_SBP[i] >= yAxisData_max)
                    yAxisData_max = yAxisData_SBP[i];
                if (yAxisData_SBP[i] <= yAxisData_min)
                    yAxisData_min = yAxisData_SBP[i];
            }
            for (int i = 0; i < yAxisData_DBP.length; i++) {
                if (yAxisData_DBP[i] >= yAxisData_max)
                    yAxisData_max = yAxisData_DBP[i];
                if (yAxisData_DBP[i] <= yAxisData_min)
                    yAxisData_min = yAxisData_DBP[i];
            }
            if (yAxisData_max >= 150)
                viewport.top = yAxisData_max + 50;
            else
                viewport.top = 150;  //default
            if (yAxisData_min <= 0)
                viewport.bottom = yAxisData_min - 50;
            else
                viewport.bottom = 0;  //default
            Log.e("yAxisData_max", Integer.toString(yAxisData_max) + "");
            Log.e("yAxisData_min", Integer.toString(yAxisData_min) + "");
        }

        else if (chart_index == 2) {
            for (int i = 0; i < current; i++) {
                if (i == 0) {
                    yAxisData_max = yAxisData_pulse[i];
                    yAxisData_min = yAxisData_pulse[i];
                }
                if (yAxisData_pulse[i] >= yAxisData_max)
                    yAxisData_max = yAxisData_pulse[i];
                if (yAxisData_pulse[i] <= yAxisData_min)
                    yAxisData_min = yAxisData_pulse[i];
            }
            if (yAxisData_max >= 150)
                viewport.top = yAxisData_max + 50;
            else
                viewport.top = 150;  //default
            if (yAxisData_min <= 0)
                viewport.bottom = yAxisData_min - 50;
            else
                viewport.bottom = -10;  //default
            Log.e("yAxisData_max", Integer.toString(yAxisData_max) + "");
            Log.e("yAxisData_min", Integer.toString(yAxisData_min) + "");
        }

        //橫向排版
        if (position == 0)
            viewport.left = (float) (-0.5);
        if (position == 1)
            viewport.left = -1;
        if (position == 2)
            viewport.left = -5;
        if (position == 3)
            viewport.left = -1;

        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);


        lineChartView.setOnValueTouchListener(new LineChartOnValueSelectListener() {

            @Override
            public void onValueDeselected() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onValueSelected(int arg0, int arg1, PointValue arg2) {
                // TODO Auto-generated method stub
                Log.d("click point", arg2.getY() + "");
                //line.setHasLabels(true);
            }
        });
    }

}
