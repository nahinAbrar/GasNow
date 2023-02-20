package com.isd.gasnow.IntroductoryPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.isd.gasnow.R;

public class IntroductoryActivity extends AppCompatActivity {
    ImageView splashImage, logo;
    TextView copyright;
    LottieAnimationView animationView;
    Animation anim, sideAnim, bottomAnim;
    SharedPreferences sharedPreferences;
    private static final int NUM_PAGES = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_introductory);

        splashImage = findViewById(R.id.introBg);
        logo = findViewById(R.id.introLogo);
        animationView = findViewById(R.id.splashAnimationView);
        copyright = findViewById(R.id.intro_copyright);

        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        logo.setAnimation(sideAnim);
        splashImage.setAnimation(sideAnim);
        animationView.setAnimation(sideAnim);
        copyright.setAnimation(bottomAnim);


        splashImage.animate().translationY(-2500).setDuration(1500).setStartDelay(4500);
        logo.animate().translationY(2000).setDuration(1500).setStartDelay(4500);
        animationView.animate().translationY(2000).setDuration(1500).setStartDelay(4500);
        copyright.animate().translationY(-2500).setDuration(1500).setStartDelay(4500);


        int SPLASH_TIME_OUT = 5650;
        new Handler().postDelayed(() -> {
            sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
            boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);

            if(isFirstTime){
                //goto boarding\
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.apply();
            }else{
                Intent intent = new Intent(getApplicationContext(),WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

        ViewPager viewPager = findViewById(R.id.pager);
        ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        anim = AnimationUtils.loadAnimation(this, R.anim.o_b_anim);
        viewPager.startAnimation(anim);
    }

    @SuppressWarnings("deprecation")
    private static class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{

        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new OnBoardingFragment1();
                case 1:
                    return new OnBoardingFragment2();
                case 2:
                    return new OnBoardingFragment3();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}