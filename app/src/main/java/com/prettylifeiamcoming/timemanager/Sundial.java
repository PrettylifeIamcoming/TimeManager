package com.prettylifeiamcoming.timemanager;

import android.app.Application;

import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Sundial extends Application {
    private static Sundial sundial;

    @Override
    public void onCreate() {
        super.onCreate();
        sundial = this;
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(RealmHelper.DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    public static Sundial getInstance() {
        return sundial;
    }

}