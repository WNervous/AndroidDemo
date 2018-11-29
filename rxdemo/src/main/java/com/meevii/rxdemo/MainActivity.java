package com.meevii.rxdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    @BindView(R.id.observe)
    Button button;
    @BindView(R.id.chainObserve)
    Button chainObserve;
    @BindView(R.id.chainConsumer)
    Button chainConsumer;
    @BindView(R.id.threadTest)
    Button threadTest;
    @BindView(R.id.opearte)
    Button opearte;

    Observable<Integer> observable;
    Observer<Integer>   observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initObser();
        initSingle();
    }

    private void initSingle() {
        Single.just(1).map(new Function<Integer, String>() {

            @Override
            public String apply(Integer integer) {
                return String.valueOf(integer);
            }
        }).map(new Function<String, Integer>() {

            @Override
            public Integer apply(String s) {
                return null;
            }
        }).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer s) {
                Log.d(TAG, s + "");
            }

            @Override
            public void onError(Throwable e) {
                //singlejust 不会掉用onError
            }
        });

    }

    private void initObser() {
        observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("observer", "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("observer", "integer :" + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("observer", e.toString());
            }

            @Override
            public void onComplete() {
                Log.d("observer", "onComplete");
            }
        };
    }

    /**
     * 分解 上游 ，下游 ，订阅关系
     */
    @OnClick(R.id.observe)
    public void observe() {
        observable.subscribe(observer);
    }

    /**
     * subscribe(） 带observe 的重载方法
     */
    @OnClick(R.id.chainObserve)
    public void chainObserve() {
        /**
         * 1.当上游 发送onComplete（） 后，上游的其他事件会继续发送，当下游接受到onComplete（）时将不再接收 事件 ：
         * 2.发送 onError() 同上。
         * 注意：onComplete 和onError 唯一并且互斥
         *
         * 3.Dispose 当下游mDisposable.dispose() 后，会切断下上游的订阅关系，导致不会接收到以后的事件
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                Log.d(TAG, "1");
                emitter.onNext(2);
                Log.d(TAG, "2");
                emitter.onNext(3);
                Log.d(TAG, "3");
                emitter.onComplete();
                Log.d(TAG, "onComplete");
                emitter.onNext(4);
                Log.d(TAG, "4");
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;
            int i = 0;

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                i++;
                if (i == 2) {
                    Log.d(TAG, "dispose");
                    mDisposable.dispose();
                    Log.d(TAG, "isDisposed : " + mDisposable.isDisposed());
                }
                Log.d(TAG, "integer :" + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }

    /**
     * 带有一个Consumer参数的方法表示下游只关心onNext事件, 其他的事件我假装没看见, 因此我们如果只需要onNext事件可以这么写:
     */
    @OnClick(R.id.chainConsumer)
    public void chainConsumer() {
        Disposable subscribe = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                Log.d(TAG, "Integer:" + integer);
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////Rxjava 线程控制
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * subscribeOn(Schedulers.newThread()) // 指定的是上游发送事件的线程
     * observeOn(AndroidSchedulers.mainThread())//指定的是下游接收事件的线程
     * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
     * 但是多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
     * <p>
     * <p>
     * Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
     * Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
     * Schedulers.newThread() 代表一个常规的新线程
     * AndroidSchedulers.mainThread() 代表Android的主线程
     */
    @OnClick(R.id.threadTest)
    public void threadTest() {
        Disposable subscribe = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onComplete();
                Log.d(TAG, "Thread:Observable" + Thread.currentThread().getName());
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                Log.d(TAG, "Integer:" + integer);
                Log.d(TAG, "Thread:subscribe" + Thread.currentThread().getName());
            }
        });
        ////////////////////////////////////
        Disposable subscribe1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onComplete();
                Log.d(TAG, "Thread:Observable" + Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                Log.d(TAG, "Integer:" + integer);
                Log.d(TAG, "Thread:subscribe" + Thread.currentThread().getName());
            }
        });
    }

    @OnClick(R.id.opearte)
    public void opearte() {
        RxjavaOperateActivity.open(this);
    }

}