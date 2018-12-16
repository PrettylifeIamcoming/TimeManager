package com.prettylifeiamcoming.timemanager.bean;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Schedule extends RealmObject implements Serializable {
    @PrimaryKey
    private String mScheduleID;          //日程ID
    private String mScheduleName;        //日程名称
    private String mSchedulePlace;    //日程地点
    private long mBeginTimestamp;        //日程的起始时间
    private long mTerminalTimestamp;     //日程的终止时间
    private double mDuration;            //日程为持续的时长，(mTerminalTimestamp-mBeginTimestamp),单位为小时
    private int mScheduleType;           //日程的类型，后期用switch绑定上具体的类型名称
    private String mUserID;              //用户ID，为从数据库中寻找用户的日程服务

    public Schedule(){
        mScheduleID = UUID.randomUUID().toString();
    }

    public String getScheduleID() {
        return mScheduleID;
    }

    public String getScheduleName() {
        return mScheduleName;
    }

    public String getSchedulePlace(){
        return mSchedulePlace;
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

    public int getScheduleType() {
        return mScheduleType;
    }

    public void setScheduleName(String scheduleName) {
        mScheduleName = scheduleName;
    }

    public void setSchedulePlace(String schedulePlace){
        mSchedulePlace = schedulePlace;
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

    public void setScheduleType(int scheduleType) {
        mScheduleType = scheduleType;
    }

    public void setUserID(String userID) {
        mUserID = userID;
    }
}