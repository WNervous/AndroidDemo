package com.meevii.retroft.servcie;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxHelper {
    public static Scheduler applyIo() {
        return Schedulers.io();
    }

    public static Scheduler applyMainThread() {
        return AndroidSchedulers.mainThread();
    }
}
