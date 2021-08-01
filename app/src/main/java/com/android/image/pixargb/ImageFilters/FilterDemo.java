package com.android.image.pixargb.ImageFilters;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.image.pixargb.R;

import java.util.ArrayList;
import java.util.List;

public class FilterDemo {

    private static List<DemoImage> filterImage = new ArrayList<DemoImage>(10);
    private static List<DemoImage> processedImage = new ArrayList<DemoImage>(10);

    private FilterDemo() {
    }

    public static void addDemoImage(DemoImage demoImage) {
        filterImage.add(demoImage);
    }

    public static List<DemoImage> processDemoImage(Context context) {
        for (DemoImage thumb : filterImage) {

            float size = context.getResources().getDimension(R.dimen.thumbnail_size);
            thumb.image = Bitmap.createScaledBitmap(thumb.image, (int) size, (int) size, false);
            thumb.image = thumb.filter.processBitmap(thumb.image);

            processedImage.add(thumb);
        }
        return processedImage;
    }

    public static void clearDemoList() {
        filterImage = new ArrayList<>();
        processedImage = new ArrayList<>();
    }
}
