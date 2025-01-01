package com.example.multiexpenserv1.Controller;

import android.content.Context;

import com.example.multiexpenserv1.Model.Login;
import com.example.multiexpenserv1.View.MainActivityView;

public class MainActivityController {

    private MainActivityView view;
    private Login model;
    private Context context;

    public MainActivityController(MainActivityView view, Context context) {
        this.view = view;
        this.context = context;
        this.model = new Login(context);
    }

    // Kiểm tra lần cài đặt đầu tiên
    public void checkFirstTime() {
        if (model.isFirstTimeInstalled()) {
            view.navigateToHome(); // Điều hướng đến Home nếu là lần đầu cài đặt
        }
    }

    // Xử lý sự kiện nút Next
    public void handleNextButtonClick() {
        view.getNextButton().setOnClickListener(v -> {
            String fname = view.getFirstName().getText().toString();
            String lname = view.getLastName().getText().toString();
            String email = view.getEmail().getText().toString();

            if (!(fname.isEmpty() || lname.isEmpty() || email.isEmpty())) {
                model.saveUserInfo(fname, lname, email); // Lưu thông tin người dùng
                view.navigateToGettingStarted(); // Điều hướng đến GettingStarted_two
            } else {
                view.showSnackbar("Vui lòng điền đầy đủ thông tin !"); // Hiển thị thông báo nếu có trường bị bỏ trống
            }
        });
    }
}
