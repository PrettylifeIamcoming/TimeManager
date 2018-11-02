package com.prettylifeiamcoming.timemanager.bean;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;

public class Plan extends RealmObject implements Serializable {
    private String mPlanID;
    private String mPlanContent;
    private int mPlanType;
    private int mPlanProcess;
    private long mTimestamp;          //后期获取时间戳当完成界面逻辑时具体跟进
    private String mUserId;

    public Plan(){
        mPlanID = UUID.randomUUID().toString();
    }

    public void setmPlanContent(String planContent) {
        mPlanContent = planContent;
    }

    public void setmPlanType(int planType) {
        mPlanType = planType;
    }

    public void setmPlanProcess(int planProcess) {
        mPlanProcess = planProcess;
    }

    public String getmPlanID() {

        return mPlanID;
    }

    public String getmPlanContent() {
        return mPlanContent;
    }

    public int getmPlanType() {
        return mPlanType;
    }

    public int getmPlanProcess() {
        return mPlanProcess;
    }

    public long getmTimestamp() {
        return mTimestamp;
    }
}