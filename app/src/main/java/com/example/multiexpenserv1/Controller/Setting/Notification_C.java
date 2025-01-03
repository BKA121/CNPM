package com.example.multiexpenserv1.Controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.multiexpenserv1.Model.NotificationSchedule;

import java.util.Calendar;

public class Notification_C {
    private final Context context;

    public Notification_C(Context context) {
        this.context = context;
    }

    public void scheduleNotification(NotificationSchedule schedule) {
        // Tạo Intent và PendingIntent
        Intent intent = new Intent(context, NotificationReceiver_C.class);
        intent.putExtra("title", schedule.getTitle());
        intent.putExtra("message", schedule.getMessage());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Sử dụng AlarmManager để đặt lịch
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            setDailyNotification(alarmManager, schedule.getCalendar(), pendingIntent);
        } else {
            Toast.makeText(context, "Không thể đặt thông báo. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setDailyNotification(AlarmManager alarmManager, Calendar calendar, PendingIntent pendingIntent) {
        long interval = AlarmManager.INTERVAL_DAY;
        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                interval,
                pendingIntent
        );
        Toast.makeText(context, "Thông báo hằng ngày đã được đặt!", Toast.LENGTH_SHORT).show();
    }
}

