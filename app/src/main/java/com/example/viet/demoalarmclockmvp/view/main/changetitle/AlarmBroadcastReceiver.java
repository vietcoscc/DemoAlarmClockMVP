package com.example.viet.demoalarmclockmvp.view.main.changetitle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by viet on 02/08/2017.
 */

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "The time has come", Toast.LENGTH_SHORT).show();
        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();
    }
}
