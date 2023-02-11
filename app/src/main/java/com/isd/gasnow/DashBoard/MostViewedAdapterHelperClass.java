package com.isd.gasnow.DashBoard;

public class MostViewedAdapterHelperClass {
    int image;
    String title, description;

    public MostViewedAdapterHelperClass(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
