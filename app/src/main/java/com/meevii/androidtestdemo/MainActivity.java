package com.meevii.androidtestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    LongImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        imageButton = findViewById(R.id.imageButton);
        imageButton.setAddListener(new LongImageButton.AddListener() {
            @Override
            public void add(int add) {
                Log.d("MainActivity", "add:" + add);
                seekBar.setProgress(add);
            }
        });
    }
}
