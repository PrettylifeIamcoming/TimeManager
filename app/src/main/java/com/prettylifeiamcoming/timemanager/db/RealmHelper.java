package com.prettylifeiamcoming.timemanager.db;

import android.content.Context;

import io.realm.Realm;

public class RealmHelper {
    public static final String DB_NAME = "Sundial.realm";
    private Realm mRealm;

    public RealmHelper(Context context) {

        mRealm = Realm.getDefaultInstance();
    }

    public Realm getRealm(){

        return mRealm;
    }

    public void close(){
        if (mRealm!=null){
            mRealm.close();
        }
    }

    /**
     * add （增）
     */


    /**
     * delete （删）
     */


    /**
     * update （改）
     */


    /**
     * query （查）
     */
}
