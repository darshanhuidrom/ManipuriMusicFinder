package com.kangladevelopers.filmfinder.Activity;

import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.ConnectionDetector;
import com.kangladevelopers.filmfinder.Utility.DialogBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class HelpDetailsActivity extends AppCompatActivity {
    private WebView webView;
    private ImageView iv_no_internet;
    private static final String FILE_DIR = "file:///android_asset/nointernet.html";
    private String url;
    private Toolbar toolbar;

    public enum Displayer {LOADER_VIEW, NO_INTERNET_VIEW, WEB_VIEW}

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url=getIntent().getStringExtra("url");
        setContentView(R.layout.activity_help_details);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        iv_no_internet = (ImageView) findViewById(R.id.iv_no_internet);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new MyBrowser());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                setTitle("Loading..." + newProgress);
                displayView(Displayer.LOADER_VIEW);
                if (newProgress == 100) {
                    setTitle(R.string.app_name);
                    displayView(Displayer.WEB_VIEW);
                }

            }


        });
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        if (ConnectionDetector.isNetworkAvailable(getApplicationContext())) {
            startLoading();

        } else {
            displayView(Displayer.NO_INTERNET_VIEW);
        }

    }


    public InputStream getFileFromAsset(String filenameWithextention) {
        AssetManager mgr = getBaseContext().getAssets();
        InputStream in = null;
        try {
            in = mgr.open(filenameWithextention, AssetManager.ACCESS_BUFFER);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;

    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {

        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {


        }


        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        }
    }


    private void startLoading() {
        webView.loadUrl(url);
        displayView(Displayer.LOADER_VIEW);
    }

    private void displayView(Displayer displayer) {
        switch (displayer) {
            case LOADER_VIEW:
                iv_no_internet.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                break;

            case NO_INTERNET_VIEW:
                iv_no_internet.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
                break;

            case WEB_VIEW:
                iv_no_internet.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                break;


        }
    }


    public static String StreamToString(InputStream in) {
        if (in == null) {
            return "";
        }
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return writer.toString();
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}