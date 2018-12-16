package com.prettylifeiamcoming.timemanager.bean;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject implements Serializable {
    @PrimaryKey
    private String mTaskID;                          //任务ID
    private String mTaskName;                        //任务名称
    private String mTaskPlace;                       //任务地点
    private long mBeginTimestamp;                    //任务的起始时间
    private long mTerminalTimestamp;                 //任务的终止时间
    private double mDuration;                        //任务持续的时长，(mTerminalTimestamp-mBeginTimestamp),单位为小时
    private long mDeadline;                          //任务的deadline
    private int mTaskType;                           //任务类型，后期用switch绑定上具体的类型名称
    private int mTaskLevel;                          //任务级别，四象限，有四个级别，后期为了显示fragment的颜色服务
    private int mTaskProcess;                        //任务的进度，需要由用户来进行填写
    private int mTaskCustomLevel;                    //任务的用户自定义级别，由用户来决定，让用户来具体区分
    private String mUserID;                          // 用户ID，为从数据库中寻找用户的日程服务

    public Task() {
        mTaskID = UUID.randomUUID().toString();
    }

    public String getTaskID() {
        return mTaskID;
    }

    public String getTaskName() {
        return mTaskName;
    }

    public String getmTaskPlace() {
        return mTaskPlace;
    }

    public long getBeginTimestamp() {
        return mBeginTimestamp;
    }

    public long getTerminalTimestamp() {
        return mTerminalTimestamp;
    }

    public double getDuration() {
        return mDuration;
    }

    public long getDeadline() {
        return mDeadline;
    }

    public int getTaskType() {
        return mTaskType;
    }

    public int getTaskLevel() {
        return mTaskLevel;
    }

    public int getTaskProcess() {
        return mTaskProcess;
    }

    public int getTaskCustomLevel() {
        return mTaskCustomLevel;
    }

    public void setTaskName(String taskName) {
        mTaskName = taskName;
    }

    public void setmTaskPlace(String taskPlace) {
        mTaskPlace = taskPlace;
    }

    public void setBeginTimestamp(long beginTimestamp) {
        mBeginTimestamp = beginTimestamp;
    }

    public void setTerminalTimestamp(long terminalTimestamp) {
        mTerminalTimestamp = terminalTimestamp;
    }

    public void setDuration(double duration) {
        mDuration = duration;
    }

    public void setDeadline(long deadline) {
        mDeadline = deadline;
    }

    public void setTaskType(int taskType) {
        mTaskType = taskType;
    }

    public void setTaskLevel(int taskLevel) {
        mTaskLevel = taskLevel;
    }

    public void setTaskProcess(int taskProcess) {
        mTaskProcess = taskProcess;
    }

    public void setTaskCustomLevel(int taskCustomLevel) {
        mTaskCustomLevel = taskCustomLevel;
    }

    public void setUserID(String userID) {
        mUserID = userID;
    }
}