package com.isd.gasnow.PasswordReset;

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
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.isd.gasnow.IntroductoryPages.WelcomeActivity;
import com.isd.gasnow.R;
import com.isd.gasnow.SignUp.VerifyOTPActivity;

public class ForgetPasswordActivity extends AppCompatActivity {

    private ImageView forgetPassIcon;
    private TextView title, description;
    private TextInputLayout phoneNumber;
    private CountryCodePicker countryCodePicker;
    private Button nextBtn;
    private Animation animation;
    private RelativeLayout progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        forgetPassIcon = findViewById(R.id.forgetPassIcon);
        title = findViewById(R.id.forgetPassText);
        description = findViewById(R.id.forgetPassPhoneText);
        phoneNumber = findViewById(R.id.forgetPassPhoneNum);
        countryCodePicker = findViewById(R.id.forgetPassCodePicker);
        nextBtn =findViewById(R.id.forgetPassNextBtn);
        progressBar = findViewById(R.id.forgetPassProgress);

//        animation = AnimationUtils.loadAnimation(this, R.anim.slide_animation);
    }

    public void verifyPhoneForOTP(View view) {

        if(!isConnectedToInternet(this)){
            showCustomDialog();
        }

        if(!validatePhoneNumber()) {
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        String _getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim();
        //For trimming Bd Numbers 017... to 17
        if(_getUserEnteredPhoneNumber.charAt(0) == '0')
        {
            _getUserEnteredPhoneNumber = _getUserEnteredPhoneNumber.substring(1);
        }
        final String _phoneNumber = "+"+countryCodePicker.getSelectedCountryCode()+_getUserEnteredPhoneNumber;

        Query checkUser = FirebaseDatabase.getInstance("https://gasnow-626582aar-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("Users")
                .orderByChild("phoneNumber").
                equalTo(_phoneNumber);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);

                    Intent intent = new Intent(getApplicationContext(), VerifyOTPActivity.class);
                    intent.putExtra("phoneNumber", _phoneNumber);
                    intent.putExtra("whatToDo", "updatePassword");
                    startActivity(intent);
                    finish();

                    progressBar.setVisibility(View.GONE);

                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ForgetPasswordActivity.this, "Phone Number Doesn't Exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ForgetPasswordActivity.this, "Fetch Error", Toast.LENGTH_SHORT).show();
                phoneNumber.requestFocus();
            }
        });



    }

    private void showCustomDialog() {
        progressBar.setVisibility(View.GONE);
        AlertDialog.Builder builder = new AlertDialog.Builder(ForgetPasswordActivity.this);
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

    private boolean isConnectedToInternet(ForgetPasswordActivity forgetPasswordActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) forgetPasswordActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
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