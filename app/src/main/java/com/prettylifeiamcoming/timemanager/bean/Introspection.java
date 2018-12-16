package com.prettylifeiamcoming.timemanager.bean;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Introspection extends RealmObject implements Serializable {
    @PrimaryKey
    private String mIntrospectionID;           //反省ID
    private String mIntrospectionMyself;       //自我反省的内容
    private int mIntrospectionType;            //反省的类型，根据任务中进行分配类别，用switch匹配类型，（1,2,3）对应（日省，月省，年省）
    private long mTimestamp;             //后期获取时间戳当完成界面逻辑时具体跟进
    private String mUserID;

    public Introspection() {
        mIntrospectionID = UUID.randomUUID().toString();
    }

    public String getIntrospectionID() {
        return mIntrospectionID;
    }

    public String getIntrospectionMyself() {
        return mIntrospectionMyself;
    }

    public int getIntrospectionType() {
        return mIntrospectionType;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public void setIntrospectionMyself(String introspectionMyself) {
        mIntrospectionMyself = introspectionMyself;
    }

    public void setUserID(String userID) {
        mUserID = userID;
    }
}