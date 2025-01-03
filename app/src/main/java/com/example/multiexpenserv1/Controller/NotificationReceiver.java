package com.example.multiexpenserv1.Controller;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.multiexpenserv1.R;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Lấy thông tin tiêu đề và nội dung từ intent
        String title = intent.getStringExtra("title");
        String message = intent.getStringExtra("message");

        // Tạo kênh thông báo (chỉ cần tạo một lần)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "notify_schedule", // ID của kênh
                    "Lịch trình thông báo", // Tên hiển thị của kênh
                    NotificationManager.IMPORTANCE_HIGH // Độ ưu tiên cao
            );
            channel.setDescription("Kênh thông báo cho lịch trình của bạn.");

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        // Xây dựng thông báo
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notify_schedule")
                .setSmallIcon(R.drawable.icon_notification) // Icon hiển thị
                .setContentTitle(title) // Tiêu đề
                .setContentText(message) // Nội dung
                .setPriority(NotificationCompat.PRIORITY_HIGH); // Độ ưu tiên

        // Hiển thị thông báo nếu có quyền
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(1, builder.build());
        }
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Nếu thiếu quyền, không thực hiện gửi thông báo
            Toast.makeText(context, "Cần cấp quyền thông báo để nhận lịch trình nhắc nhở", Toast.LENGTH_SHORT).show();

            // Hoặc bạn có thể log lỗi cho việc gỡ lỗi
            Log.d("NotificationReceiver", "Permission for POST_NOTIFICATIONS is missing.");

            // Thực hiện hành động thay thế nếu cần thiết (ví dụ, yêu cầu quyền)
            // Bạn có thể yêu cầu lại quyền tại đây nếu cần thiết hoặc thông báo cho người dùng
            // Cần phải có cách xử lý yêu cầu quyền động từ Activity
        }
    }
}
