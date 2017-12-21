package com.sean.partner.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sean.partner.R;
import com.sean.partner.view.activity.HomeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.sean.partner.view.activity.HomeActivity.NUM_RV_ITEM;

/**
 * Created by sean on 2017/12/21.
 *
 * 主页面fragment
 */

public class HomePagerFragment extends Fragment {

    @LayoutRes
    int layoutRes;
    //TabLayout tabLayout;
    //ViewPager pager;
    FragmentActivity activity;


    private static final String LAYOUT_RES = "layoutRes_home";

//    List<PageModel> pageModels = new ArrayList<>();
//
//    {
//        pageModels.add(new PageModel(R.string.title_tab_recommend, R.layout.layout_content_home_recommend));
//        pageModels.add(new PageModel(R.string.title_tab_friend, R.layout.layout_content_home_recommend));
//        pageModels.add(new PageModel(R.string.title_tab_new, R.layout.layout_content_home_recommend));
//        pageModels.add(new PageModel(R.string.title_tab_near, R.layout.layout_content_home_recommend));
//    }



    public static HomePagerFragment newInstance(@LayoutRes int layoutRes) {
        HomePagerFragment fragment = new HomePagerFragment();
        Bundle args = new Bundle();
        args.putInt(LAYOUT_RES, layoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView rvList = new RecyclerView(inflater.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext());
        rvList.setLayoutManager(linearLayoutManager);
        rvList.setHasFixedSize(true);
        HomeRecyclerViewAdapter adapter = new HomeRecyclerViewAdapter(NUM_RV_ITEM,(Activity) inflater.getContext());
        rvList.setAdapter(adapter);
        return rvList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            layoutRes = args.getInt(LAYOUT_RES);
        }
        if(getActivity() != null){
            activity = getActivity();
        }
    }

//    private class PageModel {
//        @LayoutRes
//        int layoutRes;
//        @StringRes
//        int titleRes;
//
//        PageModel(@StringRes int titleRes, @LayoutRes int layoutRes) {
//            this.layoutRes = layoutRes;
//            this.titleRes = titleRes;
//        }
//    }
}
