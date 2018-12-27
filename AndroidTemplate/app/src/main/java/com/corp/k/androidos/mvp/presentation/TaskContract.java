package com.corp.k.androidos.mvp.presentation;

import android.support.annotation.NonNull;
import com.corp.k.androidos.mvp.BasePresenter;
import com.corp.k.androidos.mvp.BaseView;
import com.corp.k.androidos.mvp.model.Enum.TasksFilterType;
import com.corp.k.androidos.mvp.model.Task;


import java.util.List;

/**
 * Created by hoangtuan on 7/18/17.
 */

public class TaskContract {

    public interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);

        void showTasks(List<Task> tasks);

        void showAddTask();

        void showTaskDetailsUi(String taskId);

        void showTaskMarkedComplete();

        void showTaskMarkedActive();

        void showCompletedTasksCleared();

        void showLoadingTasksError();

        void showNoTasks();

        void showActiveFilterLabel();

        void showCompletedFilterLabel();

        void showAllFilterLabel();

        void showNoActiveTasks();

        void showNoCompletedTasks();

        void showSuccessfullySavedMessage();

        boolean isActive();

        void showFilteringPopUpMenu();
    }

    public interface Presenter extends BasePresenter {

        void result(int requestCone, int resultCode);

        void loadTasks(boolean forceUpdate);

        void addNewTask();

        void openTaskDetails(@NonNull Task requestTask);

        void completeTask(@NonNull Task completeTask);

        void activateTask(@NonNull Task activateTask);

        void clearCompleteTask();

        void setFiltering(TasksFilterType requestType);

        TasksFilterType getFiltering();
    }
}
