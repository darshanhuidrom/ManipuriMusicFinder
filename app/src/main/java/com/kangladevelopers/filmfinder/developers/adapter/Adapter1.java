package com.kangladevelopers.filmfinder.developers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.Adapter.SimpleAdapter;
import com.kangladevelopers.filmfinder.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by HUIDROM on 7/17/2016.
 */
public class Adapter1 extends SimpleAdapter {
    HashMap<View,Boolean> CheckMap;
    ArrayList<HashMap<View,Boolean>> checkList;
    public Adapter1(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.textview3,null);
        TextView txt= (TextView) convertView.findViewById(R.id.textView1);
        txt.setText(list.get(position));
        return convertView;
    }
}
