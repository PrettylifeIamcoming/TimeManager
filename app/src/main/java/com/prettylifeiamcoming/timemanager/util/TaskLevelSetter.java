package com.prettylifeiamcoming.timemanager.util;

import android.widget.Spinner;

import com.prettylifeiamcoming.timemanager.bean.Task;

public class TaskLevelSetter {
    public static Task setTaskLevel(Spinner spinner, Task task) throws IllegalArgumentException{
        switch (spinner.getSelectedItemPosition()){
            case 0:
                task.setTaskLevel(1);
                break;
            case 1:
                task.setTaskLevel(2);
                break;
            case 2:
                task.setTaskLevel(3);
                break;
            case 3:
                task.setTaskLevel(4);
                break;
                default:
                    throw new IllegalArgumentException("任务级别出错");
        }

        return task;
    }
}
