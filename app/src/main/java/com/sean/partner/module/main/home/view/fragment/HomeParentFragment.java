package com.sean.partner.module.main.home.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.sean.partner.module.camera.CameraActivity;
import com.sean.partner.module.date.create.view.activity.CreateDateActivity;
import com.sean.partner.module.login.view.activity.UserUnLoginActivity;
import com.sean.partner.module.main.home.HomeContract;

/**
 * Created by Sean on 2017/12/25.
 */

public class HomeParentFragment extends Fragment implements HomeContract.HomeView {

    FragmentActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentActivity)
            this.activity = (FragmentActivity)context;
    }

    @Override
    public void setPresenter(HomeContract.HomePresenter presenter) {

    }

    @Override
    public void hideView() {
        //todo  fab的隐藏
    }

    @Override
    public void showCreateDate() {
        //startActivity(new Intent(activity, CreateDateActivity.class));
        startActivity(new Intent(activity, CameraActivity.class));
    }

    @Override
    public void showLogin() {
        startActivity(new Intent(activity, UserUnLoginActivity.class));
    }

    @Override
    public void showActivityDetail() {

    }

    @Override
    public int getStatusBarColor() {
        return 0;
    }
}
