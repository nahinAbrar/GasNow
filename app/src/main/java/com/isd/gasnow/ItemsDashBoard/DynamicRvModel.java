package com.isd.gasnow.ItemsDashBoard;

public class DynamicRvModel {
    String name;
    private int image;
    int pos;

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getPos() {
        return pos;
    }

    public DynamicRvModel(String name, int image, int pos) {
        this.name = name;
        this.image = image;
        this.pos = pos;
    }
}
