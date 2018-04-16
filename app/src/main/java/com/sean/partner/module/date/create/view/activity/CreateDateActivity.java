package com.sean.partner.module.date.create.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sean.partner.MainActivity;
import com.sean.partner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * 1.检测是否为默认头像，不是，不做任何处理，是则
 * 2.检测是否给相机权限，没有，申请权限，允许则不做任何处理，拒绝，给提示，允许则不做任何处理，再拒绝
 * 则提示将创建失败；有权限不做任何处理
 * 点击头像时：
 * 2.检测是否给相机权限，没有，申请权限，允许则不做任何处理，拒绝，给提示，允许则不做任何处理，再拒绝
 * 则提示将创建失败；有权限不做任何处理
 * */
/**
 * 跳转前的页面保证了user不为空
 * 如果头像、联系方式为空跳转到个人设置页面进行设置，否则不让创建
 * 1做什么加入限定100字
 * 2图片添加逻辑
 * 3标签逻辑，限定5个
 * 4时间地点逻辑
 * 5好友逻辑
 * */
public class CreateDateActivity extends MainActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.til_content)
    TextInputLayout tilContent;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.rv_pic_list)
    RecyclerView rvImageList;
    @BindView(R.id.til_tags)
    TextInputLayout tilTags;
    @BindView(R.id.et_tags)
    EditText etTags;
    @BindView(R.id.til_count)
    TextInputLayout tilCount;
    @BindView(R.id.et_count)
    EditText etCount;
    @BindView(R.id.rg_people_count)
    RadioGroup rgPeopleCount;
    @BindView(R.id.rb_max_count)
    RadioButton rbMaxCount;
    @BindView(R.id.rb_fix_count)
    RadioButton rbFixCount;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_addr)
    TextView tvAddr;
    @BindView(R.id.tv_friend)
    TextView tvFriend;

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

    }

    @Override
    public void initActivityView() {
        ButterKnife.bind(this);
    }

    @Override
    public int getContentViewResourceId() {
        return R.layout.activity_layout_create_date;
    }
}
