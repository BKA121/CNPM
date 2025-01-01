package com.example.multiexpenserv1.Model;

public class User {
    private String firstName;
    private String currentBalance;

    public User(String firstName, String currentBalance) {
        this.firstName = firstName;
        this.currentBalance = currentBalance;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }
}


