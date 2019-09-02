package com.example.codingxproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.codingxproject.DataRecord.DataRecord_BloodPressure_DBP;
import com.example.codingxproject.DataRecord.DataRecord_BloodPressure_SBP;
import com.example.codingxproject.DataRecord.DataRecord_BloodSugar;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class DataReview extends AppCompatActivity {

    private boolean is_choose_HbA1c = true;
    private boolean is_choose_BP = false;
    String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
    private int current_month = 11;
    private int current_chart_position = 0;  //0:HbA1c ; 1:BP
    private int current_period_position = 0;  //0:day ; 1:week ; 2:month ; 3:6months
    private LineChartView lineChartView;


    //main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_review);

        //set buttons color
        findViewById(R.id.HbA1c).setBackgroundColor(Color.parseColor("#A9A9A9"));
        findViewById(R.id.BP).setBackgroundColor(Color.parseColor("#F5F5F5"));
        findViewById(R.id.add_data).setBackgroundColor(Color.parseColor("#F5F5F5"));

        Spinner spinner = null;
        spinner = set_spinner(spinner);
        spinner_listener(spinner);

        //initial chart view
        set_chart_HbA1c(0);  //set_chart_BP(0) alternately

        //button listener
        Button.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Button button = (Button) findViewById(R.id.add_data);

                if (view.getId() == R.id.HbA1c) {
                    findViewById(R.id.HbA1c).setBackgroundColor(Color.parseColor("#A9A9A9"));
                    findViewById(R.id.BP).setBackgroundColor(Color.parseColor("#F5F5F5"));
                    is_choose_HbA1c = true;
                    is_choose_BP = false;
                    set_chart_HbA1c(current_period_position);
                    current_chart_position = 0;
                    button.setText("新增血糖數據");

                } else if (view.getId() == R.id.BP) {
                    findViewById(R.id.HbA1c).setBackgroundColor(Color.parseColor("#F5F5F5"));
                    findViewById(R.id.BP).setBackgroundColor(Color.parseColor("#A9A9A9"));
                    is_choose_HbA1c = false;
                    is_choose_BP = true;
                    set_chart_BP(current_period_position);
                    current_chart_position = 1;
                    button.setText("新增血壓數據");
                } else if (view.getId() == R.id.add_data) {
                    if (is_choose_HbA1c == true)
                        intent = new Intent(DataReview.this, DataRecord_BloodSugar.class);
                    else if (is_choose_BP == true)
                        intent = new Intent(DataReview.this, DataRecord_BloodPressure_SBP.class);

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    DataReview.this.startActivity(intent);
                }
            }
        };
        findViewById(R.id.HbA1c).setOnClickListener(listener);
        findViewById(R.id.BP).setOnClickListener(listener);
        findViewById(R.id.add_data).setOnClickListener(listener);
    }


    public Spinner set_spinner(Spinner sp) {
        Spinner spinner = sp;
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> period_list = ArrayAdapter.createFromResource(DataReview.this,
                R.array.choose_period,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(period_list);
        return spinner;
    }

    private void spinner_listener(Spinner spinner) {
        final String[] spinner_list = {"一天", "一週", "一月", "6個月"};
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DataReview.this, "click " + spinner_list[position], Toast.LENGTH_SHORT).show();
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

                if (current_chart_position == 0)  //HbA1c
                    set_chart_HbA1c(position);
                else if (current_chart_position == 1)  //BP
                    set_chart_BP(position);
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

    public void set_chart_HbA1c(int position)  //position為spinner的period_list的指標
    {
        int[] yAxisData_HbA1c = null;  //HbA1c折線
        int[] yAxisData_HbA1c_reference_max = set_yAxis_reference_max(position, 110);  //HbA1c參考值上限
        int[] yAxisData_HbA1c_reference_min = set_yAxis_reference_min(position, 70);  //HbA1c參考值下限
        int[] yAxisData_HbA1c_warning = null;  //HbA1c危險值
        String[] xAxisData = null;  //座標軸顯示值
        int yAxisData_max = 0;
        int yAxisData_min = 0;

        if (position == 0) {
            String[] axisData_day = {"morning", "noon", "evening", "night"};
            xAxisData = axisData_day;

            int[] yAxisData_HbA1c_day = {83, 120, 84, 50};
            yAxisData_HbA1c = yAxisData_HbA1c_day;

            //HbA1c warning
            int[] yAxisData_HbA1c_warning_day = new int[4];
            for(int i=0;i<4;i++)
                if(yAxisData_HbA1c_day[i]>110||yAxisData_HbA1c_day[i]<70)
                    yAxisData_HbA1c_warning_day[i] = yAxisData_HbA1c_day[i];
                else
                    yAxisData_HbA1c_warning_day[i] = -1000;
            yAxisData_HbA1c_warning = yAxisData_HbA1c_warning_day;
        }

        if (position == 1) {
            String[] axisData_week = {"Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat"};
            xAxisData = axisData_week;

            int[] yAxisData_HbA1c_week = {80, 81, 82, 83, 84, 85, 85};
            yAxisData_HbA1c = yAxisData_HbA1c_week;

            //HbA1c warning
            int[] yAxisData_HbA1c_warning_week = new int[7];
            for(int i=0;i<7;i++)
                if(yAxisData_HbA1c_week[i]>110||yAxisData_HbA1c_week[i]<70)
                    yAxisData_HbA1c_warning_week[i] = yAxisData_HbA1c_week[i];
                else
                    yAxisData_HbA1c_warning_week[i] = -1000;
            yAxisData_HbA1c_warning = yAxisData_HbA1c_warning_week;
        }

        if (position == 2) {
            String[] axisData_month = new String[31];
            for (int i = 0; i < 31; i++)
                axisData_month[i] = Integer.toString(i + 1);
            xAxisData = axisData_month;

            int[] yAxisData_HbA1c_month = {83, 85, 84, 83, 83, 86, 85, 82, 83, 83, 82, 82, 85, 83, 81, 82, 82, 81, 80, 82, 82, 85, 81, 82, 85, 84, 83, 83, 80, 84, 85};
            yAxisData_HbA1c = yAxisData_HbA1c_month;

            //HbA1c warning
            int[] yAxisData_HbA1c_warning_month = new int[31];
            for(int i=0;i<31;i++)
                if(yAxisData_HbA1c_month[i]>110||yAxisData_HbA1c_month[i]<70)
                    yAxisData_HbA1c_warning_month[i] = yAxisData_HbA1c_month[i];
                else
                    yAxisData_HbA1c_warning_month[i] = -1000;
            yAxisData_HbA1c_warning = yAxisData_HbA1c_warning_month;
        }

        if (position == 3) {
            //調整為當月起的前6月
            String[] axisData_6months = new String[6];
            int month_pos = current_month - 5;
            for (int i = 0; i < 6; i++) {
                if (month_pos < 0)
                    month_pos += 12;
                else if (month_pos >= 12)
                    month_pos -= 12;
                axisData_6months[i] = month[month_pos];
                month_pos++;
            }
            xAxisData = axisData_6months;

            int[] yAxisData_HbA1c_6months = {83, 85, 84, 83, 83, 86};
            yAxisData_HbA1c = yAxisData_HbA1c_6months;

            //HbA1c warning
            int[] yAxisData_HbA1c_warning_6months= new int[6];
            for(int i=0;i<6;i++)
                if(yAxisData_HbA1c_6months[i]>110||yAxisData_HbA1c_6months[i]<70)
                    yAxisData_HbA1c_warning_6months[i] = yAxisData_HbA1c_6months[i];
                else
                    yAxisData_HbA1c_warning_6months[i] = -1000;
            yAxisData_HbA1c_warning = yAxisData_HbA1c_warning_6months;
        }

        lineChartView = findViewById(R.id.chart);

        //將陣列中的String儲存在X軸顯示
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        for (int i = 0; i < xAxisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(xAxisData[i]));
        }

        //將陣列裡的y值儲存在yAxisValues
        List<PointValue> yAxisValues_HbA1c = new ArrayList<PointValue>();
        for (int i = 0; i < yAxisData_HbA1c.length; i++) {
            yAxisValues_HbA1c.add(new PointValue(i, yAxisData_HbA1c[i]));
        }
        List<PointValue> yAxisValues_HbA1c_reference_max = new ArrayList<PointValue>();
        for (int i = 0; i < yAxisData_HbA1c_reference_max.length; i++) {
            yAxisValues_HbA1c_reference_max.add(new PointValue(i, yAxisData_HbA1c_reference_max[i]));
        }
        List<PointValue> yAxisValues_HbA1c_reference_min = new ArrayList<PointValue>();
        for (int i = 0; i < yAxisData_HbA1c_reference_min.length; i++) {
            yAxisValues_HbA1c_reference_min.add(new PointValue(i, yAxisData_HbA1c_reference_min[i]));
        }
        List<PointValue> yAxisValues_HbA1c_warning = new ArrayList<PointValue>();
        for (int i = 0; i < xAxisData.length; i++) {
            yAxisValues_HbA1c_warning.add(new PointValue(i, yAxisData_HbA1c_warning[i]));
        }

        //多條線集合
        List<Line> lines = new ArrayList<Line>();

        //宣告新線並作客製化

        //HbA1c折線
        Line line_HbA1c = new Line(yAxisValues_HbA1c).setColor(Color.parseColor("#0000FF"));
        line_HbA1c.setCubic(true);  //平滑曲線
        line_HbA1c.setHasLabelsOnlyForSelected(true);

        //HbA1c參考值上限
        Line HbA1c_reference_max_line = new Line(yAxisValues_HbA1c_reference_max).setColor(Color.parseColor("#B7B7B7"));
        HbA1c_reference_max_line.setFilled(false);
        HbA1c_reference_max_line.setHasPoints(false);

        //HbA1c參考值下限
        Line HbA1c_reference_min_line = new Line(yAxisValues_HbA1c_reference_min).setColor(Color.parseColor("#B7B7B7"));
        HbA1c_reference_min_line.setFilled(false);
        HbA1c_reference_min_line.setHasPoints(false);

        //HbA1c危險值
        Line line_HbA1c_warning = new Line(yAxisValues_HbA1c_warning).setColor(Color.parseColor("#FF0000"));
        line_HbA1c_warning.setHasPoints(true);
        line_HbA1c_warning.setHasLabels(true);
        line_HbA1c_warning.setHasLines(false);
        line_HbA1c_warning.setFilled(false);
        line_HbA1c_warning.setHasPoints(true);

        //將線加入lines(線集合)
        lines.add(line_HbA1c);
        lines.add(HbA1c_reference_max_line);
        lines.add(HbA1c_reference_min_line);
        lines.add(line_HbA1c_warning);

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

        //排版
        for(int i=0;i<yAxisData_HbA1c.length;i++) {
            if (i == 0) {
                yAxisData_max = yAxisData_HbA1c[i];
                yAxisData_min = yAxisData_HbA1c[i];
            }
            if(yAxisData_HbA1c[i]>=yAxisData_max)
                yAxisData_max = yAxisData_HbA1c[i];
            if(yAxisData_HbA1c[i]<=yAxisData_min)
                yAxisData_min = yAxisData_HbA1c[i];
        }
        if(yAxisData_max>=150)
            viewport.top = yAxisData_max+50;
        else
            viewport.top = 150;  //default
        if(yAxisData_min<=0)
            viewport.bottom = yAxisData_min-50;
        else
            viewport.bottom = 0;  //default
        Log.e("yAxisData_max",Integer.toString(yAxisData_max)+"");
        Log.e("yAxisData_min",Integer.toString(yAxisData_min)+"");

        //排版
        if(position == 0)
            viewport.left=(float)(-0.5);
        if(position == 1)
            viewport.left=-1;
        if(position == 2)
            viewport.left=-5;
        if(position == 3)
            viewport.left=-1;

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

    public void set_chart_BP(int position)  //position為spinner的period_list的指標
    {
        int[] yAxisData_SBP = null;  //SBP折線
        int[] yAxisData_DBP = null;  //DBP折線
        int[] yAxisData_SBP_reference_max = set_yAxis_reference_max(position, 129);  //SBP參考值上限
        int[] yAxisData_SBP_reference_min = set_yAxis_reference_min(position, 100);  //SBP參考值下限
        int[] yAxisData_DBP_reference_max = set_yAxis_reference_max(position, 85);  //DBP參考值上限
        int[] yAxisData_DBP_reference_min = set_yAxis_reference_min(position, 66);  //DBP參考值下限
        int[] yAxisData_SBP_warning = null;  //SBP危險值
        int[] yAxisData_DBP_warning = null;  //DBP危險值
        String[] xAxisData = null;  //座標軸顯示值

        //排版
        int yAxisData_max = 0;
        int yAxisData_min = 0;

        //一天內資料
        if (position == 0) {
            String[] xAxisData_day = {"morning", "noon", "evening", "night"};
            xAxisData = xAxisData_day;

            int[] yAxisData_SBP_day = {110, 170, 111, 150};
            yAxisData_SBP = yAxisData_SBP_day;

            int[] yAxisData_DBP_day = {83, 91, 84, 97};
            yAxisData_DBP = yAxisData_DBP_day;

            //SBP warning
            int[] yAxisData_SBP_warning_day = new int[4];
            for(int i=0;i<4;i++)
                if(yAxisData_SBP_day[i]>129||yAxisData_SBP_day[i]<100)
                    yAxisData_SBP_warning_day[i] = yAxisData_SBP_day[i];
                else
                    yAxisData_SBP_warning_day[i] = -1000;
            yAxisData_SBP_warning = yAxisData_SBP_warning_day;

            //DBP warning
            int[] yAxisData_DBP_warning_day = new int[4];
            for(int i=0;i<4;i++)
                if(yAxisData_DBP_day[i]>85||yAxisData_DBP_day[i]<66)
                    yAxisData_DBP_warning_day[i] = yAxisData_DBP_day[i];
                else
                    yAxisData_DBP_warning_day[i] = -1000;
            yAxisData_DBP_warning = yAxisData_DBP_warning_day;
        }

        //一週內資料
        if (position == 1) {
            String[] xAxisData_week = {"Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat"};
            xAxisData = xAxisData_week;

            int[] yAxisData_SBP_week = {109, 102, 110, 111, 210, 115, 114};
            yAxisData_SBP = yAxisData_SBP_week;

            int[] yAxisData_DBP_week = {80, 81, 82, 83, 84, -9, 85};
            yAxisData_DBP = yAxisData_DBP_week;

            //SBP warning
            int[] yAxisData_warning_week = new int[7];
            for(int i=0;i<7;i++)
                if(yAxisData_SBP_week[i]>129||yAxisData_SBP_week[i]<100)
                    yAxisData_warning_week[i] = yAxisData_SBP_week[i];
                else
                    yAxisData_warning_week[i] = -1000;
            yAxisData_SBP_warning = yAxisData_warning_week;

            //DBP warning
            int[] yAxisData_DBP_warning_week = new int[7];
            for(int i=0;i<7;i++)
                if(yAxisData_DBP_week[i]>85||yAxisData_DBP_week[i]<66)
                    yAxisData_DBP_warning_week[i] = yAxisData_DBP_week[i];
                else
                    yAxisData_DBP_warning_week[i] = -1000;
            yAxisData_DBP_warning = yAxisData_DBP_warning_week;
        }

        //一月內資料
        if (position == 2) {
            String[] xAxisData_month = new String[31];
            for (int i = 0; i < 31; i++)
                xAxisData_month[i] = Integer.toString(i + 1);
            xAxisData = xAxisData_month;

            int[] yAxisData_SBP_month = {113, 115, 114, 113, 113, 116, 110, 112, 110, 114, 104, 111, 111, 110, 111, 111, 112, 114, 112, 113, 110, 111, 115, 117, 111, 104, 105, 105, 108, 114, 115};
            yAxisData_SBP = yAxisData_SBP_month;

            int[] yAxisData_DBP_month = {83, 85, 84, 83, 83, 86, 85, 82, 83, 83, 82, 82, 85, 83, 81, 82, 82, 81, 80, 82, 82, 85, 81, 82, 85, 84, 83, 83, 80, 84, 85};
            yAxisData_DBP = yAxisData_DBP_month;

            //SBP warning
            int[] yAxisData_warning_month = new int[31];
            for(int i=0;i<31;i++)
                if(yAxisData_SBP_month[i]>129||yAxisData_SBP_month[i]<100)
                    yAxisData_warning_month[i] = yAxisData_SBP_month[i];
                else
                    yAxisData_warning_month[i] = -1000;
            yAxisData_SBP_warning = yAxisData_warning_month;

            //DBP warning
            int[] yAxisData_DBP_warning_month = new int[31];
            for(int i=0;i<31;i++)
                if(yAxisData_DBP_month[i]>85||yAxisData_DBP_month[i]<66)
                    yAxisData_DBP_warning_month[i] = yAxisData_DBP_month[i];
                else
                    yAxisData_DBP_warning_month[i] = -1000;
            yAxisData_DBP_warning = yAxisData_DBP_warning_month;
        }

        //6個月內資料
        if (position == 3) {
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

            int[] yAxisData_SBP_6months = {110, 110, 111, 114, 104, 104};
            yAxisData_SBP = yAxisData_SBP_6months;

            int[] yAxisData_DBP_6months = {83, 85, 84, 83, 83, 86};
            yAxisData_DBP = yAxisData_DBP_6months;

            //SBP warning
            int[] yAxisData_warning_6months = new int[6];
            for(int i=0;i<6;i++)
                if(yAxisData_SBP_6months[i]>129||yAxisData_SBP_6months[i]<100)
                    yAxisData_warning_6months[i] = yAxisData_SBP_6months[i];
                else
                    yAxisData_warning_6months[i] = -1000;
            yAxisData_SBP_warning = yAxisData_warning_6months;

            //DBP warning
            int[] yAxisData_DBP_warning_6months = new int[6];
            for(int i=0;i<6;i++)
                if(yAxisData_DBP_6months[i]>85||yAxisData_DBP_6months[i]<66)
                    yAxisData_DBP_warning_6months[i] = yAxisData_DBP_6months[i];
                else
                    yAxisData_DBP_warning_6months[i] = -1000;
            yAxisData_DBP_warning = yAxisData_DBP_warning_6months;
        }

        lineChartView = findViewById(R.id.chart);

        //將陣列中的String儲存在X軸顯示
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        for (int i = 0; i < xAxisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(xAxisData[i]));
        }

        //將陣列裡的y值儲存在yAxisValues
        List<PointValue> yAxisValues_SBP = new ArrayList<PointValue>();
        for (int i = 0; i < yAxisData_SBP.length; i++) {
            yAxisValues_SBP.add(new PointValue(i, yAxisData_SBP[i]));
        }
        List<PointValue> yAxisValues_DBP = new ArrayList<PointValue>();
        for (int i = 0; i < yAxisData_DBP.length; i++) {
            yAxisValues_DBP.add(new PointValue(i, yAxisData_DBP[i]));
        }
        List<PointValue> yAxisValues_SBP_reference_max = new ArrayList<PointValue>();
        for (int i = 0; i < yAxisData_SBP_reference_max.length; i++) {
            yAxisValues_SBP_reference_max.add(new PointValue(i, yAxisData_SBP_reference_max[i]));
        }
        List<PointValue> yAxisValues_SBP_reference_min = new ArrayList<PointValue>();
        for (int i = 0; i < yAxisData_SBP_reference_min.length; i++) {
            yAxisValues_SBP_reference_min.add(new PointValue(i, yAxisData_SBP_reference_min[i]));
        }
        List<PointValue> yAxisValues_DBP_reference_max = new ArrayList<PointValue>();
        for (int i = 0; i < yAxisData_DBP_reference_max.length; i++) {
            yAxisValues_DBP_reference_max.add(new PointValue(i, yAxisData_DBP_reference_max[i]));
        }
        List<PointValue> yAxisValues_DBP_reference_min = new ArrayList<PointValue>();
        for (int i = 0; i < yAxisData_DBP_reference_min.length; i++) {
            yAxisValues_DBP_reference_min.add(new PointValue(i, yAxisData_DBP_reference_min[i]));
        }
        List<PointValue> yAxisValues_SBP_warning = new ArrayList<PointValue>();
        for (int i = 0; i < xAxisData.length; i++) {
            yAxisValues_SBP_warning.add(new PointValue(i, yAxisData_SBP_warning[i]));
        }
        List<PointValue> yAxisValues_DBP_warning = new ArrayList<PointValue>();
        for (int i = 0; i < xAxisData.length; i++) {
            yAxisValues_DBP_warning.add(new PointValue(i, yAxisData_DBP_warning[i]));
        }

        //多條線集合
        List<Line> lines = new ArrayList<Line>();

        //宣告新線並作客製化

        //SBP折線
        Line line_SBP = new Line(yAxisValues_SBP).setColor(Color.parseColor("#9C27B0"));
        line_SBP.setCubic(true);  //平滑曲線
        line_SBP.setHasLabelsOnlyForSelected(true);

        //DBP折線
        Line line_DBP = new Line(yAxisValues_DBP).setColor(Color.parseColor("#32CC32"));
        line_DBP.setCubic(true);  //平滑曲線
        line_DBP.setHasLabelsOnlyForSelected(true);

        //SBP參考值上限
        Line SBP_reference_max_line = new Line(yAxisValues_SBP_reference_max).setColor(Color.parseColor("#B7B7B7"));
        SBP_reference_max_line.setFilled(false);
        SBP_reference_max_line.setHasPoints(false);

        //SBP參考值下限
        Line SBP_reference_min_line = new Line(yAxisValues_SBP_reference_min).setColor(Color.parseColor("#B7B7B7"));
        SBP_reference_min_line.setFilled(false);
        SBP_reference_min_line.setHasPoints(false);

        //DBP參考值上限
        Line DBP_reference_max_line = new Line(yAxisValues_DBP_reference_max).setColor(Color.parseColor("#B7B7B7"));
        DBP_reference_max_line.setFilled(false);
        DBP_reference_max_line.setHasPoints(false);

        //DBP參考值下限
        Line DBP_reference_min_line = new Line(yAxisValues_DBP_reference_min).setColor(Color.parseColor("#B7B7B7"));
        DBP_reference_min_line.setFilled(false);
        DBP_reference_min_line.setHasPoints(false);

        //SBP危險值
        Line line_SBP_warning = new Line(yAxisValues_SBP_warning).setColor(Color.parseColor("#FF0000"));
        line_SBP_warning.setHasPoints(true);
        line_SBP_warning.setHasLabels(true);
        line_SBP_warning.setHasLines(false);
        line_SBP_warning.setFilled(false);
        line_SBP_warning.setHasPoints(true);

        //DBP危險值
        Line line_DBP_warning = new Line(yAxisValues_DBP_warning).setColor(Color.parseColor("#FF0000"));
        line_DBP_warning.setHasPoints(true);
        line_DBP_warning.setHasLabels(true);
        line_DBP_warning.setHasLines(false);
        line_DBP_warning.setFilled(false);
        line_DBP_warning.setHasPoints(true);

        //將線加入lines(線集合)
        lines.add(line_SBP);
        lines.add(line_DBP);
        lines.add(SBP_reference_max_line);
        lines.add(SBP_reference_min_line);
        lines.add(DBP_reference_max_line);
        lines.add(DBP_reference_min_line);
        lines.add(line_SBP_warning);
        lines.add(line_DBP_warning);

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

        //排版
        for(int i=0;i<yAxisData_SBP.length;i++) {
            if (i == 0) {
                yAxisData_max = yAxisData_SBP[i];
                yAxisData_min = yAxisData_SBP[i];
            }
            if(yAxisData_SBP[i]>=yAxisData_max)
                yAxisData_max = yAxisData_SBP[i];
            if(yAxisData_SBP[i]<=yAxisData_min)
                yAxisData_min = yAxisData_SBP[i];
        }
        for(int i=0;i<yAxisData_DBP.length;i++) {
            if(yAxisData_DBP[i]>=yAxisData_max)
                yAxisData_max = yAxisData_DBP[i];
            if(yAxisData_DBP[i]<=yAxisData_min)
                yAxisData_min = yAxisData_DBP[i];
        }
        if(yAxisData_max>=150)
            viewport.top = yAxisData_max+50;
        else
            viewport.top = 150;  //default
        if(yAxisData_min<=0)
            viewport.bottom = yAxisData_min-50;
        else
            viewport.bottom = 0;  //default
        Log.e("yAxisData_max",Integer.toString(yAxisData_max)+"");
        Log.e("yAxisData_min",Integer.toString(yAxisData_min)+"");

        //排版
        if(position == 0)
            viewport.left=(float)(-0.5);
        if(position == 1)
            viewport.left=-1;
        if(position == 2)
            viewport.left=-5;
        if(position == 3)
            viewport.left=-1;

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
