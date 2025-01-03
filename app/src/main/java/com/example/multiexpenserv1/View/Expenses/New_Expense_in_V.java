package com.example.multiexpenserv1.View.Expenses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.multiexpenserv1.Controller.Home.New_Expense_C;
import com.example.multiexpenserv1.R;

public class New_Expense_in_V extends AppCompatActivity {

    // Khai báo các thành phần giao diện
    private EditText Title,Amount,Day,Month,Year,Description;
    private ImageView save,back,show_expenses;
    private New_Expense_C newExpenseController;
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

        // Khởi tạo đối tượng New_Expense_C
        newExpenseController = new New_Expense_C(this);
        newExpenseController.handleButtonClicks(show_expenses, back);

        save.setOnClickListener(v -> newExpenseController.activity_Save(Title, Amount, Day, Month, Year, Description, save));
    }
}
