package com.meevii.rxdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author wys
 *
 * create()
 * from()
 * just()
 * interval()
 * timer()
 * empty()
 * error()
 * range()
 * repeat()
 * defer()
 * never()
 */
public class OperateActivity extends AppCompatActivity {

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate);


        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> observableEmitter) {

            }
        });
        Observable.just(1);
        Observable.empty();
        Observable.fromArray(new int[]{1, 2, 3});
        Observable.range(1, 3);
        Observable.interval(1, TimeUnit.SECONDS);
        Observable.intervalRange(10, 30, 0, 2, TimeUnit.SECONDS);
    }

    public static void open(Context context) {
        context.startActivity(new Intent(context, OperateActivity.class));
    }

    /**
     * 创建行操作符
     * create
     *
     * @param view
     */
    public void bt1(View view) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) {
                observableEmitter.onNext("sssssss");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(String s) {
                Log.d("Main", s);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    // just
    public void bt2(View view) {
        Observable.just("A", "b").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                Log.d("Main", s);
            }
        });
    }

    // fromarray
    public void bt3(View view) {
        String[] abc = {"a", "b", "c"};
        Observable.fromArray(abc).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                Log.d("Main", s);
            }
        });
    }

    public void bt4(View view) {

        Observable.empty().subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                Log.d("Main", "emptyonSubscribe");
            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                Log.d("Main", "onComplete");
            }
        });
    }

    public void bt5(View view) {
//        Observable.intervalRange(1, 5, 0, 1000, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) {
//                Log.d("Main", "aLong" + aLong);
//            }
//        });
        //  timer 是定时器   // interval  为周期性任务
        Observable.timer(1,TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                Log.d("Main", "aLong" + aLong);
            }
        });

    }
}