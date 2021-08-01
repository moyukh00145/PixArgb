package com.android.image.pixargb.NativeProcessor;

import android.graphics.Bitmap;

public class Pixelprocessor {

    public Pixelprocessor() {
    }


    public  static Bitmap rgbCurveApply(int [] rgb,int []r,int []g,int []b,Bitmap inputImage){
        Bitmap outputImage=inputImage;
        int width=inputImage.getWidth();
        int height=inputImage.getHeight();
        int pixels[]=new int[width*height];
        outputImage.getPixels(pixels,0,width,0,0,width,height);


        if (rgb != null) {
            pixels = Processor.applyRGBCurve(pixels, rgb, width, height);
        }

        if (!(r == null && g == null && b == null)) {
            pixels = Processor.applyChannelCurves(pixels, r, g, b, width, height);
        }

        try {
            outputImage.setPixels(pixels, 0, width, 0, 0, width, height);
        } catch (IllegalStateException ise) {
        }
        return outputImage;

    }

    public static Bitmap doBrightness(int brightness,Bitmap inputImage){

        Bitmap outputImage=inputImage;
        int width=inputImage.getWidth();
        int height=inputImage.getHeight();
        int pixels[]=new int[width*height];
        outputImage.getPixels(pixels,0,width,0,0,width,height);
        pixels=Processor.doBrightness(pixels,brightness,width,height);
        outputImage.setPixels(pixels,0,width,0,0,width,height);
        return outputImage;

    }

    public static Bitmap doSaturation(float saturation,Bitmap inputImage){

        Bitmap outputImage=inputImage;
        int width=inputImage.getWidth();
        int height=inputImage.getHeight();
        int pixels[]=new int[width*height];
        outputImage.getPixels(pixels,0,width,0,0,width,height);
        pixels=Processor.doSaturation(pixels,saturation,width,height);
        outputImage.setPixels(pixels,0,width,0,0,width,height);
        return outputImage;

    }
    public static Bitmap doContrast(float contrast,Bitmap inputImage){

        Bitmap outputImage=inputImage;
        int width=inputImage.getWidth();
        int height=inputImage.getHeight();
        int pixels[]=new int[width*height];
        outputImage.getPixels(pixels,0,width,0,0,width,height);
        pixels=Processor.doContrast(pixels,contrast,width,height);
        outputImage.setPixels(pixels,0,width,0,0,width,height);
        return outputImage;

    }
    public static Bitmap doColorOverlay(int depth, float red, float green, float blue, Bitmap inputImage){

        Bitmap outputImage=inputImage;
        int width=inputImage.getWidth();
        int height=inputImage.getHeight();
        int pixels[]=new int[width*height];
        outputImage.getPixels(pixels,0,width,0,0,width,height);
        pixels=Processor.doColorOverlay(pixels,depth,red,green,blue,width,height);
        outputImage.setPixels(pixels,0,width,0,0,width,height);
        return outputImage;

    }

    public static Bitmap redColorAdd(int redValue,Bitmap inputImage){
        Bitmap outputImage=inputImage;
        int width=inputImage.getWidth();
        int height=inputImage.getHeight();
        int pixels[]=new int[width*height];
        outputImage.getPixels(pixels,0,width,0,0,width,height);
        pixels=Processor.redAdd(pixels,width,height,redValue);
        outputImage.setPixels(pixels,0,width,0,0,width,height);
        return outputImage;


    }

    public static Bitmap greenColorAdd(int greenValue,Bitmap inputImage){
        Bitmap outputImage=inputImage;
        int width=inputImage.getWidth();
        int height=inputImage.getHeight();
        int pixels[]=new int[width*height];
        outputImage.getPixels(pixels,0,width,0,0,width,height);
        pixels=Processor.greenAdd(pixels,width,height,greenValue);
        outputImage.setPixels(pixels,0,width,0,0,width,height);
        return outputImage;


    }

    public static Bitmap blueColorAdd(int blueValue,Bitmap inputImage){
        Bitmap outputImage=inputImage;
        int width=inputImage.getWidth();
        int height=inputImage.getHeight();
        int pixels[]=new int[width*height];
        outputImage.getPixels(pixels,0,width,0,0,width,height);
        pixels=Processor.blueAdd(pixels,width,height,blueValue);
        outputImage.setPixels(pixels,0,width,0,0,width,height);
        return outputImage;


    }


    public static Bitmap ColorAdd(int redValue,int greenValue,int blueValue,Bitmap inputImage){
        Bitmap outputImage=inputImage;
        int width=inputImage.getWidth();
        int height=inputImage.getHeight();
        int pixels[]=new int[width*height];
        outputImage.getPixels(pixels,0,width,0,0,width,height);
        pixels=Processor.colorAdd(pixels,width,height,redValue,greenValue,blueValue);
        outputImage.setPixels(pixels,0,width,0,0,width,height);
        return outputImage;


    }


}
