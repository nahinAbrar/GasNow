package com.isd.gasnow.DashBoard;

import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CategoriesHelperClass {
//    RelativeLayout gradient;
    int image;
    String title;

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public CategoriesHelperClass(int image, String title) {
        this.image = image;
        this.title = title;
    }
}
