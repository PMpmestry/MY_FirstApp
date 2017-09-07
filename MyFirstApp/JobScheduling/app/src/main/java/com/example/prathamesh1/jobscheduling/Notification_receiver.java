package com.example.prathamesh1.jobscheduling;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Prathamesh 1 on 06-06-2017.
 */

public class Notification_receiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager nm = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        Uri notifyTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent repeatIntent = new Intent(context,Repeating_Activity.class);
        repeatIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pd = PendingIntent.getActivity(context,100,repeatIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setContentText("Notification Text")
                .setTicker("New Message")
                .setContentIntent(pd)
                .setSound(notifyTone)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Notification App")
                .setAutoCancel(true).build();

        nm.notify(100,notification);

    }
}
