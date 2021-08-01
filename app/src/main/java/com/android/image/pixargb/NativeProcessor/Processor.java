package com.android.image.pixargb.NativeProcessor;

public class Processor {

    public static native int[] doBrightness(int[] pixels, int value, int width, int height);
    public static native int[] doContrast(int[] pixels, float value, int width, int height);
    public static native int[] doSaturation(int[] pixels, float value, int width, int height);
    public static native int[] applyRGBCurve(int[] pixels, int[] rgb, int width, int height);
    public static native int[] applyChannelCurves(int[]pixels,int [] r,int [] g,int [] b,int  width,int height);
    public static native int[] doColorOverlay(int[] pixels, int depth,float red, float green, float blue, int width, int height);
    public static native int[] redAdd(int [] pixels,int width,int height,int redValue);
    public static native int[] greenAdd(int [] pixels,int width,int height,int greenValue);
    public static native int[] blueAdd(int [] pixels,int width,int height,int blueValue);
    public static native int[] colorAdd(int [] pixels,int width,int height,int redValue,int greenValue,int blueValue);
}
