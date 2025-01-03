package com.example.multiexpenserv1.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.multiexpenserv1.Model.DataBaseHelper;
import com.example.multiexpenserv1.Model.goal;
import com.example.multiexpenserv1.Adapter.MyGoals_Adapter;
import com.example.multiexpenserv1.R;

import java.util.List;

public class My_GoalsController extends AppCompatActivity {

    RecyclerView recyclerView;
    DataBaseHelper db = new DataBaseHelper(My_GoalsController.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_goals);

        recyclerView = findViewById(R.id.My_Goals_Recycler_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<goal> goalsList=db.getAllGoals();
        MyGoals_Adapter adapter=new MyGoals_Adapter(goalsList,this);
        recyclerView.setAdapter(adapter);
    }
}