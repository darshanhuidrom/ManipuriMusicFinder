package com.kangladevelopers.filmfinder.developers.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.pogo.CorrectionModel;
import com.kangladevelopers.filmfinder.pogo.SimpleResponse;
import com.kangladevelopers.filmfinder.retrofit.adapter.MusicRestAdapter;
import com.kangladevelopers.filmfinder.utils.StringUtility;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by BURNI on 7/23/2016.
 */
public class Correction extends AppCompatActivity {

    TextView tvFirstText,tvSecondText;
    EditText etCorrectName;
    Button submit;

    Toolbar toolbar;
    String dataType,firstName,secondName;
    final String typeChange="change",typeCorrection="correction";
    String type;
    int currentSelectionTv=0;//0 non selected 1 fist, 2 second...
    MusicRestAdapter restAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correction);
        dataType = getIntent().getStringExtra("dataType");
        firstName = StringUtility.removeSpaceFromFirst(getIntent().getStringExtra("firstName"));
        secondName = StringUtility.removeSpaceFromFirst(getIntent().getStringExtra("secondName"));
        String re=removeSpaceFromFirst(firstName);

        tvFirstText = (TextView) findViewById(R.id.tv_FirstText);
        tvSecondText = (TextView) findViewById(R.id.tv_SecondText);
        etCorrectName= (EditText) findViewById(R.id.et_correctName);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        tvFirstText.setText(firstName);
        tvSecondText.setText(secondName);
        submit = (Button) findViewById(R.id.btn_submit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(firstName.equals(secondName)){
            type=typeCorrection;
            tvSecondText.setVisibility(View.GONE);
        }else{
            type=typeChange;
            etCorrectName.setVisibility(View.GONE);
        }

        restAdapter = new MusicRestAdapter();
    }

    private String removeSpaceFromFirst(String name) {
        boolean flag=true;
        while (flag){
            if(name.charAt(0)==' '){
                name = name.substring(1);
                flag=true;
            }else {
                flag=false;
            }
        }
        return name;
    }


    public void  btnClick(View v){
        switch (v.getId()){
            case R.id.tv_FirstText:
                if(currentSelectionTv==0 || currentSelectionTv==2){
                    currentSelectionTv=1;
                    tvFirstText.setBackgroundColor(Color.parseColor("#acdcaf"));
                    tvSecondText.setBackgroundColor(Color.parseColor("#000000"));
                }else if(currentSelectionTv==1){
                    currentSelectionTv=0;
                    tvFirstText.setBackgroundColor(Color.parseColor("#000000"));
                }
                break;

            case R.id.tv_SecondText:
                if(currentSelectionTv==0 || currentSelectionTv==1){
                    currentSelectionTv=2;
                    tvSecondText.setBackgroundColor(Color.parseColor("#acdcaf"));
                    tvFirstText.setBackgroundColor(Color.parseColor("#000000"));
                }else if(currentSelectionTv==2) {
                    currentSelectionTv = 0;
                    tvSecondText.setBackgroundColor(Color.parseColor("#000000"));
                }
                break;
        }

    }

    public void onSubmit(View v){
        CorrectionModel correctionModel = new CorrectionModel();
        correctionModel.setAsAlice(false);
        correctionModel.setBy("apple");
        correctionModel.setDataType(dataType.toLowerCase());

        if(type.equals(typeCorrection)){
            String newName = etCorrectName.getText().toString();
            correctionModel.setOldData(firstName);
            correctionModel.setNewData(newName);
            sendCorrection(correctionModel);
        }else if(type.equals(typeChange)){

            if(currentSelectionTv==0){
                Toast.makeText(this,"Pliz select the correct one..",Toast.LENGTH_SHORT).show();
            }else if(currentSelectionTv ==1){
                correctionModel.setOldData(secondName);
                correctionModel.setNewData(firstName);
                sendCorrection(correctionModel);
            }else if(currentSelectionTv==2){
                correctionModel.setOldData(firstName);
                correctionModel.setNewData(secondName);
                sendCorrection(correctionModel);
            }

        }

    }

    private void sendCorrection(CorrectionModel cm){
        //PUT THE CODE FOR SENDING POST CALL

        retrofit2.Call<SimpleResponse> call =restAdapter.postCorrection(cm);
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(retrofit2.Call<SimpleResponse> call, Response<SimpleResponse> response) {

                SimpleResponse rere= response.body();
                Toast.makeText(Correction.this,rere.getMessage(),Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(retrofit2.Call<SimpleResponse> call, Throwable t) {

                String dd= t.getMessage();
                Toast.makeText(Correction.this,dd,Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
