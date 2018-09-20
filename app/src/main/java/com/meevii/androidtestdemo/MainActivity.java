package com.meevii.androidtestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    LongImageButton imageButton;
    LongClickButton longClick;
    int i;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        imageButton = findViewById(R.id.imageButton);
        longClick = findViewById(R.id.longClick);
        textView = findViewById(R.id.textClick);
        imageButton.setAddListener(new LongImageButton.AddListener() {
            @Override
            public void add(int add) {
                Log.d("MainActivity", "add:" + add);
                seekBar.setProgress(add);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                textView.setText(String.valueOf(i));
                Log.d("MainActivity", "imageButton:" + i);
            }
        });

        longClick.setLongClickRepeatListener(new LongClickButton.LongClickRepeatListener() {
            @Override
            public void repeatAction() {
                i++;
                textView.setText(String.valueOf(i));
                Log.d("MainActivity", "add:" + i);
            }
        });

        longClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(String.valueOf(i));
                Log.d("MainActivity", "onClick");
            }
        });
    }
}
