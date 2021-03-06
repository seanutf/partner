package com.sean.partner.module.main;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.sean.partner.R;
import com.sean.partner.MainActivity;
import com.sean.partner.module.main.home.HomeContract;
import com.sean.partner.module.main.home.HomeModelPresenter;
import com.sean.partner.module.main.home.view.fragment.HomeFragment;
import com.sean.partner.global.meta.PUser;
import com.sean.partner.module.setting.app.view.activity.AppSettingsActivity;
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
    TextView tvUserName, tvUserDescribe;



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
    public void setActivityViewInitStatus(@Nullable Bundle savedInstanceState) {
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        homeFragment = HomeFragment.newInstance();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.root_view_fragment, homeFragment,HomeFragment.TAG);
        transaction.commitNowAllowingStateLoss();

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_as_round);
//        RoundedBitmapDrawable circleDrawable = RoundedBitmapDrawableFactory.create(getResources(), BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_as_round));
//        circleDrawable.getPaint().setAntiAlias(true);
//        circleDrawable.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()));
//        imgUserAvatar.setImageDrawable(circleDrawable);

        PUser user = null;

        if(user == null){
            tvUserName.setText(getString(R.string.text_if_user_login_out));
            tvUserDescribe.setText(getString(R.string.app_placeholder));
        }


        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        mPresenter = new HomeModelPresenter(homeFragment);
        //initImmersive();
    }

    @Override
    public void initActivityView() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        imgUserAvatar = (ImageView) headerView.findViewById(R.id.img_user_avatar);
        tvUserName = (TextView) headerView.findViewById(R.id.tv_user_name);
        tvUserDescribe = (TextView) headerView.findViewById(R.id.tv_user_describe);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

    }

    @Override
    public int getContentViewResourceId() {
        return R.layout.activity_layout_home;
    }

    @Override
    public int setStatusBarColor() {
        return mPresenter.getStatusBarColor();
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
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // todo android4.4的通知栏问题
    private void initImmersive() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                //将侧边栏顶部延伸至status bar
                drawer.setFitsSystemWindows(true);
                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
                drawer.setClipToPadding(false);
            }
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_night) {
            // todo 设置成夜间模式，设置成功后，界面显示成日间模式图标和日间模式文案
        } else if (id == R.id.nav_setting) {
            // todo 跳转到设置页面
            AppSettingsActivity.startActivity(this, "This is");
        } else if (id == R.id.nav_clear_cache) {
            // todo 清理App的缓存
        } else if (id == R.id.nav_feedback) {
            // todo 跳转到反馈页面
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public HomeContract.HomePresenter getPresenter(){
        return mPresenter;
    }
}
