package com.example.anzewei.inputdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class FragmentReplaceActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,new PlusOneFragment());
        transaction.commitAllowingStateLoss();
    }

}
