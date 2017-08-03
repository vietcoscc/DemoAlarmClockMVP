package com.example.viet.demoalarmclockmvp.presenter.main.changetitle;

import com.example.viet.demoalarmclockmvp.view.main.evenbus.ChildView;

/**
 * Created by viet on 03/08/2017.
 */

public class ChangeTitlePresenter implements ChangeTitlePresenterImp {
    private ChildView childView;

    public ChangeTitlePresenter(ChildView childView) {
        this.childView = childView;
    }

    @Override
    public void onChangeTitle() {
        childView.displayResult();
    }
}
