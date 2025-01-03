package com.example.multiexpenserv1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Calendar;

public class NotificationSettingsActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Button btnSetNotification;
    private Spinner spFrequency;
    EditText etReminderContent;
    ArrayList<String> itemsFreq = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settings);

        timePicker = findViewById(R.id.timePicker);
        btnSetNotification = findViewById(R.id.btnSetNotification);
        addToSpinnerFreq();
        etReminderContent = findViewById(R.id.etReminderContent);

        btnSetNotification.setOnClickListener(v -> {
            // Lấy giờ và phút từ TimePicker
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            // Tạo Calendar với thời gian đã chọn
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);

            // Kiểm tra xem thời gian có trong quá khứ không
            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DATE, 1); // Nếu đã qua, đặt cho ngày hôm sau
            }

            // Kiểm tra và yêu cầu quyền POST_NOTIFICATIONS nếu cần
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                            1001
                    );
                    return;
                }
            }

            // Đặt lịch thông báo
            setNotification(calendar);
        });
    }
    private void addToSpinnerFreq(){
        spFrequency = findViewById(R.id.spFrequency);
        itemsFreq.add("Hằng ngày");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemsFreq);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFrequency.setAdapter(adapter);
    }

    private void setNotification(Calendar calendar) {
        // Tạo Intent và PendingIntent
        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra("title", "Ngày hôm nay của bạn ổn chứ!?");
        intent.putExtra("message", etReminderContent.getText());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Sử dụng AlarmManager để đặt lịch
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            setDailyNotification(alarmManager, calendar, pendingIntent);
        } else {
            Toast.makeText(this, "Không thể đặt thông báo. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
        }
    }
    private void setDailyNotification(AlarmManager alarmManager, Calendar calendar, PendingIntent pendingIntent) {
        // Khoảng thời gian lặp lại là 1 ngày (tính bằng milliseconds)
        long interval = AlarmManager.INTERVAL_DAY;

        // Sử dụng setRepeating để đặt lịch lặp lại hằng ngày
        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,       // Loại báo thức
                calendar.getTimeInMillis(),    // Thời gian thông báo đầu tiên
                interval,                      // Khoảng thời gian lặp lại
                pendingIntent                  // PendingIntent chứa thông báo
        );
        Toast.makeText(this, "Thông báo hằng ngày đã được đặt!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1001) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Quyền thông báo đã được cấp!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Quyền thông báo bị từ chối!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
