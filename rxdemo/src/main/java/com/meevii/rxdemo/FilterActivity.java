package com.meevii.rxdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * 05.RxJava过滤型操作符。
 * 上游  ------->    过滤操作(往右边流向的时候，进行过滤)  ---------->  下游
 * 1.filter 如果是false全部都发射给下游，如果是true，全部都不发射给下游。
 * 2.take ：只有再定时器运行基础上 加入take过滤操作符，才有take过滤操作符的价值。
 * 3.distinct过滤重复事件。
 * 4.elementAl 指定发射事件内容，如果无法指定，有默认的事件。
 */
public class FilterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
    }

    public static void open(Context context) {
        context.startActivity(new Intent(context, FilterActivity.class));
    }

    public void filter(View view) {
        Observable.just(1, 2, 3, 4, 5, 6).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 3;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                Log.d("FilterActivity", integer.toString());
            }
        });

        // 上游
        Observable.just("三鹿", "合生元", "飞鹤")

                  .filter(new Predicate<String>() {
                      @Override
                      public boolean test(String s) throws Exception {
                          // return true; // 不去过滤，默认全部都会打印
                          // return false; // 如果是false 就全部都不会打印
                          if ("三鹿".equals(s)) {
                              return false; // 不合格
                          }
                          return true;
                      }
                  })

                  // 订阅
                  .subscribe(new Consumer<String>() { // 下游
                      @Override
                      public void accept(String s) throws Exception {
                          Log.d("FilterActivity", "accept: " + s);
                      }
                  });

    }

    /**
     * ofType
     * 选择 某种类型的事件过滤
     *
     * @param view
     */
    public void ofType(View view) {
        Observable.just(1, 2, "3").ofType(Integer.class).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                Log.d("FilterActivity", "accept: " + integer);
            }
        });
    }

    public void skip(View view) {
        Observable.just(1, 2, 3, 4, 5, 6).skip(1).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                Log.d("FilterActivity", "accept: " + integer);
            }
        });

        Observable.just(1, 2, 3, 4, 5, 6).skipLast(1).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                Log.d("FilterActivity", "accept: " + integer);
            }
        });

        Observable.intervalRange(1, 6, 0, 1, TimeUnit.SECONDS)
                  .skip(2, TimeUnit.SECONDS)
                  .subscribe(new Consumer<Long>() {
                      @Override
                      public void accept(Long aLong) {
                          Log.d("FilterActivity", "accept: " + aLong);
                      }
                  });

        Observable.intervalRange(1, 6, 0, 1, TimeUnit.SECONDS)
                  .skipLast(2, TimeUnit.SECONDS)
                  .subscribe(new Consumer<Long>() {
                      @Override
                      public void accept(Long aLong) {
                          Log.d("FilterActivity", "accept: " + aLong);
                      }
                  });


    }

    /**
     * distinct 过滤重复的事件
     * <p>
     * distinctUntilChanged   过滤事件序列中 连续重复的事件
     *
     * @param view
     */
    public void distinct(View view) {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(1);
                emitter.onNext(4);
            }
        }).distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer o) throws Exception {
                Log.d("FilterActivity", "accept: " + o);
            }
        });

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(3);
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).distinctUntilChanged().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer o) throws Exception {
                Log.d("FilterActivity", "accept: " + o);
            }
        });
    }

    //    根据 指定时间 过滤事件
    public void throttle(View view) {
        Observable.intervalRange(0, 10, 0, 1, TimeUnit.SECONDS)
                  //                  .throttleFirst(5, TimeUnit.SECONDS)    只发送5s内第一个时间     输出结果  0 ，6
                  //                  .throttleLast(5, TimeUnit.SECONDS)   输出结果 5
                  .throttleLatest(5, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                Log.d("FilterActivity", "accept: " + aLong);
            }
        });

    }

    public void elementAt(View view) {
        Observable.intervalRange(0, 10, 0, 1, TimeUnit.SECONDS).elementAt(1).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                Log.d("FilterActivity", "accept: " + aLong);
            }
        });

        Observable.intervalRange(0, 10, 0, 1, TimeUnit.SECONDS)
                  .lastElement()
                  .subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                Log.d("FilterActivity", "accept: " + aLong);
            }
        });
    }
}
