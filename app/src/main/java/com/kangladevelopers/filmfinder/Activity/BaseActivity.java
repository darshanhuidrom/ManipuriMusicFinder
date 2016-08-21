package com.kangladevelopers.filmfinder.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kangladevelopers.filmfinder.R;

/**
 * Created by BURNI on 2/9/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private String TAG = "BaseActivity";
    public Toolbar toolbar;
    Activity activity;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private ProgressDialog progressDialog;
    //This is just a test
    //This is just a secon Line i had coded

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    public void slideFromLeftToRight() {
        //slide from left to right - back press
        overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
    }

    public void slideFromRightToLeft() {
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    protected void slideFromBottomToTop() {
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
    }

    protected void slideFromTopToBottom() {
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
    }
}
