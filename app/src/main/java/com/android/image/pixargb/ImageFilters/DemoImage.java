package com.android.image.pixargb.ImageFilters;

import android.graphics.Bitmap;

public class DemoImage {

    public String filterName;
    public Bitmap image;
    public Filters filter;

    public DemoImage() {
        filter=new Filters();
        image=null;
    }
}
