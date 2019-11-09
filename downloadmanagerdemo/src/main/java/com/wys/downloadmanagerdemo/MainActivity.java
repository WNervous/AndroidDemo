package com.wys.downloadmanagerdemo;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.wys.downloadmanagerdemo.download.DownloadService;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;


public class MainActivity extends AppCompatActivity {
    private String notificationChannelId = "download";
    RemoteViews         remoteViews;
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Log.d("MainActivity:pack", getPackageName());
        remoteViews = new RemoteViews(getPackageName(), R.layout.notification_download);
        ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
    }

    public void download(View view) {
        int i = 0;
        while (i < 100) {
            Notification notification= createNotification(i);
            notificationManager.notify(2000, notification);
            i++;
        }

        //        DownloadService.startDownloadTask(this, 0, "test1");
    }

    public void downloadvideo(View view) {
        DownloadService.startDownloadTask(this, 0, "test2");
    }

    public void downloadapk(View view) {
        DownloadService.startDownloadTask(this, 0, "test3");
    }


    private Notification createNotification(int progress) {
        Notification.Builder builder;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(notificationChannelId, "jjjjj", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            builder = new Notification.Builder(this, notificationChannelId);
        } else {
            builder = new Notification.Builder(this);
        }

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_download);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentText("content");
        builder.setContentTitle("TITLE");
        builder.setContent(remoteViews);
        builder.setProgress(100, progress, false);
        //        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        //        builder.setContent(remoteViews);
        return builder.build();
    }
}








