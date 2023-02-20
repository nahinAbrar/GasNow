package com.isd.gasnow.IntroductoryPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.isd.gasnow.R;

public class WorkingProcedure extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_procedure);
    }

    public void callWelcomePageFromProce(View view){
        startActivity(new Intent(WorkingProcedure.this,WelcomeActivity.class));
    }
}