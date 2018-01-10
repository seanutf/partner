package com.sean.partner.main.home;

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

    public HomeModelPresenter(@NonNull HomeContract.PageView pageView){

        mPageView = pageView;
        mPageView.setPresenter(this);
    }

    public HomeModelPresenter(@NonNull HomeContract.PageView pageView, HomeContract.HomeView mHomeView){

        this.mPageView = pageView;
        this.mPageView.setPresenter(this);

        this.mHomeView = mHomeView;
        //mHomeView.setPresenter(this);
    }

    public void setHomeView(HomeContract.HomeView mHomeView){
        this.mHomeView = mHomeView;
    }

    @Override
    public void start() {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }


    @Override
    public void createDate() {

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
}
