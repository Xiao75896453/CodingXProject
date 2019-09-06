package com.example.codingxproject.Remind;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.codingxproject.DataRecord.DataRecord_Heartbeat;
import com.example.codingxproject.MainFunctionsWithBottomBar.SetAlarmFragment;
import com.example.codingxproject.MyStartActivity;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Timer extends Thread {
    private int time = 0;
    private int current_hour = 0;
    private int current_minute = 0;
    private SetAlarmFragment.ViewHolder viewHolder = null;

    public Timer(SetAlarmFragment.ViewHolder viewHolder)
    {
        this.viewHolder = viewHolder;
    }

    public void run()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("hh");
        Log.e("current_hour",Integer.toString(current_hour));

        SimpleDateFormat formatter2 = new SimpleDateFormat("mm");
        Log.e("current_minute",Integer.toString(current_minute));

        while(time<100000 && viewHolder.getOpen().isChecked())
        {
            Date date = new Date();
            current_hour = Integer.parseInt(formatter.format(date));
            current_minute = Integer.parseInt(formatter2.format(date));
            Log.e("current time", current_hour+":"+current_minute);
            Log.e("alarm", viewHolder.getHour()+":"+viewHolder.getMinute());

            if((viewHolder.getHour() == current_hour) && (viewHolder.getMinute() == current_minute)) {
                Log.e("time's up","!!");
                break;
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.e("time", Integer.toString(time));
            time++;
        }
        Log.e("timer over", "!!");
    }

    public void clock()
    {
        Looper.prepare();
        final Context context = null;
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, RemindTakeMedActivity.class);
                context.startActivity(intent);
            }
        };
        handler.post(run);
    }
}
