package com.isd.gasnow.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.isd.gasnow.R;

public class SignupActivity extends AppCompatActivity {

    ImageView backBtn;
    Button nextBtn, loginBtn;
    TextView caText;

    ScrollView signupScrollView;

    TextInputLayout fullName, userName, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        backBtn = findViewById(R.id.signUpBackBtn);
        nextBtn = findViewById(R.id.signUpNextBtn);
        loginBtn = findViewById(R.id.signUpLoginBtn);
        caText = findViewById(R.id.signUpCaText);

        fullName = findViewById(R.id.signupFullName);
        userName = findViewById(R.id.signupUserName);
        email = findViewById(R.id.signupEmail);
        password = findViewById(R.id.signupPassword);

        signupScrollView = findViewById(R.id.signUpScrollView);
    }

    public void callNextSignUpScreen(View view) {

        if(!validateFullName() | !validateUsername() | !validateEmail() | !validatePassword())
        {
            return;
        }

        String _fullName = fullName.getEditText().getText().toString().trim();
        String _userName = userName.getEditText().getText().toString().trim();
        String _email = email.getEditText().getText().toString().trim();
        String _password = password.getEditText().getText().toString();

        Intent intent = new Intent(getApplicationContext(), SignUpSecondActivity.class);

        intent.putExtra("fullName", _fullName);
        intent.putExtra("userName", _userName);
        intent.putExtra("email", _email);
        intent.putExtra("password", _password);

        //Add Transition
        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backBtn, "transition_back_btn");
        pairs[1] = new Pair<View, String>(nextBtn, "transition_next_btn");
        pairs[2] = new Pair<View, String>(loginBtn, "transition_login_btn");
        pairs[3] = new Pair<View, String>(caText, "transition_ca_text");

        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(SignupActivity.this, pairs);

        startActivity(intent, activityOptions.toBundle());
    }

    private boolean validateFullName() {
        String val = fullName.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            fullName.setError("Full Name can not be Empty");
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = userName.getEditText().getText().toString().trim();
        String checkWhite = "\\A\\w{1,20}\\z";

        if (val.isEmpty()) {
            userName.setError("Username can not be Empty!");
            return false;
        } else if (val.length() > 20) {
            userName.setError("Username too large!");
            return false;
        } else if (!val.matches(checkWhite)) {
            userName.setError("No White spaces are allowed!");
            return false;
        } else {
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = email.getEditText().getText().toString().trim();
        String checkWhite = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Email can not be Empty!");
            return false;
        } else if (!val.matches(checkWhite)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();
        String checkWhite = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            password.setError("Password can not be Empty!");
            return false;}
//        } else if (!val.matches(checkWhite)) {
//            password.setError("Invalid Password!");
//            return false;
//        }
          else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}