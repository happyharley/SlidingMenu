package com.example.test.cavas.ui.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.test.R;

public class RoundAngleView extends View {
    private BitmapShader mBitmapShader;
    private Bitmap mBitmap;
    Paint mPaint = new Paint();

    public RoundAngleView(Context context) {
        super(context);
    }

    public RoundAngleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.id);
//        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
//        mPaint.setShader(mBitmapShader);

        mPaint.setShader(new LinearGradient(0, 0, 400, 0, Color.YELLOW, Color.BLUE, Shader.TileMode.REPEAT));
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(mBitmapShader);

    }


    public RoundAngleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        canvas.drawRect(0, 0, 1000, 1000, mPaint);
    }
}
