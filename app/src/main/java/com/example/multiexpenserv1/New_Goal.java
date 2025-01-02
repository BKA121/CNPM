package com.example.multiexpenserv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.multiexpenserv1.Model.DataBaseHelper;
import com.example.multiexpenserv1.Model.goal;
import com.example.multiexpenserv1.View.Home;
import com.example.multiexpenserv1.View.Success;
import com.google.android.material.snackbar.Snackbar;

public class New_Goal extends AppCompatActivity {
    private ImageView save,back,myGoals;
    private EditText Title,Amount,Type,Day,Month,Year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goal);
        //Getting all the ui elements
        Title = findViewById(R.id.New_Goal_Title);
        Amount = findViewById(R.id.New_Goal_Amount);
        Type = findViewById(R.id.New_Goal_Type);
        Day = findViewById(R.id.New_Goal_Day);
        Month = findViewById(R.id.New_Goal_Month);
        Year = findViewById(R.id.New_Goal_Year);
        save = findViewById(R.id.Save_Btn_New_Goal);
        back = findViewById(R.id.Back_Btn_New_Goal);
        myGoals = findViewById(R.id.New_Goal_My_Goals_Bg);

        // On click listener for back button
        back.setOnClickListener(v -> {
            startActivity(new Intent(New_Goal.this, Home.class));
            finish();
        });

        //Save OnCLickListener
        save.setOnClickListener(v -> {
            String title= Title.getText().toString();
            String amount = Amount.getText().toString();
            String type = Type.getText().toString();
            String day = Day.getText().toString();
            String month = Month.getText().toString();
            String year = Year.getText().toString();

            // Call method to save the goal
            saveGoal(title, amount, type, day, month, year, v);
        });

        //Setting onclick listener for my goals
        myGoals.setOnClickListener(v -> startActivity(new Intent(New_Goal.this,My_Goals.class)));
    }
    private void saveGoal(String title, String amount, String type, String day, String month, String year, View v) {
        boolean isBalanceConsistent = true;

        // Check if any field is empty
        if (!(title.isEmpty() || amount.isEmpty() || day.isEmpty() || month.isEmpty() || year.isEmpty() || type.isEmpty())) {

            // Check if the amount is valid (positive number)
            try {
                double amountValue = Double.parseDouble(amount);
                if (amountValue <= 0) {
                    isBalanceConsistent = false; // Invalid amount
                }
            } catch (NumberFormatException e) {
                isBalanceConsistent = false; // Not a valid number
            }

            if (isBalanceConsistent) {
                // Create goal object and set status to "PENDING"
                goal obj = new goal(title, amount, type, day, month, year);
                obj.setStatus("PENDING");

                // Create database helper and insert the goal data
                DataBaseHelper db = new DataBaseHelper(New_Goal.this);
                boolean isSaved = db.AddGoalToDB(obj);
                db.close();

                if (isSaved) {
                    startActivity(new Intent(New_Goal.this, Success.class));
                    finish();
                } else {
                    Snackbar snackbar = Snackbar.make(v, "Vui lòng điền chi tiết thông tin!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            } else {
                Snackbar snackbar = Snackbar.make(v, "Vui lòng nhập số tiền hợp lệ!", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        } else {
            Snackbar snackbar = Snackbar.make(v, "Vui lòng điền đầy đủ thông tin!", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}