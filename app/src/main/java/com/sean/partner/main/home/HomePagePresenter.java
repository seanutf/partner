package com.sean.partner.main.home;

import android.support.annotation.NonNull;

/**
 * Created by sean on 2017/12/24.
 */

public class HomePagePresenter {

    private HomeContract.PageView mPageView;

    public HomePagePresenter(@NonNull HomeContract.PageView pagerView){

        mPageView = pagerView;
        //mPageView.setPresenter(this);
    }

    public HomePagePresenter() {
        super();
    }


}
