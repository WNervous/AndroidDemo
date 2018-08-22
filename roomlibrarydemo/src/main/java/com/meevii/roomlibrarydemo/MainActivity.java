package com.meevii.roomlibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.meevii.roomlibrarydemo.data.Repo;
import com.meevii.roomlibrarydemo.data.Reposity;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    Reposity reposity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reposity = new Reposity();
        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repo repo = new Repo();
                repo.setId(1);
                repo.setName("");
                repo.setUrl("Tomurl");
                reposity.insert(MainActivity.this, repo);
            }
        });
        findViewById(R.id.getAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disposable subscribe = reposity.getAll(MainActivity.this).subscribe(new Consumer<List<Repo>>() {
                    @Override
                    public void accept(List<Repo> repos) throws Exception {
                        Log.d("MainActivity", repos.toString());
                    }
                });
            }
        });

        findViewById(R.id.insertSame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repo repo = new Repo();
                repo.setId(1);
                repo.setName("JERRY");
                reposity.insert(MainActivity.this, repo);
            }
        });

        findViewById(R.id.queryExist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disposable mainActivity = reposity.query(MainActivity.this, 1).subscribe(new Consumer<Repo>() {
                    @Override
                    public void accept(Repo repo) {
                        Log.d("MainActivity", repo.toString());
                    }
                });
            }
        });
        findViewById(R.id.queryNoExist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disposable mainActivity = reposity.query(MainActivity.this, 4).subscribe(new Consumer<Repo>() {
                    @Override
                    public void accept(Repo repo) {
                        Log.d("MainActivity", repo.toString());
                    }
                });
            }
        });
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repo repo = new Repo();
                repo.setId(1);
                repo.setName("WO");
                repo.setUrl("Tomurl");
                reposity.update(MainActivity.this, repo);
            }
        });
    }
}
