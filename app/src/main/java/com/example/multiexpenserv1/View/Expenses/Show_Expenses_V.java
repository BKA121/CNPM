package com.example.multiexpenserv1.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.multiexpenserv1.Adapter.ShowExpenses_Adapter;
import com.example.multiexpenserv1.Data.DataBaseHelper;
import com.example.multiexpenserv1.Model.Expense;
import com.example.multiexpenserv1.R;

import java.util.List;

public class Show_Expenses_V extends AppCompatActivity {

    private RecyclerView recyclerView;
    DataBaseHelper db = new DataBaseHelper(Show_Expenses_V.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expenses);

        recyclerView = findViewById(R.id.Show_Expenses_Recycler_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Expense> expenseList=db.getAllExpenses();
        ShowExpenses_Adapter adapter=new ShowExpenses_Adapter(expenseList,this);
        recyclerView.setAdapter(adapter);
    }
}