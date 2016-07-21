package com.kangladevelopers.filmfinder.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.kangladevelopers.filmfinder.R;

/**
 * Created by HUIDROM on 7/22/2016.
 */
public class CustomDialogBox extends Dialog implements android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    private Listeners listeners;

    public CustomDialogBox(Activity a,Listeners listeners) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.listeners=listeners;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.selector_dialog_fragment);
        yes = (Button) findViewById(R.id.galleryButton);
        no = (Button) findViewById(R.id.camerButton);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.galleryButton:
                listeners.onGalleryClick();
                break;
            case R.id.camerButton:
                listeners.onCameraClick();
                break;
            default:
                break;
        }
        dismiss();
    }

    public interface Listeners{
        void onGalleryClick();
        void onCameraClick();
    }
}