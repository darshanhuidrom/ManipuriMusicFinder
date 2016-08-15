package com.kangladevelopers.filmfinder.storage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.kangladevelopers.filmfinder.MyApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by HUIDROM on 7/22/2016.
 */
public class LocalStore {
    private static final String BASE_ROOT = "";
    private static final String IMAGE_NAME = "profile.jpg";
    private static final String FOLDER_NAME = "local_data";
    public static final String SINGER_LIST="singer_list.json";
    public static final String COMPOSER_LIST="composer_list.json";
    public static final String DIRECTOR_LIST="director_list.json";
    public static final String ACTOR_LIST="actor_list.json";
    public static final String WRITER_LIST="writer_list.json";

    private Context context;


    public static File getRootDirectory() {
        ContextWrapper cw = new ContextWrapper(MyApplication.getAppContext());
        File directory = cw.getDir(FOLDER_NAME, Context.MODE_PRIVATE);
        return directory;
    }

    public static  void saveToInternalStorage(Bitmap bitmapImage) {
        File mypath = new File(getRootDirectory(), IMAGE_NAME);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void loadImageFromStorage(ImageView imageView) {

        try {
            File f = new File(getRootDirectory(), IMAGE_NAME);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            imageView.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static  void writeToFile(String data,String fileNameWithExtension) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(MyApplication.getAppContext().openFileOutput(fileNameWithExtension, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static  String readToFile(String fileNameWithExtension) {
        int ch;
        StringBuffer fileContent = new StringBuffer("");
        FileInputStream fis;
        try {
            fis = MyApplication.getAppContext().openFileInput(fileNameWithExtension);
            try {
                while( (ch = fis.read()) != -1)
                    fileContent.append((char)ch);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String data = new String(fileContent);
        return data;
    }


    public static String AssetJSONFile(String filename, Context context) {
        AssetManager manager = context.getAssets();
        InputStream file;
        byte[] formArray = new byte[0];
        try {
            file = manager.open(filename);
            formArray = new byte[file.available()];
            file.read(formArray);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(formArray);
    }

    public static String test(String filename, Context context) {
        AssetManager manager = context.getAssets();
        InputStream file;
        byte[] formArray = new byte[0];
        try {
            file = manager.open(filename);
            formArray = new byte[file.available()];
            file.read(formArray);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(formArray);
    }

}
