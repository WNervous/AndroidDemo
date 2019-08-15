package com.wys.realmdemo;

import android.app.Application;

import io.realm.Realm;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        // 默认不设置configuration
//        RealmConfiguration defaultConfiguration = new RealmConfiguration.Builder()
//                .modules().build();

        // 设置configuration

    }
}
