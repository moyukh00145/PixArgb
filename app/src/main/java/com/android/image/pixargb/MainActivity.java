package com.android.image.pixargb;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.image.pixargb.Fragments.BasicTune;
import com.android.image.pixargb.Fragments.Brightness;
import com.android.image.pixargb.Fragments.Brush;
import com.android.image.pixargb.Fragments.ColorAdd;
import com.android.image.pixargb.Fragments.Contrast;
import com.android.image.pixargb.Fragments.Emoji;
import com.android.image.pixargb.Fragments.Filterfrag;
import com.android.image.pixargb.Fragments.Saturation;
import com.android.image.pixargb.ImageFilters.BrushPrepare;
import com.android.image.pixargb.ImageFilters.FilterDemo;
import com.android.image.pixargb.ImageFilters.Filtermaker;
import com.android.image.pixargb.ImageFilters.Filters;
import com.android.image.pixargb.ImageFilters.brightnessBaseFilter;
import com.android.image.pixargb.ImageFilters.contrastBaseFilter;
import com.android.image.pixargb.ImageFilters.rgbBaseFilter;
import com.android.image.pixargb.ImageFilters.saturationBaseFilter;
import com.android.image.pixargb.Interfaces.BaseFilter;
import com.android.image.pixargb.Interfaces.FilterSelected;
import com.android.image.pixargb.Interfaces.Optionclicked;
import com.android.image.pixargb.Interfaces.StoreFilter;
import com.android.image.pixargb.Measurement.Point;
import com.android.image.pixargb.NativeProcessor.Pixelprocessor;
import com.android.image.pixargb.adapters.OptionAdapter;
import com.android.image.pixargb.adapters.ShowColorAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Stack;

import static com.android.image.pixargb.ApplicationClass.colors;
import static com.android.image.pixargb.ApplicationClass.options;

public class MainActivity extends AppCompatActivity implements Optionclicked,
        FilterSelected, Brightness.BrightnessChangeListener,
        Contrast.ContrastChange, StoreFilter, Saturation.SaturationChange,
        ShowColorAdapter.BrushSelected , ColorAdd.ColourAddListener {

    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    OptionAdapter adapter;
    LinearLayout option_lay;
    ArrayList<Integer> optionView = options;
    Context context;
    ImageView swipe_btn, centerImage,add_image,save_image,centerImage2;
    ConstraintLayout swipe_lay;
    static Filterfrag filterfrag;
    Brightness brightnessfrag;
    Contrast contrastFrag;
    Saturation saturationFrag;
    Emoji emojiFrag;
    BasicTune basicTuneFrag;
    ColorAdd colorAddFrag;
    Brush brushFrag;
    public static Bitmap originBitmap;
    public static boolean load=false;
    Bitmap post_bitmap,bright_post_bit;
    ActivityResultLauncher<Intent> activityResultLauncher;
    brightnessBaseFilter baseFilter;
    contrastBaseFilter contrastBaseFilter;
    saturationBaseFilter saturationBaseFilter;
    BrushPrepare brushPrepare;
    Stack<String>stack;
    Runnable runnable;
    int color=Color.RED;
    ArrayList<Integer>colorShowList;

    Uri sendUri=null;

    Button btnSend;

    static {
        System.loadLibrary("processor");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        initElemrnts();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9, getResources().getDisplayMetrics());
        Log.w("value2", value + "");


        stack=new Stack<>();
        colorShowList=colors;

        initializeFragments();
        originBitmap=filterfrag.makeBitmap(MainActivity.this,centerImage.getWidth(),centerImage.getMaxHeight());
        centerImage.setImageBitmap(originBitmap);
        recyclerView = findViewById(R.id.option_recycler_ciew);
        adapter = new OptionAdapter(MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(adapter);


        swipe_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (swipe_lay.getVisibility() == View.GONE)
                {
                    Log.w("inside", "gone");
                    swipe_lay.setVisibility(View.VISIBLE);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                            swipe_btn.setImageResource(R.drawable.right_arrow);
                            Log.w("start", "called");
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    swipe_lay.startAnimation(animation);


                }
                else
                    {


                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slidein);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            animation.cancel();
                            swipe_lay.setVisibility(View.GONE);
                            swipe_btn.setImageResource(R.drawable.left_arrow);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    swipe_lay.startAnimation(animation);


                }
            }
        });


        filterfrag=new Filterfrag();
        stack.push(getResources().getString(R.string.Filters));
        getSupportFragmentManager().beginTransaction().replace(R.id.option_show,filterfrag).commit();
        add_image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED&&
                        ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                    addImage();
                }
                else{
                    askPermission();
                }

            }
        });

        save_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED&&
                        ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){



                    if (!stack.empty()){
                        if (stack.peek().equals(getResources().getString(R.string.Brush))){
                            Log.w("inside","Brush");
                            bright_post_bit=brushPrepare.getDraw_bitmap();

                        }
                        store();
                    }

                    if (post_bitmap!=null){
                        originBitmap=post_bitmap;
                    }
                    saveImage();


                }
                else{
                    askPermission();
                }


            }
        });

         activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()==RESULT_OK){
                    Intent data=result.getData();
                    extractImage(data);
                }
            }
        });


         btnSend.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 if (sendUri==null){
                     Toast.makeText(MainActivity.this, "Save the Image to send", Toast.LENGTH_SHORT).show();
                 }
                 else{
                     Intent intent=new Intent(Intent.ACTION_SEND);
                     intent.setType("image/*");
                     intent.putExtra(Intent.EXTRA_STREAM,sendUri);
                     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     startActivity(Intent.createChooser(intent,"Send Image through : "));
                 }

             }
         });


    }

    private void saveImage()
    {
        OutputStream outputStream;

       if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
           ContentResolver contentResolver=getContentResolver();

           ContentValues values=new ContentValues();
           values.put(MediaStore.Images.Media.TITLE,System.currentTimeMillis()+"_pixargb.jpg");
           values.put(MediaStore.Images.Media.DISPLAY_NAME,System.currentTimeMillis()+"_pixargb.jpg");
           values.put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg");
           values.put(MediaStore.Images.Media.DATE_ADDED,System.currentTimeMillis());
           values.put(MediaStore.Images.Media.DATE_TAKEN,System.currentTimeMillis());
           values.put(MediaStore.Images.Media.RELATIVE_PATH,Environment.DIRECTORY_PICTURES+File.separator+"Pixargb");


           Uri uri= contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
           try {
               outputStream=contentResolver.openOutputStream(uri);
               originBitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
               sendUri=uri;
               Toast.makeText(this, "Sucessfully save to"+uri.getPath(), Toast.LENGTH_LONG).show();
           } catch (FileNotFoundException e) {
               e.printStackTrace();

               Log.w("msg",e.getLocalizedMessage());
           }
       }
       else{
           Log.w("working","till");

           String root=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();


           File dir=new File(root,"PixArgb");
           if (!dir.exists()){
               dir.mkdir();
           }

           File file=new File(dir,System.currentTimeMillis()+"_pixargb.jpg");

           try {
               outputStream=new FileOutputStream(file);
               originBitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
               sendUri=Uri.fromFile(file);
               Toast.makeText(this, "Sucessfully save to"+file.getAbsolutePath(), Toast.LENGTH_LONG).show();
           } catch (FileNotFoundException e) {
               e.printStackTrace();
               Log.w("msg2",e.getLocalizedMessage());
           }


       }




    }


    void initElemrnts(){

        option_lay = findViewById(R.id.options);

        swipe_btn = findViewById(R.id.swipe_button);
        swipe_lay = findViewById(R.id.swipe_lay);

        centerImage = findViewById(R.id.centerImage);
        centerImage2=findViewById(R.id.centerImage2);
        add_image=findViewById(R.id.image_add);
        save_image=findViewById(R.id.save_image);
        btnSend=findViewById(R.id.sendBtn);

    }


    private void extractImage(Intent data) {

        try {
            InputStream inputStream=getContentResolver().openInputStream(data.getData());
            final Bitmap bitmap=BitmapFactory.decodeStream(inputStream);

            load=true;

           final Matrix matrix=new Matrix();
           matrix.postRotate(270);

            if (bitmap.getWidth()>bitmap.getHeight()){
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choose Option")
                        .setMessage("The image is in Landscape do you want to rotate?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                originBitmap=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,false);
                                centerImage.setImageBitmap(originBitmap);
                                filterfrag.makeDemoImages(originBitmap);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                originBitmap=Bitmap.createScaledBitmap(bitmap,centerImage.getWidth(),centerImage.getHeight(),false);
                                centerImage.setImageBitmap(originBitmap);
                                filterfrag.makeDemoImages(originBitmap);
                            }
                        }).show();
            }
            else {
                originBitmap=Bitmap.createScaledBitmap(bitmap,centerImage.getWidth(),centerImage.getHeight(),false);
                centerImage.setImageBitmap(originBitmap);
                filterfrag.makeDemoImages(originBitmap);
            }






        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addImage() {
        Log.w("addImage","Called");

        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activityResultLauncher.launch(intent);

    }

    public void askPermission(){

        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},102);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==102){
            if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED){
                addImage();
            }
            else{

            }
        }

    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
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

    @Override
    public void onClicked(int position) {

        int id = optionView.get(position);
        toggleColour(position);
        recyclerView.scrollToPosition(position);


        setFragments(position);

    }

    private void initializeFragments() {
        Log.w("MainActivity","fragmentInitilize");
        brightnessfrag = new Brightness();
        contrastFrag=new Contrast();
        filterfrag = new Filterfrag();
        saturationFrag=new Saturation();
        colorAddFrag=new ColorAdd();
        brushFrag=new Brush();
        emojiFrag=new Emoji();
        basicTuneFrag=new BasicTune();
    }


    private void setFragments(int position) {

        store();

        switch (position) {
            case 0: {
                stack.push(getResources().getString(R.string.Brightness));
                getSupportFragmentManager().beginTransaction().replace(R.id.option_show, brightnessfrag).commit();
            }
            break;
            case 1:
                stack.push(getResources().getString(R.string.Contrast));
                getSupportFragmentManager().beginTransaction().replace(R.id.option_show, contrastFrag).commit();
                break;
            case 2: {
                stack.push(getResources().getString(R.string.Filters));
                getSupportFragmentManager().beginTransaction().replace(R.id.option_show,filterfrag).commit();
            }
            break;
            case 3:
                stack.push(getResources().getString(R.string.Saturation));
                getSupportFragmentManager().beginTransaction().replace(R.id.option_show, saturationFrag).commit();
                break;
            case 4:
                stack.push(getResources().getString(R.string.Colour_Add));



                getSupportFragmentManager().beginTransaction().replace(R.id.option_show, colorAddFrag).commit();
                break;
            case 5:
                stack.push(getResources().getString(R.string.Brush));
                getSupportFragmentManager().beginTransaction().replace(R.id.option_show, brushFrag).commit();
                makeBrushPrepare();
                break;
            case 6:
                stack.push(getResources().getString(R.string.Emojis));
                getSupportFragmentManager().beginTransaction().replace(R.id.option_show, emojiFrag).commit();
                break;
            case 7:
                stack.push(getResources().getString(R.string.Basic_tune));
                getSupportFragmentManager().beginTransaction().replace(R.id.option_show, basicTuneFrag).commit();
                break;
            default:

        }


    }


    private void makeBrushPrepare() {


        runnable=new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap;
                if (post_bitmap==null){
                    bitmap=originBitmap.copy(Bitmap.Config.ARGB_8888,true);
                }
                else{

                    bitmap=post_bitmap.copy(Bitmap.Config.ARGB_8888,true);

                }
                brushPrepare=new BrushPrepare(bitmap,centerImage2);
                brushPrepare.setColor(color);

                brushPrepare.prepareCanvas();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        centerImage2.setVisibility(View.VISIBLE);
                        centerImage.setVisibility(View.GONE);

                    }
                });

            }
        };

        new Thread(runnable).start();

    }


    private void toggleColour(int position) {

        for (int i = 0; i < optionView.size(); i++) {
            if (i != position) {
                getResources().getDrawable(optionView.get(i)).setTint(Color.GRAY);
            } else {
                getResources().getDrawable(optionView.get(i)).setTint(Color.WHITE);
            }
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void filterSelected(Filters filters) {
        Bitmap pre_bitmap=originBitmap.copy(Bitmap.Config.ARGB_8888,true);
        bright_post_bit=filters.processBitmap(pre_bitmap);
        centerImage.setImageBitmap(bright_post_bit);
    }

    @Override
    public void progress(int progress) {
        baseFilter=new brightnessBaseFilter(progress);
        processManual(baseFilter);

    }

    @Override
    public void store() {


        if (centerImage.getVisibility()==View.GONE){
            Log.w("visiblity","called");
            centerImage.setVisibility(View.VISIBLE);
            centerImage2.setVisibility(View.GONE);
        }
        Log.w("called","store");
        if (!stack.empty()){


            if (stack.peek().equals(getResources().getString(R.string.Brush))){
                Log.w("inside","Brush");
                bright_post_bit=brushPrepare.getDraw_bitmap();

            }

            stack.pop();
        }
        if (bright_post_bit==null){
            post_bitmap=originBitmap;
        }
        else{
            post_bitmap=bright_post_bit;
        }

        centerImage.setImageBitmap(post_bitmap);
    }

    @Override
    public void contrastChange(float progress) {
        contrastBaseFilter=new contrastBaseFilter(progress);
        processManual(contrastBaseFilter);

    }

    @Override
    public void saturationChange(float progress) {

        saturationBaseFilter=new saturationBaseFilter(progress);
        processManual(saturationBaseFilter);
    }

    void processManual(BaseFilter filter){


        Bitmap bright_pre_bit;
        if (post_bitmap==null) {
            bright_pre_bit= originBitmap.copy(Bitmap.Config.ARGB_8888, true);
        }
        else{
            bright_pre_bit=post_bitmap.copy(Bitmap.Config.ARGB_8888,true);
        }
        bright_post_bit=filter.processFilter(bright_pre_bit);
        centerImage.setImageBitmap(bright_post_bit);


    }

    @Override
    public void BrushSelected(int index) {
        store();
        stack.push(getResources().getString(R.string.Brush));
        this.color=colorShowList.get(index);
        makeBrushPrepare();

    }

    @Override
    public void redAdded(int pgred,int pggreen,int pgblue) {
        Log.w("colors1","red="+pgred);
        Log.w("colors1","blue="+pgblue);
        Log.w("colors1","green="+pggreen);

        Bitmap color_pre_bit;

        if (post_bitmap==null) {
            color_pre_bit= originBitmap.copy(Bitmap.Config.ARGB_8888, true);
        }
        else{
            color_pre_bit=post_bitmap.copy(Bitmap.Config.ARGB_8888,true);
        }

        bright_post_bit= Pixelprocessor.ColorAdd(pgred,pggreen,pgblue,color_pre_bit);
        centerImage.setImageBitmap(bright_post_bit);

    }

    @Override
    public void blueAdded(int pgred,int pggreen,int pgblue) {
        Log.w("colors2","red="+pgred);
        Log.w("colors2","blue="+pgblue);
        Log.w("colors2","green="+pggreen);

        Bitmap color_pre_bit;

        if (post_bitmap==null) {
            color_pre_bit= originBitmap.copy(Bitmap.Config.ARGB_8888, true);
        }
        else{
            color_pre_bit=post_bitmap.copy(Bitmap.Config.ARGB_8888,true);
        }

        bright_post_bit= Pixelprocessor.ColorAdd(pgred,pggreen,pgblue,color_pre_bit);
        centerImage.setImageBitmap(bright_post_bit);

    }

    @Override
    public void greenAdded(int pgred,int pggreen,int pgblue) {


        Log.w("colors3","red="+pgred);
        Log.w("colors3","blue="+pgblue);
        Log.w("colors3","green="+pggreen);

        Bitmap color_pre_bit;

        if (post_bitmap==null) {
            color_pre_bit= originBitmap.copy(Bitmap.Config.ARGB_8888, true);
        }
        else{
            color_pre_bit=post_bitmap.copy(Bitmap.Config.ARGB_8888,true);
        }

        bright_post_bit= Pixelprocessor.ColorAdd(pgred,pggreen,pgblue,color_pre_bit);
        centerImage.setImageBitmap(bright_post_bit);

    }
}