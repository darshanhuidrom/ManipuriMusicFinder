package com.kangladevelopers.filmfinder.storage;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.kangladevelopers.filmfinder.MyApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by HUIDROM on 7/22/2016.
 */
public class LocalStore {
    private static final String BASE_ROOT = "";
    private static final String IMAGE_NAME = "profile.jpg";
    private static final String FOLDER_NAME = "imageDir";
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

}
