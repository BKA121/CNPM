package com.example.multiexpenserv1.Model.Balance;

public class Balance {
    private String title,amount;
    private String day;
    private final String month;
    private final String year;
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Balance(String title, String amount, String day, String month, String year) {
        this.amount = amount;
        this.day = day;
        this.month = month;
        this.year = year;
        this.title=title;
    }
    public String getAmount() {
        return amount;
    }
    public  String getAmountWithRS(){
        return "RS "+amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getMonth() {
        return month;
    }
    public String getYear() {
        return year;
    }
    public String getDate(){
        return (getDay()+"/"+getMonth()+"/"+getYear());
    }
}
