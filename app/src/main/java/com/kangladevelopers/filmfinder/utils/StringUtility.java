package com.kangladevelopers.filmfinder.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.kangladevelopers.filmfinder.Activity.BaseActivity;
import com.kangladevelopers.filmfinder.MyApplication;
import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.Utility.DateFormatString;
import com.kangladevelopers.filmfinder.storage.LocalStore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by HUIDROM on 3/8/2016.
 */
public class StringUtility {


    public static String readAsStringFromAsset(String fileName) {
        StringBuilder buf = new StringBuilder();
        InputStream is = null;
        String str = null;
        String data = null;
        try {
            is = MyApplication.getAppContext().getAssets().open(fileName);
            BufferedReader in = null;
            in = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            while ((str = in.readLine()) != null) {
                buf.append(str).append("\n");
            }
            in.close();
            data = buf.toString();
        } catch (Exception e) {

        }
        return data;
    }


    public static String extractYouTubeCode(String url) {
        int pos = url.indexOf("=");
        String code = url.substring(pos + 1);
        return code;
    }

    public static String getOnlyName(String data) {
        int pos = data.indexOf(":");
        return data.substring(0, pos);
    }

    public static boolean isMale(String s) {
        int pos = s.indexOf(":");
        if (s.substring(pos + 1, pos + 2).equals("M")) {
            return true;
        }
        return false;

    }


    public static String removeSpaceFromFirst(String name) {
        boolean flag = true;
        while (flag) {
            if (name.charAt(0) == ' ') {
                name = name.substring(1);
                flag = true;
            } else {
                flag = false;
            }
        }
        return name;
    }

    public static String removeSpaceFromEnd(String s) {

        boolean b = true;
        while (b) {
            if (s.charAt(s.length()) == ' ') {
                s = s.substring(0, s.length() - 1);
                b = true;
            } else {
                b = false;
            }
        }
        return s;
    }


    public static String removeSpaceFromFirstAndEnd(String name) {
        return removeSpaceFromEnd(removeSpaceFromFirst(name));
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    public static String parseDateByFormatToString(Date date, String stringFormat) {
        SimpleDateFormat format = new SimpleDateFormat(stringFormat);
        String dateString = format.format(date);
        return dateString;
    }

    public static String parseDateByFormatToString(Date date) {
        return parseDateByFormatToString(date, DateFormatString.COMMON_FORMAT);
    }

    public static Date convertStringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat(DateFormatString.COMMON_FORMAT);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date convertStringToDate(String dateString, String stringFormat) {
        SimpleDateFormat format = new SimpleDateFormat(stringFormat);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static String getAllValuesAsString(List<String> list){
        String dataInString ="";
        for (int i=0;i<list.size();i++){
            dataInString+=list.get(i);
            if(i<list.size()-1)
                dataInString+=",";
        }
        return dataInString;
    }


}
