package com.example.multiexpenserv1.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.multiexpenserv1.Controller.NewExpenseController;
import com.example.multiexpenserv1.Model.DataBaseHelper;
import com.example.multiexpenserv1.Model.expense;
import com.example.multiexpenserv1.R;
import com.google.android.material.snackbar.Snackbar;

public class new_expense_in extends AppCompatActivity {

    // Khai báo các thành phần giao diện
    private EditText Title,Amount,Day,Month,Year,Description;
    private ImageView save,back,show_expenses;
    private NewExpenseController newExpenseController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense_in);

        // Ánh xạ các thành phần giao diện bằng ID
        Title = findViewById(R.id.Title);
        Amount = findViewById(R.id.Amount);
        Day = findViewById(R.id.Day);
        Month = findViewById(R.id.Month);
        Year = findViewById(R.id.Year);
        Description = findViewById(R.id.Description);
        show_expenses = findViewById(R.id.show_expenses_btn);
        save = findViewById(R.id.save_btn);
        back = findViewById(R.id.back_btn);

        // Khởi tạo đối tượng NewExpenseController
        newExpenseController = new NewExpenseController(this);
        newExpenseController.handleButtonClicks(show_expenses, back);

        String title = Title.getText().toString();
        String amount = Amount.getText().toString();
        String day = Day.getText().toString();
        String month = Month.getText().toString();
        String year = Year.getText().toString();
        String description = Description.getText().toString();

        save.setOnClickListener(v -> newExpenseController.activity_Save(title, amount, day, month, year, description, save));
    }
}
