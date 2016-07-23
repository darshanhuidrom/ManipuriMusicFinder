package com.kangladevelopers.filmfinder.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by HUIDROM on 7/15/2016.
 */
public class SimpleAdapter extends BaseAdapter {

    protected Context context;
   protected  List<String> list;
    public HashMap<String,String> viewMap=new HashMap<>();
    public SimpleAdapter(Context context,List<String> list){
        this.context=context;
        this.list=list;
        for (int i=0; i<list.size();i++){
            viewMap.put(list.get(i),"#2e2d2d");
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.textview1,null);
        TextView txt= (TextView) convertView.findViewById(R.id.textView1);
        txt.setText(list.get(position));
        convertView.setBackgroundColor(Color.parseColor(viewMap.get(list.get(position))));
        return convertView;
    }

    private String getOnlyName(String s){
        int pos = s.indexOf(":");
        return s.substring(0,pos);
    }
    public List<String> getData(){
        return list;
    }
}
