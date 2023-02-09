package com.isd.gasnow.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;
import android.view.View;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ScrollView;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;
import com.isd.gasnow.R;

public class SignUpThirdActivity extends AppCompatActivity {


    ScrollView scrollView;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up_third);

        phoneNumber = findViewById(R.id.signupThirdPhoneNum);
        countryCodePicker = findViewById(R.id.signupThirdCodePicker);
        scrollView = findViewById(R.id.signupThirdScrollView);
    }

    public void callOTPPage(View view)
    {
        if(!validatePhoneNumber())
        {
            return;
        }

        String _fullName = getIntent().getStringExtra("fullName");
        String _userName = getIntent().getStringExtra("userName");
        String _email = getIntent().getStringExtra("email");
        String _password = getIntent().getStringExtra("password");
        String _area = getIntent().getStringExtra("area");
        String _address = getIntent().getStringExtra("address");

        String _getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim();
        //For trimming Bd Numbers 017... to 17
        if(_getUserEnteredPhoneNumber.charAt(0) == '0')
        {
            _getUserEnteredPhoneNumber = _getUserEnteredPhoneNumber.substring(1);
        }
        String _phoneNumber = "+"+countryCodePicker.getSelectedCountryCode()+_getUserEnteredPhoneNumber;


        Intent intent = new Intent(getApplicationContext(), VerifyOTPActivity.class);

        intent.putExtra("fullName", _fullName);
        intent.putExtra("userName", _userName);
        intent.putExtra("email", _email);
        intent.putExtra("password", _password);
        intent.putExtra("area", _area);
        intent.putExtra("address", _address);
        intent.putExtra("phoneNumber", _phoneNumber);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(scrollView, "transition_OTP_screen");


        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(SignUpThirdActivity.this, pairs);

        startActivity(intent, activityOptions.toBundle());

    }



    private boolean validatePhoneNumber() {
        String val = phoneNumber.getEditText().getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            phoneNumber.setError("Enter valid phone number");
            return false;
        }
//        else if (!val.matches(checkspaces)) {
//            phoneNumber.setError("No White spaces are allowed!");
//            return false;
//        }
        else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }


}