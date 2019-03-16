package com.prettylifeiamcoming.timemanager.db;

import android.content.Context;

import com.prettylifeiamcoming.timemanager.Sundial;
import com.prettylifeiamcoming.timemanager.bean.Task;

import java.util.List;

public class TaskLab {
    private static TaskLab sTaskLab;

    private List<Task> mTaskTableTasks;

    private List<Task> mOverdueTasks;

    private List<Task> mCompletedTasks;

    private RealmHelper realmHelper = new RealmHelper(Sundial.getInstance());

    private TaskLab(Context context) {
        mTaskTableTasks = realmHelper.queryTaskTable();
        mOverdueTasks = realmHelper.queryOverdueTask();
        mCompletedTasks = realmHelper.queryCompletedTask();
    }

    public static TaskLab get(Context context) {
        if (sTaskLab == null) {
            sTaskLab = new TaskLab(context);
        }

        return sTaskLab;
    }

    public void refreshTaskTableTasks() {
        mTaskTableTasks = realmHelper.queryTaskTable();
    }

    public void refreshOverdueTasks() {
        mOverdueTasks = realmHelper.queryOverdueTask();
    }

    public void refreshCompletedTasks() {
        mCompletedTasks = realmHelper.queryCompletedTask();
    }

    public List<Task> getTaskTableTasks() {
        return mTaskTableTasks;
    }

    public List<Task> getOverdueTasks() {
        return mOverdueTasks;
    }

    public List<Task> getCompletedTasks() {
        return mCompletedTasks;
    }

    //    public List<Task> getTasks() {
//        return mTasks;
//    }
//
//    public Task getTask(UUID id) {
//        for (Task task : mTasks) {
//            if (task.getTaskID().equals(id)) {
//                return task;
//            }
//        }
//
//        return null;
//    }
}
