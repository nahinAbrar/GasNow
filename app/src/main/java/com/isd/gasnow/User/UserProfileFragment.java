package com.isd.gasnow.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isd.gasnow.R;


public class UserProfileFragment extends Fragment {

    TextView nameLabel, userNameLabel;
    TextInputLayout fullName, email, area, address;
    Button updateBtn;

    String _fullName, _userName,_phoneNumber, _email, _area, _address;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        databaseReference = FirebaseDatabase
                .getInstance("https://gasnow-626582aar-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("Users");

        Bundle bundle = this.getArguments();
        _fullName = bundle.getString("fullName");
        _userName = bundle.getString("userName");
        _phoneNumber = bundle.getString("phoneNumber");
        _email = bundle.getString("email");
        _area = bundle.getString("area");
        _address = bundle.getString("address");

        nameLabel = view.findViewById(R.id.fullNameLabel);
        userNameLabel = view.findViewById(R.id.userNameLabel);
        fullName = view.findViewById(R.id.dashboardFullName);
        email = view.findViewById(R.id.dashboardEmail);
        area = view.findViewById(R.id.dashboardArea);
        address = view.findViewById(R.id.dashboardAddress);
        updateBtn = view.findViewById(R.id.dashboardUpdateBtn);


        nameLabel.setText(_fullName);
        userNameLabel.setText(_userName);
        fullName.getEditText().setText(_fullName);
        email.getEditText().setText(_email);
        area.getEditText().setText(_area);
        address.getEditText().setText(_address);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateData();

            }
        });


        return view;
    }

    private void updateData() {
        if (isFullNameChanged() || isEmailChanged() || isAreaChanged() || isAddressChanged()) {
            Toast.makeText(getActivity(), "Updated!\nLogin Again to See Effect", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "Nothing Changed!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isAddressChanged() {
        if (!(_address.equals(address.getEditText().getText().toString()))){
            databaseReference.child(_phoneNumber).child("address").setValue(address.getEditText().getText().toString());
            return true;
        }else{
            return false;
        }
    }

    private boolean isAreaChanged() {
        if (!(_area.equals(area.getEditText().getText().toString()))){
            databaseReference.child(_phoneNumber).child("area").setValue(area.getEditText().getText().toString());
            return true;
        }else{
            return false;
        }
    }

    private boolean isEmailChanged() {
        if (!(_email.equals(email.getEditText().getText().toString()))){
            databaseReference.child(_phoneNumber).child("email").setValue(email.getEditText().getText().toString());
            return true;
        }else{
            return false;
        }
    }

    private boolean isFullNameChanged() {
        if (!(_fullName.equals(fullName.getEditText().getText().toString()))){
            databaseReference.child(_phoneNumber).child("fullName").setValue(fullName.getEditText().getText().toString());
            _fullName = fullName.getEditText().getText().toString();
            fullName.getEditText().setText(_fullName);
            nameLabel.setText(_fullName);
            return true;
        }else{
            return false;
        }
    }


}