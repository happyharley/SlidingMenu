package com.example.test.slidingmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

import com.example.test.R;


public class SlidingMenu extends FrameLayout {
    private View menulayout;
    ViewDragHelper viewDragHelper;
    private View mainlayout;
    private View child;
    private int mTouchSlop;//toggle菜单开始响应的距离
    private boolean overScroll = false;

    public SlidingMenu(Context context) {
        this(context, null, 0);
    }

    public SlidingMenu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop() * 2;
        viewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return SlidingMenu.this.child == child;
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                Log.i("clampViewPositionHoriz", "left==" + left + "==dx==" + dx);
                if (!overScroll) {
                    if (left > 0)
                        return 0;
                    if (left < -mMenuWidth) {
                        return -mMenuWidth;
                    }
                }
                return left;
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                Log.i("onViewReleased", "left==" + releasedChild.getLeft() + "==xvel==" + xvel);
                if (isMenuClosed) {//如果已关闭菜单
                    if (releasedChild.getLeft() < -mMenuWidth + mTouchSlop) {//完全关闭的时候releasedChild.getLeft()=-mMenuWidth，手指抬起后，如果滑动的距离超过mTouchSlop，则打开抽屉菜单
                        viewDragHelper.smoothSlideViewTo(child, -mMenuWidth, 0);//相当于scroller的startScroll方法
                        ViewCompat.postInvalidateOnAnimation(SlidingMenu.this);
                    } else {
                        viewDragHelper.smoothSlideViewTo(child, 0, 0);
                        ViewCompat.postInvalidateOnAnimation(SlidingMenu.this);
                    }
                } else {//菜单已打开状态
                    if (releasedChild.getLeft() < -mTouchSlop) {//手指抬起后，如果距离左边小于500，则自由滑动到边缘，关闭抽屉菜单
                        viewDragHelper.smoothSlideViewTo(child, -mMenuWidth, 0);//相当于scroller的startScroll方法
                        ViewCompat.postInvalidateOnAnimation(SlidingMenu.this);
                    } else {
                        viewDragHelper.smoothSlideViewTo(child, 0, 0);
                        ViewCompat.postInvalidateOnAnimation(SlidingMenu.this);
                    }
                }

            }

        });
    }

    public boolean isOverScroll() {
        return overScroll;
    }

    public void setOverScroll(boolean overScroll) {
        this.overScroll = overScroll;
    }

    boolean isMenuClosed = true;//菜单的关闭打开状态

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        child = getChildAt(0);
        menulayout = findViewById(R.id
                .menulayout);
        mainlayout = findViewById(R.id
                .mainlayout);
    }

    int mMenuWidth;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMenuWidth = menulayout.getMeasuredWidth();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            if (child.getLeft() < -mMenuWidth + mTouchSlop) {
                isMenuClosed = true;
            } else {
                isMenuClosed = false;
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }
}
