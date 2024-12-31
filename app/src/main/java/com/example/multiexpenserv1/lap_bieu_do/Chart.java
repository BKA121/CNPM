package com.example.multiexpenserv1.lap_bieu_do;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.multiexpenserv1.data.DataBaseHelper;
import com.example.multiexpenserv1.R;
import com.example.multiexpenserv1.ql_expensive.expense;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.Description;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Chart extends AppCompatActivity {

    private LineChart lineChart;
    // Lấy dữ liệu chi tiêu từ cơ sở dữ liệu
    DataBaseHelper dbHelper;
    List<expense> expenses;
    ArrayList<Entry> entries;
    // Tạo LineDataSet
    LineDataSet lineDataSet;

    TextView minExpenseText;
    TextView maxExpenseText;
    TextView totalExpenseText;
    int minExpenseDay = -1, maxExpenseDay = -1; // Ngày ít và nhiều chi tiêu nhất
    float minExpense = Float.MAX_VALUE, maxExpense = Float.MIN_VALUE; // Giá trị chi tiêu ít và nhiều nhất
    float totalExpenses = 0; // Tổng chi tiêu trong tháng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        lineChart= findViewById(R.id.lineChart);
        dbHelper = new DataBaseHelper(Chart.this);
        expenses = dbHelper.getAllExpenses();
        minExpenseText = findViewById(R.id.minExpenseText);
        maxExpenseText = findViewById(R.id.maxExpenseText);
        totalExpenseText = findViewById(R.id.totalExpenseText);

        // Xác định số ngày trong tháng hiện tại
        Calendar calendar = Calendar.getInstance();
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Tạo một mảng để lưu tổng chi tiêu của mỗi ngày trong tháng
        float[] dailyExpenses = new float[daysInMonth];
        // Duyệt qua tất cả chi tiêu và tính tổng chi tiêu theo ngày
        for (expense exp : expenses) {
            int day = Integer.parseInt(exp.getDay());  // Lấy ngày từ dữ liệu chi tiêu
            float amount = Float.parseFloat(exp.getAmount());  // Lấy số tiền chi tiêu

            // Cộng dồn chi tiêu vào ngày tương ứng
            if (day >= 1 && day <= daysInMonth) {
                dailyExpenses[day - 1] += amount;  // day-1 vì mảng bắt đầu từ chỉ số 0
            }
        }

        // Tạo dữ liệu cho biểu đồ đường
        entries = new ArrayList<>();
        for (int i = 0; i < daysInMonth; i++) {
            if (dailyExpenses[i] != 0) { // Chỉ thêm giá trị khác 0
                totalExpenses += dailyExpenses[i];
                if (dailyExpenses[i] < minExpense && dailyExpenses[i] > 0) { // Chi tiêu thấp nhất > 0
                    minExpense = dailyExpenses[i];
                    minExpenseDay = i + 1; // Ngày chi tiêu ít nhất
                }
                if (dailyExpenses[i] > maxExpense) { // Chi tiêu cao nhất
                    maxExpense = dailyExpenses[i];
                    maxExpenseDay = i + 1; // Ngày chi tiêu nhiều nhất
                }
                entries.add(new Entry(i + 1, dailyExpenses[i])); // Gán chi tiêu cho từng ngày, bắt đầu từ 1
            }
        }

        minExpenseText.setText("Ngày chi tiêu ít nhất: " + minExpenseDay + " (" + minExpense + " VND)");
        maxExpenseText.setText("Ngày chi tiêu nhiều nhất: " + maxExpenseDay + " (" + maxExpense + " VND)");
        totalExpenseText.setText("Tổng chi tiêu: " + totalExpenses + " VND");

        // Thiết lập màu sắc cho đường
        lineDataSet = new LineDataSet(entries, "Dữ liệu cho 30 ngày");
        lineDataSet.setColor(ContextCompat.getColor(this, R.color.colorPrimary));
        // Thiết lập màu sắc cho giá trị (chữ số trên biểu đồ)
        lineDataSet.setValueTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        // Thiết lập kích thước chữ
        lineDataSet.setValueTextSize(10f);
        // Thiết lập độ dày của đường
        lineDataSet.setLineWidth(2f);

        lineChart.setHardwareAccelerationEnabled(true); // Bật tăng tốc phần cứng
        lineChart.setLayerType(View.LAYER_TYPE_HARDWARE, null); // Tăng chất lượng vẽ

        // Thêm các điểm tròn tại mỗi Entry
        lineDataSet.setCircleRadius(4f); // Đường kính của điểm tròn
        lineDataSet.setCircleColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)); // Màu của điểm tròn

        lineDataSet.setDrawCircles(true); // Hiển thị vòng tròn

        // Sử dụng IValueFormatter để chỉ hiển thị giá trị khác 0
        lineDataSet.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> {
            if (value == 0f) {
                return ""; // Không hiển thị giá trị nếu là 0
            }
            return String.valueOf(value); // Hiển thị giá trị nếu khác 0
        });

        // Thêm hiệu ứng Gradient
        lineDataSet.setDrawFilled(true); // Hiển thị vùng được lấp đầy
        lineDataSet.setFillColor(ContextCompat.getColor(this, R.color.colorPrimary)); // Màu lấp đầy


        // Tạo LineData và gán cho LineChart
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        // Tùy chỉnh XAxis và YAxis
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(6); // Hiển thị 6 nhãn trên trục X (tương ứng với 6 mốc thời gian)
        xAxis.setAxisMinimum(1); // Bắt đầu từ ngày 1
        xAxis.setAxisMaximum(daysInMonth); // Kết thúc ở ngày cuối tháng
        xAxis.setValueFormatter((value, axis) -> String.valueOf((int) value));
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setAxisMinimum(0f);

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);

        // Cấu hình hiệu ứng vẽ (animate)
        lineChart.animateX(1000); // animate theo trục X trong 1000ms
        lineChart.animateY(1000); // animate theo trục Y trong 1000ms

        // Cấu hình mô tả cho biểu đồ
        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description);

        // Cấu hình nền và viền của biểu đồ
        lineChart.setDrawBorders(true); // Vẽ viền
        lineChart.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white)); // Nền trắng
        lineChart.setDrawGridBackground(false); // Tắt nền lưới

        // Vẽ lại biểu đồ
        lineChart.invalidate();
    }
}

