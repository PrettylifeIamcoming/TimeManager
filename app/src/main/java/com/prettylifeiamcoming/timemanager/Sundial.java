package com.prettylifeiamcoming.timemanager;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Sundial extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("Sundial.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}