package com.example.test.slidingmenu;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

public class SlidingMenuActivity extends AppCompatActivity {

    private SlidingMenu slidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_menu);
        slidingMenu = ((SlidingMenu) findViewById(R.id.slidingmenu));
    }

    public void setOverScroll(View view) {
        slidingMenu.setOverScroll(!slidingMenu.isOverScroll());
    }
}
