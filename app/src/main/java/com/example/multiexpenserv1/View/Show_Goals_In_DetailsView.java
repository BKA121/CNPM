package com.example.multiexpenserv1.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multiexpenserv1.Controller.Show_Goals_In_DetailsController;
import com.example.multiexpenserv1.R;
import com.example.multiexpenserv1.Adapter.MyGoals_Adapter;
import java.util.Objects;

public class Show_Goals_In_DetailsView extends AppCompatActivity {

    private TextView title, date, amount, category, status;
    private ImageView ChangeStatus, Back;
    private TextView ChangeStatusText;
    private Show_Goals_In_DetailsController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_goals_in_details);

        // Get UI elements
        title = findViewById(R.id.Title_Show_Goals_In_Details);
        date = findViewById(R.id.Date_Show_Goals_In_Details);
        amount = findViewById(R.id.Amount_Show_Goals_In_Details);
        category = findViewById(R.id.Category_Show_Goals_In_Details);
        status = findViewById(R.id.Status_Show_Goals_In_Details);
        ChangeStatus = findViewById(R.id.Change_Status_Show_Goals_In_Details);
        Back = findViewById(R.id.Back_Show_Goals_In_Details);
        ChangeStatusText = findViewById(R.id.Change_Status_Text_Show_Goals_In_Details);

        Intent intent = getIntent();

        // Set data from Intent
        title.setText(intent.getStringExtra(MyGoals_Adapter.Show_Goals_Title));
        date.setText(intent.getStringExtra(MyGoals_Adapter.Show_Goals_Date));
        amount.setText(intent.getStringExtra(MyGoals_Adapter.Show_Goals_Amount));
        category.setText(intent.getStringExtra(MyGoals_Adapter.Show_Goals_Category));
        status.setText(intent.getStringExtra(MyGoals_Adapter.Show_Goals_Status));
        ChangeStatusText.setText("Thay đổi trạng thái");

        // Check if status is "ACHIEVED"
        if (Objects.equals(intent.getStringExtra(MyGoals_Adapter.Show_Goals_Status), "Hoàn thành")) {
            ChangeStatus.setVisibility(View.INVISIBLE);
            ChangeStatusText.setVisibility(View.INVISIBLE);
        }

        // Set listeners
        controller = new Show_Goals_In_DetailsController(this);

        Back.setOnClickListener(v -> finish());
        ChangeStatus.setOnClickListener(v -> controller.changeStatus(intent.getIntExtra(MyGoals_Adapter.Show_Goals_ID, -1)));
    }
}
