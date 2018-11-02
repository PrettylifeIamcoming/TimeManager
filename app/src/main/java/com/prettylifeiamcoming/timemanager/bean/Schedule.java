package com.prettylifeiamcoming.timemanager.bean;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Schedule extends RealmObject implements Serializable {
    @PrimaryKey
    private String mScheduleID;
    private String mScheduleName;
    private long mBeginTimestamp;
    private long mTerminalTimestamp;
    private int mScheduleType;
    private String mUserId;

    public Schedule(){
        mScheduleID = UUID.randomUUID().toString();
    }

    public void setmScheduleName(String scheduleName) {
        mScheduleName = scheduleName;
    }

    public void setmBeginTimestamp(long beginTimestamp) {
        mBeginTimestamp = beginTimestamp;
    }

    public void setmTerminalTimestamp(long terminalTimestamp) {
        mTerminalTimestamp = terminalTimestamp;
    }

    public void setmScheduleType(int scheduleType) {
        mScheduleType = scheduleType;
    }

    public String getmScheduleID() {
        return mScheduleID;
    }

    public String getmScheduleName() {
        return mScheduleName;
    }

    public long getmBeginTimestamp() {
        return mBeginTimestamp;
    }

    public long getmTerminalTimestamp() {
        return mTerminalTimestamp;
    }

    public int getmScheduleType() {
        return mScheduleType;
    }
}