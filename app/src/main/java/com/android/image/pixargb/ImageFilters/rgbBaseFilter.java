package com.android.image.pixargb.ImageFilters;

import android.graphics.Bitmap;
import com.android.image.pixargb.Interfaces.BaseFilter;
import com.android.image.pixargb.Measurement.PlotCurve;
import com.android.image.pixargb.Measurement.Point;
import com.android.image.pixargb.NativeProcessor.Pixelprocessor;

public class rgbBaseFilter implements BaseFilter {

    Point[] rgbPoint;
    Point [] rPoint;
    Point [] gPoint;
    Point [] bPoint;

    private int[] rgb;
    private int[] r;
    private int[] g;
    private int[] b;


    public rgbBaseFilter(Point[] rgbPoint, Point[] rPoint, Point[] gPoint, Point[] bPoint) {

        Point [] general=new Point[2];
       general[0]=new Point(0,0);
       general[1]=new Point(255,255);

        if (rgbPoint!=null){
            this.rgbPoint = rgbPoint;
        }
        else{
            this.rgbPoint=general;
        }
        if (rPoint!=null){
            this.rPoint = rPoint;
        }
        else{
            this.rPoint=general;
        }
        if (gPoint!=null){
            this.gPoint = gPoint;
        }
        else{
            this.gPoint=general;
        }
        if (bPoint!=null){
            this.bPoint = bPoint;
        }
        else{
            this.bPoint=general;
        }


    }

    @Override
    public Bitmap processFilter(Bitmap bitmap) {
        rgbPoint=sortPointsOnXAxis(rgbPoint);
        rPoint=sortPointsOnXAxis(rPoint);
        gPoint=sortPointsOnXAxis(gPoint);
        bPoint=sortPointsOnXAxis(bPoint);
        if (rgb==null){
            rgb=PlotCurve.curveGenerator(rgbPoint);
        }
        if (r==null){
            r= PlotCurve.curveGenerator(rPoint);
        }
        if (g==null){
            g= PlotCurve.curveGenerator(gPoint);
        }
        if (b==null){
            b= PlotCurve.curveGenerator(bPoint);
        }

        return Pixelprocessor.rgbCurveApply(rgb,r,g,b,bitmap);
    }

    public Point[] sortPointsOnXAxis(Point[] points) {
        if (points == null) {
            return null;
        }
        for (int s = 1; s < points.length - 1; s++) {
            for (int k = 0; k <= points.length - 2; k++) {
                if (points[k].x > points[k + 1].x) {
                    float temp = 0;
                    temp = points[k].x;
                    points[k].x = points[k + 1].x; //swapping values
                    points[k + 1].x = temp;
                }
            }
        }
        return points;
    }

}
