package com.isd.gasnow.PasswordReset;

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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isd.gasnow.Login.LoginActivity;
import com.isd.gasnow.R;

public class SetNewPasswordActivity extends AppCompatActivity {

    TextInputLayout password, confirmPassword ;
    Button setPassBtn;
    RelativeLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);

        password = findViewById(R.id.setNewPass);
        confirmPassword = findViewById(R.id.setConfirmNewPass);
        progressBar = findViewById(R.id.setPassProgress);
        setPassBtn = findViewById(R.id.setPassBtn);
    }

    public void setNewPassword(View view){

        if(!isConnectedToInternet(this))
        {
            showCustomDialog();
            return;
        }
        if(!validateBothPassword())
        {
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        String _newPassword = password.getEditText().getText().toString().trim();
        String phoneNumber = getIntent().getStringExtra("phoneNumber");

        DatabaseReference reference = FirebaseDatabase.getInstance("https://gasnow-626582aar-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users");
        reference.child(phoneNumber).child("passWord").setValue(_newPassword);

        Toast.makeText(this, "Password Updated!", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        startActivity(new Intent(getApplicationContext(), ForgetPassSuccessMessageActivity.class));
        finish();

    }

    private boolean validateBothPassword() {
        String _passWord = password.getEditText().getText().toString().trim();
        String _confirmPassWord = confirmPassword.getEditText().getText().toString().trim();

        if (_passWord.isEmpty()) {
            password.setError("Phone Number can not be Empty!");
            password.requestFocus();
            return false;
        } else if (_confirmPassWord.isEmpty()) {
            confirmPassword.setError("Phone Number can not be Empty!");
            confirmPassword.requestFocus();
            return false;
        }  else if (!(_passWord.equals(_confirmPassWord))) {
            password.setError("Password Doesn't Match");
            password.requestFocus();
            confirmPassword.setError("Password Doesn't Match");
            confirmPassword.requestFocus();
            return false;
        }else {
            password.setError(null);
            confirmPassword.setError(null);
            return true;
        }

    }

    private void showCustomDialog() {
        progressBar.setVisibility(View.GONE);
        AlertDialog.Builder builder = new AlertDialog.Builder(SetNewPasswordActivity.this);
        builder.setMessage("No Internet Connection! \nPlease Have an Active Internet Connection").setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                }).show();
    }

    private boolean isConnectedToInternet(SetNewPasswordActivity setNewPasswordActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) setNewPasswordActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
}