package com.example.viet.demoalarmclockmvp.view.main.changetitle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.viet.demoalarmclockmvp.R;
import com.example.viet.demoalarmclockmvp.model.Message;
import com.example.viet.demoalarmclockmvp.model.Schedule;
import com.example.viet.demoalarmclockmvp.presenter.main.alarm.MainPresenterImp;
import com.example.viet.demoalarmclockmvp.view.main.evenbus.ChildActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {
    private MainPresenterImp mMainPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btnSchedule)
    Button btnSchedule;
    @BindView(R.id.btnChangeTitle)
    Button btnChangeTitle;
    private ScheduleRecyclerViewAdapter mScheduleRecyclerViewAdapter;
    private ArrayList<Schedule> mArrSchedule = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initPresenter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mScheduleRecyclerViewAdapter = new ScheduleRecyclerViewAdapter(mArrSchedule);
        recyclerView.setAdapter(mScheduleRecyclerViewAdapter);
        btnSchedule.setOnClickListener(this);
        btnChangeTitle.setOnClickListener(this);
    }


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
        mMainPresenter = new MainPresenterImp(this, this);
    }

    @Override
    public void displaySchedule(Schedule schedule) {
        mArrSchedule.add(schedule);
        mScheduleRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayChildActivity() {
        Intent intent = new Intent(this, ChildActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnChangeTitle) {
            mMainPresenter.onChangeTitle();
        } else if (view.getId() == R.id.btnSchedule) {
            mMainPresenter.onSchedule();
        }
    }
}
