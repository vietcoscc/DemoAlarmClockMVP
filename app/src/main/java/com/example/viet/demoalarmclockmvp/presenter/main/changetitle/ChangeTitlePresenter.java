package com.example.viet.demoalarmclockmvp.presenter.main.changetitle;

import com.example.viet.demoalarmclockmvp.view.main.evenbus.ChildView;

/**
 * Created by viet on 03/08/2017.
 */

public class ChangeTitlePresenter implements ChangeTitlePresenterImp {
    private ChildView mChildView;

    public ChangeTitlePresenter(ChildView childView) {
        this.mChildView = childView;
    }

    @Override
    public void onChangeTitle() {
        mChildView.displayResult();
    }
}
