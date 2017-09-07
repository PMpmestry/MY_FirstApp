package com.example.prathamesh1.jobscheduling;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Prathamesh 1 on 07-06-2017.
 */

public class ActivityJobScheduler extends AppCompatActivity {

    public static final int MY_JOB_ID = 1;
    private JobScheduler js;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        js = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        Button start = (Button) findViewById(R.id.start);
        scheduleJob();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void scheduleJob() {
        ComponentName serviceName = new ComponentName(this, JobSchedulerService.class);
        JobInfo.Builder builder = new JobInfo.Builder(MY_JOB_ID, serviceName)
                .setPeriodic(10000)
                .setPersisted(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE);
        int test = js.schedule(builder.build());
        /*if(test == JobScheduler.RESULT_SUCCESS){
            js.cancel(MY_JOB_ID);
        }*/
    }
}
