package com.github.anzewei.hideInput;

import android.app.Activity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by 58 on 2016/11/26.
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
        Log.d("ViewTag", "outside");
    }
}
