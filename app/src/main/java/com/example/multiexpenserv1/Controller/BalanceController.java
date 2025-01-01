package com.example.multiexpenserv1.Controller;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.multiexpenserv1.Model.DataBaseHelper;
import com.example.multiexpenserv1.Model.balance;
import com.example.multiexpenserv1.View.Balance_in;
import com.example.multiexpenserv1.View.Home;
import com.example.multiexpenserv1.new_expense_in;
import com.example.multiexpenserv1.show_transactions;

public class BalanceController {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public BalanceController(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }
    // Lấy số dư hiện tại
    public String getCurrentBalance() {
        return sharedPreferences.getString("Current_Balance", "0");
    }
    // Cập nhật số dư
    public void updateBalance(int updatedBalance) {
        editor.putString("Current_Balance", String.valueOf(updatedBalance));
        editor.apply();
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

