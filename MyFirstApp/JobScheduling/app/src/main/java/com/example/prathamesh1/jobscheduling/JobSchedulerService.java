package com.example.prathamesh1.jobscheduling;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by Prathamesh 1 on 07-06-2017.
 */

public class JobSchedulerService extends JobService{

    private JobParameters p;
    Context context = this;
    NotificationManager nm;
    Notification notification;
    @Override
    public void onCreate() {
        nm = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        Uri notifyTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent repeatIntent = new Intent(context,Repeating_Activity.class);
        repeatIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pd = PendingIntent.getActivity(context,100,repeatIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        notification = builder.setContentText("Notification Text")
                .setTicker("New Message")
                .setContentIntent(pd)
                .setSound(notifyTone)
                .setSmallIcon(R.drawable.ic_stat_name1)
                .setContentTitle("Notification App")
                .setAutoCancel(true).build();

    }

    Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(getApplicationContext(),"JOB", Toast.LENGTH_SHORT).show();
                System.out.println("**********************PM************************");
                jobFinished(p,false);
            }
        };
    @Override
    public boolean onStartJob(JobParameters params) {
        p = params;
        nm.notify(100,notification);
//        new Thread(runnable).start();
        /*Toast.makeText(getApplicationContext(),"JOB", Toast.LENGTH_SHORT).show();
        System.out.println("**********************PM************************");*/
        jobFinished(p,false);
        /*JobScheduler js = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        js.cancel(ActivityJobScheduler.MY_JOB_ID);*/
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }

}
