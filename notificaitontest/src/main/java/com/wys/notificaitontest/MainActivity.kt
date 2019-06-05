package com.wys.notificaitontest

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendNotification.setOnClickListener {
            sendNotify()
        }
    }

    private fun sendNotify() {
        val intent = Intent(this, MainActivity::class.java)
        var pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManagerCompat = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = Notification.Builder(this, "sss")
        notification.setSmallIcon(R.mipmap.ic_launcher).setTicker("接收到一条新的消息").setContentText("测测星座").setContentTitle("title").setAutoCancel(true).setChannelId("sss").setContentIntent(pi)
        notificationManagerCompat.notify(1, notification.build())
    }

}
