package com.isd.gasnow.SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ScrollView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.isd.gasnow.R;

public class SignUpThirdActivity extends AppCompatActivity {


    ScrollView scrollView;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        Query query = FirebaseDatabase
                .getInstance("https://gasnow-626582aar-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("Users").orderByChild("phoneNumber").equalTo(_phoneNumber);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.exists())){
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

                }else {
                    Snackbar.make(scrollView,"Phone Number Already Exists!",Snackbar.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent i=new Intent(SignUpThirdActivity.this,SignupActivity.class);
                            startActivity(i);
                        }
                    }, 3000);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




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