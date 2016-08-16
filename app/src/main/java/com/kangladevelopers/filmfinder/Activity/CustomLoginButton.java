package com.kangladevelopers.filmfinder.Activity;

import android.content.Context;

import com.facebook.login.widget.LoginButton;

/**
 * Created by HUIDROM on 8/16/2016.
 */
public class CustomLoginButton extends LoginButton  {
    public CustomLoginButton(Context context) {
        super(context);
    }



     interface Listener {
        void onListenClick();
    }
}
