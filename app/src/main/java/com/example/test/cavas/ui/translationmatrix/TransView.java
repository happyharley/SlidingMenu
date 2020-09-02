package com.example.test.cavas.ui.translationmatrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.test.R;

public class TransView extends View {
    private final int HEIGHT = 100;
    private final int WIDTH = 100;
    private final float[] orig = new float[2 * (WIDTH + 1) * (HEIGHT + 1)];
    private final float[] verts = new float[2 * (WIDTH + 1) * (HEIGHT + 1)];
    Bitmap bitmap;
    private float scale = 122.0f;

    public TransView(Context context) {
        super(context);
    }

    public TransView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meinvzhu);
        float bitmapHeight = bitmap.getHeight();
        float bitmapWidth = bitmap.getWidth();
        int index = 0;
        for (int y = 0; y <= HEIGHT; y++) {
            float fy = bitmapHeight * y / HEIGHT;
            for (int x = 0; x <= WIDTH; x++) {
                float fx = bitmapWidth * x / WIDTH;
                orig[index * 2 + 0] = verts[index * 2 + 0] = fx;
                //这里人为将坐标 + 100 是为了让图像下移，避免扭曲后被屏幕遮挡
                orig[index * 2 + 1] = verts[index * 2 + 1] = fy + 150;
                index++;
            }
        }
    }

    private void flagWave() {
        for (int j = 0; j <= HEIGHT; j++) {
            for (int i = 0; i <= WIDTH; i++) {
                verts[(j * (WIDTH + 1) + i) * 2 + 0] += 5;
                float offsetY = (float) Math.sin((float) i / WIDTH * 2 * Math.PI + Math.PI * k);
                verts[(j * (WIDTH + 1) + i) * 2 + 1] = orig[(j * WIDTH + 1) * 2 + 1] + offsetY * scale;
            }
        }
    }

    public TransView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float k;

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        k += 0.1f;
        flagWave();
        canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
        postInvalidateDelayed(50);
    }
}
