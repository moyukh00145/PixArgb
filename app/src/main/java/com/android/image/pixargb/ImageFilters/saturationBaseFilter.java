package com.android.image.pixargb.ImageFilters;

import android.graphics.Bitmap;

import com.android.image.pixargb.Interfaces.BaseFilter;
import com.android.image.pixargb.NativeProcessor.Pixelprocessor;

public class saturationBaseFilter implements BaseFilter {

    private float saturation=0;

    public saturationBaseFilter(float saturation) {
        this.saturation = saturation;
    }

    @Override
    public Bitmap processFilter(Bitmap bitmap) {

        return Pixelprocessor.doSaturation(saturation,bitmap);
    }
}
