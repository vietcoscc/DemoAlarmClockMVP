package com.example.viet.demoalarmclockmvp.presenter.main.alarm;

import com.example.viet.demoalarmclockmvp.view.main.changetitle.MainView;

/**
 * Created by viet on 03/08/2017.
 */

public class MainPresenter implements MainPresenterImp {
    private MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onDataChange() {
        mainView.displaySchedule();
    }

    @Override
    public void onChangeTitle() {
        mainView.displayChildActivity();
    }
}
