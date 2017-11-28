package com.sean.partner.view.activity;

import android.support.annotation.NonNull;

/**
 * Created by sean on 2017/11/28.
 *
 */

public class HomePresenter implements HomeContract.Presenter {

    private final HomeContract.View mHomeView;

    public HomePresenter(@NonNull HomeContract.View homeView){

        mHomeView = homeView;
        mHomeView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void createDate() {
        mHomeView.showCreateDate();
    }

    @Override
    public void userLogin() {
        mHomeView.showLogin();
    }
}
