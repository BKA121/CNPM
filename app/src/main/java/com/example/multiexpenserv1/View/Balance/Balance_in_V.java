package com.example.multiexpenserv1.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.multiexpenserv1.Controller.Balance_C;
import com.example.multiexpenserv1.R;

public class Balance_in_V extends AppCompatActivity {
    private TextView Current_Balance_Balance_in;
    private EditText Title,Amount,Day,Month,Year;
    private ImageView Add,Minus,Save,Back,Transactions;
    private Balance_C controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_in);

        Current_Balance_Balance_in = findViewById(R.id.Balance_in_balance);
        Title = findViewById(R.id.title_balance);
        Amount = findViewById(R.id.amount_balance);
        Day = findViewById(R.id.Day_Balance);
        Month = findViewById(R.id.Month_Balance);
        Year = findViewById(R.id.Year_Balance);
        Add = findViewById(R.id.balance_add);
        Minus = findViewById(R.id.balance_minus);
        Save = findViewById(R.id.save_button_balance);
        Back = findViewById(R.id.back_btn_balance);
        Transactions = findViewById(R.id.transactions);

        controller = new Balance_C(this);
        Current_Balance_Balance_in.setText(controller.getCurrentBalance()+ " VND");

        Save.setOnClickListener(v -> controller.activity_Save(Title,Amount,Day,Month,Year, Save));

        controller.handleButtonClicks(Transactions,Back);
        Add.setOnClickListener(v -> controller.activity_Add());
        Minus.setOnClickListener(v -> controller.activity_Minus());
    }
}