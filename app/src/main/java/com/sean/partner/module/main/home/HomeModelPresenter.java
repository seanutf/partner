package com.sean.partner.module.main.home;

import android.support.annotation.NonNull;


/**
 * Created by sean on 2017/11/28.
 *
 */

public class HomeModelPresenter implements HomeContract.HomePresenter {

    private HomeContract.HomeView mHomeView;
    private HomeContract.PageView mPageView;


    public HomeModelPresenter(@NonNull HomeContract.HomeView homeView){

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

    @Override
    public void clickItem() {

    }

    @Override
    public void listSliding() {
        mHomeView.hideView();
    }

    @Override
    public int getStatusBarColor() {
        return mHomeView.getStatusBarColor();
    }
}
