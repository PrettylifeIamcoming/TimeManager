package com.prettylifeiamcoming.timemanager.util;

import android.widget.Spinner;
import android.widget.Toast;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.Sundial;
import com.prettylifeiamcoming.timemanager.bean.Schedule;
import com.prettylifeiamcoming.timemanager.bean.Task;

public class TaskOrScheduleTypeConverter {

    public static String getType(int i) {
        switch (i) {
            case 1:
                return Sundial.getInstance().getString(R.string.add_task_study);
            case 2:
                return Sundial.getInstance().getString(R.string.add_task_social);
            case 3:
                return Sundial.getInstance().getString(R.string.add_task_work);
            case 4:
                return Sundial.getInstance().getString(R.string.add_task_play);
            case 5:
                return Sundial.getInstance().getString(R.string.add_task_sleep);
            case 6:
                return Sundial.getInstance().getString(R.string.add_task_others);
        }

        return Sundial.getInstance().getString(R.string.add_task_error);
    }

    public static Task setTaskType(Spinner spinner, Task task) throws IllegalArgumentException{
        switch (spinner.getSelectedItemPosition()){
            case 0:
                task.setTaskType(1);
                break;
            case 1:
                task.setTaskType(2);
                break;
            case 2:
                task.setTaskType(3);
                break;
            case 3:
                task.setTaskType(4);
                break;
            case 4:
                task.setTaskType(5);
                break;
            case 5:
                task.setTaskType(6);
                break;
                default:
                    Toast.makeText(Sundial.getInstance(),R.string.add_task_error,Toast.LENGTH_LONG).show();
                    throw new IllegalArgumentException(Sundial.getInstance().getString(R.string.add_task_error));
        }

        return task;
    }

    public static Schedule setScheduleType(Spinner spinner, Schedule schedule) throws IllegalArgumentException{
        switch (spinner.getSelectedItemPosition()){
            case 0:
                schedule.setScheduleType(1);
                break;
            case 1:
                schedule.setScheduleType(2);
                break;
            case 2:
                schedule.setScheduleType(3);
                break;
            case 3:
                schedule.setScheduleType(4);
                break;
            case 4:
                schedule.setScheduleType(5);
                break;
            case 5:
                schedule.setScheduleType(6);
                break;
            default:
                Toast.makeText(Sundial.getInstance(),R.string.add_task_error,Toast.LENGTH_LONG).show();
                throw new IllegalArgumentException(Sundial.getInstance().getString(R.string.add_task_error));
        }

        return schedule;
    }
}
