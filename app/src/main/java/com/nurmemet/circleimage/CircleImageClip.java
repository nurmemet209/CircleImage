package com.nurmemet.circleimage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * Created by nurmemet on 10/16/2016.
 */

public class CircleImageClip extends Drawable {
    private Paint mPaint;
    private Bitmap mBitmap;
    private Path mPath;
    private RectF mBounds;

    public CircleImageClip(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
        init();
    }

    private void init() {
        mBounds = new RectF();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPath = new Path();


    }

    @Override
    public void draw(Canvas canvas) {

        canvas.save();
        canvas.clipPath(mPath);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.restore();
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
        mPath.reset();
        final float radius = Math.min(mBounds.width() / 2, mBounds.height() / 2);
        mPath.addCircle(radius, radius, radius, Path.Direction.CCW);
    }
}
