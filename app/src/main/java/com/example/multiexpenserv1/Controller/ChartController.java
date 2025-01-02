package com.example.multiexpenserv1.Controller;

import android.content.Context;

import com.example.multiexpenserv1.Model.DataBaseHelper;
import com.example.multiexpenserv1.Model.expense;

import java.util.Calendar;
import java.util.List;

public class ChartController {
    private final DataBaseHelper dbHelper;
    private final int daysInMonth;

    public ChartController(Context context) {
        dbHelper = new DataBaseHelper(context);

        // Xác định số ngày trong tháng hiện tại
        Calendar calendar = Calendar.getInstance();
        daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    public List<expense> getAllExpenses() {
        return dbHelper.getAllExpenses();
    }

    public float[] calculateDailyExpenses() {
        List<expense> expenses = getAllExpenses();
        float[] dailyExpenses = new float[daysInMonth];

        for (expense exp : expenses) {
            int day = Integer.parseInt(exp.getDay());  // Lấy ngày
            float amount = Float.parseFloat(exp.getAmount());  // Lấy số tiền

            if (day >= 1 && day <= daysInMonth) {
                dailyExpenses[day - 1] += amount;  // Cộng dồn chi tiêu theo ngày
            }
        }
        return dailyExpenses;
    }
    public int getDaysInMonth() {
        return daysInMonth;
    }
}

