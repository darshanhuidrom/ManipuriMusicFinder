package com.kangladevelopers.filmfinder.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.kangladevelopers.filmfinder.MyApplication;
import com.kangladevelopers.filmfinder.Utility.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HUIDROM on 8/15/2016.
 */
public class Utility {
    public static String getVersionName() {
        PackageInfo pInfo = null;
        String version;
        try {
            pInfo = MyApplication.getAppContext().getPackageManager().
                    getPackageInfo(MyApplication.getAppContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            version = "NA";

        }
        version = pInfo.versionName;
        return version;
    }

    public static int getVersionCode() {
        PackageInfo pInfo = null;
        int version;
        try {
            pInfo = MyApplication.getAppContext().getPackageManager().
                    getPackageInfo(MyApplication.getAppContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            version = 0;
        }
        version = pInfo.versionCode;
        return version;
    }


    public static void openPlayStore(Activity activity) {
        final String appPackageName = activity.getPackageName(); // getPackageName() from Context or Activity object
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Constants.YOUTUBE_PACKAGE_NAME)));
        } catch (android.content.ActivityNotFoundException anfe) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.PLAYSTORE_MARKET_LINK + Constants.YOUTUBE_PACKAGE_NAME)));
        }
    }

    public static String getCurrentDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }
}
