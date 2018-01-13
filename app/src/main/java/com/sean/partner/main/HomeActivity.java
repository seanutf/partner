package com.sean.partner.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.sean.partner.R;
import com.sean.partner.MainActivity;
import com.sean.partner.main.home.HomeContract;
import com.sean.partner.main.home.HomeModelPresenter;
import com.sean.partner.main.home.view.fragment.HomeFragment;
import com.sean.partner.utils.view.BottomNavigationViewHelper;


public class HomeActivity extends MainActivity
        implements NavigationView.OnNavigationItemSelectedListener{



    DrawerLayout drawer;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    FragmentManager manager;
    HomeContract.HomePresenter mPresenter;
    HomeFragment homeFragment;
    ImageView imgUserAvatar;



    public  static final int NUM_RV_ITEM = 50;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //todo 显示首页fragment并刷新
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_group:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setActivityData() {

    }

    @Override
    public void loadActivityData() {

    }

    @Override
    public void getIntentData() {

    }

    @Override
    public void setActivityView() {
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        homeFragment = HomeFragment.newInstance();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.root_view_fragment, homeFragment,HomeFragment.TAG);
        transaction.commit();


        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        mPresenter = new HomeModelPresenter(homeFragment);
    }

    @Override
    public void initActivityView() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        imgUserAvatar = (ImageView) findViewById(R.id.img_user_avatar);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

    }

    @Override
    public void setActivityContentView() {
        setContentView(R.layout.activity_layout_home);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, homeFragment.getToolBar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }






    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public HomeContract.HomePresenter getPresenter(){
        return mPresenter;
    }
}
