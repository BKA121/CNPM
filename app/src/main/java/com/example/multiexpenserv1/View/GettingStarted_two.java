package com.example.multiexpenserv1.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.multiexpenserv1.Model.UserSettings;
import com.example.multiexpenserv1.R;
import com.google.android.material.snackbar.Snackbar;

public class GettingStarted_two extends AppCompatActivity {

    private EditText Monthly_Income,Current_Balance,Extra_Income;
    private ImageView Finish_Button;
    private UserSettings userSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started_two);

        Monthly_Income = findViewById(R.id.Monthly_Income);
        Current_Balance = findViewById(R.id.Current_Balance);
        Extra_Income = findViewById(R.id.Extra_Income);
        Finish_Button = findViewById(R.id.Finish_Button);

        // Initialize Model
        userSettings = new UserSettings(GettingStarted_two.this);

        Finish_Button.setOnClickListener(v -> {
            String mIncome = Monthly_Income.getText().toString();
            String cBalance = Current_Balance.getText().toString();
            String eIncome = Extra_Income.getText().toString();

            //Checking whether data is not null
            if(!(mIncome.isEmpty()||cBalance.isEmpty()|| eIncome.isEmpty())) {
                // Save user settings using the Model
                userSettings.saveUserSettings(mIncome, cBalance, eIncome);
                startActivity(new Intent(GettingStarted_two.this, Home.class));
                finish();
            }
            else {
                Snackbar snackbar=Snackbar.make(v,"Vui lòng điền đủ thông tin !",Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }
}