package com.example.multiexpenserv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multiexpenserv1.data.DataBaseHelper;
import com.example.multiexpenserv1.ql_balance.balance;
import com.google.android.material.snackbar.Snackbar;

public class Balance_in extends AppCompatActivity {

    private TextView Current_Balance_Balance_in;
    private EditText Title,Amount,Day,Month,Year;
    private ImageView Add,Minus,Save,Back,Transactions;
    private boolean isAddOperationDone=false,isMinusOperationDone=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_in);
        // Getting all the elements from the frontend
        Current_Balance_Balance_in=findViewById(R.id.Balance_in_balance);
        Title=findViewById(R.id.title_balance);
        Amount=findViewById(R.id.amount_balance);
        Day=findViewById(R.id.Day_Balance);
        Month=findViewById(R.id.Month_Balance);
        Year=findViewById(R.id.Year_Balance);
        Add=findViewById(R.id.balance_add);
        Minus=findViewById(R.id.balance_minus);
        Save=findViewById(R.id.save_button_balance);
        Back=findViewById(R.id.back_btn_balance);
        Transactions=findViewById(R.id.transactions);
        //Setting the original balance from the shared preferences
        String Original_Balance;
        SharedPreferences sharedPreferences=getSharedPreferences("PREFERENCE",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Original_Balance=sharedPreferences.getString("Current_Balance","");
        Current_Balance_Balance_in.setText("RS "+Original_Balance);

        //Setting click listener for showing transactions
        Transactions.setOnClickListener(v -> startActivity(new Intent(Balance_in.this,show_transactions.class)));
        // getting text from the elements when the save button is clicked and setting on click listener
        Save.setOnClickListener(v -> {
            String title,amount,day,month,year;

            //Storing data from the edit text into the strings
            title= Title.getText().toString();
            amount=Amount.getText().toString();
            day=Day.getText().toString();
            month=Month.getText().toString();
            year=Year.getText().toString();

            boolean isBalanceConsistent=true;
            //Checking if only one operation is selected
            if(isMinusOperationDone&&isAddOperationDone){
                Snackbar snackbar=Snackbar.make(v,"Vui lòng chọn thêm hoặc bớt !!!",Snackbar.LENGTH_LONG);
                snackbar.show();
                //Resetting values , so users can select the operation again
                isAddOperationDone=false;
                isMinusOperationDone=false;
            }
            else if(!isMinusOperationDone&&!isAddOperationDone){
                Snackbar snackbar=Snackbar.make(v,"Vui lòng chọn thêm hoặc bớt !!!",Snackbar.LENGTH_LONG);
                snackbar.show();
            }
            else {
                //Checking whether the data fields are filled
                if (!(title.isEmpty() || amount.isEmpty() || day.isEmpty() || month.isEmpty() || year.isEmpty())) {
                    //Creating object of expense and saving the data into it
                    balance obj = new balance(title, amount, day, month, year);
                    // Doing Add operation
                    if(isAddOperationDone){
                        int Balance_Integer = Integer.parseInt(Original_Balance);
                        String Amount_String = Amount.getText().toString();
                        if (Amount_String.isEmpty()) {
                            Snackbar snackbar = Snackbar.make(v, "Vui lòng điền chi tiết số tiền !!!", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                        else {
                            int amount_t = Integer.parseInt(Amount_String);
                            Balance_Integer+=amount_t;
                            editor.putString("Current_Balance",Integer.toString(Balance_Integer));
                        }
                    }
                    //Doing Minus operation
                    else if(isMinusOperationDone){
                        int Balance_Integer = Integer.parseInt(Original_Balance);
                        String Amount_String = Amount.getText().toString();
                        if (Amount_String.isEmpty()) {
                            Snackbar snackbar = Snackbar.make(v, "Vui lòng điền chi tiết số tiền !!!", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                        else {
                            int amount_t = Integer.parseInt(Amount_String);
                            if(Balance_Integer>=amount_t) {
                                Balance_Integer -= amount_t;
                                editor.putString("Current_Balance", Integer.toString(Balance_Integer));
                            }
                            else{
                                Toast.makeText(Balance_in.this, "Số dư không đủ !", Toast.LENGTH_LONG).show();
                                isBalanceConsistent=false;
                            }
                        }
                    }
                    //Creating database object
                    DataBaseHelper db = new DataBaseHelper(Balance_in.this);
                    if(isAddOperationDone)
                        obj.setStatus("Deposit");
                    else if(isMinusOperationDone)
                        obj.setStatus("Withdraw");
                    // Calling function to add the expense data
                    boolean isSaved = db.addBalanceDetailsToDB(obj);
                    db.close();
                    if (isSaved&&isBalanceConsistent) {

                        editor.apply();
                        startActivity(new Intent(Balance_in.this, Success.class));
                        finish();
                    }
                } else {
                    //Giving warning to the user to fill all the details
                    Snackbar snackbar = Snackbar.make(v, "Vui lòng điền chính xác thông tin !", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        // setting on click listener for the back button
        Back.setOnClickListener(v -> {
            startActivity(new Intent(Balance_in.this,Home.class));
            finish();
        });


        //Setting on click listener for the add and  subtract button
        Add.setOnClickListener(v -> {
                isAddOperationDone=true;
                Toast.makeText(Balance_in.this, "Số dư sẽ được cộng, vui lòng lưu lại !", Toast.LENGTH_LONG).show();
            });

        Minus.setOnClickListener(v -> {
            isMinusOperationDone = true;
            Toast.makeText(Balance_in.this, "Số dư sẽ bị trừ, vui lòng lưu lại !", Toast.LENGTH_LONG).show();
        });
    }
}