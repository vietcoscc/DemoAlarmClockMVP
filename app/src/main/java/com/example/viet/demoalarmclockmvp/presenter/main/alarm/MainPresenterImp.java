package com.example.viet.demoalarmclockmvp.presenter.main.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.viet.demoalarmclockmvp.model.Schedule;
import com.example.viet.demoalarmclockmvp.view.main.changetitle.AlarmBroadcastReceiver;
import com.example.viet.demoalarmclockmvp.view.main.changetitle.MainView;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by viet on 03/08/2017.
 */

public class MainPresenterImp implements MainPresenter {
    private MainView mMainView;
    private Context context;

    public MainPresenterImp(MainView mMainView, Context context) {
        this.mMainView = mMainView;
        this.context = context;
    }

    @Override
    public void onSchedule() {
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        TimePickerDialog dialog = new TimePickerDialog(context,
                android.R.style.Widget_Material_Light_TimePicker,
                listener,
                currentHour,
                currentMinute,
                true);
        dialog.show();
    }

    @Override
    public void onChangeTitle() {
        mMainView.displayChildActivity();
    }

    private TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onTimeSet(TimePicker view, int setHour, int setMinute) {
//                String time = timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();
//                Toast.makeText(MainActivity.this, time, Toast.LENGTH_SHORT).show();
            if (view.isShown()) {
                Schedule schedule = new Schedule(setHour, setMinute);

                Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, Intent.FILL_IN_ACTION);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, setHour);
                calendar.set(Calendar.MINUTE, setMinute);
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                mMainView.displaySchedule(schedule);
                Toast.makeText(context, setHour + ":" + setMinute, Toast.LENGTH_SHORT).show();
            }

        }
    };
}
