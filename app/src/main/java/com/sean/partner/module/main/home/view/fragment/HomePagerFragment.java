package com.sean.partner.module.main.home.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sean.partner.module.main.HomeActivity;
import com.sean.partner.module.main.home.HomeContract;
import com.sean.partner.module.main.home.HomeRecyclerViewAdapter;

import static com.sean.partner.module.main.HomeActivity.NUM_RV_ITEM;

/**
 * Created by sean on 2017/12/21.
 *
 * 主页面fragment
 */

public class HomePagerFragment extends HomeParentFragment{

    FragmentActivity activity;
    HomeContract.HomePresenter mPresenter;

    public static final String TAG = "HomePagerFragment";

    public static HomePagerFragment newInstance() {
        //mPresenter = mPresenter;
        return new HomePagerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView rvList = new RecyclerView(inflater.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext());
        rvList.setLayoutManager(linearLayoutManager);
        rvList.setHasFixedSize(true);
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0){
                    mPresenter.listSliding();
                }
            }
        });
        HomeRecyclerViewAdapter adapter = new HomeRecyclerViewAdapter(NUM_RV_ITEM,(Activity) inflater.getContext());
        rvList.setAdapter(adapter);
        return rvList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity() != null){
            activity = getActivity();
            if(activity instanceof HomeActivity){
                mPresenter = ((HomeActivity)activity).getPresenter();
            }
        }
    }


    @Override
    public void setPresenter(HomeContract.HomePresenter presenter) {
        //mPresenter = presenter;
    }
}
