package com.example.arunnagarajan.diplomate.Adapters;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.example.arunnagarajan.diplomate.Activities.MainActivity;

public class AlarmReceiver extends BroadcastReceiver {

    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {



        //Trigger the notification
        Notifications.showNotification(context, MainActivity.class,
                "Task due!", "Please complete the task as soon as possible");
    }

    // GIVE THE TASK A VARIABLE TITLE AS WELL
}