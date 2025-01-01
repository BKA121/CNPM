package com.example.multiexpenserv1.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multiexpenserv1.Controller.MainActivityController;
import com.example.multiexpenserv1.R;
import com.google.android.material.snackbar.Snackbar;

public class MainActivityView extends AppCompatActivity {

    private EditText FirstName, LastName,Email;
    private ImageView nextButton;
    private MainActivityController controller;

    public EditText getFirstName() {
        return FirstName;
    }
    public EditText getLastName() {
        return LastName;
    }
    public EditText getEmail() {
        return Email;
    }
    public ImageView getNextButton() {
        return nextButton;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo giao diện
        FirstName = findViewById(R.id.First_Name);
        LastName = findViewById(R.id.Last_Name);
        Email = findViewById(R.id.Email_Address);
        nextButton = findViewById(R.id.Next_Button_GS1);

        // Khởi tạo controller và kiểm tra lần cài đặt đầu tiên
        controller = new MainActivityController(this, this);
        controller.checkFirstTime();

        // Xử lý sự kiện nút Next
        controller.handleNextButtonClick();
    }

    // Phương thức để chuyển màn hình Home
    public void navigateToHome() {
        Intent intent = new Intent(MainActivityView.this, Home.class);
        startActivity(intent);
        finish();
    }

    // Phương thức để chuyển sang màn hình GettingStarted_two
    public void navigateToGettingStarted() {
        Intent intent = new Intent(MainActivityView.this, GettingStarted_two.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
        finish();
    }

    // Phương thức hiển thị thông báo lỗi
    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
