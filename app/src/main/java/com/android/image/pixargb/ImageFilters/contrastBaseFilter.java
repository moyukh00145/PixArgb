package com.android.image.pixargb.ImageFilters;

import android.graphics.Bitmap;

import com.android.image.pixargb.Interfaces.BaseFilter;
import com.android.image.pixargb.NativeProcessor.Pixelprocessor;

public class contrastBaseFilter implements BaseFilter {

    private float contrast=0;

    public contrastBaseFilter(float contrast) {
        this.contrast = contrast;
    }

    @Override
    public Bitmap processFilter(Bitmap bitmap) {
        return Pixelprocessor.doContrast(contrast,bitmap);
    }
}
