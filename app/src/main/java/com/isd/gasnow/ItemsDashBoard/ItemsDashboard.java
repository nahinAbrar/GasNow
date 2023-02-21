package com.isd.gasnow.ItemsDashBoard;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.isd.gasnow.R;
import com.isd.gasnow.StoreStationDetails.StoreStationDetails;

import java.util.ArrayList;

public class ItemsDashboard extends AppCompatActivity implements UpdateRecyclerView {

    private RecyclerView recyclerView, dRecyclerView;
    private StaticRvAdapter staticRvAdapter;
    private DynamicRvAdapter dynamicRvAdapter;

    int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_dashboard);

        ArrayList<StaticRvModel> items = new ArrayList<>();
        items.add(new StaticRvModel(R.drawable.lpg, "LP Gas"));
        items.add(new StaticRvModel(R.drawable.fire, "Disel"));
        items.add(new StaticRvModel(R.drawable.octane, "Octane"));
        items.add(new StaticRvModel(R.drawable.oil, "Oil"));

        recyclerView = findViewById(R.id.itemsDashRV01);
        staticRvAdapter = new StaticRvAdapter(items, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);

        ArrayList<DynamicRvModel> item = new ArrayList();
        item.add(new DynamicRvModel("Bashundhara LP 12Kg", R.drawable.lpg,0));
        item.add(new DynamicRvModel("Bashundhara LP 22Kg", R.drawable.lpg,0));
        item.add(new DynamicRvModel("Bashundhara LP 30Kg", R.drawable.lpg,0));
        item.add(new DynamicRvModel("Beximco LP 12Kg", R.drawable.lpg,0));
        item.add(new DynamicRvModel("Beximco LP 22Kg", R.drawable.lpg,0));
        dRecyclerView = findViewById(R.id.itemsDashRV02);
        dynamicRvAdapter = new DynamicRvAdapter(item);
        dRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dRecyclerView.setAdapter(dynamicRvAdapter);


    }

    @Override
    public void callback(int position, ArrayList<DynamicRvModel> items) {
        dynamicRvAdapter = new DynamicRvAdapter(items);
        dynamicRvAdapter.notifyDataSetChanged();
        dRecyclerView.setAdapter(dynamicRvAdapter);

        dynamicRvAdapter.setOnItemClickListener(new DynamicRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                pos = items.get(position).getPos();
                Intent intent = new Intent(getApplicationContext(), StoreStationDetails.class);
                intent.putExtra("pos",pos);
                startActivity(intent);
            }
        });
    }
}