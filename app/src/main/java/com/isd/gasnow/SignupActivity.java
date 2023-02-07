package com.isd.gasnow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    ImageView backBtn;
    Button nextBtn, loginBtn;
    TextView caText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        backBtn = findViewById(R.id.signUpBackBtn);
        nextBtn = findViewById(R.id.signUpNextBtn);
        loginBtn = findViewById(R.id.signUpLoginBtn);
        caText = findViewById(R.id.signUpCaText);
    }

    public void callNextSignUpScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUpSecondActivity.class);

        //Add Transition
        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backBtn, "transition_back_btn");
        pairs[1] = new Pair<View, String>(nextBtn, "transition_next_btn");
        pairs[2] = new Pair<View, String>(loginBtn, "transition_login_btn");
        pairs[3] = new Pair<View, String>(caText, "transition_ca_text");

        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(SignupActivity.this, pairs);

        startActivity(intent, activityOptions.toBundle());
    }
}