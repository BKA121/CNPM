package com.example.multiexpenserv1.Controller;

import android.content.Intent;

import com.example.multiexpenserv1.Model.DataBaseHelper;
import com.example.multiexpenserv1.View.Congratulations;
import com.example.multiexpenserv1.View.Show_Goals_In_DetailsView;

public class Show_Goals_In_DetailsController {

    private Show_Goals_In_DetailsView view;
    private DataBaseHelper db;

    public Show_Goals_In_DetailsController(Show_Goals_In_DetailsView view) {
        this.view = view;
        db = new DataBaseHelper(view);
    }

    // Method to handle status change logic
    public void changeStatus(int goalId) {
        // Call method in Model to update the goal status
        db.ChangeGoalStatus(goalId);

        // Navigate to the Congratulations screen
        view.startActivity(new Intent(view, Congratulations.class));
        view.finish();
    }
}
