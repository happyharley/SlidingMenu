package com.example.test.cavas.ui.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.test.R;

public class InvertedView extends View {
    private Bitmap mBitmap;
    private Bitmap mInvertedBitmap;
    Paint mPaint = new Paint();
    PorterDuffXfermode porterDuffXfermode;

    public InvertedView(Context context) {
        super(context);
    }

    public InvertedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meinvzhu);
        Matrix matrix = new Matrix();
        matrix.setScale(1, -1);
        mInvertedBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
        mPaint.setShader(new LinearGradient(0, mBitmap.getHeight(), 0, mBitmap.getHeight() + mBitmap.getHeight(), 0xff000000, 0x11000000, Shader.TileMode.CLAMP));
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        mPaint.setColor(Color.WHITE);
    }


    public InvertedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(100, 100, 500, mPaint);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.drawBitmap(mInvertedBitmap, 0, mBitmap.getHeight(), null);
        mPaint.setXfermode(porterDuffXfermode);
        canvas.drawRect(0, mBitmap.getHeight(), mBitmap.getWidth(), mBitmap.getHeight() * 2, mPaint);
        mPaint.setXfermode(null);
    }
}
