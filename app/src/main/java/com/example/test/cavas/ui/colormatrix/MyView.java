package com.example.test.cavas.ui.colormatrix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import static android.graphics.Canvas.ALL_SAVE_FLAG;

public class MyView extends View {
    Paint mPaint = new Paint();

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(150, 150, 100, mPaint);
        canvas.saveLayerAlpha(0, 0, 400, 400, 127, ALL_SAVE_FLAG);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(200, 200, 100, mPaint);
        canvas.restore();
    }
}
