package com.example.multiexpenserv1.Controller;

import android.content.Context;
import com.example.multiexpenserv1.Model.DataBaseHelper;
import com.example.multiexpenserv1.Model.balance;

public class BalanceController {
    private DataBaseHelper dbHelper;

    public BalanceController(Context context) {
        this.dbHelper = new DataBaseHelper(context);
    }

    public boolean saveBalanceData(balance balance) {
        return dbHelper.addBalanceDetailsToDB(balance);
    }

}

