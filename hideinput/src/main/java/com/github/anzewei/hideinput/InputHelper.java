package com.github.anzewei.hideinput;

import android.app.Activity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

/**
 * 辅助activity点击EditTextview区域外时隐藏键盘
 *     使用方法
 *      protected void onPostCreate(@Nullable Bundle savedInstanceState) {
 *            super.onPostCreate(savedInstanceState);
 *            new InputHelper(this).onPostCreate();
 *     }
 * @author AnZewei
 */

public class InputHelper {
    private Activity mActivity;
    private DocView mDocView;
    private InputMethodManager inputManager;

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
}
