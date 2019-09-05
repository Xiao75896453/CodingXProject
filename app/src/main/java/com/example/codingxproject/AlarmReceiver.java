package com.example.codingxproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.example.codingxproject.Remind.RemindTakeMedActivity;

import static android.content.Intent.FLAG_RECEIVER_FOREGROUND;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver(){

    }
    @Override
    public void onReceive(Context context, Intent intent)

    {
        Toast.makeText(context, "要記得吃藥喔!", Toast.LENGTH_LONG).show( );
        Intent intent1=new Intent(context, RemindTakeMedActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}

