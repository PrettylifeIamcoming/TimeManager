package com.prettylifeiamcoming.timemanager.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;

public class RealmObjectLab {
    private static RealmObjectLab sRealmObjectLab;

    private List<RealmObject> mRealmObjects;

    private RealmObjectLab(Context context) {
        mRealmObjects = new ArrayList<>();
    }

    public static RealmObjectLab get(Context context) {
        if (sRealmObjectLab == null) {
            sRealmObjectLab = new RealmObjectLab(context);
        }

        return sRealmObjectLab;
    }

    public List<RealmObject> getRealmObjects() {
        return mRealmObjects;
    }
}
