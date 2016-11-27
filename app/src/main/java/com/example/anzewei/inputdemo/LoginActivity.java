package com.example.anzewei.inputdemo;

import android.os.Bundle;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getInputHelper().setIgnoreView(R.id.email_sign_in_button);
    }
}

