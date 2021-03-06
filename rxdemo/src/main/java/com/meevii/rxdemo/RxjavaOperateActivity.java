package com.meevii.rxdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxjavaOperateActivity extends AppCompatActivity {
    public String TAG = "RxjavaOperateActivity";
    @BindView(R.id.map)
    Button map;
    @BindView(R.id.flatMap)
    Button flatMap;
    @BindView(R.id.zip)
    Button zip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        ButterKnife.bind(this);
    }

    /**
     * map 操作符
     * 通过Map, 可以将上游发来的事件转换为任意的类型, 可以是一个Object, 也可以是一个集合
     */

    @OnClick(R.id.map)
    public void map() {
        Disposable subscribe = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "map:" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                Log.d(TAG, s);
            }
        });
    }

    /**
     * FlatMap将一个发送事件的上游Observable变换为多个发送事件的Observables，然后将它们发射的事件合并后放进一个单独的Observable里.
     * 注意： flatMap并不保证事件的顺序, 并不是事件1就在事件2的前面. 如果需要保证顺序则需要使用concatMap.
     */

    @OnClick(R.id.flatMap)
    public void flatMap() {
        Disposable subscribe = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                Log.d(TAG, s);
            }
        });
    }

    /**
     * zip 操作符
     * Zip通过一个函数将多个Observable发送的事件结合到一起，然后发送这些组合到一起的事件.
     * 它按照严格的顺序应用这个函数。它只发射与发射数据项最少的那个Observable一样多的数据。
     */
    @OnClick(R.id.zip)
    public void zio() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emitter 1");
                emitter.onNext(1);
                Thread.sleep(1000);

                Log.d(TAG, "emitter 2");
                emitter.onNext(2);
                Thread.sleep(1000);

                Log.d(TAG, "emitter 3");
                emitter.onNext(3);
                Thread.sleep(1000);

                Log.d(TAG, "emitter 4");
                emitter.onNext(4);
                Thread.sleep(1000);
//                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "A");
                emitter.onNext("A");
                Thread.sleep(1000);
                Log.d(TAG, "B");
                emitter.onNext("B");
                Thread.sleep(1000);
                Log.d(TAG, "C");
                emitter.onNext("C");
                Thread.sleep(1000);

            }
        }).subscribeOn(Schedulers.io());

        Disposable subscribe = Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + "_" + s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "zip_" + s);
            }
        });

    }

    public static void open(Context context) {
        context.startActivity(new Intent(context, RxjavaOperateActivity.class));
    }
}
