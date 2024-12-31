package com.example.multiexpenserv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private TextView name,balance;
    private  String fname;
    private String cbalance;
    private ImageView share,newexpense,Balance_in,Goals,Chart;
    private ImageView information;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPreferences=getSharedPreferences("PREFERENCE",MODE_PRIVATE);

        //Accessing the text view by id from backend
        name=findViewById(R.id.Welcome);
        balance=findViewById(R.id.Home_Balance);
        share=findViewById(R.id.share);
        newexpense=findViewById(R.id.newexpense_button);
        Balance_in=findViewById(R.id.Balance_button);
        Goals=findViewById(R.id.Goals_button);
        information=findViewById(R.id.Information);
        Chart=findViewById(R.id.Gifts_Button);

        //Getting the user data from the sharedPreferences
        fname=sharedPreferences.getString("First_Name","");
        cbalance=sharedPreferences.getString("Current_Balance","");

        // Setting textview on the home screen
        name.setText("Welcome "+fname);
        balance.setText("RS "+cbalance);

        //Share activity
        share.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Download MultiExpenser ");
            intent.putExtra(Intent.EXTRA_TEXT, "Regards of the day , try this amazing app to save your money named as MultiExpenser. Download it from the playStore");
            startActivity(Intent.createChooser(intent, "choose one"));
        });

        //New expense activity
        newexpense.setOnClickListener(v -> startActivity(new Intent(Home.this,new_expense_in.class)));

        //Balance Activity
        Balance_in.setOnClickListener(v -> startActivity(new Intent(Home.this, com.example.multiexpenserv1.Balance_in.class)));

        //Goals Activity
        Goals.setOnClickListener(v -> startActivity(new Intent(Home.this,New_Goal.class)));

        //Chart Activity
        Chart.setOnClickListener(v -> startActivity(new Intent(Home.this,Chart.class)));

        //Information Activity
        information.setOnClickListener(v -> startActivity(new Intent(Home.this, Information.class)));
    }
}