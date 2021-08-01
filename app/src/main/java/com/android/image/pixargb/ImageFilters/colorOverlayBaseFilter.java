package com.android.image.pixargb.ImageFilters;

import android.graphics.Bitmap;

import com.android.image.pixargb.Interfaces.BaseFilter;
import com.android.image.pixargb.NativeProcessor.Pixelprocessor;

public class colorOverlayBaseFilter implements BaseFilter {

    private  int colorOverlayDepth;

    private  float colorOverlayRed;
    private  float colorOverlayGreen;
    private  float colorOverlayBlue;


    public colorOverlayBaseFilter(int colorOverlayDepth, float colorOverlayRed, float colorOverlayGreen, float colorOverlayBlue) {
        this.colorOverlayDepth = colorOverlayDepth;
        this.colorOverlayRed = colorOverlayRed;
        this.colorOverlayGreen = colorOverlayGreen;
        this.colorOverlayBlue = colorOverlayBlue;
    }

    @Override
    public Bitmap processFilter(Bitmap bitmap) {
        return Pixelprocessor.doColorOverlay(colorOverlayDepth,colorOverlayRed,colorOverlayGreen,colorOverlayBlue,bitmap);
    }
}
