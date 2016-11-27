package com.github.anzewei.hideinput;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

/**
 * 用于检测键盘事件
 *
 * @author AnZewei
 */

class DocView extends FrameLayout {
    private Rect mRect = new Rect();
    private InputHelper mHelper;
    private View[] mIgnoreView;
    private ArrayList<View> mFindViews = new ArrayList<>();
    private int[] mIgnoreViewIds;

    public final static int MODE_OUTSIDE = 1;
    public final static int MODE_TOP = 2;
    public final static int MODE_BOTTOM = 3;
    public final static int MODE_BOTH = 4;

    private int mMode = MODE_OUTSIDE;

    public DocView(Context context) {
        super(context);
    }

    public DocView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DocView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mFindViews.clear();
        if (mIgnoreViewIds != null) {
            View c;
            for (int ignoreViewId : mIgnoreViewIds) {
                c = findViewById(ignoreViewId);
                if (c != null) {
                    mFindViews.add(c);
                }
            }
        }
    }

    public void setIgnoreView(View... ignoreView) {
        mIgnoreView = ignoreView;
    }

    public void setIgnoreView(int... ids) {
        mIgnoreViewIds = ids;
        requestLayout();
    }


    public void attachToActivity(Activity activity, InputHelper inputHelper) {
        mHelper = inputHelper;
        ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
        ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
        decor.removeView(decorChild);
        addView(decorChild);
        decor.addView(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && isInputShow()) {
            View inputView = findFocus();//获取焦点view
            if (!shouldIgnore(motionEvent)) {
                mHelper.onTouchOutside();
                inputView.clearFocus();
                return true;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /*
     * 键盘是否弹出
     */
    private boolean isInputShow() {
        getWindowVisibleDisplayFrame(mRect);
        int height = getResources().getDisplayMetrics().heightPixels;
        height -= height / 5;
        return mRect.height() < height;
    }

    /*
     * 键盘是否弹出
     */
    private boolean shouldIgnore(MotionEvent event) {
        for (View findView : mFindViews) {
            if (touchInView(findView, event))
                return true;
        }
        if (mIgnoreView != null) {
            for (View view : mIgnoreView) {
                if (touchInView(view, event))
                    return true;
            }
        }

        View inputView = findFocus();//获取焦点view
        return inputView == null || touchModeView(inputView, event);
    }

    private boolean touchInView(View view, MotionEvent event) {
        view.getGlobalVisibleRect(mRect);//屏幕中的位置
        return mRect.contains((int) event.getX(), (int) event.getY());
    }

    private boolean touchModeView(View view, MotionEvent event) {
        view.getGlobalVisibleRect(mRect);//屏幕中的位置
        switch (mMode) {
            case MODE_BOTH:
                return mRect.top < event.getY() || mRect.bottom > event.getY();
            case MODE_TOP:
                return mRect.top < event.getY();
            case MODE_BOTTOM:
                return mRect.bottom > event.getY();
            case MODE_OUTSIDE:
                return mRect.contains((int) event.getX(), (int) event.getY());
        }
        return false;
    }

    public void setMode(int mode) {
        mMode = mode;
    }
}
