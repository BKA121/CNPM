package com.example.multiexpenserv1.Model.Goal;

import com.example.multiexpenserv1.Model.User.User;

public class Goal {
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private String Title,Amount,Type,Day,Month,Year,Status;
    User user;
    
    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Goal(String title, String amount, String type, String day, String month, String year) {
        Title = title;
        Amount = amount;
        Type = type;
        Day = day;
        Month = month;
        Year = year;
    }
    public  String getAmountWithRS(){
       return user.formatCurrency(Amount) + " VND";
    }

    public String getTitle() {
        return Title;
    }

    public String getDate(){
        return (getDay()+"/"+getMonth()+"/"+getYear());
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }
}
