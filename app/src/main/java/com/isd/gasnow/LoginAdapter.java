package com.isd.gasnow;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

@SuppressWarnings("deprecation")
public class LoginAdapter extends FragmentPagerAdapter {
    int totalTabs;
    public LoginAdapter(FragmentManager fm, Context context, int totalTabs)
    {
        super(fm);
        this.totalTabs = totalTabs;
    }
    @NonNull
    public Fragment getItem(int position)
    {
        switch (position){
            case 0:
                return new LoginTabFragment();
            case 1:
                return new SignupTabFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}


