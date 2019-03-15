package com.prettylifeiamcoming.timemanager.db;

import android.content.Context;

import com.prettylifeiamcoming.timemanager.bean.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ScheduleLab {
    private static ScheduleLab sScheduleLab;

    private List<Schedule> mSchedules;

    private ScheduleLab(Context context) {
        mSchedules = new ArrayList<>();
    }

    public static ScheduleLab get(Context context) {
        if (sScheduleLab == null) {
            sScheduleLab = new ScheduleLab(context);
        }

        return sScheduleLab;
    }

    public List<Schedule> getSchedules() {
        return mSchedules;
    }

    public Schedule getSchedule(UUID id) {
        for (com.prettylifeiamcoming.timemanager.bean.Schedule Schedule : mSchedules) {
            if (Schedule.getScheduleID().equals(id)) {
                return Schedule;
            }
        }

        return null;
    }

}
