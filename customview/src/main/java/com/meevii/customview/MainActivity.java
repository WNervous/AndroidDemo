package com.meevii.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.meevii.customview.practice.ProgressView;

public class MainActivity extends AppCompatActivity {
    ProgressView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        progressView = findViewById(R.id.progress);
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressView.setCurrentProgress(50);
//            }
//        });
    }
}
