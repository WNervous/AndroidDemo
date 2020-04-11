package com.meevii.rxdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

/**
 * 07.RxJava合并型操作符。
 * 两个或者多个 被观察者 合并。
 * 1.startWait，concatWith ：先创建被观察者，然后再组合其他的被观察者，然后再订阅
 * 2.concat/merge/zip：直接合并多个被观察者，然后订阅
 * 细节：
 * a:startWith 先执行 startWith
 * b:concatWait 后执行 concatWait括号里面的被观察者
 * c:concat 是按照顺序依次执行 最多四个被观察者进行合并
 * d:merge 并列执行的，（演示并列的执行，所以学了intervalRange） 最多四个被观察者进行合并
 * e:zip 需要对应关系 需要对应，如果不对应，会被忽略的， 最多9个被观察者 进行合并
 */

public class MergeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);
    }

    public static void open(Context context) {
        context.startActivity(new Intent(context, MergeActivity.class));
    }

    public void startWith(View view) {
        Observable.just(1).startWith(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("MergeActivity", integer.toString());
            }
        });
    }

    public void concatWith(View view) {
        Observable.just(1).concatWith(new ObservableSource<Integer>() {
            @Override
            public void subscribe(Observer<? super Integer> observer) {
                observer.onNext(22);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("MergeActivity", integer.toString());
            }
        });
    }

    public void concat(View view) {
        Observable.concat(Observable.just(1), Observable.just(2), Observable.just(3), Observable.just(4))
                  .subscribe(new Consumer<Integer>() {
                      @Override
                      public void accept(Integer integer) throws Exception {
                          Log.d("MergeActivity", integer.toString());
                      }
                  });
    }


    public void merge(View view) {
        Observable.merge(Observable.just(1), Observable.just(1), Observable.just(1), Observable.just(1))
                  .subscribe(new Consumer<Integer>() {
                      @Override
                      public void accept(Integer integer) throws Exception {
                          Log.d("MergeActivity", integer.toString());
                      }
                  });

    }

    public void zip(View view) {


    }
}
