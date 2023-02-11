package com.isd.gasnow.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.isd.gasnow.Database.SessionManager;
import com.isd.gasnow.PasswordReset.ForgetPasswordActivity;
import com.isd.gasnow.IntroductoryPages.WelcomeActivity;
import com.isd.gasnow.R;
import com.isd.gasnow.SignUp.SignupActivity;
import com.isd.gasnow.DashBoard.UserDashboard;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    CountryCodePicker countryCodePicker;
    TextInputLayout phoneNumber, passWord;
    RelativeLayout progressBar;

    EditText phoneEdit, passEdit;

    CheckBox rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        countryCodePicker = findViewById(R.id.loginCodePicker);
        phoneNumber = findViewById(R.id.loginPhoneNum);
        passWord = findViewById(R.id.loginPassword);
        progressBar = findViewById(R.id.loginProgress);
        rememberMe = findViewById(R.id.loginRememberMe);

        phoneEdit = findViewById(R.id.loginPhoneEditText);
        passEdit = findViewById(R.id.loginPasswordEditText);


        SessionManager sessionManager = new SessionManager(LoginActivity.this, SessionManager.USER_REMEMBER_ME_SESSION);
        if(sessionManager.checkRememberMe()){
            HashMap<String,String> rememberMeDetails = sessionManager.getRememberMeDetailFromSession();
            phoneEdit.setText(rememberMeDetails.get(SessionManager.KEY_REMEMBER_ME_PHONE_NUMBER));
            passEdit.setText(rememberMeDetails.get(SessionManager.KEY_REMEMBER_ME_PASSWORD));

        }

    }

    public void callWelcomePage(View view) {
        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(intent);
    }


    public void letTheUserLoggedIn(View view) {

        if (!isConnectedToInternet(this)) {
            showCustomDialog();
            return;
        }


        if (!validateFields()) {
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _passWord = passWord.getEditText().getText().toString().trim();

        if (_phoneNumber.charAt(0) == '0') {
            _phoneNumber = _phoneNumber.substring(1);
        }

        String _completePhoneNumber = "+" + countryCodePicker.getSelectedCountryCode() + _phoneNumber;


        if(rememberMe.isChecked()) {
            SessionManager sessionManager = new SessionManager(LoginActivity.this, SessionManager.USER_REMEMBER_ME_SESSION);
            sessionManager.createRememberMeSession(_phoneNumber,_passWord);
        }

        Query checkUser = FirebaseDatabase.getInstance("https://gasnow-626582aar-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("Users")
                .orderByChild("phoneNumber").
                equalTo(_completePhoneNumber);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);

                    String checkPassWord = snapshot.child(_completePhoneNumber).
                            child("passWord").getValue(String.class);

                    assert checkPassWord != null;
                    if (checkPassWord.equals(_passWord)) {
                        passWord.setError(null);
                        passWord.setErrorEnabled(false);

                        //intent ashbe
                        String _fullName = snapshot.child(_completePhoneNumber).child("fullName").getValue(String.class);
                        String _userName = snapshot.child(_completePhoneNumber).child("userName").getValue(String.class);
                        String _email = snapshot.child(_completePhoneNumber).child("email").getValue(String.class);
                        String _password = snapshot.child(_completePhoneNumber).child("passWord").getValue(String.class);
                        String _area = snapshot.child(_completePhoneNumber).child("area").getValue(String.class);
                        String _address = snapshot.child(_completePhoneNumber).child("address").getValue(String.class);
                        String _phoneNumber = snapshot.child(_completePhoneNumber).child("phoneNumber").getValue(String.class);

                        SessionManager sessionManager = new SessionManager(LoginActivity.this, SessionManager.USER_SESSION);
                        sessionManager.createLoginSession(_fullName,_userName,_email,_passWord,_area,_address,_phoneNumber);

                        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                        finish();

                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, _fullName + "\n"
                                + _userName + "\n"
                                + _email + "\n"
                                + _area + "\n"
                                + _address + "\n", Toast.LENGTH_LONG).show();


                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "Password Mismatch!", Toast.LENGTH_SHORT).show();
                    }
                } else {
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

    private void showCustomDialog() {
        progressBar.setVisibility(View.GONE);
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("No Internet Connection! \nPlease Have an Active Internet Connection").setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                        finish();
                    }
                }).show();
    }

    private boolean isConnectedToInternet(LoginActivity loginActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) loginActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private boolean validateFields() {
        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _passWord = passWord.getEditText().getText().toString().trim();

        if (_phoneNumber.isEmpty()) {
            phoneNumber.setError("Phone Number can not be Empty!");
            phoneNumber.requestFocus();
            return false;
        } else if (_passWord.isEmpty()) {
            passWord.setError("Phone Number can not be Empty!");
            passWord.requestFocus();
            return false;
        } else {
            phoneNumber.setError(null);
            passWord.setError(null);
            return true;
        }

    }

    public void callForgetPasswordPage(View view) {
        startActivity(new Intent(getApplicationContext(), ForgetPasswordActivity.class));
    }

    public void callCreateAccount(View view){
        startActivity(new Intent(getApplicationContext(), SignupActivity.class));
        finish();
    }
}