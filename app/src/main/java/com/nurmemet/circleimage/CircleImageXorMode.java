package com.nurmemet.circleimage;

import android.content.ComponentName;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;

/**
 * Created by nurmemet on 10/16/2016.
 * 通过混合模式实现
 */

public class CircleImageXorMode extends Drawable {
    private Paint mPaint;
    private Bitmap mBitmap;
    private RectF mBounds;
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

    public CircleImageXorMode(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.RED);
        mBounds = new RectF();

    }

    @Override
    public void draw(Canvas canvas) {

       Bitmap bp=Bitmap.createBitmap(mBitmap.getWidth(),mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas os=new Canvas(bp);
        //绘制原图
        os.drawBitmap(mBitmap,0,0,null);
        mPaint.setXfermode(mXfermode);
        mPaint.setFilterBitmap(false);
        //绘制目标图形（决定目标区域）
        os.drawBitmap(getMaskBitmap(),0,0,mPaint);
        mPaint.setXfermode(null);
        canvas.drawBitmap(bp,0,0,mPaint);




    }

    @Override
    public void setAlpha(int alpha) {

        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mBounds.set(left, top, right, bottom);
    }

    private Bitmap getMaskBitmap() {
        Bitmap bmp = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        final float height = Math.min(mBounds.width(), mBounds.height());
        Paint pt = new Paint(Paint.ANTI_ALIAS_FLAG);
        pt.setColor(Color.BLUE);
        canvas.drawCircle(height / 2, height / 2, height / 2, pt);
        return bmp;


    }


}
