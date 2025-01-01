package com.example.multiexpenserv1.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multiexpenserv1.Controller.BalanceController;
import com.example.multiexpenserv1.Model.balance;
import com.example.multiexpenserv1.R;
import com.google.android.material.snackbar.Snackbar;

public class Balance_in extends AppCompatActivity {
    private TextView Current_Balance_Balance_in;
    private EditText Title,Amount,Day,Month,Year;
    private ImageView Add,Minus,Save,Back,Transactions;
    private boolean isAddOperationDone = false;
    private boolean isMinusOperationDone = false;
    private BalanceController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_in);

        Current_Balance_Balance_in = findViewById(R.id.Balance_in_balance);
        Title = findViewById(R.id.title_balance);
        Amount = findViewById(R.id.amount_balance);
        Day = findViewById(R.id.Day_Balance);
        Month = findViewById(R.id.Month_Balance);
        Year = findViewById(R.id.Year_Balance);
        Add = findViewById(R.id.balance_add);
        Minus = findViewById(R.id.balance_minus);
        Save = findViewById(R.id.save_button_balance);
        Back = findViewById(R.id.back_btn_balance);
        Transactions = findViewById(R.id.transactions);

        controller = new BalanceController(this);
        Current_Balance_Balance_in.setText(controller.getCurrentBalance()+ " VND");

        // getting text from the elements when the save button is clicked and setting on click listener
        Save.setOnClickListener(v -> {

            //Storing data from the edit text into the strings
            String title = Title.getText().toString();
            String amount = Amount.getText().toString();
            String day = Day.getText().toString();
            String month = Month.getText().toString();
            String year = Year.getText().toString();

            boolean isBalanceConsistent = true;
            //Checking if only one operation is selected
            if(isMinusOperationDone && isAddOperationDone){
                Snackbar snackbar=Snackbar.make(v,"Hãy chọn thêm hoặc bớt !!!",Snackbar.LENGTH_LONG);

                snackbar.show();
                //Resetting values , so users can select the operation again
                isAddOperationDone=false;
                isMinusOperationDone=false;
            } else if(!isMinusOperationDone && !isAddOperationDone){
                Snackbar snackbar=Snackbar.make(v,"Hãy chọn thêm hoặc bớt !!!",Snackbar.LENGTH_LONG);
                snackbar.show();
            }
            else {
                //Checking whether the data fields are filled
                if (!(title.isEmpty() || amount.isEmpty() || day.isEmpty() || month.isEmpty() || year.isEmpty())) {

                    balance transaction = new balance(title, amount, day, month, year);
                    int valueBalance = Integer.parseInt(controller.getCurrentBalance());
                    String Amount_String = Amount.getText().toString();

                    if (Amount_String.isEmpty()) {
                        Snackbar snackbar = Snackbar.make(v, "Vui lòng điền chi tiết số tiền !!!", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else {
                        int amount_t = Integer.parseInt(Amount_String);
                        if(isAddOperationDone){
                            valueBalance += amount_t;
                            controller.updateBalance(valueBalance);
                            transaction.setStatus("Deposit");
                        } else if(isMinusOperationDone && valueBalance >= amount_t){
                            valueBalance -= amount_t;
                            controller.updateBalance(valueBalance);
                            transaction.setStatus("Withdraw");
                        } else if(isMinusOperationDone && valueBalance < amount_t){
                            Toast.makeText(Balance_in.this, "Số dư hiện không đủ !", Toast.LENGTH_LONG).show();
                            isBalanceConsistent = false;
                            transaction.setStatus("Withdraw");
                        }
                    }
                    controller.navigateToSuccess(transaction, isBalanceConsistent);

                } else {
                    //Giving warning to the user to fill all the details
                    Snackbar snackbar = Snackbar.make(v, "Vui lòng điền đầy đủ thông tin !", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
        controller.handleButtonClicks(Transactions,Back);
        Add.setOnClickListener(v -> {
            isAddOperationDone=true;
            Toast.makeText(Balance_in.this, "Số dư của bạn sẽ được cộng, vui lòng lưu lại !", Toast.LENGTH_LONG).show();
        });
        Minus.setOnClickListener(v -> {
            isMinusOperationDone = true;
            Toast.makeText(Balance_in.this, "Số dư của bạn sẽ bị trừ, vui lòng lưu lại !", Toast.LENGTH_LONG).show();
        });
    }
}