package com.meevii.rxdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * 06.RxJava条件型操作符。
 * 上游  ------->    条件操作(往右边流向的时候，条件判断)  ---------->  下游
 * 操作符：RxJava说的很神奇，API的调用， RxJava改变开发者的思维
 * RxJava == Java编程
 * 语法 == 操作符API
 * 所有的操作符都学会了，才能真正的证明把RxJava的使用学会了 == Java所有的语法学会，Java入门
 * RxJava作为: Android之神 2010 开源过Android开源的框架库， RxJava巅峰之作
 * <p>
 * All: 如同 if 那样的功能 ：全部为true，才是true，只要有一个为false，就是false.
 * contains 是否包含
 * any 全部为 false，才是false， 只要有一个为true，就是true
 * 如果使用了条件操作符，下一层，接收的类型 就是条件类型(Boolean)
 */

public class CondicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condication);
    }

    public static void open(Context context) {
        context.startActivity(new Intent(context, CondicationActivity.class));
    }

    public void all(View view) {
        String[] abc = {"a", "b", "c"};
        Observable.fromArray(abc).all(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                return s.isEmpty();
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean s) {
                Log.d("CondicationActivity", "all:" + s);
            }
        });

        Observable.fromArray(abc).any(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                return s == "d";
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean s) {
                Log.d("CondicationActivity", "any:" + s);
            }
        });

        Observable.fromArray(abc).contains("a").subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean s) {
                Log.d("CondicationActivity", "contains:" + s);
            }
        });


    }
}
// all
// contain
//any


/////////////// 合并操作符
// startwith
// concatwith // concat
// merge
//zip
