package com.android.image.pixargb.Fragments;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.image.pixargb.ImageFilters.DemoImage;
import com.android.image.pixargb.ImageFilters.FilterDemo;
import com.android.image.pixargb.ImageFilters.Filtermaker;
import com.android.image.pixargb.ImageFilters.Filters;
import com.android.image.pixargb.Interfaces.FilterSelected;
import com.android.image.pixargb.R;
import com.android.image.pixargb.adapters.FilterAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.android.image.pixargb.MainActivity.load;
import static com.android.image.pixargb.MainActivity.originBitmap;

public class Filterfrag extends Fragment {


    private static final String TAG ="Failed" ;
    FilterAdapter filterAdapter;
    ArrayList<String >filtername;
    RecyclerView recyclerView;
    ArrayList<Filters>filterList;
    List<DemoImage> filterImages;
    Runnable runnable;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.w("Frag","oncreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_filterfrag, container, false);


        filterImages=new ArrayList<>();
        Log.w("Frag","oncreated");

        if (load){
            makeDemoImages(originBitmap);
        }
        else {
            makeDemoImages(null);
        }





        recyclerView=v.findViewById(R.id.filter_recycle_view);
        filtername=new ArrayList<>();
        filterAdapter=new FilterAdapter(getContext(),filterImages);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(filterAdapter);




        // Inflate the layout for this fragment
        return v;

    }


    public void makeDemoImages(Bitmap bitmap){

        Log.w("Frag","demoImage");

        Bitmap initialBitmap=null;

        if (bitmap==null){
            initialBitmap=makeBitmap(getContext(),100,100);
        }
        else{
            initialBitmap=bitmap.copy(Bitmap.Config.ARGB_8888,true);
        }

        final Bitmap processbitmap=initialBitmap;


        runnable=new Runnable() {
            @Override
            public void run() {



                filterImages.clear();
                FilterDemo.clearDemoList();
                filterList= Filtermaker.getFilters(getContext());
                Log.w("filter","called ="+filterList.size() );

                DemoImage normalImage=new DemoImage();
                normalImage.filterName="Normal";
                normalImage.image=processbitmap;
                FilterDemo.addDemoImage(normalImage);

                for (Filters filters:filterList){

                    DemoImage demoImage=new DemoImage();
                    demoImage.filter=filters;
                    demoImage.filterName=filters.getFilterTag();
                    demoImage.image=processbitmap;
                    FilterDemo.addDemoImage(demoImage);

                }

                filterImages.addAll(FilterDemo.processDemoImage(getContext()));

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        filterAdapter.notifyDataSetChanged();
                    }
                });



            }
        };
        new Thread(runnable).start();





    }

    public Bitmap makeBitmap(Context context,int width,int height){

        Bitmap copyBitmap=null;


        AssetManager assetManager = context.getAssets();

        InputStream istr;
        Bitmap bitmap = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            istr = assetManager.open("dog.jpg");

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, width, height);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            bitmap=BitmapFactory.decodeStream(istr, null, options);
            copyBitmap=bitmap.copy(Bitmap.Config.ARGB_8888,true);
            return copyBitmap;
        } catch (IOException e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return null;
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}