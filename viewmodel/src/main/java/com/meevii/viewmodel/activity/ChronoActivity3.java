package com.meevii.viewmodel.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.meevii.viewmodel.R;
import com.meevii.viewmodel.model.LiveDataTimerViewModel;

public class ChronoActivity3 extends AppCompatActivity {

    LiveDataTimerViewModel liveDataTimerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono3);
        liveDataTimerViewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel.class);
        subscribe();
    }

    private void subscribe() {
        liveDataTimerViewModel.getElapsedTime().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long aLong) {
                String newText = ChronoActivity3.this.getResources().getString(
                        R.string.seconds, aLong);
                ((TextView) findViewById(R.id.timer_textview)).setText(newText);
                Log.d("ChronoActivity3", "Updating timer");
            }
        });


    }
}
