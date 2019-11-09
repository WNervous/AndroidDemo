package com.meevii.alarmmanagerdemo.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.meevii.alarmmanagerdemo.alarm.receiver.VideoReceiver;

import java.util.Calendar;


public class AlarmUtils {

    private static AlarmManager alarmManager;
    private static long intervalTime = 24 * 3600 * 100;//一天的间隔

    public static void startAlarm(Context context, long starTime) {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, VideoReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
        alarmManager.setRepeating(AlarmManager.RTC, timeInMillis, intervalTime, pi);
    }

    public static void cancel(Context context) {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, VideoReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pi);
    }

}
