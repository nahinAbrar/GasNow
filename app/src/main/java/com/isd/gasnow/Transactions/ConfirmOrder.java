package com.isd.gasnow.Transactions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.isd.gasnow.DashBoard.UserDashboard;
import com.isd.gasnow.R;

public class ConfirmOrder extends AppCompatActivity {

    RecyclerView orderRv;
    ConfirmOrderActivityAdapter confirmOrderActivityAdapter;
    Activity context;
    UpdateSelectedItems updateSelectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        Toolbar toolbar = findViewById(R.id.confrimOrderToolBar);
        toolbar.setTitle("Confirm Order");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        orderRv = findViewById(R.id.confirmOrderRecyclerView);
        confirmOrderActivityAdapter = new ConfirmOrderActivityAdapter(context);
        orderRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        orderRv.setAdapter(confirmOrderActivityAdapter);

    }

    public void callOrderPlaced(View view){
        startActivity(new Intent(getApplicationContext(), OrderPlaced.class));
    }

}