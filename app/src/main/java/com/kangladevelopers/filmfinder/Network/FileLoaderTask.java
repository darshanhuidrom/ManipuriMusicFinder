package com.kangladevelopers.filmfinder.Network;

import android.app.Activity;
import android.os.AsyncTask;

import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.Utility.ProgressBarConfig;
import com.kangladevelopers.filmfinder.storage.LocalStore;

import java.util.ArrayList;

// This class is to load files
public abstract class FileLoaderTask extends AsyncTask<Void, Void, String> {

    ArrayList<String> urls = new ArrayList<String>();
    ArrayList<String> fileNames = new ArrayList<String>();
    private Activity activity;
    private String url;
    private String fileName;


    public FileLoaderTask(Activity activity) {


        this.activity = activity;
        urls.add(Constants.SINGER_LIST_URL);
        urls.add(Constants.COMPOSER_LIST_URL);
        urls.add(Constants.DIRECTOR_LIST_URL);
        urls.add(Constants.ACTOR__LIST_URL);

        fileNames.add(LocalStore.SINGER_LIST);
        fileNames.add(LocalStore.COMPOSER_LIST);
        fileNames.add(LocalStore.DIRECTOR_LIST);
        fileNames.add(LocalStore.ACTOR_LIST);

    }

    public FileLoaderTask(Activity activity, String url,String fileName) {
        this.url = url;
        this.fileName=fileName;

    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(Void... params) {


        String data = null;
        if(url==null){
            for (int i = 0; i < urls.size(); i++) {
                 data = HTTP.sendGET(urls.get(i));
                if(data!=null)
                LocalStore.writeToFile(data, fileNames.get(i));
            }
        }
        else {
             data = HTTP.sendGET(url);
            if(data!=null)
            LocalStore.writeToFile(data, fileName);
        }
        if(data!=null&&!data.trim().isEmpty()){
            return "success";
        }
        else {
            return null;
        }

    }

    @Override
    protected void onPostExecute(String s) {
        postAction(s);
    }

    abstract public void postAction(String a);
}
