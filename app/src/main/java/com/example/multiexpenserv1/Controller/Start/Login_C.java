package com.example.multiexpenserv1.Controller.Start;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.multiexpenserv1.Model.User.Login;
import com.example.multiexpenserv1.R;
import com.example.multiexpenserv1.View.Home.Home_V;
import com.example.multiexpenserv1.View.Login.Login_Balance_V;
import com.example.multiexpenserv1.View.Login.Login_V;
import com.google.android.material.snackbar.Snackbar;

public class Login_C {
    private final Context context;
    private final Login model;

    public Login_C(Context context) {
        this.context = context;
        this.model = new Login(context);
    }

    // Kiểm tra lần cài đặt đầu tiên
    public boolean checkFirstTime() {
        return model.isFirstTimeInstalled();
    }
    // Phương thức để chuyển màn hình Home_V
    public void navigateToHome() {
        Intent intent = new Intent(context, Home_V.class);
        context.startActivity(intent);
        finish();
    }
    private void finish() {
        ((Login_V) context).finish();
    }
    // Phương thức để chuyển sang màn hình Login_Balance_V
    public void navigateToGettingStarted() {
        Intent intent = new Intent(context, Login_Balance_V.class);
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
                navigateToGettingStarted(); // Điều hướng đến Login_Balance_V
            } else {
                Snackbar snackbar=Snackbar.make(v,"Vui lòng điền đầy đủ thông tin !",Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }
}
