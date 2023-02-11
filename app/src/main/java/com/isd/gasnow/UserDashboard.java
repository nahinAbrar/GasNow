package com.isd.gasnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity {

    RecyclerView featureRecycler;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        featureRecycler = findViewById(R.id.dashboardFeaturedRecycleView);
        
        featuredRecycler();
    }

    private void featuredRecycler() {
        featureRecycler.setHasFixedSize(true);
        featureRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelperClass> featuredHelperClassArrayList = new ArrayList<>();
        featuredHelperClassArrayList.add(new FeaturedHelperClass(R.drawable.card1,"Gas", "Lorem ipsum"));
        featuredHelperClassArrayList.add(new FeaturedHelperClass(R.drawable.card2,"Store", "Lorem ipsum"));

        adapter = new FeaturedAdapter(featuredHelperClassArrayList);
        featureRecycler.setAdapter(adapter);


    }
}