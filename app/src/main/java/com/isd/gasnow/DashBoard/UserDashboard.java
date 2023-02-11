package com.isd.gasnow.DashBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.isd.gasnow.DashBoard.FeaturedAdapter;
import com.isd.gasnow.DashBoard.FeaturedHelperClass;
import com.isd.gasnow.R;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity {

    RecyclerView featureRecycler,mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;

    private GradientDrawable gradient1, gradient2, gradient3, gradient4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        featureRecycler = findViewById(R.id.dashboardFeaturedRecycleView);
        mostViewedRecycler = findViewById(R.id.dashboardMostviewedRecyclerView);
        categoriesRecycler = findViewById(R.id.dashboardCategoriesRecyclerView);


        
        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();

    }

    private void categoriesRecycler() {
        //All Gradients
//        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
//        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
//        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
//        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});
        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.card1, "Education"));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.card1, "HOSPITAL"));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.card1, "Restaurant"));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.card1, "Shopping"));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.card1, "Transport"));
        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);
    }


    private void mostViewedRecycler() {
        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<MostViewedAdapterHelperClass> mostViewedAdapterHelperClassArrayList = new ArrayList<>();
        mostViewedAdapterHelperClassArrayList.add(new MostViewedAdapterHelperClass(R.drawable.card1,"Gas", "Lorem ipsum abc abc sjdsjddskd"));
        mostViewedAdapterHelperClassArrayList.add(new MostViewedAdapterHelperClass(R.drawable.card2,"Store", "Lorem ipsum abc abc sjdsjddskd"));

        adapter = new MostViewedAdapter(mostViewedAdapterHelperClassArrayList);
        mostViewedRecycler.setAdapter(adapter);
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