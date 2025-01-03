package com.example.multiexpenserv1.View.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.multiexpenserv1.Model.User.UserSettings;
import com.example.multiexpenserv1.R;
import com.example.multiexpenserv1.View.Home.Home_V;
import com.google.android.material.snackbar.Snackbar;

public class Login_Balance_V extends AppCompatActivity {

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
        userSettings = new UserSettings(Login_Balance_V.this);

        Finish_Button.setOnClickListener(v -> {
            String mIncome = Monthly_Income.getText().toString();
            String cBalance = Current_Balance.getText().toString();
            String eIncome = Extra_Income.getText().toString();

            //Checking whether data is not null
            if(!(mIncome.isEmpty()||cBalance.isEmpty()|| eIncome.isEmpty())) {
                // Save user settings using the Model
                userSettings.saveUserSettings(mIncome, cBalance, eIncome);
                startActivity(new Intent(Login_Balance_V.this, Home_V.class));
                finish();
            }
            else {
                Snackbar snackbar=Snackbar.make(v,"Vui lòng điền đủ thông tin !",Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }
}