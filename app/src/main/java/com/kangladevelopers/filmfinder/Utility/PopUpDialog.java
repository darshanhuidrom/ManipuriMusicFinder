package com.kangladevelopers.filmfinder.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.R;

import java.util.List;

/**
 * Created by HUIDROM on 7/23/2016.
 */
public abstract class PopUpDialog {
    private Activity activity;
    private String[] displayData;

    public PopUpDialog(Activity activity, String[] displayData) {
        this.activity = activity;
        this.displayData = displayData;
    }

    public void show() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);
        builderSingle.setTitle("Select:");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.addAll(displayData);
        builderSingle.setNegativeButton(
                "cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(
                arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        onItemClick(strName,which);
                    }
                });
        builderSingle.show();
    }

    abstract public void onItemClick(String s,int pos);
}
