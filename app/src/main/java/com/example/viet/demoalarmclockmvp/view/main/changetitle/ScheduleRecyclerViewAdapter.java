package com.example.viet.demoalarmclockmvp.view.main.changetitle;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viet.demoalarmclockmvp.R;
import com.example.viet.demoalarmclockmvp.model.Schedule;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by viet on 02/08/2017.
 */

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Schedule> arrSchedule;

    public ScheduleRecyclerViewAdapter(ArrayList<Schedule> arrSchedule) {
        this.arrSchedule = arrSchedule;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule_recycler_view, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ScheduleViewHolder scheduleViewHolder = (ScheduleViewHolder) holder;
        Schedule schedule = arrSchedule.get(position);
        scheduleViewHolder.tvTime.setText(schedule.toString());
    }

    @Override
    public int getItemCount() {
        return arrSchedule.size();
    }

    class ScheduleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.swCompat)
        SwitchCompat switchCompat;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
