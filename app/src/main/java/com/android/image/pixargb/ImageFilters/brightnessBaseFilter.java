package com.android.image.pixargb.ImageFilters;

import android.graphics.Bitmap;

import com.android.image.pixargb.Interfaces.BaseFilter;
import com.android.image.pixargb.NativeProcessor.Pixelprocessor;

public class brightnessBaseFilter implements BaseFilter {

    private int brightness=0;

    public brightnessBaseFilter(int brightness) {
        this.brightness = brightness;
    }

    @Override
    public Bitmap processFilter(Bitmap bitmap) {

        return Pixelprocessor.doBrightness(brightness,bitmap);
    }
}
