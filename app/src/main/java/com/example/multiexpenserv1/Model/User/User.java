package com.example.multiexpenserv1.Model.User;

import java.text.NumberFormat;
import java.util.Locale;

public class User {
    private final String lastName;
    private final String currentBalance;

    public User( String lastName, String currentBalance) {
        this.lastName = lastName;
        this.currentBalance = currentBalance;
    }

    public String getLastName() {
        return lastName;
    }

    public static String formatCurrency(String amount) {
        try {
            double value = Double.parseDouble(amount);
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
            return numberFormat.format(value);
        } catch (NumberFormatException e) {
            return amount; // Nếu không thể parse, trả lại chuỗi gốc
        }
    }
    
    public String getCurrentBalance() {
        return currentBalance;
    }
}


