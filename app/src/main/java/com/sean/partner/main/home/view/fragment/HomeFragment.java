package com.sean.partner.main.home.view.fragment;

import android.app.Activity;
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
import com.sean.partner.main.home.HomeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.sean.partner.main.home.view.activity.HomeActivity.NUM_RV_ITEM;

/**
 * Created by sean on 2017/12/21.
 *
 * 首页模块的fragment
 */

public class HomeFragment extends Fragment {


    TabLayout tabLayout;
    ViewPager pager;
    FragmentActivity activity;

    List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.string.title_tab_recommend, R.layout.layout_content_home_recommend));
        pageModels.add(new PageModel(R.string.title_tab_friend, R.layout.layout_content_home_recommend));
        pageModels.add(new PageModel(R.string.title_tab_new, R.layout.layout_content_home_recommend));
        pageModels.add(new PageModel(R.string.title_tab_near, R.layout.layout_content_home_recommend));
    }



    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_content_home, container);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tl_home);
        pager = (ViewPager) rootView.findViewById(R.id.vp_home);
        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        pager.setAdapter(new FragmentPagerAdapter(activity.getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                PageModel pageModel = pageModels.get(position);
                return HomePagerFragment.newInstance(pageModel.layoutRes);
            }

            @Override
            public int getCount() {
                return pageModels.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getString(pageModels.get(position).titleRes);
            }
        });
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
        if(getActivity() != null){
            activity = getActivity();
        }
    }

    private class PageModel {
        @LayoutRes
        int layoutRes;
        @StringRes
        int titleRes;

        PageModel(@StringRes int titleRes, @LayoutRes int layoutRes) {
            this.layoutRes = layoutRes;
            this.titleRes = titleRes;
        }
    }

}
