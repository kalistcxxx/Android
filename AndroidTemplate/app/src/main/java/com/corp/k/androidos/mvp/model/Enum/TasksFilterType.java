package com.corp.k.androidos.mvp.model.Enum;

/**
 * Created by hoangtuan on 7/18/17.
 */

public enum  TasksFilterType {
    /**
     * Do not filter tasks.
     */
    ALL_TASKS,

    /**
     * Filters only the active (not completed yet) tasks.
     */
    ACTIVE_TASKS,

    /**
     * Filters only the completed tasks.
     */
    COMPLETED_TASKS
}
