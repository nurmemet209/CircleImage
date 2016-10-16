package com.nurmemet.circleimage;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by nurmemet on 10/16/2016.
 */

public class CircleImageShader extends Drawable {
    private Paint mPaint;
    private Bitmap mBitmap;
    private RectF mBounds;

    public CircleImageShader(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.RED);
        mBounds=new RectF();
        mPaint.setShader(getShaderBitmap());

    }

    private BitmapShader getShaderBitmap() {
        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        return shader;
    }

    @Override
    public void draw(Canvas canvas) {
        final float radius = Math.min(mBounds.width() / 2, mBounds.height() / 2);
        canvas.drawCircle(radius, radius, radius, mPaint);
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
        mBounds.set(left,top,right,bottom);
    }
}
