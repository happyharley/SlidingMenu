package com.example.test.cavas.ui.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PathEffectView extends View {
    Path mPath;
    PathEffect[] pathEffects = new PathEffect[6];
    Paint mPaint;

    public PathEffectView(Context context) {
        super(context);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(30);
        mPath = new Path();
        mPath.moveTo(0, 0);
        for (int i = 0; i < 30; i++) {
            mPath.lineTo(i * 35, (float) (Math.random() * 200));
        }
        pathEffects[0] = null;
        pathEffects[1] = new CornerPathEffect(30);
        pathEffects[2] = new DiscretePathEffect(130f, 50f);
        pathEffects[3] = new DashPathEffect(new float[]{20, 10}, 10);
        Path path = new Path();
        path.addRect(0, 0, 20, 20, Path.Direction.CCW);
        pathEffects[4] = new PathDashPathEffect(path, 30, 0, PathDashPathEffect.Style.ROTATE);
        pathEffects[5] = new ComposePathEffect(pathEffects[3], pathEffects[1]);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < pathEffects.length; i++) {
            mPaint.setPathEffect(pathEffects[i]);
            canvas.drawPath(mPath, mPaint);
            canvas.translate(0, 200);
        }
    }
}
