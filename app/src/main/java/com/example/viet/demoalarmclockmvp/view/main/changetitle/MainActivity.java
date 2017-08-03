package com.example.viet.demoalarmclockmvp.view.main.changetitle;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.viet.demoalarmclockmvp.R;
import com.example.viet.demoalarmclockmvp.model.Message;
import com.example.viet.demoalarmclockmvp.model.Schedule;
import com.example.viet.demoalarmclockmvp.presenter.main.alarm.MainPresenter;
import com.example.viet.demoalarmclockmvp.view.main.evenbus.ChildActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {
    private MainPresenter mainPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btnSchedule)
    Button btnSchedule;
    @BindView(R.id.btnChangeTitle)
    Button btnChangeTitle;
    private ScheduleRecyclerViewAdapter adapter;
    private ArrayList<Schedule> arrSchedule = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initPresenter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new ScheduleRecyclerViewAdapter(arrSchedule);
        recyclerView.setAdapter(adapter);
        btnSchedule.setOnClickListener(this);
        btnChangeTitle.setOnClickListener(this);
    }

    private TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onTimeSet(TimePicker view, int setHour, int setMinute) {
//                String time = timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();
//                Toast.makeText(MainActivity.this, time, Toast.LENGTH_SHORT).show();
            if (view.isShown()) {
                Schedule schedule = new Schedule(setHour, setMinute);
                arrSchedule.add(schedule);
                Intent intent = new Intent(MainActivity.this, AlarmBroadcastReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, Intent.FILL_IN_ACTION);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, setHour);
                calendar.set(Calendar.MINUTE, setMinute);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                mainPresenter.onDataChange();
                Toast.makeText(MainActivity.this, setHour + ":" + setMinute, Toast.LENGTH_SHORT).show();
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Message event) {
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
        getSupportActionBar().setTitle(event.getMessage());
    }

    private void initPresenter() {
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public void displaySchedule() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displayChildActivity() {
        Intent intent = new Intent(this, ChildActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnChangeTitle) {
            Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            mainPresenter.onChangeTitle();
        } else if (view.getId() == R.id.btnSchedule) {
            int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
            int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            TimePickerDialog dialog = new TimePickerDialog(MainActivity.this,
                    android.R.style.Widget_Material_Light_TimePicker,
                    listener,
                    currentHour,
                    currentMinute,
                    true);
            dialog.show();
        }
        Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
        mainPresenter.onChangeTitle();
    }
}
