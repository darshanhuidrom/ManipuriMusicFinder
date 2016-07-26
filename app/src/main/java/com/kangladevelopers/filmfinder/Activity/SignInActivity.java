package com.kangladevelopers.filmfinder.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.AppPreference;
import com.kangladevelopers.filmfinder.Utility.Constants;

public class SignInActivity extends BaseActivity {

    private EditText etEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        etEmail = (EditText) findViewById(R.id.et_email);
    }

    public void onSave(View view){
        if(etEmail.getText().toString().trim().isEmpty()){
            finish();
            return;
        }
        AppPreference.saveToAppPreference(getApplicationContext(), Constants.USER_NAME,etEmail.getText()+"");
        AppPreference.saveToAppPreference(getApplicationContext(),Constants.IS_SIGNED_IN,true);
        setResult(RESULT_OK);
        finish();
    }
}
