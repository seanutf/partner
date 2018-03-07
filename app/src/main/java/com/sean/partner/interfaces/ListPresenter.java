package com.sean.partner.interfaces;

/**
 * Created by Sean on 2018/3/7.
 * 用于列表页面的公共Presenter
 */

public interface ListPresenter extends BasePresenter {

    /**
     * 初始化列表数据的Adapter
     * */
    void initAdapter();


    /**
     * 设置列表数据的Adapter
     * */
    void setAdapter();
}
