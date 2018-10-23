package com.prettylifeiamcoming.timemanager.bean;

import java.io.Serializable;
import java.util.UUID;

public class Plan implements Serializable {
    private UUID mPlanID;
    private String mPlanContent;
    private int mPlanType;
    private int mPlanProcess;
    private long mTimestamp;          //后期获取时间戳当完成界面逻辑时具体跟进

    public Plan(){
        mPlanID = UUID.randomUUID();
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

    public UUID getmPlanID() {

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