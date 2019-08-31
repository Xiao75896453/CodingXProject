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

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class DataReview extends AppCompatActivity {

    private boolean is_choose_HbA1c = true;
    private boolean is_choose_SBP = false;
    private boolean is_choose_DBP = false;
    private int current_chart_position = 0;  //0:HbA1c ; 1:SBP ; 2:DBP
    private LineChartView lineChartView;



    //main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_review);

        findViewById(R.id.HbA1c).setBackgroundColor(Color.parseColor("#A9A9A9"));
        findViewById(R.id.DBP).setBackgroundColor(Color.parseColor("#F5F5F5"));
        findViewById(R.id.SBP).setBackgroundColor(Color.parseColor("#F5F5F5"));
        findViewById(R.id.add_data).setBackgroundColor(Color.parseColor("#F5F5F5"));

        Spinner spinner = null;
        spinner = set_spinner(spinner);
        spinner_listener(spinner);

        set_chart_HbA1c(0);  //set_chart_SBP(0);set_chart_DBP(0) alternately

        Button.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                if (view.getId() == R.id.HbA1c) {
                    findViewById(R.id.HbA1c).setBackgroundColor(Color.parseColor("#A9A9A9"));
                    findViewById(R.id.SBP).setBackgroundColor(Color.parseColor("#F5F5F5"));
                    findViewById(R.id.DBP).setBackgroundColor(Color.parseColor("#F5F5F5"));
                    is_choose_HbA1c = true;
                    is_choose_SBP = false;
                    is_choose_DBP = false;
                    set_chart_HbA1c(0);
                    current_chart_position=0;
                } else if (view.getId() == R.id.SBP) {
                    findViewById(R.id.HbA1c).setBackgroundColor(Color.parseColor("#F5F5F5"));
                    findViewById(R.id.SBP).setBackgroundColor(Color.parseColor("#A9A9A9"));
                    findViewById(R.id.DBP).setBackgroundColor(Color.parseColor("#F5F5F5"));
                    is_choose_HbA1c = false;
                    is_choose_SBP = true;
                    is_choose_DBP = false;
                    set_chart_SBP(0);
                    current_chart_position=1;
                } else if (view.getId() == R.id.DBP) {
                    findViewById(R.id.HbA1c).setBackgroundColor(Color.parseColor("#F5F5F5"));
                    findViewById(R.id.SBP).setBackgroundColor(Color.parseColor("#F5F5F5"));
                    findViewById(R.id.DBP).setBackgroundColor(Color.parseColor("#A9A9A9"));
                    is_choose_HbA1c = false;
                    is_choose_SBP = false;
                    is_choose_DBP = true;
                    set_chart_DBP(0);
                    current_chart_position=2;
                }

                else if (view.getId() == R.id.add_data) {
                    if (is_choose_HbA1c == true)
                        intent = new Intent(DataReview.this, DataRecord_BloodSugar.class);
                    else if (is_choose_SBP == true)
                        intent = new Intent(DataReview.this, DataRecord_BloodPressure_SBP.class);
                    else if (is_choose_DBP == true)
                        intent = new Intent(DataReview.this, DataRecord_BloodPressure_DBP.class);

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    DataReview.this.startActivity(intent);
                }
            }
        };
        findViewById(R.id.HbA1c).setOnClickListener(listener);
        findViewById(R.id.SBP).setOnClickListener(listener);
        findViewById(R.id.DBP).setOnClickListener(listener);
        findViewById(R.id.add_data).setOnClickListener(listener);
    }




    public Spinner set_spinner(Spinner sp)
    {
        Spinner spinner = sp;
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> period_list = ArrayAdapter.createFromResource(DataReview.this,
                R.array.choose_period,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(period_list);
        return spinner;
    }

    private void spinner_listener(Spinner spinner) {
        final String[] spinner_list = {"一天","一週","一月","6個月"};
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DataReview.this, "click " + spinner_list[position], Toast.LENGTH_SHORT).show();
                if(position==0)  //指標:一天
                {

                }
                else if(position==1)  //指標:一週
                {

                }
                else if(position==2)  //指標:一月
                {

                }
                else if(position==3)  //指標:6個月
                {

                }

                if(current_chart_position==0)  //HbA1c
                    set_chart_HbA1c(position);
                else if(current_chart_position==1)  //SBP
                    set_chart_SBP(position);
                else if(current_chart_position==2)  //DBP
                    set_chart_DBP(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void set_chart_HbA1c(int position)  //position為spinner的period_list的指標
    {
        int[] yAxisData = null;
        String[] axisData = null;
        int yAxisData_max = 0;
        int yAxisData_min = 0;

        //if(position==0)
        {
            String[] axisData_day = {"morning", "noon", "evening", "night"};
            axisData = axisData_day;

            int[] yAxisData_HbA1c_day = {83, 85, 84, 83};
            yAxisData = yAxisData_HbA1c_day;
        }

        if(position==1) {
            String[] axisData_week = {"Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat"};
            axisData = axisData_week;

            int[] yAxisData_HbA1c_week = {80, 81, 82, 83, 84, 85, 85};
            yAxisData = yAxisData_HbA1c_week;
        }

        if(position==2) {
            String[] axisData_month= new String[31];
            for(int i=0;i<31;i++)
                axisData_month[i]=Integer.toString(i+1);
            axisData = axisData_month;

            int[] yAxisData_HbA1c_month = {83, 85, 84, 83, 83, 86, 85, 82, 83, 83, 82, 82, 85, 83, 81, 82, 82, 81, 80, 82, 82, 85, 81, 82, 85, 84, 83, 83, 80, 84, 85};
            yAxisData = yAxisData_HbA1c_month;
        }

        if(position==3) {
            String[] axisData_6years = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
                    "Oct", "Nov", "Dec"};
            axisData = axisData_6years;

            int[] yAxisData_HbA1c_6years = {83, 85, 84, 83, 83, 86, 85, 82, 83, 83, 82, 82};
            yAxisData = yAxisData_HbA1c_6years;
        }

        //排版
        for(int i=0;i<axisData.length;i++)
            if(yAxisData[i]>=yAxisData_max)
                yAxisData_max = yAxisData[i];
        for(int i=0;i<axisData.length;i++)
            if(i==0)
                yAxisData_min = yAxisData[i];
            else if(yAxisData[i]<=yAxisData_min)
                yAxisData_min = yAxisData[i];

        lineChartView = findViewById(R.id.chart);
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));
        line.setCubic(true);  //平滑曲線

        for (int i = 0; i < axisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(16);
        axis.setTextColor(Color.parseColor("#03A9F4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("mmol/L");
        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        viewport.top = 2*yAxisData_max-yAxisData_min;  //排版
        viewport.bottom = 3*yAxisData_min-2*yAxisData_max;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);
    }

    public void set_chart_SBP(int position)  //position為spinner的period_list的指標
    {
        int[] yAxisData = {20, 10, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};

        lineChartView = findViewById(R.id.chart);
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));

//        for (int i = 0; i < axisData.length; i++) {
//            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
//        }

        for (int i = 0; i < yAxisData.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(16);
        axis.setTextColor(Color.parseColor("#03A9F4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("mm-hg");
        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        //viewport.top = 110;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);
    }

    public void set_chart_DBP(int position)  //position為spinner的period_list的指標
    {
        int[] yAxisData = {80, 70, 85, 82, 83, 80, 79, 79, 82, 81, 82, 81};

        lineChartView = findViewById(R.id.chart);
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));

//        for (int i = 0; i < axisData.length; i++) {
//            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
//        }

        for (int i = 0; i < yAxisData.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(16);
        axis.setTextColor(Color.parseColor("#03A9F4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("mm-hg");
        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        //viewport.top = 110;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);
    }
}
