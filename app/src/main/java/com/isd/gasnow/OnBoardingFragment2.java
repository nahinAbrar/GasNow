package com.isd.gasnow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OnBoardingFragment2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_on_boarding2,container,false);

        TextView skipTextView = root.findViewById(R.id.boarding2SkiptText);

        skipTextView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WelcomeActivity.class);
            startActivity(intent);
        });

        return root;
    }
}
