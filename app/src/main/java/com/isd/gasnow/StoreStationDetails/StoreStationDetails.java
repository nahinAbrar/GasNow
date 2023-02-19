package com.isd.gasnow.StoreStationDetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.isd.gasnow.R;
import com.isd.gasnow.Transactions.ConfirmOrder;
import com.isd.gasnow.Transactions.UpdateSelectedItems;

import java.util.ArrayList;
public class StoreStationDetails extends AppCompatActivity implements UpdateRecyclerView2 {

    private RecyclerView dRecyclerView;
    private DynamicRvAdapter2 dynamicRvAdapter;
    int pos;
    Activity context;
    ImageView cartIcon;
    UpdateSelectedItems updateSelectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_station_details);

        Intent intent = getIntent();
        pos = intent.getIntExtra("pos",0);

        ArrayList<StaticRvModel2> items = new ArrayList<>();
        items.add(new StaticRvModel2(R.drawable.gas_store,pos));
        items.add(new StaticRvModel2(R.drawable.gas_icon,pos));
        items.add(new StaticRvModel2(R.drawable.gas_store,pos));
        items.add(new StaticRvModel2(R.drawable.gas_station,pos));

        RecyclerView recyclerView = findViewById(R.id.store_stat_rv01);
        StaticRvAdapter2 staticRvAdapter = new StaticRvAdapter2(items, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);

        ArrayList<DynamicRvModel2> item = new ArrayList<>();
        item.add(new DynamicRvModel2("LPG Gas Cylinder 48Kg", 3500));
        item.add(new DynamicRvModel2("LPG Gas Cylinder 22Kg", 3500));
        item.add(new DynamicRvModel2("LPG Gas Cylinder 28Kg", 3500));
        dRecyclerView = findViewById(R.id.store_stat_rv02);
        dynamicRvAdapter = new DynamicRvAdapter2(item,context,updateSelectedItems);
        dRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dRecyclerView.setAdapter(dynamicRvAdapter);

        cartIcon = findViewById(R.id.cartIcon);

        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StoreStationDetails.this, ConfirmOrder.class));
            }
        });

    }

    @Override
    public void callback2(int position, ArrayList<DynamicRvModel2> items) {
        dynamicRvAdapter = new DynamicRvAdapter2(items,context,updateSelectedItems);
        dynamicRvAdapter.notifyDataSetChanged();
        dRecyclerView.setAdapter(dynamicRvAdapter);
    }
}