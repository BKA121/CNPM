package com.example.multiexpenserv1.Controller;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.example.multiexpenserv1.Model.DataBaseHelper;
import com.example.multiexpenserv1.Model.UserSettings;
import com.example.multiexpenserv1.Model.balance;
import com.example.multiexpenserv1.View.Balance_in;
import com.example.multiexpenserv1.View.Home;
import com.example.multiexpenserv1.show_transactions;

public class BalanceController {
    private final Context context;
    private UserSettings userSettings;

    public BalanceController(Context context) {
        this.context = context;
        userSettings = new UserSettings(context);
    }
    // Lấy số dư hiện tại
    public String getCurrentBalance() {
        return userSettings.getCurrentBalance();
    }
    public String getFirstName() {
        return userSettings.getFirstName();
    }
    public String getLastName() {
        return userSettings.getLastName();
    }
    public String getEmail() {
        return userSettings.getEmail();
    }
    // Cập nhật số dư
    public void updateBalance(int updatedBalance) {
        userSettings.updateBalance(updatedBalance);
    }
    // Lưu giao dịch vào cơ sở dữ liệu
    public boolean saveTransaction(balance transaction) {
        DataBaseHelper db = new DataBaseHelper(context);
        boolean isSaved = db.addBalanceDetailsToDB(transaction);
        db.close();
        return isSaved;
    }
    public void navigateToSuccess(balance transaction, boolean isBalanceConsistent) {
        boolean isSaved = saveTransaction(transaction);
        if(isSaved && isBalanceConsistent){
            Intent intent = new Intent(context, Home.class);
            context.startActivity(intent);
            finish();
        }
    }
    public void navigateToShowTransactions() {
        Intent intent = new Intent(context, show_transactions.class);
        context.startActivity(intent);
    }
    public void navigateToHome() {
        Intent intent = new Intent(context, Home.class);
        context.startActivity(intent);
        finish();
    }
    private void finish() {
        ((Balance_in) context).finish();
    }

    public void handleButtonClicks(ImageView transactions, ImageView back) {
        transactions.setOnClickListener(v -> navigateToShowTransactions());
        back.setOnClickListener(v -> navigateToHome());
    }
}

