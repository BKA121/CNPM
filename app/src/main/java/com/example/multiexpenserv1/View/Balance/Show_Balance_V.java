package com.example.multiexpenserv1.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.multiexpenserv1.Data.DataBaseHelper;
import com.example.multiexpenserv1.Model.Balance;
import com.example.multiexpenserv1.R;
import com.example.multiexpenserv1.Adapter.Show_Transactions_Adapter;

import java.util.List;

public class Show_Transactions_V extends AppCompatActivity {
    private RecyclerView recyclerView;
    DataBaseHelper db = new DataBaseHelper(Show_Transactions_V.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_transactions);
        recyclerView = findViewById(R.id.show_transactions_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Balance> balanceList = db.getAllBalance();
        Show_Transactions_Adapter adapter=new Show_Transactions_Adapter(balanceList,this);
        recyclerView.setAdapter(adapter);
    }
}