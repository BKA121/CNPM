package com.example.multiexpenserv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.multiexpenserv1.Model.DataBaseHelper;
import com.example.multiexpenserv1.Model.expense;
import com.example.multiexpenserv1.View.Home;
import com.google.android.material.snackbar.Snackbar;

public class new_expense_in extends AppCompatActivity {

    // Khai báo các thành phần giao diện
    private EditText Title,Amount,Day,Month,Year,Description;
    private ImageView save,back,show_expenses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense_in);

        // Ánh xạ các thành phần giao diện bằng ID
        Title=findViewById(R.id.Title);
        Amount=findViewById(R.id.Amount);
        Day=findViewById(R.id.Day);
        Month=findViewById(R.id.Month);
        Year=findViewById(R.id.Year);
        Description=findViewById(R.id.Description);
        show_expenses=findViewById(R.id.show_expenses_btn);
        save=findViewById(R.id.save_btn);
        back=findViewById(R.id.back_btn);

        //Sự kiện khi nhấn nút Hiển thị chi tiêu
        show_expenses.setOnClickListener(v -> startActivity(new Intent(new_expense_in.this,Show_Expenses.class)));

        // Sự kiện khi nhấn nút quay lại
        back.setOnClickListener(v -> {
            startActivity(new Intent(new_expense_in.this, Home.class));
            finish();
        });

        // Sự kiện khi nhấn nút Lưu
        save.setOnClickListener(v -> {
            // Lấy dữ liệu từ các ô nhập liệu
            // Lấy dữ liệu từ các ô nhập liệu
            String title = Title.getText().toString();
            String amount = Amount.getText().toString();
            String day = Day.getText().toString();
            String month = Month.getText().toString();
            String year = Year.getText().toString();
            String description = Description.getText().toString();
            boolean isBalanceConsistent = true;

            //Checking whether the data fields are filled
            if(!(title.isEmpty()||amount.isEmpty()||day.isEmpty()||month.isEmpty()||year.isEmpty()||description.isEmpty())) {

                // Khởi tạo đối tượng expense và các biến cần thiết
                expense obj;
                DataBaseHelper db;
                boolean isSaved = false;

                // Thêm SharedPreferences để quản lý số dư hiện tại
                SharedPreferences sharedPreferences=getSharedPreferences("PREFERENCE",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                String Original_Balance=sharedPreferences.getString("Current_Balance","0");
                int currentBalance;

                // Xử lý chuyển đổi số dư sang kiểu số nguyên
                try {
                    currentBalance = Integer.parseInt(Original_Balance);
                } catch (NumberFormatException e) {
                    currentBalance = 0; // Mặc định nếu số dư bị lỗi
                }

                // Xử lý logic trừ số dư
                try {
                    int amountValue = Integer.parseInt(amount);

                    if (currentBalance >= amountValue) {
                        currentBalance -= amountValue; // Cập nhật số dư mới
                        editor.putString("Current_Balance", String.valueOf(currentBalance));
                        editor.apply(); // Lưu lại số dư mới vào SharedPreferences

                        // Tạo đối tượng expense và lưu vào cơ sở dữ liệu
                        obj = new expense(title, amount, day, month, year, description);
                        db = new DataBaseHelper(new_expense_in.this);
                        isSaved = db.addExpenseToDB(obj); // Ghi dữ liệu vào database
                        db.close(); // Đóng kết nối cơ sở dữ liệu

                    } else {
                        Toast.makeText(new_expense_in.this, "Số dư không đủ!", Toast.LENGTH_LONG).show();
                        isBalanceConsistent = false;
                    }

                } catch (NumberFormatException e) {
                    Snackbar.make(v, "Vui lòng nhập số tiền hợp lệ!", Snackbar.LENGTH_LONG).show();
                    return;
                }

                // Nếu lưu thành công và số dư hợp lệ, chuyển đến màn hình Success
                if (isSaved && isBalanceConsistent) {
                    startActivity(new Intent(new_expense_in.this, Success.class));
                    finish();
                } else if (!isBalanceConsistent) {
                    Snackbar.make(v, "Số dư không đủ, vui lòng điều chỉnh lại mức chi tiêu!", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(v, "Không thể lưu dữ liệu, vui lòng thử lại!", Snackbar.LENGTH_LONG).show();
                }

            } else {
                // Hiển thị thông báo nếu thiếu thông tin nhập liệu
                Snackbar.make(v, "Vui lòng điền đầy đủ thông tin!", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
