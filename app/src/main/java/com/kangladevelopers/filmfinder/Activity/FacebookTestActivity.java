package com.kangladevelopers.filmfinder.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.internal.Utility;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kangladevelopers.filmfinder.R;

import java.util.Collections;
import java.util.List;

public class FacebookTestActivity extends AppCompatActivity {

    private LoginButton loginButton;
    CallbackManager callbackManager;

    // https://www.numetriclabz.com/get-facebook-friends-list-in-android-list-view/


    // https://graph.facebook.com/POST_ID/likes?summary=true&access_token=XXXXXXXXXXXX
    //https://developers.facebook.com/docs/graph-api/reference/v2.7/user/feed
    //https://developers.facebook.com/docs/facebook-login/android#prerequisites
    // https://graph.facebook.com/225639331164520_203950056666781/likes?summary=true&access_token=EAAZAryXrIaXsBAAstJo37MLOMApmRAAnhAyZCFZBEkGw2HJ1Om2ZBSfKkZBZBr9huWcwJHxS77GmgU5V4PXLZBVTZBbHN7udZC9MOX4IxdsG8KKbZCRfr8DEwhTxQyXXegkhEkhUm2NtQN7ZCGBzq6KWbkquXiqQv3zoqXsuLohityBheDl0Q3ugcuNZBBHMcQjFbBcd50nt0Ssu9goIts2zqT5PyKhmd9LgMNhRnKirCMEXNgZDZD
    // https://developers.facebook.com/docs/facebook-login/permissions/v2.4
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_facebook_test);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // If using in a fragment
        //  loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration


        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                LoginResult l = loginResult;

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
        Intent ddd=data;
    }


    public void userPost(View view){
        Bundle bundle=new Bundle();
        bundle.putBoolean("summary",true);
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/225639331164520_203950056666781/likes",
                bundle,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
            /* handle the result */
                        GraphResponse responsess=response;

                    }
                }
        ).executeAsync();
    }

    public void trackProfile(View view){
        ProfileTracker profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Profile oldProfile1=oldProfile;
                Profile currentProfile2=currentProfile;
            }
        };
        Profile profile= Profile.getCurrentProfile();
         profile= Profile.getCurrentProfile();
        AccessToken accessToken =AccessToken.getCurrentAccessToken();
        String token =accessToken.getToken();


    }



    public void getFeed(View view){
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/feed",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
            /* handle the result */
                        GraphResponse gs =response;
                    }
                }
        ).executeAsync();
    }

    public void getFriends(View view){
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/taggable_friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
            /* handle the result */
                        GraphResponse gs =response;
                    }
                }
        ).executeAsync();
    }
}
