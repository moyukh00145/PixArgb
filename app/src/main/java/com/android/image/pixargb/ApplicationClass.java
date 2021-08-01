package com.android.image.pixargb;

import android.app.Application;
import android.graphics.Color;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static ArrayList<Integer>options;
    public static String []option_name;
    public static ArrayList<Integer>colors;

    @Override
    public void onCreate() {
        super.onCreate();
        options=new ArrayList<>();


        options.add(R.drawable.brightness);
        options.add(R.drawable.contrast);
        options.add(R.drawable.filters);
        options.add(R.drawable.saturation);
        options.add(R.drawable.colour_add);
        options.add(R.drawable.brush);
        options.add(R.drawable.emoji);
        options.add(R.drawable.basic_tune);

        option_name=getResources().getStringArray(R.array.optionname);

        colors=new ArrayList<>();

        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.WHITE);
        colors.add(Color.BLACK);
        colors.add(Color.CYAN);
        colors.add(Color.DKGRAY);
        colors.add(Color.GRAY);





    }
}
