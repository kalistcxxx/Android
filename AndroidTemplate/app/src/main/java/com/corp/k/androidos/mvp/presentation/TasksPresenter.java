package com.corp.k.androidos.mvp.presentation;

import android.support.annotation.NonNull;
import com.corp.k.androidos.mvp.model.Enum.TasksFilterType;
import com.corp.k.androidos.mvp.model.Repository.TaskRepository;
import com.corp.k.androidos.mvp.model.Task;
import com.corp.k.androidos.mvp.utils.ActivityUtils;

import java.util.ArrayList;

/**
 * Created by hoangtuan on 7/18/17.
 */

public class TasksPresenter implements TaskContract.Presenter {

    private TaskRepository mTaskRepository;
    private TaskContract.View mTasksView;
    private TasksFilterType mCurrentFiltering = TasksFilterType.ALL_TASKS;
    private boolean mFirstLoad = true;

    public TasksPresenter(@NonNull TaskRepository taskRepository, @NonNull TaskContract.View taskViews){
        mTaskRepository = ActivityUtils.checkNotNull(taskRepository);
        mTasksView = ActivityUtils.checkNotNull(taskViews);

        mTasksView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTasks(false);
    }

    @Override
    public void result(int requestCode, int resultCode) {
//        if (AddEditTaskActivity.REQUEST_ADD_TASK == requestCode && Activity.RESULT_OK == resultCode) {
            mTasksView.showSuccessfullySavedMessage();
//        }
    }

    @Override
    public void loadTasks(boolean forceUpdate) {
        loadTasks(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadTasks(boolean forceUpdate, boolean showLoadingUI){
        if(showLoadingUI){
            mTasksView.setLoadingIndicator(true);
        }

        if(forceUpdate){
//            mTaskRepository.refreshTask();
        }

        //handle get Task data from Model
        ArrayList<Task> tasksToShow = new ArrayList<>();
        processTasks(tasksToShow);
    }

    private void processTasks(ArrayList<Task> listTasks){
        if(listTasks.size() == 0){
            processEmptyTasks();
        }else{
            mTasksView.showTasks(listTasks);
            showFilterLabel();
        }
    }

    private void showFilterLabel() {
        switch (mCurrentFiltering) {
            case ACTIVE_TASKS:
                mTasksView.showActiveFilterLabel();
                break;
            case COMPLETED_TASKS:
                mTasksView.showCompletedFilterLabel();
                break;
            default:
                mTasksView.showAllFilterLabel();
                break;
        }
    }

    private void processEmptyTasks() {
        switch (mCurrentFiltering) {
            case ACTIVE_TASKS:
                mTasksView.showNoActiveTasks();
                break;
            case COMPLETED_TASKS:
                mTasksView.showNoCompletedTasks();
                break;
            default:
                mTasksView.showNoTasks();
                break;
        }
    }



    @Override
    public void addNewTask() {
        mTasksView.showAddTask();
    }

    @Override
    public void openTaskDetails(@NonNull Task requestedTask) {
        ActivityUtils.checkNotNull(requestedTask, "requestedTask cannot be null!");
        mTasksView.showTaskDetailsUi(requestedTask.getId());
    }

    @Override
    public void completeTask(@NonNull Task completedTask) {
        ActivityUtils.checkNotNull(completedTask, "completedTask cannot be null!");
//        mTaskRepository.completeTask(completedTask); //Call model to comple a task
        mTasksView.showTaskMarkedComplete();
        loadTasks(false, false);
    }

    @Override
    public void activateTask(@NonNull Task activeTask) {
        ActivityUtils.checkNotNull(activeTask, "activeTask cannot be null!");
//        mTaskRepository.activateTask(activeTask);
        mTasksView.showTaskMarkedActive();
        loadTasks(false, false);
    }

    @Override
    public void clearCompleteTask() {
//        mTaskRepository.clearCompletedTasks();
        mTasksView.showCompletedTasksCleared();
        loadTasks(false, false);
    }

    @Override
    public void setFiltering(TasksFilterType requestType) {
        mCurrentFiltering = requestType;
    }

    @Override
    public TasksFilterType getFiltering() {
        return mCurrentFiltering;
    }
}
