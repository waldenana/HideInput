package com.github.anzewei.hideinput;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 辅助activity点击EditTextview区域外时隐藏键盘
 * 使用方法
 * protected void onPostCreate(@Nullable Bundle savedInstanceState) {
 * super.onPostCreate(savedInstanceState);
 * new InputHelper(this).onPostCreate();
 * }
 *
 * @author AnZewei
 */

public class InputHelper {
    private Activity mActivity;
    private DocView mDocView;
    private InputMethodManager inputManager;

    public final static int MODE_OUTSIDE = DocView.MODE_OUTSIDE;
    public final static int MODE_TOP = DocView.MODE_TOP;
    public final static int MODE_BOTTOM = DocView.MODE_BOTTOM;
    public final static int MODE_BOTH = DocView.MODE_BOTH;

    public InputHelper(Activity activity) {
        this.mActivity = activity;
        inputManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        mDocView = new DocView(activity);
    }

    public void onPostCreate() {
        mDocView.attachToActivity(mActivity, this);
    }

    void onTouchOutside() {
        inputManager.hideSoftInputFromWindow(mDocView.getWindowToken(), 0);
    }

    /**
     * 点击在这些view上不会隐藏键盘
     *
     */
    public void setIgnoreView(View... ignoreView) {
        mDocView.setIgnoreView(ignoreView);
    }

    /**
     * 点击在这些view上不会隐藏键盘
     *
     */
    public void setIgnoreView(int... ids) {
        mDocView.setIgnoreView(ids);
    }

    /**
     * 设置隐藏键盘的4种模式，{@link #MODE_OUTSIDE},{@link #MODE_TOP},{@link #MODE_BOTTOM},{@link #MODE_BOTH}
     *
     **/
    public void setMode(int mode) {
        mDocView.setMode(mode);
    }
}
