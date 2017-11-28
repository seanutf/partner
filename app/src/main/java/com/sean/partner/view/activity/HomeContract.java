package com.sean.partner.view.activity;

import android.support.annotation.NonNull;

import com.sean.partner.BasePresenter;
import com.sean.partner.BaseView;
import com.sean.partner.meta.DateMeta;

import java.util.List;

/**
 * Created by sean on 2017/11/28.
 *
 */

public interface HomeContract {

    interface View extends BaseView<Presenter> {

        //void setLoadingIndicator(boolean active);

        //void showTasks(List<DateMeta> tasks);

        //void showAddTask();

        void showCreateDate();

        void showLogin();

        //void showTaskDetailsUi(String taskId);

        //void showTaskMarkedComplete();

        //void showTaskMarkedActive();

        //void showCompletedTasksCleared();

        //void showLoadingTasksError();

        //void showNoTasks();

        //void showActiveFilterLabel();

        //void showCompletedFilterLabel();

        //void showAllFilterLabel();

        //void showNoActiveTasks();

        //void showNoCompletedTasks();

        //void showSuccessfullySavedMessage();

        //boolean isActive();

        //void showFilteringPopUpMenu();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        //void loadTasks(boolean forceUpdate);

        //void addNewTask();

        void createDate();

        void userLogin();

        //void openTaskDetails(@NonNull DateMeta requestedTask);

        //void completeTask(@NonNull DateMeta completedTask);

        //void activateTask(@NonNull DateMeta activeTask);

        //void clearCompletedTasks();

        //void setFiltering(int requestType);
    }
}
