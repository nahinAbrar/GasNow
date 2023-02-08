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

import com.google.android.material.textfield.TextInputLayout;

public class SignUpSecondActivity extends AppCompatActivity {

    ImageView backBtn;
    Button nextBtn, loginBtn;
    TextView caText;

    TextInputLayout area, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up_second);

        backBtn = findViewById(R.id.signUpBackBtn);
        nextBtn = findViewById(R.id.signUpNextBtn);
        loginBtn = findViewById(R.id.signUpLoginBtn);
        caText = findViewById(R.id.signUpCaText);

        area = findViewById(R.id.signupTwoArea);
        address = findViewById(R.id.signupTwoAddress);
    }

    public void callNextSignUpScreen(View view) {

        if(!validateArea() | !validateAddress()){
            return;
        }

        String _fullName = getIntent().getStringExtra("fullName");
        String _userName = getIntent().getStringExtra("userName");
        String _email = getIntent().getStringExtra("email");
        String _password = getIntent().getStringExtra("password");
        String _area = area.getEditText().getText().toString().trim();
        String _address = address.getEditText().getText().toString().trim();

        Intent intent = new Intent(getApplicationContext(), SignUpThirdActivity.class);

        intent.putExtra("fullName", _fullName);
        intent.putExtra("userName", _userName);
        intent.putExtra("email", _email);
        intent.putExtra("password", _password);
        intent.putExtra("area", _area);
        intent.putExtra("address", _address);



        //Add Transition
        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backBtn, "transition_back_btn");
        pairs[1] = new Pair<View, String>(nextBtn, "transition_next_btn");
        pairs[2] = new Pair<View, String>(loginBtn, "transition_login_btn");
        pairs[3] = new Pair<View, String>(caText, "transition_ca_text");

        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(SignUpSecondActivity.this, pairs);

        startActivity(intent, activityOptions.toBundle());
    }

    private boolean validateArea() {
        String val = area.getEditText().getText().toString().trim();
        String checkWhite = "\\A\\w{1,10}\\z";

        if (val.isEmpty()) {
            area.setError("Area can not be Empty!");
            return false;
        } else if (val.length() > 10) {
            area.setError("Area too large!");
            return false;
        } else if (!val.matches(checkWhite)) {
            area.setError("No White spaces are allowed!");
            return false;
        } else {
            area.setError(null);
            area.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateAddress() {
        String val = address.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            address.setError("Address can not be Empty!");
            return false;
        }
        else {
            address.setError(null);
            address.setErrorEnabled(false);
            return true;
        }
    }
}