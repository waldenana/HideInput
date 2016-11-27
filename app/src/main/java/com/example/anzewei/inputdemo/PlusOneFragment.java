package com.example.anzewei.inputdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.anzewei.hideinput.InputHelper;

/**
 * A fragment with a Google +1 button.
 */
public class PlusOneFragment extends Fragment {

    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    private Button mPlusOneButton;


    public PlusOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plus_one, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((BaseActivity)getActivity()).getInputHelper().setMode(InputHelper.MODE_TOP);
    }
}
