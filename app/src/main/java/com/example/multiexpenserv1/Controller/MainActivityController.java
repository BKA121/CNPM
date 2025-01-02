package com.example.multiexpenserv1.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.multiexpenserv1.Model.Login;
import com.example.multiexpenserv1.R;
import com.example.multiexpenserv1.View.GettingStarted_two;
import com.example.multiexpenserv1.View.Home;
import com.example.multiexpenserv1.View.MainActivityView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivityController {
    private Context context;
    private Login model;

    public MainActivityController(Context context) {
        this.context = context;
        this.model = new Login(context);
    }

    // Kiểm tra lần cài đặt đầu tiên
    public boolean checkFirstTime() {
        return model.isFirstTimeInstalled();
    }
    // Phương thức để chuyển màn hình Home
    public void navigateToHome() {
        Intent intent = new Intent(context, Home.class);
        context.startActivity(intent);
        finish();
    }
    private void finish() {
        ((MainActivityView) context).finish();
    }
    // Phương thức để chuyển sang màn hình GettingStarted_two
    public void navigateToGettingStarted() {
        Intent intent = new Intent(context, GettingStarted_two.class);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
        }
        finish();
    }
    // Xử lý sự kiện nút Next
    public void handleNextButtonClick(EditText FirstName, EditText LastName, EditText Email, ImageView nextButton) {
        nextButton.setOnClickListener(v -> {
            String fname = FirstName.getText().toString();
            String lname = LastName.getText().toString();
            String email = Email.getText().toString();

            if (!(fname.isEmpty() || lname.isEmpty() || email.isEmpty())) {
                model.saveUserInfo(fname, lname, email); // Lưu thông tin người dùng
                navigateToGettingStarted(); // Điều hướng đến GettingStarted_two
            } else {
                Snackbar snackbar=Snackbar.make(v,"Vui lòng điền đầy đủ thông tin !",Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }
}
