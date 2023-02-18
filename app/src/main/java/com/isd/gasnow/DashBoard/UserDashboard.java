package com.isd.gasnow.DashBoard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.isd.gasnow.IntroductoryPages.WelcomeActivity;
import com.isd.gasnow.MainActivity;
import com.isd.gasnow.R;
import com.isd.gasnow.User.UserProfileDashboard;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView featureRecycler, mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;

    static final float END_SCALE = 0.7f;

//    private GradientDrawable gradient1, gradient2, gradient3, gradient4;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout contentView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        featureRecycler = findViewById(R.id.dashboardFeaturedRecycleView);
        mostViewedRecycler = findViewById(R.id.dashboardMostviewedRecyclerView);
        categoriesRecycler = findViewById(R.id.dashboardCategoriesRecyclerView);
        drawerLayout = findViewById(R.id.nav_drawer_layout);
        navigationView = findViewById(R.id.nav_navigationview);
        menuIcon = findViewById(R.id.navbar_menuIcon);
        contentView = findViewById(R.id.content);




        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();
        navigataionDrawer();

    }

    private void navigataionDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else
                    drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.darker_blue));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String _fullName = getIntent().getStringExtra("fullName");
        String _userName = getIntent().getStringExtra("userName");
        String _phoneNumber = getIntent().getStringExtra("phoneNumber");
        String _email = getIntent().getStringExtra("email");
        String _area = getIntent().getStringExtra("area");
        String _address = getIntent().getStringExtra("address");
        switch (item.getItemId()) {
            case R.id.nav_logout:
                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_orders:
                startActivity(new Intent(getApplicationContext(), AllCategoriesActivity.class));
                break;
            case R.id.nav_profile:
                intent = new Intent(getApplicationContext(),UserProfileDashboard.class);
                intent.putExtra("fullName",_fullName);
                intent.putExtra("userName",_userName);
                intent.putExtra("phoneNumber",_phoneNumber);
                intent.putExtra("email",_email);
                intent.putExtra("area",_area);
                intent.putExtra("address",_address);
                startActivity(intent);
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void categoriesRecycler() {
        //All Gradients
//        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
//        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
//        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
//        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});
        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.card1, "Household Gas/Fuel"));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.card2, "Vehicle Gas"));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.card2, "Business Solutions"));
        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);
    }

    private void mostViewedRecycler() {
        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewedAdapterHelperClass> mostViewedAdapterHelperClassArrayList = new ArrayList<>();
        mostViewedAdapterHelperClassArrayList.add(new MostViewedAdapterHelperClass(R.drawable.card1, "Gas", "Lorem ipsum abc abc sjdsjddskd"));
        mostViewedAdapterHelperClassArrayList.add(new MostViewedAdapterHelperClass(R.drawable.card2, "Store", "Lorem ipsum abc abc sjdsjddskd"));

        adapter = new MostViewedAdapter(mostViewedAdapterHelperClassArrayList);
        mostViewedRecycler.setAdapter(adapter);
    }

    private void featuredRecycler() {
        featureRecycler.setHasFixedSize(true);
        featureRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredHelperClassArrayList = new ArrayList<>();
        featuredHelperClassArrayList.add(new FeaturedHelperClass(R.drawable.card1, "Gas", "Lorem ipsum"));
        featuredHelperClassArrayList.add(new FeaturedHelperClass(R.drawable.card2, "Store", "Lorem ipsum"));

        adapter = new FeaturedAdapter(featuredHelperClassArrayList);
        featureRecycler.setAdapter(adapter);


    }

    public void callProfile(View view){
        String _fullName = getIntent().getStringExtra("fullName");
        String _userName = getIntent().getStringExtra("userName");
        String _phoneNumber = getIntent().getStringExtra("phoneNumber");
        String _email = getIntent().getStringExtra("email");
        String _area = getIntent().getStringExtra("area");
        String _address = getIntent().getStringExtra("address");

        Intent intent = new Intent(getApplicationContext(),UserProfileDashboard.class);
        intent.putExtra("fullName",_fullName);
        intent.putExtra("userName",_userName);
        intent.putExtra("phoneNumber",_phoneNumber);
        intent.putExtra("email",_email);
        intent.putExtra("area",_area);
        intent.putExtra("address",_address);
        startActivity(intent);
    }

    public void callMaps(View view){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }


}