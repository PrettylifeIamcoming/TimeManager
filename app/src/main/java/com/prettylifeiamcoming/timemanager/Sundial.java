package com.prettylifeiamcoming.timemanager;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import com.prettylifeiamcoming.timemanager.db.RealmHelper;


/**
 * 创建名为Sundial的realm数据库
 */

public class Sundial extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration=new RealmConfiguration.Builder()
                .name(RealmHelper.DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}

