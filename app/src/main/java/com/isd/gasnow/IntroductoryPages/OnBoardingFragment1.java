package com.isd.gasnow.IntroductoryPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.isd.gasnow.R;

public class OnBoardingFragment1 extends Fragment {
    TextView skipTextView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_on_boarding1,container,false);


        skipTextView = root.findViewById(R.id.boarding1SkipText);

        skipTextView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WelcomeActivity.class);
            startActivity(intent);
        });



        return root;
    }
}
