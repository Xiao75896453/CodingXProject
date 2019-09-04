package com.example.codingxproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver(){

    }
    @Override
    public void onReceive(Context context, Intent intent)

    {
        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show( );
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.abc);
        mediaPlayer.start();
    }
}

