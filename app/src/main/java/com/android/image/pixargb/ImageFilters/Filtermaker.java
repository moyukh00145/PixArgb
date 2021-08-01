package com.android.image.pixargb.ImageFilters;

import android.content.Context;

import com.android.image.pixargb.Interfaces.BaseFilter;
import com.android.image.pixargb.Measurement.Point;
import com.android.image.pixargb.R;

import java.util.ArrayList;

public class Filtermaker {



    static ArrayList<Filters>filters;

    public Filtermaker() {

    }

    public static ArrayList<Filters> getFilters(Context context){

        filters=new ArrayList<>();

        filters.add(getAweStruckVibeFilter(context));
        filters.add(getClarendon(context));
        filters.add(getOldManFilter(context));
        filters.add(getMarsFilter(context));
        filters.add(getRiseFilter(context));
        filters.add(getAprilFilter(context));
        filters.add(getAmazonFilter(context));
        filters.add(getStarLitFilter(context));
        filters.add(getNightWhisperFilter(context));
        filters.add(getLimeStutterFilter(context));
        filters.add(getHaanFilter(context));
        filters.add(getBlueMessFilter(context));
        filters.add(getAdeleFilter(context));
        filters.add(getCruzFilter(context));
        filters.add(getMetropolis(context));
        filters.add(getAudreyFilter(context));

        return filters;
    }

    public static Filters getStarLitFilter(Context context){
        Point[] rgbKnots;
        rgbKnots = new Point[8];
        rgbKnots[0] = new Point(0, 0);
        rgbKnots[1] = new Point(34, 6);
        rgbKnots[2] = new Point(69, 23);
        rgbKnots[3] = new Point(100, 58);
        rgbKnots[4] = new Point(150, 154);
        rgbKnots[5] = new Point(176, 196);
        rgbKnots[6] = new Point(207, 233);
        rgbKnots[7] = new Point(255, 255);
        Filters filter = new Filters();
        filter.setFilterTag(context.getString(R.string.starlit));
        filter.addBaseFilter(new rgbBaseFilter(rgbKnots,null,null,null));
        return filter;

    }

    public static Filters getBlueMessFilter(Context context) {
        Point[] redKnots;
        redKnots = new Point[8];
        redKnots[0] = new Point(0, 0);
        redKnots[1] = new Point(86, 34);
        redKnots[2] = new Point(117, 41);
        redKnots[3] = new Point(146, 80);
        redKnots[4] = new Point(170, 151);
        redKnots[5] = new Point(200, 214);
        redKnots[6] = new Point(225, 242);
        redKnots[7] = new Point(255, 255);
        Filters filter = new Filters();
        filter.setFilterTag(context.getString(R.string.bluemess));
        filter.addBaseFilter(new rgbBaseFilter(null, redKnots, null, null));
        filter.addBaseFilter(new brightnessBaseFilter(30));
        filter.addBaseFilter(new contrastBaseFilter(1f));
        return filter;
    }

    public static Filters getAweStruckVibeFilter(Context context) {
        Point[] rgbKnots;
        Point[] redKnots;
        Point[] greenKnots;
        Point[] blueKnots;

        rgbKnots = new Point[5];
        rgbKnots[0] = new Point(0, 0);
        rgbKnots[1] = new Point(80, 43);
        rgbKnots[2] = new Point(149, 102);
        rgbKnots[3] = new Point(201, 173);
        rgbKnots[4] = new Point(255, 255);

        redKnots = new Point[5];
        redKnots[0] = new Point(0, 0);
        redKnots[1] = new Point(125, 147);
        redKnots[2] = new Point(177, 199);
        redKnots[3] = new Point(213, 228);
        redKnots[4] = new Point(255, 255);


        greenKnots = new Point[6];
        greenKnots[0] = new Point(0, 0);
        greenKnots[1] = new Point(57, 76);
        greenKnots[2] = new Point(103, 130);
        greenKnots[3] = new Point(167, 192);
        greenKnots[4] = new Point(211, 229);
        greenKnots[5] = new Point(255, 255);


        blueKnots = new Point[7];
        blueKnots[0] = new Point(0, 0);
        blueKnots[1] = new Point(38, 62);
        blueKnots[2] = new Point(75, 112);
        blueKnots[3] = new Point(116, 158);
        blueKnots[4] = new Point(171, 204);
        blueKnots[5] = new Point(212, 233);
        blueKnots[6] = new Point(255, 255);

        Filters filter = new Filters();
        filter.setFilterTag(context.getString(R.string.struck));
        filter.addBaseFilter(new rgbBaseFilter(rgbKnots, redKnots, greenKnots, blueKnots));
        return filter;
    }

    public static Filters getLimeStutterFilter(Context context) {
        Point[] blueKnots;
        blueKnots = new Point[3];
        blueKnots[0] = new Point(0, 0);
        blueKnots[1] = new Point(165, 114);
        blueKnots[2] = new Point(255, 255);
        Filters filter = new Filters();
        filter.setFilterTag(context.getString(R.string.lime));
        filter.addBaseFilter(new rgbBaseFilter(null, null, null, blueKnots));
        return filter;
    }

    public static Filters getNightWhisperFilter(Context context) {
        Point[] rgbKnots;
        Point[] redKnots;
        Point[] greenKnots;
        Point[] blueKnots;

        rgbKnots = new Point[3];
        rgbKnots[0] = new Point(0, 0);
        rgbKnots[1] = new Point(174, 109);
        rgbKnots[2] = new Point(255, 255);

        redKnots = new Point[4];
        redKnots[0] = new Point(0, 0);
        redKnots[1] = new Point(70, 114);
        redKnots[2] = new Point(157, 145);
        redKnots[3] = new Point(255, 255);

        greenKnots = new Point[3];
        greenKnots[0] = new Point(0, 0);
        greenKnots[1] = new Point(109, 138);
        greenKnots[2] = new Point(255, 255);

        blueKnots = new Point[3];
        blueKnots[0] = new Point(0, 0);
        blueKnots[1] = new Point(113, 152);
        blueKnots[2] = new Point(255, 255);

        Filters filter = new Filters();
        filter.setFilterTag(context.getString(R.string.whisper));
        filter.addBaseFilter(new contrastBaseFilter(1.5f));
        filter.addBaseFilter(new rgbBaseFilter(rgbKnots, redKnots, greenKnots, blueKnots));
        return filter;
    }

    public static Filters getAmazonFilter(Context context) {
        Point[] blueKnots;
        blueKnots = new Point[6];
        blueKnots[0] = new Point(0, 0);
        blueKnots[1] = new Point(11, 40);
        blueKnots[2] = new Point(36, 99);
        blueKnots[3] = new Point(86, 151);
        blueKnots[4] = new Point(167, 209);
        blueKnots[5] = new Point(255, 255);
        Filters filter = new Filters(context.getString(R.string.amazon));
        filter.addBaseFilter(new contrastBaseFilter(1.2f));
        filter.addBaseFilter(new rgbBaseFilter(null, null, null, blueKnots));
        return filter;
    }

    public static Filters getAdeleFilter(Context context) {
        Filters filter = new Filters(context.getString(R.string.adele));
        filter.addBaseFilter(new saturationBaseFilter(-100f));
        return filter;
    }

    public static Filters getCruzFilter(Context context) {
        Filters filter = new Filters(context.getString(R.string.cruz));
        filter.addBaseFilter(new saturationBaseFilter(-100f));
        filter.addBaseFilter(new contrastBaseFilter(1.3f));
        filter.addBaseFilter(new brightnessBaseFilter(20));
        return filter;
    }

    public static Filters getMetropolis(Context context) {
        Filters filter = new Filters(context.getString(R.string.metropolis));
        filter.addBaseFilter(new saturationBaseFilter(-1f));
        filter.addBaseFilter(new contrastBaseFilter(1.7f));
        filter.addBaseFilter(new brightnessBaseFilter(70));
        return filter;
    }

    public static Filters getAudreyFilter(Context context) {
        Filters filter = new Filters(context.getString(R.string.audrey));

        Point[] redKnots;
        redKnots = new Point[3];
        redKnots[0] = new Point(0, 0);
        redKnots[1] = new Point(124, 138);
        redKnots[2] = new Point(255, 255);

        filter.addBaseFilter(new saturationBaseFilter(-100f));
        filter.addBaseFilter(new contrastBaseFilter(1.3f));
        filter.addBaseFilter(new brightnessBaseFilter(20));
        filter.addBaseFilter(new rgbBaseFilter(null, redKnots, null, null));
        return filter;
    }

    public static Filters getRiseFilter(Context context) {
        Point[] blueKnots;
        Point[] redKnots;

        blueKnots = new Point[4];
        blueKnots[0] = new Point(0, 0);
        blueKnots[1] = new Point(39, 70);
        blueKnots[2] = new Point(150, 200);
        blueKnots[3] = new Point(255, 255);

        redKnots = new Point[4];
        redKnots[0] = new Point(0, 0);
        redKnots[1] = new Point(45, 64);
        redKnots[2] = new Point(170, 190);
        redKnots[3] = new Point(255, 255);

        Filters filter = new Filters(context.getString(R.string.rise));
        filter.addBaseFilter(new contrastBaseFilter(1.9f));
        filter.addBaseFilter(new brightnessBaseFilter(60));
//        filter.addBaseFilter(new vintageBaseFilter(context, 200));
        filter.addBaseFilter(new rgbBaseFilter(null, redKnots, null, blueKnots));
        return filter;
    }

    public static Filters getMarsFilter(Context context) {
        Filters filter = new Filters(context.getString(R.string.mars));
        filter.addBaseFilter(new contrastBaseFilter(1.5f));
        filter.addBaseFilter(new brightnessBaseFilter(10));
        return filter;
    }

    public static Filters getAprilFilter(Context context) {
        Point[] blueKnots;
        Point[] redKnots;

        blueKnots = new Point[4];
        blueKnots[0] = new Point(0, 0);
        blueKnots[1] = new Point(39, 70);
        blueKnots[2] = new Point(150, 200);
        blueKnots[3] = new Point(255, 255);

        redKnots = new Point[4];
        redKnots[0] = new Point(0, 0);
        redKnots[1] = new Point(45, 64);
        redKnots[2] = new Point(170, 190);
        redKnots[3] = new Point(255, 255);

        Filters filter = new Filters(context.getString(R.string.april));
        filter.addBaseFilter(new contrastBaseFilter(1.5f));
        filter.addBaseFilter(new brightnessBaseFilter(5));
//        filter.addBaseFilter(new vintageBaseFilter(context, 150));
        filter.addBaseFilter(new rgbBaseFilter(null, redKnots, null, blueKnots));
        return filter;
    }

    public static Filters getHaanFilter(Context context) {
        Point[] greenKnots;
        greenKnots = new Point[3];
        greenKnots[0] = new Point(0, 0);
        greenKnots[1] = new Point(113, 142);
        greenKnots[2] = new Point(255, 255);

        Filters filter = new Filters(context.getString(R.string.haan));
        filter.addBaseFilter(new contrastBaseFilter(1.3f));
        filter.addBaseFilter(new brightnessBaseFilter(60));
//        filter.addBaseFilter(new vintageBaseFilter(context, 200));
        filter.addBaseFilter(new rgbBaseFilter(null, null, greenKnots, null));
        return filter;
    }

    public static Filters getOldManFilter(Context context) {
        Filters filter = new Filters(context.getString(R.string.oldman));
        filter.addBaseFilter(new brightnessBaseFilter(30));
        filter.addBaseFilter(new saturationBaseFilter(0.8f));
        filter.addBaseFilter(new contrastBaseFilter(1.3f));
//        filter.addBaseFilter(new vintageBaseFilter(context, 100));
        filter.addBaseFilter(new colorOverlayBaseFilter(100, .2f, .2f, .1f));
        return filter;
    }

    public static Filters getClarendon(Context context) {
        Point[] redKnots;
        Point[] greenKnots;
        Point[] blueKnots;

        redKnots = new Point[4];
        redKnots[0] = new Point(0, 0);
        redKnots[1] = new Point(56, 68);
        redKnots[2] = new Point(196, 206);
        redKnots[3] = new Point(255, 255);


        greenKnots = new Point[4];
        greenKnots[0] = new Point(0, 0);
        greenKnots[1] = new Point(46, 77);
        greenKnots[2] = new Point(160, 200);
        greenKnots[3] = new Point(255, 255);


        blueKnots = new Point[4];
        blueKnots[0] = new Point(0, 0);
        blueKnots[1] = new Point(33, 86);
        blueKnots[2] = new Point(126, 220);
        blueKnots[3] = new Point(255, 255);

        Filters filter = new Filters(context.getString(R.string.clarendon));
        filter.addBaseFilter(new contrastBaseFilter(1.5f));
        filter.addBaseFilter(new brightnessBaseFilter(-10));
        filter.addBaseFilter(new rgbBaseFilter(null, redKnots, greenKnots, blueKnots));
        return filter;
    }



}
