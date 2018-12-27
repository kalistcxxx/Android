package com.corp.k.androidos.mvp.view.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.corp.k.androidos.mvp.model.Task;
import com.corp.k.androidos.mvp.presentation.TaskContract;

import java.util.List;

/**
 * Created by hoangtuan on 7/18/17.
 */

public class TaskFragment extends Fragment implements TaskContract.View{

    private TaskContract.Presenter mPresenter;

    public static TaskFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(TaskContract.Presenter presenter) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showTasks(List<Task> tasks) {

    }

    @Override
    public void showAddTask() {

    }

    @Override
    public void showTaskDetailsUi(String taskId) {

    }

    @Override
    public void showTaskMarkedComplete() {

    }

    @Override
    public void showTaskMarkedActive() {

    }

    @Override
    public void showCompletedTasksCleared() {

    }

    @Override
    public void showLoadingTasksError() {

    }

    @Override
    public void showNoTasks() {

    }

    @Override
    public void showActiveFilterLabel() {

    }

    @Override
    public void showCompletedFilterLabel() {

    }

    @Override
    public void showAllFilterLabel() {

    }

    @Override
    public void showNoActiveTasks() {

    }

    @Override
    public void showNoCompletedTasks() {

    }

    @Override
    public void showSuccessfullySavedMessage() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showFilteringPopUpMenu() {

    }
}
