package com.isd.gasnow.Transactions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.isd.gasnow.DashBoard.UserDashboard;
import com.isd.gasnow.R;

public class OrderPlaced extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);
    }

    public void CallDashboardPage (View view){
        startActivity(new Intent(OrderPlaced.this, UserDashboard.class));
    }
}