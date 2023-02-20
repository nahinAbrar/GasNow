package com.isd.gasnow.IntroductoryPages;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import com.isd.gasnow.Login.LoginActivity;
import com.isd.gasnow.R;
import com.isd.gasnow.SignUp.SignupActivity;

public class WelcomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);



    }

    public void callLoginPage(View view){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(findViewById(R.id.wcLoginBtn),"transition_login");

        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(WelcomeActivity.this,pairs);
        startActivity(intent, activityOptions.toBundle());
    }

    public void callSignUpPage(View view)
    {
        Intent intent = new Intent (getApplicationContext(), SignupActivity.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.wcSignUpBtn), "transition_signup");

        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(WelcomeActivity.this,pairs);
        startActivity(intent, activityOptions.toBundle());
    }

    public void callProcedure(View view){
        startActivity(new Intent(WelcomeActivity.this, WorkingProcedure.class));
    }

}