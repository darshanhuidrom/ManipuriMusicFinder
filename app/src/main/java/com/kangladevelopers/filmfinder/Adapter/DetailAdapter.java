package com.kangladevelopers.filmfinder.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by HUIDROM on 7/17/2016.
 */
public class DetailAdapter extends SimpleAdapter {
    public DetailAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
