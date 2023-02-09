package com.isd.gasnow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.isd.gasnow.Database.SessionManager;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);

        SessionManager sessionManager = new SessionManager(this, SessionManager.USER_SESSION);
        HashMap<String,String> userDetails = sessionManager.getUserDetailFromSession();

        String fullName = userDetails.get(SessionManager.KEY_FULL_NAME);
        String phoneNumber = userDetails.get(SessionManager.KEY_PHONE_NUMBER);

        textView.setText(fullName+"\n"+phoneNumber);


    }
}