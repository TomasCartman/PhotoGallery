package com.dashboarder.photogallery

import android.app.Activity
import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationManagerCompat

private const val TAG = "NotificationReceiver"

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "received result: $resultCode")
        if(resultCode != Activity.RESULT_OK) {
            // A foreground activity canceled the broadcast
            return
        }
        val requestCode = intent?.getIntExtra(PollWorker.REQUEST_CODE, 0)
        val notification: Notification? = intent?.getParcelableExtra(PollWorker.NOTIFICATION)

        val notificationManager = context?.let { NotificationManagerCompat.from(it) }
        if (requestCode != null && notification != null) {
            notificationManager?.notify(requestCode, notification)
        }
    }
}