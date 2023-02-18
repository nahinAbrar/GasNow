package com.isd.gasnow.SignUp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.isd.gasnow.Database.UserHelperClass;
import com.isd.gasnow.IntroductoryPages.WelcomeActivity;
import com.isd.gasnow.R;
import com.isd.gasnow.PasswordReset.SetNewPasswordActivity;

import java.util.concurrent.TimeUnit;

public class VerifyOTPActivity extends AppCompatActivity {
    PinView pinView;
    String codeBySystem;

    String fullName, userName, email, passWord, area, address, phoneNumber;
    String whatToDo;
    ScrollView scrollView;
    SignupActivity signupActivity = new SignupActivity();

    TextView verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otpactivity);
        pinView = findViewById(R.id.pinView);
        scrollView = findViewById(R.id.verifyLayout);
        verify = findViewById(R.id.verifyOTPText);

        fullName = getIntent().getStringExtra("fullName");
        userName = getIntent().getStringExtra("userName");
        email = getIntent().getStringExtra("email");
        passWord = getIntent().getStringExtra("password");
        area = getIntent().getStringExtra("area");
        address = getIntent().getStringExtra("address");
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        whatToDo = getIntent().getStringExtra("whatToDo");

        verify.setText("Enter The Code Sent To "+ phoneNumber);
        sendVerificationCodeToUser(phoneNumber);

    }


    private void sendVerificationCodeToUser(String phoneNumber) {


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
            String code = credential.getSmsCode();
            if (code != null) {
                pinView.setText(code);
                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            Toast.makeText(VerifyOTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e instanceof FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            }

            // Show a message and update the UI
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(TAG, "onCodeSent:" + verificationId);
            codeBySystem = verificationId;
            // Save verification ID and resending token so we can use them later
            //mVerificationId = verificationId;
            //mResendToken = token;
        }

    };

    public void callNextScreenFromOTP(View view) {

        String code = pinView.getText().toString();
        if (!code.isEmpty()) {
            verifyCode(code);
        }
//        Toast.makeText(VerifyOTPActivity.this,"Verification Completed!", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(getApplicationContext(), SetNewPasswordActivity.class));
//        finish();

    }
    private void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");

                            if(whatToDo != null && whatToDo.equals("updatePassword"))
                            {
                                setNewPassword();
                            }else {
                                Toast.makeText(VerifyOTPActivity.this, "Verification Completed!", Toast.LENGTH_SHORT).show();
                                storeUserData();
                                startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                            }


                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(VerifyOTPActivity.this, "Verification Not Completed, Try Again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void setNewPassword() {
        Intent intent = new Intent(getApplicationContext(), SetNewPasswordActivity.class);
        intent.putExtra("phoneNumber",phoneNumber);
        startActivity(intent);
        finish();
    }

    private void storeUserData() {
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance("https://gasnow-626582aar-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference reference = rootNode.getReference("Users");

        UserHelperClass addNewUser = new UserHelperClass(fullName,userName,email,passWord,area,address,phoneNumber);

        //userName node er under e each user er data add hobe
        reference.child(phoneNumber).setValue(addNewUser);



    }


}