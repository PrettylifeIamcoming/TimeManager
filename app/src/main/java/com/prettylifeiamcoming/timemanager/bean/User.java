package com.prettylifeiamcoming.timemanager.bean;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;

public  class User extends RealmObject implements Serializable {
    private String mUserID;
    private String mUserTelephone;
    private String mPassword;
    private String mUserName;
    private String mUserSignature;
    //private ImageView mUserHeadPortrait;             //后期添加

    public User(){
        mUserID = UUID.randomUUID().toString();
    }

    public void setmUserTelephone(String userTelephone) {
        mUserTelephone = userTelephone;
    }

    public void setmPassword(String password) {
        mPassword = password;
    }

    public void setmUserName(String userName) {
        mUserName = userName;
    }

    public void setmUserSignature(String userSignature) {
        mUserSignature = userSignature;
    }

    /*
    public void setmUserHeadPortrait(ImageView userHeadPortrait) {
        mUserHeadPortrait = userHeadPortrait;
    }*/

    public String getmUserID() {
        return mUserID;
    }

    public String getmUserTelephone() {
        return mUserTelephone;
    }

    public String getmPassword() {
        return mPassword;
    }

    public String getmUserName() {
        return mUserName;
    }

    public String getmUserSignature() {
        return mUserSignature;
    }

    /*
    public ImageView getmUserHeadPortrait() {
        return mUserHeadPortrait;
    }*/
}