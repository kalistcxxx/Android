package com.corp.k.androidos.mvp.view.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.corp.k.androidos.R;
import com.corp.k.androidos.mvp.model.Enum.TasksFilterType;
import com.corp.k.androidos.mvp.model.Repository.TaskRepository;
import com.corp.k.androidos.mvp.presentation.TasksPresenter;
import com.corp.k.androidos.mvp.view.Fragment.TaskFragment;

/**
 * Created by hoangtuan on 7/18/17.
 */

public class TaskActivity extends AppCompatActivity {

    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";

    private TasksPresenter mTasksPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_activity);

        TaskFragment taskFragment = TaskFragment.newInstance();
        mTasksPresenter = new TasksPresenter(new TaskRepository(), taskFragment);

        if(savedInstanceState!=null){
            TasksFilterType currentFiltering = (TasksFilterType) savedInstanceState.getSerializable(CURRENT_FILTERING_KEY);
            mTasksPresenter.setFiltering(currentFiltering);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(CURRENT_FILTERING_KEY, mTasksPresenter.getFiltering());

        super.onSaveInstanceState(outState);
    }
}
