package com.kangladevelopers.filmfinder.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.R;

import java.util.List;

/**
 * Created by HUIDROM on 8/16/2016.
 */
public class HelpAdapter extends SimpleBaseAdapter {
    public HelpAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.textview1,null);
        TextView txt= (TextView) convertView.findViewById(R.id.textView1);
        txt.setText(list.get(position));
        return convertView;
    }
}
