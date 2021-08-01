package com.android.image.pixargb.ImageFilters;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class BrushPrepare {

    Canvas canvas;
    ImageView imageView;
    Bitmap draw_bitmap;
    Bitmap canvaBit;
    Path path;
    Paint paint;
    int color= Color.RED;
    float prvX,prvY;

    public BrushPrepare(Bitmap bitmap, ImageView imageView) {
        this.draw_bitmap = bitmap;
        this.imageView=imageView;
        canvaBit=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        canvas=new Canvas(canvaBit);
        path=new Path();
        paint=new Paint();

    }

    @SuppressLint("ClickableViewAccessibility")
    public void prepareCanvas(){
        Log.w("called","preparecanvas");

        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(10f);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));

        canvas.drawBitmap(draw_bitmap,0,0,paint);
//        imageView.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(canvaBit);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.w("listening","touch");

                float x=event.getX();
                float y=event.getY();


                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        Log.w("touch","down");
                        prvX = x;
                        prvY = y;
                        drawOnProjectedBitMap((ImageView) v, canvaBit, prvX, prvY, x, y);
                        path.moveTo(x,y);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        Log.w("touch","move");
                        drawOnProjectedBitMap((ImageView) v, canvaBit, prvX, prvY, x, y);
                        prvX = x;
                        prvY = y;
                        path.lineTo(x,y);
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.w("touch","up");
                        drawOnProjectedBitMap((ImageView) v,canvaBit, prvX, prvY, x, y);
                        return true;
                }


                return false;
            }
        });
    }

    private void drawOnProjectedBitMap(ImageView iv, Bitmap bm,
                                       float x0, float y0, float x, float y){
        if(x<0 || y<0 || x > iv.getWidth() || y > iv.getHeight()){
            //outside ImageView
            return;
        }else{

//            float ratioWidth = (float)bm.getWidth()/(float)iv.getWidth();
//            float ratioHeight = (float)bm.getHeight()/(float)iv.getHeight();
//
//            canvas.drawLine(
//                    x0 * ratioWidth,
//                    y0 * ratioHeight,
//                    x * ratioWidth,
//                    y * ratioHeight,
//                    paint);
            canvas.drawPath(path,paint);
            imageView.invalidate();
        }
    }

    public void setColor(int color1){
        this.color=color1;
    }

    public Bitmap getDraw_bitmap(){
        return canvaBit;
    }
}
