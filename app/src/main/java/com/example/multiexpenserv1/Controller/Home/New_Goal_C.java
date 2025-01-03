package com.example.multiexpenserv1.Controller;

import android.content.Intent;
import android.view.View;

import com.example.multiexpenserv1.Data.DataBaseHelper;
import com.example.multiexpenserv1.Model.Goal;
import com.example.multiexpenserv1.View.FlashScreen.Success_V;
import com.example.multiexpenserv1.View.Goal.New_Goal_V;
import com.google.android.material.snackbar.Snackbar;

public class New_Goal_C {
    private final New_Goal_V view;

    public New_Goal_C(New_Goal_V view) {
        this.view = view;
    }

    public void saveGoal(String title, String amount, String type, String day, String month, String year, View v) {
        boolean isBalanceConsistent = true;

        // Kiểm tra nếu trường thông tin còn trống
        if (title.isEmpty() || amount.isEmpty() || type.isEmpty() || day.isEmpty() || month.isEmpty() || year.isEmpty()) {
            Snackbar.make(v, "Vui lòng điền đầy đủ thông tin!", Snackbar.LENGTH_LONG).show();
            return;
        }

        // Kiểm tra xem số tiền có hợp lệ không (là số dương)
        try {
            double amountValue = Double.parseDouble(amount);
            if (amountValue <= 0) {
                isBalanceConsistent = false; // Nếu số tiền không hợp lệ
            }
        } catch (NumberFormatException e) {
            isBalanceConsistent = false; // Nếu không phải là số hợp lệ
        }

        // Nếu tất cả điều kiện hợp lệ
        if (isBalanceConsistent) {
            // Tạo đối tượng Goal với trạng thái mặc định "Đang chờ"
            Goal Goal = new Goal(title, amount, type, day, month, year);
            Goal.setStatus("Đang chờ");

            // Lưu vào cơ sở dữ liệu
            DataBaseHelper db = new DataBaseHelper(view);
            boolean isSaved = db.AddGoalToDB(Goal);
            db.close();

            // Kiểm tra nếu lưu thành công
            if (isSaved) {
                view.startActivity(new Intent(view, Success_V.class));
                view.finish();
            } else {
                Snackbar.make(v, "Có lỗi xảy ra. Vui lòng thử lại!", Snackbar.LENGTH_LONG).show();
            }
        } else {
            // Nếu không hợp lệ, hiển thị thông báo lỗi
            Snackbar.make(v, "Vui lòng nhập số tiền hợp lệ!", Snackbar.LENGTH_LONG).show();
        }
    }
}
