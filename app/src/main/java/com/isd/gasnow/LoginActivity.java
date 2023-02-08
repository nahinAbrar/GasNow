package com.isd.gasnow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class LoginActivity extends AppCompatActivity {
    CountryCodePicker countryCodePicker;
    TextInputLayout phoneNumber, passWord;
    RelativeLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        countryCodePicker = findViewById(R.id.loginCodePicker);
        phoneNumber = findViewById(R.id.loginPhoneNum);
        passWord = findViewById(R.id.loginPassword);
        progressBar = findViewById(R.id.loginProgress);

    }

    public void callWelcomePage(View view){
        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(intent);
    }


    public void letTheUserLoggedIn(View view) {
        if(!validateFields())
        {
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _passWord = passWord.getEditText().getText().toString().trim();

        if(_phoneNumber.charAt(0) == '0'){
            _phoneNumber = _phoneNumber.substring(1);
        }

        String _completePhoneNumber = "+"+countryCodePicker.getSelectedCountryCode()+_phoneNumber;


        Query checkUser = FirebaseDatabase.getInstance("https://gasnow-626582aar-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("Users")
                .orderByChild("phoneNumber").
                equalTo(_completePhoneNumber);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);

                    String checkPassWord = snapshot.child(_completePhoneNumber).
                            child("passWord").getValue(String.class);

                    if(checkPassWord.equals(_passWord)){
                        passWord.setError(null);
                        passWord.setErrorEnabled(false);

                        //intent ashbe
                        String _fullName = snapshot.child(_completePhoneNumber).child("fullName").getValue(String.class);
                        String _userName = snapshot.child(_completePhoneNumber).child("userName").getValue(String.class);
                        String _email = snapshot.child(_completePhoneNumber).child("email").getValue(String.class);
                        String _area = snapshot.child(_completePhoneNumber).child("area").getValue(String.class);
                        String _address = snapshot.child(_completePhoneNumber).child("address").getValue(String.class);

                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, _fullName +"\n"
                                + _userName + "\n"
                                + _email + "\n"
                                + _area + "\n"
                                +_address + "\n", Toast.LENGTH_LONG).show();



                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "Password Mismatch!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Phone Number Doesn't Exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Fetch Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateFields() {
        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _passWord = passWord.getEditText().getText().toString().trim();

        if(_phoneNumber.isEmpty()){
            phoneNumber.setError("Phone Number can not be Empty!");
            phoneNumber.requestFocus();
            return false;
        } else if (_passWord.isEmpty()) {
            passWord.setError("Phone Number can not be Empty!");
            passWord.requestFocus();
            return false;
        }else {
            phoneNumber.setError(null);
            passWord.setError(null);
            return true;
        }

    }
}