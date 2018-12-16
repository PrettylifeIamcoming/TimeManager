package com.prettylifeiamcoming.timemanager;

import android.app.Application;
import android.content.Context;

import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Sundial extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(RealmHelper.DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);

        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}