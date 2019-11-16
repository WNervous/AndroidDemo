package com.wys.downloadmanagerdemo.download;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import com.wys.downloadmanagerdemo.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadService extends Service {

    public static final String KEY_TASK_ID           = "key_task_id";
    public static final String KEY_TASK_URL          = "key_task_url";
    private             String notificationChannelId = "com.meta.android.bobtail";
    private             String channelName           = "download";
    private final       int    DOWN_START            = 0;
    private final       int    DOWN_LOADING          = 1;
    private final       int    DOWN_SUCCESS          = 2;
    private final       int    DOWN_FAILED           = 3;
    /**
     * Timer 执行时间间隔
     */
    private final       int    TIMER_PERIOD          = 1500;

    private NotificationManager notificationManager;
    private RemoteViews         remoteViews;
    private int                 taskId  = -1;
    private String              taskUrl;
    private Handler             handler = new MyHandler();
    ExecutorService executor = Executors.newFixedThreadPool(3);

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        remoteViews = new RemoteViews(getPackageName(), R.layout.activity_main);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            taskId = intent.getIntExtra(KEY_TASK_ID, -1);
            taskUrl = intent.getStringExtra(KEY_TASK_URL);
        }
        if (taskId != -1 && !TextUtils.isEmpty(taskUrl)) {
            startDownload(taskId, taskUrl);
            createNotification();
        }
        return super.onStartCommand(intent, flags, startId);
    }


    private void startDownload(int taskId, String taskUrl) {
        //        showNotification();
        executor.execute(new DownloadRunnable(taskId, taskUrl));
    }


    private class DownloadRunnable implements Runnable {

        private String taskurl;

        public DownloadRunnable(int taskId, String taskUrl) {
            taskurl = taskUrl;
        }

        @Override
        public void run() {
            try {
                Message.obtain(handler, DOWN_LOADING).sendToTarget();
                int currentLength = 0;
                Log.d("DownloadService", "start");
                String path = Environment.getExternalStorageDirectory().getPath() + "/AAAAImg/";
                URL url = new URL("https://cecemain.xxwolo.com/xxdoc-android-release-offline-8.2.0.apk");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(10000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
                InputStream inputStream = urlConnection.getInputStream();
                Log.d("DownloadService", "length:" + urlConnection.getContentLength());
                byte[] buffer = new byte[1024];
                int len;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((len = inputStream.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                    currentLength += len;
//                    Message.obtain(handler, MSG_progress, ).sendToTarget();
                    Log.d("DownloadService", "currentLength:" + currentLength);
                }
                bos.close();
                //文件保存位置
                File saveDir = new File(path);
                if (!saveDir.exists()) {
                    saveDir.mkdir();
                }
                File file = new File(saveDir + File.separator + "12344.apk");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bos.toByteArray());
                fos.close();
                inputStream.close();
                urlConnection.disconnect();
                Log.d("DownloadService", "success:" + file.getAbsolutePath());
                if (executor.isTerminated()) {
                    stopSelf();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void showNotification() {
        Notification notification;
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContent(remoteViews);
        builder.setContentTitle("test");
        builder.setContentText("dddddddd");
        notification = builder.build();
        notificationManager.notify(1, notification);
    }

    private Notification createNotification() {
        Notification.Builder builder;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(notificationChannelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            builder = new Notification.Builder(this, notificationChannelId);
        } else {
            builder = new Notification.Builder(this);
        }
        builder.setOngoing(true);
        builder.setOnlyAlertOnce(true);
        builder.setContent(remoteViews);
        return builder.build();
    }


    private void updateRemote() {
        remoteViews.setProgressBar(R.id.progress, 100, 3, false);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void startDownloadTask(Context context, int taskId, String url) {
        Intent intent = new Intent(context.getApplicationContext(), DownloadService.class);
        intent.putExtra(KEY_TASK_ID, taskId);
        intent.putExtra(KEY_TASK_URL, url);
        context.startService(intent);
    }

    //////////////////////////////////////////////////////////////////////////////
    // inner class
    //////////////////////////////////////////////////////////////////////////////

    private class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DOWN_START:
                    break;
                case DOWN_LOADING:
                    break;
                case DOWN_FAILED:
                    break;
                case DOWN_SUCCESS:
                    break;

            }
        }
    }


    private class MyTimer extends TimerTask {

        @Override
        public void run() {

        }
    }


}
