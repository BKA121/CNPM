package com.example.multiexpenserv1.Controller;

import android.content.Intent;

import com.example.multiexpenserv1.Data.DataBaseHelper;
import com.example.multiexpenserv1.View.FlashScreen.Congratulations_V;
import com.example.multiexpenserv1.View.Goal.Show_Goals_In_Details_V;

public class Show_Goals_In_Details_C {

    private Show_Goals_In_Details_V view;
    private DataBaseHelper db;

    public Show_Goals_In_Details_C(Show_Goals_In_Details_V view) {
        this.view = view;
        db = new DataBaseHelper(view);
    }

    // Method to handle status change logic
    public void changeStatus(int goalId) {
        // Call method in Model to update the Goal status
        db.ChangeGoalStatus(goalId);

        // Navigate to the Congratulations_V screen
        view.startActivity(new Intent(view, Congratulations_V.class));
        view.finish();
    }
}
