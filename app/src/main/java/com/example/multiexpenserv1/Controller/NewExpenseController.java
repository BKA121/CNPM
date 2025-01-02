package com.example.multiexpenserv1.Controller;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.multiexpenserv1.Model.DataBaseHelper;
import com.example.multiexpenserv1.Model.UserSettings;
import com.example.multiexpenserv1.Model.expense;
import com.example.multiexpenserv1.View.Home;
import com.example.multiexpenserv1.View.Show_Expenses;
import com.example.multiexpenserv1.View.Success;
import com.example.multiexpenserv1.View.new_expense_in;
import com.google.android.material.snackbar.Snackbar;

public class NewExpenseController {
    private final Context context;
    private final UserSettings userSettings;

    public NewExpenseController(Context context) {
        this.context = context;
        userSettings = new UserSettings(context);
    }
    public String getCurrentBalance() {
        return userSettings.getCurrentBalance();
    }
    public void updateBalance(int updatedBalance) {
        userSettings.updateBalance(updatedBalance);
    }
    public void navigateShowExpense() {
        Intent intent = new Intent(context, Show_Expenses.class);
        context.startActivity(intent);
    }
    public void navigateToHome() {
        Intent intent = new Intent(context, Home.class);
        context.startActivity(intent);
        finish();
    }
    public void activity_Save(String title, String amount, String day, String month, String year, String description, ImageView v) {
        boolean isBalanceConsistent = true;

        //Checking whether the data fields are filled
        if(!(title.isEmpty()||amount.isEmpty()||day.isEmpty()||month.isEmpty()||year.isEmpty()||description.isEmpty())) {

            // Khởi tạo đối tượng expense và các biến cần thiết
            expense obj;
            DataBaseHelper db;
            boolean isSaved = false;

            String Original_Balance = getCurrentBalance();
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
                    updateBalance(currentBalance);

                    // Tạo đối tượng expense và lưu vào cơ sở dữ liệu
                    obj = new expense(title, amount, day, month, year, description);
                    db = new DataBaseHelper(context);
                    isSaved = db.addExpenseToDB(obj); // Ghi dữ liệu vào database
                    db.close(); // Đóng kết nối cơ sở dữ liệu
                } else {
                    Toast.makeText(context, "Số dư không đủ!", Toast.LENGTH_LONG).show();
                    isBalanceConsistent = false;
                }
            } catch (NumberFormatException e) {
                Snackbar.make(v, "Vui lòng nhập số tiền hợp lệ!", Snackbar.LENGTH_LONG).show();
                return;
            }

            // Nếu lưu thành công và số dư hợp lệ, chuyển đến màn hình Success
            if (isSaved && isBalanceConsistent) {
                context.startActivity(new Intent(context, Success.class));
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
    }
    public void handleButtonClicks(ImageView show_expenses, ImageView back) {
        show_expenses.setOnClickListener(v -> navigateShowExpense());
        back.setOnClickListener(v -> navigateToHome());
    }
    private void finish() {
        ((new_expense_in) context).finish();
    }
}