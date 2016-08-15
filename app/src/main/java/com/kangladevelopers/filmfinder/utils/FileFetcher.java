package com.kangladevelopers.filmfinder.utils;

import com.kangladevelopers.filmfinder.storage.LocalStore;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by HUIDROM on 8/15/2016.
 */
public class FileFetcher {
    private static  boolean isFromInternel =true;
    private static String NAME="name";
    private static String GENDER="gender";


    public static  String[] getSingerList(){
        if(isFromInternel){

            return getFromJSON(LocalStore.SINGER_LIST);

        }
        String data = StringUtility.readAsStringFromAsset("singers.txt");
        String[] array =data.split("\n");
        return array;
    }

    public static  String[] getDirectorList(){
        if(isFromInternel){

            return getFromJSON(LocalStore.DIRECTOR_LIST);

        }
        String data = StringUtility.readAsStringFromAsset("directors.txt");
        String[] array =data.split("\n");
        return array;
    }
    public static  String[] getActorList(){
        if(isFromInternel){

            return getFromJSON(LocalStore.ACTOR_LIST);

        }
        String data = StringUtility.readAsStringFromAsset("actors.txt");
        String[] array =data.split("\n");
        return array;
    }

    public static  String[] getComposer(){
        if(isFromInternel){

            return getFromJSON(LocalStore.COMPOSER_LIST);

        }
        String data = StringUtility.readAsStringFromAsset("composer.txt");
        String[] array =data.split("\n");
        return array;
    }




    public static String[] getFromJSON(String fileNameWithExtension){
        String data = LocalStore.readToFile(fileNameWithExtension);
        String[] strings =null;
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(data);
            strings = new String[jsonArray.length()];
            for (int i=0;i<jsonArray.length();i++){
                strings[i]=jsonArray.getJSONObject(i).getString(NAME)+":"+jsonArray.getJSONObject(i).getString(GENDER);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return strings;
    }
}
