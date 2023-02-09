package com.isd.gasnow.PasswordReset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.isd.gasnow.Login.LoginActivity;
import com.isd.gasnow.R;

public class ForgetPassSuccessMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass_success_message);
    }

    public void CallLoginPage(View view)
    {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}