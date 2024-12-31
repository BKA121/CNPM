package com.example.multiexpenserv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.multiexpenserv1.Model.DataBaseHelper;
import com.example.multiexpenserv1.Model.goal;
import com.example.multiexpenserv1.View.Home;
import com.google.android.material.snackbar.Snackbar;

public class New_Goal extends AppCompatActivity {
    private ImageView save,back,myGoals;
    private EditText Title,Amount,Type,Day,Month,Year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goal);
        //Getting all the ui elements
        Title=findViewById(R.id.New_Goal_Title);
        Amount=findViewById(R.id.New_Goal_Amount);
        Type=findViewById(R.id.New_Goal_Type);
        Day=findViewById(R.id.New_Goal_Day);
        Month=findViewById(R.id.New_Goal_Month);
        Year=findViewById(R.id.New_Goal_Year);
        save=findViewById(R.id.Save_Btn_New_Goal);
        back=findViewById(R.id.Back_Btn_New_Goal);
        myGoals=findViewById(R.id.New_Goal_My_Goals_Bg);

        // On click listener for back button
        back.setOnClickListener(v -> {
            startActivity(new Intent(New_Goal.this, Home.class));
            finish();
        });

        //Save OnCLickListener
        save.setOnClickListener(v -> {
            String title,amount,day,month,year,type;
            boolean isBalanceConsistent=true;
            //Storing data from the edit text into the strings
            title= Title.getText().toString();
            amount=Amount.getText().toString();
            type=Type.getText().toString();
            day=Day.getText().toString();
            month=Month.getText().toString();
            year=Year.getText().toString();

            //Checking whether the data fields are filled
            if(!(title.isEmpty()||amount.isEmpty()||day.isEmpty()||month.isEmpty()||year.isEmpty()||type.isEmpty())) {
                //Creating object of expense and saving the data into it
                goal obj = new goal(title, amount,type, day, month, year);
                obj.setStatus("PENDING");
                //Creating database object
                DataBaseHelper db = new DataBaseHelper(New_Goal.this);
                // Calling function to add the expense data
                boolean isSaved= db.AddGoalToDB(obj);
                db.close();
                if (isSaved) {
                    startActivity(new Intent(New_Goal.this,Success.class));
                    finish();
                }
                else {
                    Snackbar snackbar=Snackbar.make(v,"Vui lòng điền chi tiết thông tin !",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
            else {
                //Giving warning to the user to fill all the details
                Snackbar snackbar=Snackbar.make(v,"Vui lòng điền chi tiết thông tin !",Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        //Setting onclick listener for my goals
        myGoals.setOnClickListener(v -> startActivity(new Intent(New_Goal.this,My_Goals.class)));
    }
}