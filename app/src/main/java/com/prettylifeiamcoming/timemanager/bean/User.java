package com.prettylifeiamcoming.timemanager.bean;

import android.widget.ImageView;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject implements Serializable {
    @PrimaryKey
    private UUID mUserID;
    private String mUserTelephone;
    private String mPassword;
    private String mUserName;
    private String mUserSignature;
    private ImageView mUserHeadPortrait;

    public User(){
        mUserID = UUID.randomUUID();
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

    public void setmUserHeadPortrait(ImageView userHeadPortrait) {
        mUserHeadPortrait = userHeadPortrait;
    }

    public UUID getmUserID() {
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

    public ImageView getmUserHeadPortrait() {
        return mUserHeadPortrait;
    }
}