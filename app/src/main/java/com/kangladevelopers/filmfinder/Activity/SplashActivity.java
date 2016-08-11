package com.kangladevelopers.filmfinder.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.R;

/**
 * Created by DELL PC on 8/12/2016.
 */
public class SplashActivity extends BaseActivity{

    private static int SPLASH_TIME_OUT = 2500;

    TextView h1,h2;

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

        // activity = this;
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }*/
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,HomePage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        }, SPLASH_TIME_OUT);

    }


    private void setHeader(){

/*        String hd1 = fcRead + "M" + fcEnd +
                fcOrange + "A" +fcEnd+
                fcYellow + "N" +fcEnd+
                fcPurple + "I" +fcEnd+
                fcOrange + "P" +fcEnd+
                fcGreen + "U" +fcEnd+
                fcRead + "R" +fcEnd+
                fcPurple + "I" +fcEnd;*/

        String hd1 = fcRead + "S" + fcEnd +
                fcOrange + "A" +fcEnd+
                fcYellow + "N" +fcEnd+
                fcPurple + "G" +fcEnd+
                fcOrange + "A" +fcEnd+
                fcPurple + "I" +fcEnd;

        String hd2 = fcRead + "M" + fcEnd +
                fcGreen + "U" +fcEnd+
                fcYellow + "S" +fcEnd+
                fcPurple + "I" +fcEnd+
                fcOrange + "C" +fcEnd;

        h1.setText(Html.fromHtml(hd1));
        h2.setText(Html.fromHtml(hd2));

        Typeface splashFont = Typeface.createFromAsset(getAssets(),"fonts/splash_font.ttf");
        h1.setTypeface(splashFont);
        h2.setTypeface(splashFont);
    }
}
