package com.android.image.pixargb.ImageFilters;

import android.graphics.Bitmap;
import android.util.Log;

import com.android.image.pixargb.Interfaces.BaseFilter;

import java.util.ArrayList;
import java.util.List;

public class Filters {

    private ArrayList<BaseFilter> baseFilters=new ArrayList<>();
    private String FilterTag;

    public Filters() {
    }

    public Filters(String filterTag) {
        this.FilterTag=filterTag;
    }


    public void addBaseFilter(BaseFilter filter){
        baseFilters.add(filter);
    }

    public String getFilterTag() {
        return FilterTag;
    }

    public void setFilterTag(String filterTag) {
        FilterTag = filterTag;
    }

    public Bitmap processBitmap(Bitmap inputimage){
        Bitmap processedImage=inputimage;
        for (BaseFilter baseFilterObj:baseFilters){
            try{
                processedImage=baseFilterObj.processFilter(processedImage);

            }
            catch (OutOfMemoryError e){
                System.gc();
                try{
                    processedImage=baseFilterObj.processFilter(processedImage);

                }
                catch (OutOfMemoryError ignore){

                }
            }
        }

        return processedImage;
    }
}
