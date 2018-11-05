package com.prettylifeiamcoming.timemanager.bean;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Plan extends RealmObject implements Serializable {
    @PrimaryKey
    private String mPlanID;           //计划ID
    private String mPlanContent;      //任务内容
    private int mPlanType;            //任务类型，后期用switch绑定上具体的类型名称
    private int mPlanProcess;         //任务进度，需要由用户来进行填写
    private long mTimestamp;          //后期获取时间戳当完成界面逻辑时具体跟进
    private String mUserID;

    public Plan(){
        mPlanID = UUID.randomUUID().toString();
    }

    public String getPlanID() {
        return mPlanID;
    }

    public String getPlanContent() {
        return mPlanContent;
    }

    public int getPlanType() {
        return mPlanType;
    }

    public int getPlanProcess() {
        return mPlanProcess;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public void setPlanContent(String planContent) {
        mPlanContent = planContent;
    }

    public void setPlanType(int planType) {
        mPlanType = planType;
    }

    public void setPlanProcess(int planProcess) {
        mPlanProcess = planProcess;
    }

    public void setUserID(String userID) {
        mUserID = userID;
    }
}