package com.example.multiexpenserv1.Controller;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.multiexpenserv1.Model.DataBaseHelper;
import com.example.multiexpenserv1.Model.UserSettings;
import com.example.multiexpenserv1.Model.balance;
import com.example.multiexpenserv1.View.Balance_in;
import com.example.multiexpenserv1.View.Home;
import com.example.multiexpenserv1.View.show_transactions;
import com.google.android.material.snackbar.Snackbar;

public class BalanceController {
    private final Context context;
    private final UserSettings userSettings;
    boolean isAddOperationDone = false;
    boolean isMinusOperationDone = false;

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
    public void  activity_Add(){
        isAddOperationDone = true;
        Toast.makeText(context, "Số dư của bạn sẽ được cộng, vui lòng lưu lại !", Toast.LENGTH_LONG).show();
    }
    public void  activity_Minus(){
        isMinusOperationDone = true;
        Toast.makeText(context, "Số dư của bạn sẽ bị trừ, vui lòng lưu lại !", Toast.LENGTH_LONG).show();
    }
    public void activity_Save(EditText Title, EditText Amount, EditText Day, EditText Month, EditText Year, ImageView v){

        String title = Title.getText().toString();
        String amount = Amount.getText().toString();
        String day = Day.getText().toString();
        String month = Month.getText().toString();
        String year = Year.getText().toString();

        boolean isBalanceConsistent = true;
        if (!(title.isEmpty() || amount.isEmpty() || day.isEmpty() || month.isEmpty() || year.isEmpty())) {
            if(isMinusOperationDone && isAddOperationDone){
                Snackbar snackbar = Snackbar.make(v,"Hãy chọn thêm hoặc bớt !!!",Snackbar.LENGTH_LONG);
                snackbar.show();

                isAddOperationDone = false;
                isMinusOperationDone = false;
            } else if(!isMinusOperationDone && !isAddOperationDone){
                Snackbar snackbar = Snackbar.make(v,"Hãy chọn thêm hoặc bớt !!!",Snackbar.LENGTH_LONG);
                snackbar.show();
            } else {
                balance transaction = new balance(title, amount, day, month, year);
                int valueBalance = Integer.parseInt(getCurrentBalance());
                String Amount_String = amount;

                if (Amount_String.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Vui lòng điền chi tiết số tiền !!!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    int amount_t = Integer.parseInt(Amount_String);
                    if (isAddOperationDone) {
                        valueBalance += amount_t;
                        updateBalance(valueBalance);
                        transaction.setStatus("Deposit");
                    } else if (isMinusOperationDone && valueBalance >= amount_t) {
                        valueBalance -= amount_t;
                        updateBalance(valueBalance);
                        transaction.setStatus("Withdraw");
                    } else if (isMinusOperationDone && valueBalance < amount_t) {
                        Toast.makeText(context, "Số dư hiện không đủ !", Toast.LENGTH_LONG).show();
                        isBalanceConsistent = false;
                        transaction.setStatus("Withdraw");
                    }
                }
                navigateToSuccess(transaction, isBalanceConsistent);
            }
        } else {
            Snackbar snackbar = Snackbar.make(v, "Vui lòng điền đầy đủ thông tin !", Snackbar.LENGTH_LONG);
            snackbar.show();
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

