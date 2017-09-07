package com.example.prathamesh1.jobscheduling;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
//import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    int hour, min,sec;
    TextView tv;
    TimePicker tp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tp = (TimePicker) findViewById(R.id.timePicker2);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour = tp.getHour();
                min = tp.getMinute();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,hour);
                calendar.set(Calendar.MINUTE,min);
                calendar.set(Calendar.SECOND,00);
                tv = (TextView) findViewById(R.id.textView2);
                tv.setText(calendar.getTime().toString());
                Intent intent = new Intent(getApplicationContext(),Notification_receiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplication(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
                am.setRepeating(AlarmManager.RTC_WAKEUP,10000,60000,pendingIntent);
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

       /* TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay+":"+minute;
                tv.setText(time);
            }
        },12,34,false);*/
    }
}
