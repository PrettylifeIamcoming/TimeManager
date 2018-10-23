package com.prettylifeiamcoming.timemanager.bean;

import java.io.Serializable;
import java.util.UUID;

public class Introspection implements Serializable {
    private UUID mIntrospectionID;
    private String mIntrospectionMyself;
    private int mIntrospectionType;
    private long mTimestamp;             //后期获取时间戳当完成界面逻辑时具体跟进

    public Introspection(){
        mIntrospectionID = UUID.randomUUID();
    }

    public void setmIntrospectionMyself(String introspectionMyself) {
        mIntrospectionMyself = introspectionMyself;
    }

    public UUID getmIntrospectionID() {
        return mIntrospectionID;
    }

    public String getmIntrospectionMyself() {
        return mIntrospectionMyself;
    }

    public int getmIntrospectionType() {
        return mIntrospectionType;
    }

    public long getmTimestamp() {
        return mTimestamp;
    }
}