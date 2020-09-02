package com.example.test.cavas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.test.R;
import com.example.test.cavas.ui.colormatrix.ImageHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.InputStream;

public class HueSaturationLumActivity extends AppCompatActivity {
    float mHue;
    float mSaturation;
    float mLum;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cavas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final SeekBar seekBarHue = (SeekBar) findViewById(R.id.seekBarHue);
        SeekBar seekBarSaturation = (SeekBar) findViewById(R.id.seekBarSaturation);
        SeekBar seekBarLum = (SeekBar) findViewById(R.id.seekBarLum);
        imageView = ((ImageView) findViewById(R.id.imageView));
        InputStream in = getResources().openRawResource(R.raw.fullscreen);
        final Bitmap bitmap = BitmapFactory.decodeStream(in, null, null);
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getId()) {
                    case R.id.seekBarHue:
                        mHue = (progress - seekBar.getMax()) * 1.0f / seekBar.getMax() * 180;
                        break;
                    case R.id.seekBarSaturation:
                        mSaturation = progress * 1.0f / seekBar.getMax();
                        break;
                    case R.id.seekBarLum:
                        mLum = progress * 1.0f / seekBar.getMax();
                        break;
                }
                Bitmap bm = ImageHelper.handlelmageEffect(bitmap, mHue, mSaturation, mLum);
                imageView.setImageBitmap(bm);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
        seekBarHue.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekBarSaturation.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekBarLum.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }
}
