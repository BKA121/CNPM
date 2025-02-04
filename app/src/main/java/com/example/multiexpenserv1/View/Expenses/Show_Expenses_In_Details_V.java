package com.example.multiexpenserv1.View.Expenses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.multiexpenserv1.R;
import com.example.multiexpenserv1.Adapter.ShowExpenses_Adapter;

public class Show_Expenses_In_Details_V extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expenses_in_details);
        //Getting Data from the intent
        Intent intent = getIntent();
        String Title = intent.getStringExtra(ShowExpenses_Adapter.ShowExpenseTitleKey);
        String Amount = intent.getStringExtra(ShowExpenses_Adapter.ShowExpenseAmountKey);
        String Date = intent.getStringExtra(ShowExpenses_Adapter.ShowExpenseDateKey);
        String Description =intent.getStringExtra(ShowExpenses_Adapter.ShowExpenseDescriptionKey);

        //Getting Elements from xml
        TextView title = findViewById(R.id.Title_Show_Expenses_in_Detail);
        TextView amount = findViewById(R.id.Amount_show_expsense_in_details);
        TextView date = findViewById(R.id.Date_show_Expenses_in_details);
        TextView desc = findViewById(R.id.Description_show_expenses_in_detail);
        ImageView back = findViewById(R.id.back_Show_expenses_in_detail);

        //Exporting Data
        title.setText(Title);
        amount.setText(Amount);
        date.setText(Date);
        desc.setText(Description);

        //Setting on click listener for back
        back.setOnClickListener(v -> finish());
    }
}