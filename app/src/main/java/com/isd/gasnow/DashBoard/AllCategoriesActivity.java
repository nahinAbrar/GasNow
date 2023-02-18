package com.isd.gasnow.DashBoard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.isd.gasnow.R;

public class AllCategoriesActivity extends AppCompatActivity {

    ImageView categoriesBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        categoriesBackBtn = findViewById(R.id.allCategoriesBackBtn);
        categoriesBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllCategoriesActivity.super.onBackPressed();
            }
        });
    }

}