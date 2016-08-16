package com.kangladevelopers.filmfinder.Utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;

import com.kangladevelopers.filmfinder.R;

/**
 * Created by HUIDROM on 7/21/2016.
 */
public class ProgressBarConfig {

    public static ProgressDialog  PROGRESS_DIALOG;

    public static void dismissProgressBar(){
        if(PROGRESS_DIALOG!=null&&PROGRESS_DIALOG.isShowing()){
            PROGRESS_DIALOG.dismiss();
        }
    }

    public static void showProgressBar(Activity activity,@Nullable String msg){
       PROGRESS_DIALOG = new ProgressDialog(activity, R.style.ProgressBarTheme);
        if(msg==null){
            PROGRESS_DIALOG.setMessage("Loading..");
        }
        else {
           // PROGRESS_DIALOG.setMessage(msg);
        }
        PROGRESS_DIALOG.setCancelable(false);
        PROGRESS_DIALOG.show();
       // PROGRESS_DIALOG.setContentView(R.layout.progress_bar);

    }

}
