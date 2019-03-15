package com.example.realmexample;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myDB.realm") // db name must have .realm
                .schemaVersion(0)   //version
                .deleteRealmIfMigrationNeeded()  //after migration delete everything then make new one
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
