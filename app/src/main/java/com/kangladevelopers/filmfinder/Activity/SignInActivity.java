package com.kangladevelopers.filmfinder.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.*;
import com.facebook.internal.Utility;
import com.facebook.login.DefaultAudience;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.AppPreference;
import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.storage.LocalStore;
import com.kangladevelopers.filmfinder.utils.StringUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SignInActivity extends BaseActivity implements View.OnClickListener{

    private LoginButton loginButton;
    CallbackManager callbackManager;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_sign_in1);
        loginButton= (LoginButton) findViewById(R.id.login_button);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign In");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loginButton.setReadPermissions("email");
        loginButton.setOnClickListener(this);

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                LoginResult l = loginResult;

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());

                                // Application code
                                try {
                                    String userName = object.getString("name");
                                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                    StrictMode.setThreadPolicy(policy);
                                    String profilePicUrl = object.getJSONObject("picture").getJSONObject("data").getString("url");
                                    URL fb_url = new URL(profilePicUrl);//small | noraml | large
                                    HttpsURLConnection conn1 = (HttpsURLConnection) fb_url.openConnection();
                                    HttpsURLConnection.setFollowRedirects(true);
                                    conn1.setInstanceFollowRedirects(true);
                                    Bitmap fb_img = BitmapFactory.decodeStream(conn1.getInputStream());
                                    LocalStore.saveToInternalStorage(fb_img);
                                    AppPreference.saveToAppPreference(getApplicationContext(), Constants.USER_NAME, userName);
                                    AppPreference.saveToAppPreference(getApplicationContext(),
                                            Constants.IS_SIGNED_IN, true);

                                    setResult(RESULT_OK, null);
                                    finish();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday,picture.width(150).height(150)");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }


            @Override
            public void onError(FacebookException error) {

            }

        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);



    }






    public void trackProfile(){
        ProfileTracker profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(com.facebook.Profile oldProfile, com.facebook.Profile currentProfile) {
                com.facebook.Profile oldProfile1=oldProfile;
                com.facebook.Profile currentProfile2=currentProfile;

            }
        };
        com.facebook.Profile profile= com.facebook.Profile.getCurrentProfile();
        profile= com.facebook.Profile.getCurrentProfile();

        AccessToken accessToken =AccessToken.getCurrentAccessToken();
        String token =accessToken.getToken();


    }

    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String name= loginButton.getText().toString();
        if(name.equals("Log in with Facebook")){
            AppPreference.saveToAppPreference(getApplicationContext(),
                    Constants.IS_SIGNED_IN, false);
            AppPreference.saveToAppPreference(getApplicationContext(), Constants.USER_NAME, "");
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ts_2);
            LocalStore.saveToInternalStorage(bitmap);


        }

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

    @Override
    public void onClick(View v) {
        String name= loginButton.getText().toString();
        if(name.equals("Log in with Facebook")){
            AppPreference.saveToAppPreference(getApplicationContext(),
                    Constants.IS_SIGNED_IN, false);
            AppPreference.saveToAppPreference(getApplicationContext(), Constants.USER_NAME, "");
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ts_2);
            LocalStore.saveToInternalStorage(bitmap);


        }
    }
}
