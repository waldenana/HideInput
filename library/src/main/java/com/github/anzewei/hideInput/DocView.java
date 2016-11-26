package com.github.anzewei.hideInput;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * 用于检测键盘事件
 * @author AnZewei
 */

class DocView extends FrameLayout {
    private Rect mRect = new Rect();
    private InputHelper mHelper;
    public DocView(Context context) {
        super(context);
    }

    public DocView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DocView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
            if (inputView != null) {
                inputView.getGlobalVisibleRect(mRect);//屏幕中的位置
                if (!mRect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    mHelper.onTouchOutside();
                    inputView.clearFocus();
                    return true;
                }
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
        height-=height/5;
        return mRect.height() < height;
    }
}
