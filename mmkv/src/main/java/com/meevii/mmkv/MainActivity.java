package com.meevii.mmkv;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tencent.mmkv.MMKV;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MMKV kv = MMKV.defaultMMKV();
        kv.encode("bool", true);
        boolean bValue = kv.decodeBool("bool");
        Log.d("Main", "bValue:" + bValue);

        kv.encode("int", Integer.MIN_VALUE);
        int iValue = kv.decodeInt("int");

        Log.d("Main", "iValue:" + iValue);
        kv.encode("string", "Hello from mmkv");
        String str = kv.decodeString("string");
        Log.d("Main", "str:" + str);

        String hello = kv.decodeString("hello");
        Log.d("Main", "hello:" + hello);


        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        defaultSharedPreferences.edit().putInt("abc", 11111).apply();
        defaultSharedPreferences.edit().putLong("ccccc", 11111).apply();
        defaultSharedPreferences.edit().putBoolean("kkkkkkk", false).apply();
        defaultSharedPreferences.edit().putString("ghhhh", "sssssss").apply();

        kv.importFromSharedPreferences(defaultSharedPreferences);
        defaultSharedPreferences.edit().clear().apply();

        String[] all = kv.allKeys();
        for (String string : all) {
            Log.d("Main", "string:" + string);
        }
    }
}
