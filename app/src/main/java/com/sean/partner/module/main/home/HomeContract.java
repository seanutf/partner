package com.sean.partner.module.main.home;


import com.sean.partner.global.interfaces.BasePresenter;
import com.sean.partner.global.interfaces.BaseView;

/**
 * Created by sean on 2017/11/28.
 *
 */

public interface HomeContract {


    /**
     * 用于处理 HomePagerFragment和HomeFragment之间显示逻辑
     */
    interface HomeView extends BaseView<HomePresenter> {

        void hideView();

        void showCreateDate();

        void showLogin();

        void showActivityDetail();
    }

    /**
     * 用于处理 HomePagerFragment自己内部显示逻辑
     */
    interface PageView extends BaseView<HomePresenter> {

        void showActivityDetail();
    }

    /**
     * 用于处理 HomePagerFragment和HomeFragment之间交互逻辑
     */
    interface HomePresenter extends BasePresenter {

        void result(int requestCode, int resultCode);


        void createDate();

        void userLogin();


        void clickItem();

        void listSliding();
    }



//    /**
//     * 用于处理 HomePagerFragment自己内部交互逻辑
//     */
//
//    interface PagePresenter extends BasePresenter {
//
//    }
}
