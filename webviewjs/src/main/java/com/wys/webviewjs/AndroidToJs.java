package com.wys.webviewjs;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class AndroidToJs extends Object {

    @JavascriptInterface
    public void hello(String msg) {
        Log.d("MainActivity", "JS调用了Android的hello方法");
    }
}
