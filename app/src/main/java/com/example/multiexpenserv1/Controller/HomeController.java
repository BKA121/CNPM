package com.example.multiexpenserv1.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.multiexpenserv1.View.Balance_in;
import com.example.multiexpenserv1.Information;
import com.example.multiexpenserv1.Model.User;
import com.example.multiexpenserv1.New_Goal;
import com.example.multiexpenserv1.View.Chart;
import com.example.multiexpenserv1.new_expense_in;

public class HomeController {
    private Context context;
    private SharedPreferences sharedPreferences;
    public HomeController(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
    }
    public User getUserData() {
        String firstName = sharedPreferences.getString("First_Name", "");
        String currentBalance = sharedPreferences.getString("Current_Balance", "");
        return new User(firstName, currentBalance);
    }

    public void navigateToNewExpense() {
        Intent intent = new Intent(context, new_expense_in.class);
        context.startActivity(intent);
    }

    public void navigateToBalance() {
        Intent intent = new Intent(context, Balance_in.class);
        context.startActivity(intent);
    }

    public void navigateToGoals() {
        Intent intent = new Intent(context, New_Goal.class);
        context.startActivity(intent);
    }

    public void navigateToChart() {
        Intent intent = new Intent(context, Chart.class);
        context.startActivity(intent);
    }

    public void navigateToInformation() {
        Intent intent = new Intent(context, Information.class);
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

