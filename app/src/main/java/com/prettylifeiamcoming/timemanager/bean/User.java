package com.prettylifeiamcoming.timemanager.bean;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public  class User extends RealmObject implements Serializable {
    @PrimaryKey
    private String mUserID;                            //用户的ID，一个人只拥有一个
    private String mUserTelephone;                     //用户的电话号码
    private String mPassword;                          //用户的密码
    private String mUserName;                          //用户名称
    private String mUserSignature;                     //用户的签名
    //private ImageView mUserHeadPortrait;             //后期添加

    public User(){
        mUserID = UUID.randomUUID().toString();
    }

    public String getUserID() {
        return mUserID;
    }

    public String getUserTelephone() {
        return mUserTelephone;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getUserSignature() {
        return mUserSignature;
    }

    public void setUserTelephone(String userTelephone) {
        mUserTelephone = userTelephone;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public void setUserSignature(String userSignature) {
        mUserSignature = userSignature;
    }
}