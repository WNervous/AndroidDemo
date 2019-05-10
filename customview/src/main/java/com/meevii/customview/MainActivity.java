package com.meevii.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.meevii.customview.AutoNumber.AutoNumberTextView;
import com.meevii.customview.practice.ProgressView;

public class MainActivity extends AppCompatActivity {
    ProgressView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoNumberTextView autoNumberTextView = findViewById(R.id.AutoNumberTextView);
        autoNumberTextView.setNumber(100, false);
        SeekBar seekBar = findViewById(R.id.seekBar);
        final SlideBlock slideBlock = findViewById(R.id.slideBlock);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                slideBlock.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //        progressView = findViewById(R.id.progress);
        //        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                progressView.setCurrentProgress(50);
        //            }
        //        });
    }
}
