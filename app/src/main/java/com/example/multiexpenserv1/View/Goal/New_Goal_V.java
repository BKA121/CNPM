package com.example.multiexpenserv1.View.Goal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multiexpenserv1.Controller.Home.My_Goals_C;
import com.example.multiexpenserv1.Controller.Home.New_Goal_C;
import com.example.multiexpenserv1.R;

public class New_Goal_V extends AppCompatActivity {

    private EditText titleEditText, amountEditText, typeEditText, dayEditText, monthEditText, yearEditText;
    private ImageView saveButton, backButton, myGoalsButton;

    private New_Goal_C controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goal);

        // Liên kết UI
        titleEditText = findViewById(R.id.New_Goal_Title);
        amountEditText = findViewById(R.id.New_Goal_Amount);
        typeEditText = findViewById(R.id.New_Goal_Type);
        dayEditText = findViewById(R.id.New_Goal_Day);
        monthEditText = findViewById(R.id.New_Goal_Month);
        yearEditText = findViewById(R.id.New_Goal_Year);
        saveButton = findViewById(R.id.Save_Btn_New_Goal);
        backButton = findViewById(R.id.Back_Btn_New_Goal);
        myGoalsButton = findViewById(R.id.New_Goal_My_Goals_Bg);

        // Khởi tạo Controller
        controller = new New_Goal_C(this);

        // Gắn sự kiện
        backButton.setOnClickListener(v -> finish());
        saveButton.setOnClickListener(this::saveGoal);
        myGoalsButton.setOnClickListener(v -> startActivity(new Intent(New_Goal_V.this, My_Goals_C.class)));
    }

    private void saveGoal(View view) {
        String title = titleEditText.getText().toString();
        String amount = amountEditText.getText().toString();
        String type = typeEditText.getText().toString();
        String day = dayEditText.getText().toString();
        String month = monthEditText.getText().toString();
        String year = yearEditText.getText().toString();

        controller.saveGoal(title, amount, type, day, month, year, view);
    }
}
