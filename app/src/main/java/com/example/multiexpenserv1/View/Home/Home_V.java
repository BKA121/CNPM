package com.example.multiexpenserv1.View.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.multiexpenserv1.Controller.Home.Home_C;
import com.example.multiexpenserv1.Model.User.User;
import com.example.multiexpenserv1.R;

public class Home_V extends AppCompatActivity {

    private TextView name,balance;
    private ImageView share,newexpense,Balance_in,Goals,Chart;
    private ImageView information;
    private Home_C homeController;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeController = new Home_C(this);

        //Accessing the text view by id from backend
        name = findViewById(R.id.Welcome);
        balance = findViewById(R.id.Home_Balance);
        share = findViewById(R.id.share);
        newexpense = findViewById(R.id.newexpense_button);
        Balance_in = findViewById(R.id.Balance_button);
        Goals = findViewById(R.id.Goals_button);
        information = findViewById(R.id.Information);
        Chart = findViewById(R.id.Gifts_Button);

        // Lấy thông tin người dùng và hiển thị
        user = homeController.getUserData();
        name.setText("Xin chào " + user.getLastName());
        balance.setText(user.getCurrentBalance() + " VND");

        // Xử lý sự kiện nhấn nút
        homeController.handleButtonClicks(share, newexpense, Balance_in, Goals, Chart, information);
    }
}