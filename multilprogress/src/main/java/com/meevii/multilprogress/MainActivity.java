package com.meevii.multilprogress;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyConnection myConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.startService).setOnClickListener(this);
        findViewById(R.id.bindService).setOnClickListener(this);
        findViewById(R.id.stopService).setOnClickListener(this);
        findViewById(R.id.unbindService).setOnClickListener(this);
        myConnection = new MyConnection();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startService:
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.bindService:
                Intent BIDN = new Intent(this, MyService.class);
                bindService(BIDN, myConnection, BIND_AUTO_CREATE);
                break;
            case R.id.stopService:
                Intent stop = new Intent(this, MyService.class);
                stopService(stop);
                break;
            case R.id.unbindService:
                unbindService(myConnection);
                break;
        }
    }

    private class MyConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("MyService", "onServiceConnected");
            ((MyService.MyBinder) service).startDownload();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("MyService", "onServiceDisconnected");
        }
    }
}
