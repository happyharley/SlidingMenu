package com.example.test.cavas.ui.colormatrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.test.R;


public class MatrixFragment extends Fragment implements View.OnClickListener {

    private MatrixViewModel matrixViewModel;
    private ImageView mlmageView;
    private GridLayout mGroup;
    private int mEtWidth;
    private int mEtHeight;
    private EditText[] mEts = new EditText[20];
    float[] mColorMatrix = new float[20];
    Bitmap bitmap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        matrixViewModel =
                ViewModelProviders.of(this).get(MatrixViewModel.class);
        View root = inflater.inflate(R.layout.fragment_colormatrix, container, false);
        matrixViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fullscreen);
        root.findViewById(R.id.btnchange).setOnClickListener(this);
        root.findViewById(R.id.btnreset).setOnClickListener(this);
        mlmageView = (ImageView) root.findViewById(R.id.imageView);
        mGroup = (GridLayout) root.findViewById(R.id.group);
        mlmageView.setImageBitmap(bitmap);
        mGroup.post(new Runnable() {
            @Override
            public void run() {
                //获取宽高信息
                mEtWidth = mGroup.getWidth() / 5;
                mEtHeight = mGroup.getHeight() / 4;
                addEts();
                initMatrix();
            }
        }); //添加 EditText
        return root;
    }

    private void addEts() {
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(getActivity());
            mEts[i] = editText;
            mGroup.addView(editText, mEtWidth, mEtHeight);
        }

    }

    //初始化颜色矩阵为初始状态
    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEts[i].setText(String.valueOf(1));
            } else {
                mEts[i].setText(String.valueOf(0));
            }
        }
    }

    //获取矩阵值
    private void getMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrix[i] = Float.valueOf(mEts[i].getText().toString());
        }
    }
    //将矩阵值设置到图像

    private void setImageMatrix() {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new android.graphics.ColorMatrix();
        colorMatrix.set(mColorMatrix);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        mlmageView.setImageBitmap(bmp);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnchange://作用矩阵效果
                getMatrix();
                setImageMatrix();
                break;
            case R.id.btnreset://重置矩阵效果
                initMatrix();
                getMatrix();
                setImageMatrix();
                break;
        }
    }
}