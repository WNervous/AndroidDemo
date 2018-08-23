package com.meevii.patterndemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.meevii.patterndemo.builder.ui.BuilderActivity;
import com.meevii.patterndemo.clone.ConcretePrototype;
import com.meevii.patterndemo.clone.ConcretePrototype1;
import com.meevii.patterndemo.clone.Prototype;
import com.meevii.patterndemo.clone.example.Client;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.builderMode)
    Button builderMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Prototype prototype = new ConcretePrototype();
        Prototype clones = prototype.clones();
        boolean equals = prototype.getClass() == clones.getClass();
        Log.d("MainActivity", equals + "_ConcretePrototype");

        Prototype prototype1 = new ConcretePrototype1();
        Prototype clones1 = prototype1.clones();
        boolean equals1 = prototype1.getClass() == clones1.getClass();
        Log.d("MainActivity", equals1 + "_ConcretePrototype1");

        Client.Test();
        Client.TestWithAttachment();
        com.meevii.patterndemo.clone.DeepClone.Client.DeepCloneTest();


        builderMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BuilderActivity.class));
            }
        });
    }


}
