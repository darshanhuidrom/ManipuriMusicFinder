package com.kangladevelopers.filmfinder.Utility;

import android.content.Context;

/**
 * Created by HUIDROM on 7/17/2016.
 */
public class AppPreference {

    private static final String APP_PREFERENCE = "app_preference";
    public static final int MODE_BOOLEAN = 1;
    public static final int MODE_FLOAT = 2;
    public static final int MODE_INT = 3;
    public static final int MODE_STRING = 4;

    public static void saveToAppPreference(Context context, String key, String value) {
        context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE).edit().putString(key, value).commit();
    }

    public static void saveToAppPreference(Context context, String key, int value) {
        context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE).edit().putInt(key, value).commit();
    }

    public static void saveToAppPreference(Context context, String key, boolean value) {
        context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE).edit().putBoolean(key, value).commit();
    }

    public static void saveToAppPreference(Context context, String key, float value) {
        context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE).edit().putFloat(key, value).commit();
    }


    public static String getDataFromAppPreference(Context context, String key) {
        return context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE).getString(key, "");
    }

    public static Object getDataFromAppPreference(Context context, String key, int mode) {
        if (mode == MODE_BOOLEAN) {
            return context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE).getBoolean(key, false);
        } else if (mode == MODE_FLOAT) {
            return context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE).getFloat(key, 0);
        } else if (mode == MODE_INT) {
            return context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE).getInt(key, 0);
        }else if (mode == MODE_STRING) {
            return context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE).getString(key,"");
        }
        return null;
    }

    public static boolean isInstalledFirst(Context context) {
        return context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE).getBoolean(Constants.IS_INSTALLED_FIRST, true);
    }
    public static boolean isInstalledFirst2(Context context) {
        return context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE).getBoolean(Constants.IS_INSTALLED_FIRST2, true);
    }
}
