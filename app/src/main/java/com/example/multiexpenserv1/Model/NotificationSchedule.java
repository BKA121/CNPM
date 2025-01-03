package com.example.multiexpenserv1.Model;

import java.util.Calendar;

public class NotificationSchedule {
    private final Calendar calendar;
    private final String title;
    private final String message;

    public NotificationSchedule(Calendar calendar, String title, String message) {
        this.calendar = calendar;
        this.title = title;
        this.message = message;
    }
    public Calendar getCalendar() {return calendar;}
    public String getTitle() {return title;}
    public String getMessage() {return message;}
}

