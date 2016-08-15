package com.kangladevelopers.filmfinder.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.kangladevelopers.filmfinder.MyApplication;

/**
 * Created by HUIDROM on 8/15/2016.
 */
public class ConnectionDetector {

    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) MyApplication.getAppContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }
}