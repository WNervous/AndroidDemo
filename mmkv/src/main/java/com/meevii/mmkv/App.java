package com.meevii.mmkv;

import android.app.Application;
import android.util.Log;

import com.tencent.mmkv.MMKV;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String rootDir = MMKV.initialize(this);
        Log.d("Main", rootDir);
    }
}
