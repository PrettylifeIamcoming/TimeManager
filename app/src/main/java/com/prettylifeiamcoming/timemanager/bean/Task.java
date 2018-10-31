package com.prettylifeiamcoming.timemanager.bean;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject implements Serializable {
    @PrimaryKey
    private UUID mTaskID;
    private String mTaskName;
    private long mBeginTimestamp;
    private long mTerminalTimestamp;
    private int mTaskType;
    private int mTaskLevel;
    private int mTaskProcess;
    private int mTaskCustomLevel;
    private UUID mUserID;

    public Task(){
        mTaskID = UUID.randomUUID();
    }

    public void setmTaskName(String taskName) {
        mTaskName = taskName;
    }

    public void setmBeginTimestamp(long beginTimestamp) {
        mBeginTimestamp = beginTimestamp;
    }

    public void setmTerminalTimestamp(long terminalTimestamp) {
        mTerminalTimestamp = terminalTimestamp;
    }

    public void setmTaskType(int taskType) {
        mTaskType = taskType;
    }

    public void setmTaskLevel(int taskLevel) {
        mTaskLevel = taskLevel;
    }

    public void setmTaskProcess(int taskProcess) {
        mTaskProcess = taskProcess;
    }

    public void setmTaskCustomLevel(int taskCustomLevel) {
        mTaskCustomLevel = taskCustomLevel;
    }

    public UUID getmTaskID() {
        return mTaskID;
    }

    public String getTaskName() {
        return mTaskName;
    }

    public long getmBeginTimestamp() {
        return mBeginTimestamp;
    }

    public long getmTerminalTimestamp() {
        return mTerminalTimestamp;
    }

    public int getmTaskType() {
        return mTaskType;
    }

    public int getmTaskLevel() {
        return mTaskLevel;
    }

    public int getmTaskProcess() {
        return mTaskProcess;
    }

    public int getmTaskCustomLevel() {
        return mTaskCustomLevel;
    }
}