package com.meevii.viewmodel;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Chronometer;

import com.meevii.viewmodel.model.ChronometerViewModel;

/**
 * 问题： 当不使用viewModel 的时候 旋转屏幕，计时器每次都会从0 开始计时 。
 * 解决放案：添加ChronometerViewModel
 */
public class MainActivity extends AppCompatActivity {

    private ChronometerViewModel chronometerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometerViewModel = ViewModelProviders.of(this).get(ChronometerViewModel.class);
        Chronometer chronometer = findViewById(R.id.chronometer);
        if (chronometerViewModel.getStartTime() == null) {
            // If the start date is not defined, it's a new ViewModel so set it.
            long startTime = SystemClock.elapsedRealtime();
            chronometerViewModel.setStartTime(startTime);
            chronometer.setBase(startTime);
        } else {
            // Otherwise the ViewModel has been retained, set the chronometer's base to the original
            // starting time.
            Log.d("MainActivity", "test：" + chronometerViewModel.getStartTime());
            chronometer.setBase(chronometerViewModel.getStartTime());
        }
        chronometer.start();
    }
}
