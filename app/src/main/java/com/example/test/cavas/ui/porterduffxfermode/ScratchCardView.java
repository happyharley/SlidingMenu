package com.example.test.cavas.ui.porterduffxfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.test.R;

public class ScratchCardView extends View {
    private Canvas mCanvas;
    private Bitmap mFgBitmap;
    private Bitmap mBitmap;
    private Path mPath;
    private Bitmap mOut;
    Paint mPaint = new Paint();

    public ScratchCardView(Context context) {
        super(context);
    }

    public ScratchCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fullscreen);
        mOut = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mOut);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        RectF rect = new RectF();
        rect.left = 0;
        rect.top = 0;
        rect.right = mBitmap.getWidth();
        rect.bottom = mBitmap.getHeight();
        canvas.drawRoundRect(rect, 80, 80, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        setBackground(new BitmapDrawable(getResources(), mOut));


//        Paint mPaint = new Paint();
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);

        mPath = new Path();
        int dimension = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 500, getResources().getDisplayMetrics());
        mFgBitmap = Bitmap.createBitmap(mBitmap.getWidth(), dimension, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mFgBitmap);
        rect.bottom = dimension;
        mCanvas.drawRoundRect(rect, 80, 80, mPaint);
        mCanvas.drawColor(Color.GRAY, PorterDuff.Mode.SRC_IN);


        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        this.mPaint.setColor(Color.TRANSPARENT);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mPaint.setStrokeWidth(50);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                break;
        }
        mCanvas.drawPath(mPath, mPaint);
        invalidate();
        return true;
    }

    public ScratchCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mFgBitmap, 0, 0, null);
    }
}
