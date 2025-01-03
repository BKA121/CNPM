package com.example.multiexpenserv1.Model;

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
    public String getCurrentBalance() {
        return currentBalance;
    }
}


