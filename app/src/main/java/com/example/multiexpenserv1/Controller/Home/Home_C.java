package com.example.multiexpenserv1.Controller.Home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;

import com.example.multiexpenserv1.View.Balance.Balance_in_V;
import com.example.multiexpenserv1.View.Home.Information_V;
import com.example.multiexpenserv1.Model.User.User;

import com.example.multiexpenserv1.View.Chart.Chart_V;
import com.example.multiexpenserv1.View.Goal.New_Goal_V;
import com.example.multiexpenserv1.View.Expenses.New_Expense_in_V;

public class Home_C {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    public Home_C(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
    }
    public User getUserData() {
        String lastName = sharedPreferences.getString("Last_Name", "");
        String currentBalance = sharedPreferences.getString("Current_Balance", "");
        return new User(lastName, currentBalance);
    }

    // Xử lý sự kiện nhấn nút
    public void handleButtonClicks(ImageView share, ImageView newexpense, ImageView Balance_in,
                                   ImageView Goals, ImageView Chart, ImageView information) {
        // Gắn sự kiện vào các nút
        share.setOnClickListener(v -> shareApp());
        newexpense.setOnClickListener(v -> navigateToNewExpense());
        Balance_in.setOnClickListener(v -> navigateToBalance());
        Goals.setOnClickListener(v -> navigateToGoals());
        Chart.setOnClickListener(v -> navigateToChart());
        information.setOnClickListener(v -> navigateToInformation());
    }

    public void navigateToNewExpense() {
        Intent intent = new Intent(context, New_Expense_in_V.class);
        context.startActivity(intent);
    }

    public void navigateToBalance() {
        Intent intent = new Intent(context, Balance_in_V.class);
        context.startActivity(intent);
    }

    public void navigateToGoals() {
        Intent intent = new Intent(context, New_Goal_V.class);
        context.startActivity(intent);
    }

    public void navigateToChart() {
        Intent intent = new Intent(context, Chart_V.class);
        context.startActivity(intent);
    }

    public void navigateToInformation() {
        Intent intent = new Intent(context, Information_V.class);
        context.startActivity(intent);
    }
    public void shareApp() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Download MultiExpenser");
        intent.putExtra(Intent.EXTRA_TEXT, "Regards of the day, try this amazing app to save your money named MultiExpenser. Download it from the Play Store.");
        context.startActivity(Intent.createChooser(intent, "Choose one"));
    }
}


