package com.sean.partner.module.main.home.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sean.partner.MainActivity;
import com.sean.partner.R;
import com.sean.partner.meta.User;
import com.sean.partner.module.date.create.view.activity.CreateDateActivity;
import com.sean.partner.module.login.view.activity.UserUnLoginActivity;
import com.sean.partner.module.main.home.HomeContract;

import java.util.ArrayList;
import java.util.List;

import static cn.bmob.v3.BmobUser.getCurrentUser;

/**
 * Created by sean on 2017/12/21.
 *
 * 首页模块的fragment
 */

public class HomeFragment extends HomeParentFragment {


    public static final String TAG = HomeFragment.class.getSimpleName();

    TabLayout tabLayout;
    Toolbar toolbar;
    ViewPager pager;
    FloatingActionButton fab;

    private HomeContract.HomePresenter mPresenter;

    List<PageModel> pageModels = new ArrayList<>();
    FragmentTransaction transaction;
    private Fragment[] mFragments;


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_content_home, null);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tl_home);
        pager = (ViewPager) rootView.findViewById(R.id.vp_home);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        tabLayout.setupWithViewPager(pager);
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((MainActivity)activity).setSupportActionBar(toolbar);
        ((MainActivity)activity).getSupportActionBar().setTitle(getString(R.string.title_tool_bar_for_home_fragment));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = getCurrentUser(User.class);
                if(user != null){
                    mPresenter.createDate();
                }else{
                    mPresenter.userLogin();
                }
            }
        });
        pager.setAdapter(new FragmentStatePagerAdapter(activity.getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return pageModels.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getString(pageModels.get(position).titleRes);
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                //super.instantiateItem(container, position);
                if (transaction == null) {
                    transaction = activity.getSupportFragmentManager().beginTransaction();
                }
                Fragment fragment = null;
                switch(position) {
                    case 0:
                        fragment = mFragments[0];
                        if (fragment == null) {
                            fragment = HomePagerFragment.newInstance();
                            transaction.add(container.getId(), fragment, HomePagerFragment.TAG);
                            mFragments[0] = fragment;
                            if (transaction != null) {
                                transaction.commitNowAllowingStateLoss();
                                transaction = null;
                            }
                        }

                        break;
                    case 1:
                        fragment = mFragments[1];
                        if (fragment == null) {
                            fragment = HomeFriendFragment.newInstance();
                            transaction.add(container.getId(), fragment, HomeFriendFragment.TAG);
                            mFragments[1] = fragment;
                            if (transaction != null) {
                                transaction.commitNowAllowingStateLoss();
                                transaction = null;
                            }
                        }

                        break;
                    case 2:
                        fragment = mFragments[2];
                        if (fragment == null) {
                            fragment = HomeNewFragment.newInstance();
                            transaction.add(container.getId(), fragment, HomeNewFragment.TAG);
                            mFragments[2] = fragment;
                            if (transaction != null) {
                                transaction.commitNowAllowingStateLoss();
                                transaction = null;
                            }
                        }

                        break;
                    case 3:
                        fragment = mFragments[3];
                        if (fragment == null) {
                            fragment = HomeNearFragment.newInstance();
                            transaction.add(container.getId(), fragment, HomeNearFragment.TAG);
                            mFragments[3] = fragment;
                            if (transaction != null) {
                                transaction.commitNowAllowingStateLoss();
                                transaction = null;
                            }
                        }

                        break;
                }
                if(fragment != null){
                    fragment.setMenuVisibility(false);
                    fragment.setUserVisibleHint(false);
                }

                return fragment;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //super.destroyItem(container, position, object);
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity() != null){
            activity = getActivity();
        }
        setHasOptionsMenu(true);
        mFragments = new Fragment[4];

        pageModels.add(new PageModel(R.string.title_tab_recommend, R.layout.layout_content_home_recommend));
        pageModels.add(new PageModel(R.string.title_tab_friend, R.layout.layout_content_home_recommend));
        pageModels.add(new PageModel(R.string.title_tab_new, R.layout.layout_content_home_recommend));
        pageModels.add(new PageModel(R.string.title_tab_near, R.layout.layout_content_home_recommend));


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

    public HomeFragment() {
        super();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void setPresenter(HomeContract.HomePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflate) {
        inflate.inflate(R.menu.home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_search:
                showSearchView();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSearchView() {

    }

    @Override
    public int getStatusBarColor() {
        return getResources().getColor(R.color.colorDayPrimary);
    }

    public Toolbar getToolBar(){
        return toolbar;
    }
}
