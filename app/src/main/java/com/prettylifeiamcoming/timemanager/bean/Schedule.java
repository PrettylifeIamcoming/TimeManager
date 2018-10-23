package com.prettylifeiamcoming.timemanager.bean;

import java.io.Serializable;
import java.util.UUID;

public class Schedule implements Serializable {
    private UUID mScheduleID;
    private String mScheduleName;
    private long mBeginTimestamp;
    private long mTerminalTimestamp;
    private int mScheduleType;

    public Schedule(){
        mScheduleID = UUID.randomUUID();
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

    public UUID getmScheduleID() {
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