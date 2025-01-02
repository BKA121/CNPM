package com.example.multiexpenserv1.View;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multiexpenserv1.Controller.MainActivityController;
import com.example.multiexpenserv1.R;

public class MainActivityView extends AppCompatActivity {

    private EditText FirstName, LastName,Email;
    private ImageView nextButton;
    private MainActivityController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainActivityController( this);
        if(controller.checkFirstTime()){
            controller.navigateToHome();
        } else {
            // Khởi tạo giao diện
            FirstName = findViewById(R.id.First_Name);
            LastName = findViewById(R.id.Last_Name);
            Email = findViewById(R.id.Email_Address);
            nextButton = findViewById(R.id.Next_Button_GS1);

            controller.handleNextButtonClick(FirstName, LastName, Email, nextButton);
        }
    }
}
