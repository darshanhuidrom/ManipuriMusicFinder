package com.kangladevelopers.filmfinder.developers.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.pogo.CorrectionModel;

/**
 * Created by BURNI on 7/23/2016.
 */
public class Correction extends AppCompatActivity {

    TextView tvFirstText,tvSecondText;
    EditText etCorrectName;
    Button submit;

    String dataType,firstName,secondName;
    final String typeChange="change",typeCorrection="correction";
    String type;
    int currentSelectionTv=0;//0 non selected 1 fist, 2 second...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correction);
        dataType = getIntent().getStringExtra("dataType");
        firstName = getIntent().getStringExtra("firstName");
        secondName = getIntent().getStringExtra("secondName");

        tvFirstText = (TextView) findViewById(R.id.tv_FirstText);
        tvSecondText = (TextView) findViewById(R.id.tv_SecondText);
        etCorrectName= (EditText) findViewById(R.id.et_correctName);
        submit = (Button) findViewById(R.id.btn_submit);

        if(firstName.equals(secondName)){
            type=typeCorrection;
            tvSecondText.setVisibility(View.GONE);
        }else{
            type=typeChange;
            etCorrectName.setVisibility(View.GONE);
        }

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
        correctionModel.setDataType(dataType);

        if(type.equals(typeChange)){
            String newName = etCorrectName.getText().toString();
            correctionModel.setOldData(firstName);
            correctionModel.setNewData(newName);
            sendCorrection(correctionModel);
        }else if(type.equals(typeCorrection)){

            if(currentSelectionTv==0){
                Toast.makeText(this,"Pliz select the correct one..",Toast.LENGTH_SHORT);
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
    }


}
