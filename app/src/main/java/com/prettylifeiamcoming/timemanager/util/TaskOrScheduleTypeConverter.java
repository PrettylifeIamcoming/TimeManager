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

    public static Task setTaskType(Spinner spinner, Task task) throws IllegalArgumentException {
        try {
            if (spinner.getSelectedItemPosition() >= 0 && spinner.getSelectedItemPosition() <= 5) {
                task.setTaskType(spinner.getSelectedItemPosition() + 1);
            } else {
                Toast.makeText(Sundial.getInstance(), R.string.add_task_error, Toast.LENGTH_LONG).show();
                throw new IllegalArgumentException(Sundial.getInstance().getString(R.string.add_task_error));
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return task;
    }

    public static Schedule setScheduleType(Spinner spinner, Schedule schedule) throws IllegalArgumentException {
        try {
            if (spinner.getSelectedItemPosition() >= 0 && spinner.getSelectedItemPosition() <= 5) {
                schedule.setScheduleType(spinner.getSelectedItemPosition() + 1);
            } else {
                Toast.makeText(Sundial.getInstance(), R.string.add_task_error, Toast.LENGTH_LONG).show();
                throw new IllegalArgumentException(Sundial.getInstance().getString(R.string.add_task_error));
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return schedule;
    }
}
