package com.meevii.rxdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

/**
 * @author wys
 * 04.RxJava变换型操作符。
 * 上游  ------->    变换操作(往右边流向的时候，进行变换)  ---------->  下游
 * 1.map       把上一层Int  Int变换String                                           观察者String类型。
 * 2.flatMap   把上一层Int  Int变换ObservableSource<String>{还可以再次发射多次事件}   观察者String类型。 不排序的
 * 3.concatMap 把上一层Int  Int变换ObservableSource<Bitmap>{还可以再次发射多次事件}   观察者Bitmap类型。 排序的
 * 4.groupBy   把上一层Int  Int变换String(高端配置电脑)     观察者GroupedObservable类型 {key="高端", 细节再包裹一层}
 * 5.buffer    100个事件 Integer     .buffer(20)    观察者List<Integer>==五个集合
 */
public class BianhuaOperateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bianhua);
    }

    public static void open(Context context) {
        context.startActivity(new Intent(context, BianhuaOperateActivity.class));
    }

    public void C1(View view) {
        Observable.just(1).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "变化前是：" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String integer) {
                Log.d("BianhuaOperateActivity", integer);
            }
        });
    }

    public void C2(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) {
                for (int i = 0; i < 3; i++) {
                    observableEmitter.onNext(i);
                }
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(final Integer integer) {
                return Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> observableEmitter) {
                        observableEmitter.onNext(integer + "变化操作符");
                    }
                });
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String integer) {
                Log.d("BianhuaOperateActivity", integer);
            }
        });

    }

    public void C3(View view) {
        Observable.range(10, 10).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(final Integer integer) {
                return Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> observableEmitter) {
                        observableEmitter.onNext(integer + "concatmap");
                    }
                });
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String integer) {
                Log.d("BianhuaOperateActivity", integer);
            }
        });

    }

    public void C4(View view) {

        Observable.just(1, 2, 3, 4, 5, 6, 7).groupBy(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return integer > 6 ? "大树" : "小数";
            }
        }).subscribe(new Consumer<GroupedObservable<String, Integer>>() {
            @Override
            public void accept(GroupedObservable<String, Integer> groupedObservable) {
                final String key = groupedObservable.getKey();
                Log.d("BianhuaOperateActivity", key);
                groupedObservable.subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        Log.d("BianhuaOperateActivity", "key:" + key + "jjj" + integer);
                    }
                });
            }
        });
    }

    public void C5(View view) {
        Observable.range(1, 111).buffer(20).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) {
                Log.d("BianhuaOperateActivity", integers.toString());
            }
        });

    }
}
