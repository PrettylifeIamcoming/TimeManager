package com.prettylifeiamcoming.timemanager.bean;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Introspection extends RealmObject implements Serializable {
    @PrimaryKey
    private String mIntrospectionID;
    private String mIntrospectionMyself;
    private int mIntrospectionType;
    private long mTimestamp;             //后期获取时间戳当完成界面逻辑时具体跟进
    private String mUserId;

    public Introspection(){
        mIntrospectionID = UUID.randomUUID().toString();
    }

    public void setmIntrospectionMyself(String introspectionMyself) {
        mIntrospectionMyself = introspectionMyself;
    }

    public String getmIntrospectionID() {
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