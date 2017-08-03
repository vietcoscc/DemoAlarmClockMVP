package com.example.viet.demoalarmclockmvp.model;

/**
 * Created by viet on 03/08/2017.
 */

public class Schedule {
    int hour;
    int minute;

    public Schedule(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return hour + ":" + minute;
    }
}
