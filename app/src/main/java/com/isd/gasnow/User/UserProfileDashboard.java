package com.isd.gasnow.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.isd.gasnow.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class UserProfileDashboard extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_dashboard);

        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.bottom_nav_profile,true);

        String _fullName = getIntent().getStringExtra("fullName");
        String _userName = getIntent().getStringExtra("userName");
        String _phoneNumber = getIntent().getStringExtra("phoneNumber");
        String _email = getIntent().getStringExtra("email");
        String _area = getIntent().getStringExtra("area");
        String _address = getIntent().getStringExtra("address");

        Bundle bundle = new Bundle();
        bundle.putString("fullName",_fullName);
        bundle.putString("userName",_userName);
        bundle.putString("phoneNumber",_phoneNumber);
        bundle.putString("email",_email);
        bundle.putString("area",_area);
        bundle.putString("address",_address);
        UserProfileFragment userProfileFragment = new UserProfileFragment();
        userProfileFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_layout,userProfileFragment).commit();
        bottomMenu();
    }

    private void bottomMenu() {

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i){
                    case R.id.bottom_nav_profile:
                        String _fullName = getIntent().getStringExtra("fullName");
                        String _userName = getIntent().getStringExtra("userName");
                        String _phoneNumber = getIntent().getStringExtra("phoneNumber");
                        String _email = getIntent().getStringExtra("email");
                        String _area = getIntent().getStringExtra("area");
                        String _address = getIntent().getStringExtra("address");

                        Bundle bundle = new Bundle();
                        bundle.putString("fullName",_fullName);
                        bundle.putString("userName",_userName);
                        bundle.putString("phoneNumber",_phoneNumber);
                        bundle.putString("email",_email);
                        bundle.putString("area",_area);
                        bundle.putString("address",_address);
                        fragment = new UserProfileFragment();
                        fragment.setArguments(bundle);
                        break;
                    case R.id.bottom_nav_manage:
                        fragment = new UserManageFragment();
                        break;
                    case R.id.bottom_nav_history:
                        fragment = new UserHistoryFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_layout,fragment).commit();
            }
        });
    }
}