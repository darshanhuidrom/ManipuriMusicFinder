package com.kangladevelopers.filmfinder.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Html;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.MyApplication;
import com.kangladevelopers.filmfinder.Network.FileLoaderTask;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.AppPreference;
import com.kangladevelopers.filmfinder.Utility.ConnectionDetector;
import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.Utility.DialogBox;
import com.kangladevelopers.filmfinder.pogo.Music;
import com.kangladevelopers.filmfinder.pogo.VersionInfo;
import com.kangladevelopers.filmfinder.storage.LocalStore;
import com.kangladevelopers.filmfinder.utils.Utility;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DELL PC on 8/12/2016.
 */
public class SplashActivity extends BaseActivity {

    private static int SPLASH_TIME_OUT = 2500;

    TextView h1, h2;
    private String VERSION_NO ="version_no";
    private ArrayList<Music> musicList;

    String fcBlue = "<font color ='#0b8be4'>",
            fcRead = "<font color ='#ba3434'>",
            fcGreen = "<font color ='#00d777'>",
            fcYellow = "<font color ='#f4e60b'>",
            fcPurple = "<font color ='#f045e1'>",
            fcOrange = "<font color ='#f78b0f'>";

    String fcEnd = "</font>";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        h1 = (TextView) findViewById(R.id.tv_h1);
        h2 = (TextView) findViewById(R.id.tv_h2);
        setHeader();
        if(!ConnectionDetector.isConnected()){
            new DialogBox(this) {
                @Override
                public void onPositive(DialogInterface dialog) {
                    dialog.dismiss();
                    finish();
                    Intent intent=new Intent(Settings.ACTION_SETTINGS);
                    startActivity(intent);
/*
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
                    startActivity(intent);*/
                }

                @Override
                public void onNegative(DialogInterface dialog) {

                }
            }.setValues("OK","This App works only on Online Mode"); ;
            return;
        }
      /*  if(ConnectionDetector.isNetworkAvailable(getApplicationContext())){
            new DialogBox(this) {
                @Override
                public void onPositive(DialogInterface dialog) {
                    dialog.dismiss();
                    finish();
                }

                @Override
                public void onNegative(DialogInterface dialog) {

                }
            }.setValues("OK","This App works only on Online Mode"); ;
            return;
        }*/
        if (AppPreference.isInstalledFirst(getApplicationContext())) {
            FileLoaderTask fileLoaderTask = new FileLoaderTask(SplashActivity.this) {
                @Override
                public void postAction(String a) {
                    Call<List<Music>> call2 = MyApplication.getResAdapter().getFirstCall(Utility.getCurrentDate());
                    call2.enqueue(new Callback<List<Music>>() {
                        @Override
                        public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
                            musicList = (ArrayList<Music>) response.body();
                            startHomeActivity(0);
                        }

                        @Override
                        public void onFailure(Call<List<Music>> call, Throwable t) {
                            startHomeActivity(0);
                        }
                    });
                    AppPreference.saveToAppPreference(getApplicationContext(), Constants.IS_INSTALLED_FIRST, false);
                }
            };
            fileLoaderTask.execute();

        } else {

            checkForUpdates();
            /*new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, HomePage.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
            }, SPLASH_TIME_OUT);*/
        }

        // activity = this;
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }*/
       /* new Handler().postDelayed(new Runnable() {

            *//*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             *//*

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,HomePage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        }, SPLASH_TIME_OUT);*/

    }


    private void setHeader() {

/*        String hd1 = fcRead + "M" + fcEnd +
                fcOrange + "A" +fcEnd+
                fcYellow + "N" +fcEnd+
                fcPurple + "I" +fcEnd+
                fcOrange + "P" +fcEnd+
                fcGreen + "U" +fcEnd+
                fcRead + "R" +fcEnd+
                fcPurple + "I" +fcEnd;*/

        String hd1 = fcRead + "M" + fcEnd +
                fcOrange + "A" + fcEnd +
                fcGreen + "N" + fcEnd +
                fcPurple + "I" + fcEnd +
                fcOrange + "P" + fcEnd +
                fcPurple + "U" + fcEnd+
                fcYellow + "R" + fcEnd;

        String hd2 = fcRead + "M" + fcEnd +
                fcOrange + "A" + fcEnd +
                fcYellow + "M" + fcEnd +
                fcPurple + "I" + fcEnd;

        h1.setText(Html.fromHtml(hd1));
        h2.setText(Html.fromHtml(hd2));

        Typeface splashFont = Typeface.createFromAsset(getAssets(), "fonts/splash_font.ttf");
        h1.setTypeface(splashFont);
        h2.setTypeface(splashFont);
    }


    public void checkForUpdates(){
        Call<VersionInfo> call =MyApplication.getResAdapter().getVersionInfo();
        call.enqueue(new Callback<VersionInfo>() {
            @Override
            public void onResponse(Call<VersionInfo> call, Response<VersionInfo> response) {
                VersionInfo res = response.body();
                final int verCode = res.getCurrentAppVersionCode();
                int sysVerCode = Utility.getVersionCode();

                boolean actorChange = res.getDataInfo().getActorFileChange();
                boolean singerChange = res.getDataInfo().getSingerFileChange();
                boolean composerChange = res.getDataInfo().getComposerFileChange();
                boolean directorChange = res.getDataInfo().getDirectorFileChange();

                if (actorChange) {
                    new FileLoaderTask(SplashActivity.this, Constants.ACTOR__LIST_URL, LocalStore.ACTOR_LIST) {
                        @Override
                        public void postAction(String a) {

                        }
                    }.execute();
                }
                if (singerChange) {
                    new FileLoaderTask(SplashActivity.this, Constants.SINGER_LIST_URL, LocalStore.SINGER_LIST) {
                        @Override
                        public void postAction(String a) {

                        }
                    }.execute();
                }
                if (composerChange) {
                    new FileLoaderTask(SplashActivity.this, Constants.COMPOSER_LIST_URL, LocalStore.COMPOSER_LIST) {
                        @Override
                        public void postAction(String a) {

                        }
                    }.execute();
                }
                if (directorChange) {
                    new FileLoaderTask(SplashActivity.this, Constants.DIRECTOR_LIST_URL, LocalStore.DIRECTOR_LIST) {
                        @Override
                        public void postAction(String a) {

                        }
                    }.execute();
                }


                Call<List<Music>> call2 = MyApplication.getResAdapter().getFirstCall(Utility.getCurrentDate());
                call2.enqueue(new Callback<List<Music>>() {
                    @Override
                    public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
                        musicList = (ArrayList<Music>) response.body();
                        startHomeActivity(verCode);
                    }

                    @Override
                    public void onFailure(Call<List<Music>> call, Throwable t) {
                        startHomeActivity(verCode);
                    }
                });


            }

            @Override
            public void onFailure(Call<VersionInfo> call, Throwable t) {

                String t2 = t.getMessage();

                startHomeActivity(0);
            }
        });
    }

    private void startHomeActivity(final int verCode){
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, HomePage.class);
                i.putExtra(VERSION_NO, verCode);
                i.putExtra("musics", musicList);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        }, SPLASH_TIME_OUT);



    }

    private void setMobileDataEnabled(Context context, boolean enabled) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ConnectivityManager conman = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final Class conmanClass = Class.forName(conman.getClass().getName());
        final Field connectivityManagerField = conmanClass.getDeclaredField("mService");
        connectivityManagerField.setAccessible(true);
        final Object connectivityManager = connectivityManagerField.get(conman);
        final Class connectivityManagerClass =  Class.forName(connectivityManager.getClass().getName());
        final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
        setMobileDataEnabledMethod.setAccessible(true);
        setMobileDataEnabledMethod.invoke(connectivityManager, enabled);
    }
}
