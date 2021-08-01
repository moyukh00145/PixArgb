package com.android.image.pixargb.ImageFilters;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.image.pixargb.Interfaces.BaseFilter;

public class vintageBaseFilter implements BaseFilter {

    private  int alpha=0;


    public vintageBaseFilter(Context context,int alpha) {
        this.alpha = alpha;
    }

    @Override
    public Bitmap processFilter(Bitmap bitmap) {
        return null;
    }
}
