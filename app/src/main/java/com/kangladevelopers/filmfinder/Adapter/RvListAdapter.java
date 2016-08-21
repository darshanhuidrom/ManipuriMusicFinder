package com.kangladevelopers.filmfinder.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.utils.StringUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by HUIDROM on 8/12/2016.
 */
public class RvListAdapter extends RecyclerView.Adapter<RvListAdapter.CustomViewHolder> {

    private RvAdapterClickListener rvAdapterClickListener;
    private Context context;
    private List<String> items;

    public RvListAdapter(Context context, List<String> items,RvAdapterClickListener listener) {
        this.context = context;
        this.items = items;
        this.rvAdapterClickListener=listener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        String name = items.get(position);
        String name1="";

        if(name.contains(":")){
            int pos= name.indexOf(":");

            name1= name.substring(0, pos);
            holder.name.setText(name1);
            if(name.substring(pos+1).equalsIgnoreCase("B")){
                holder.llParent.setBackgroundColor(Color.GREEN);
            }
            else {
                holder.llParent.setBackgroundColor(Color.WHITE);
            }
        }
        else {
            holder.name.setText(name);
        }

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.a3)
                .showImageForEmptyUri(R.drawable.a3)
                .showImageOnFail(R.drawable.a3)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        name=name.replaceAll(" ","");
        imageLoader.displayImage(Constants.PERSON_ICON_PIC_URL + StringUtility.removeSpaceFromFirst(name1)+ Constants.IMAGE_FORMAT, holder.iv, options);

        Log.d(">>>>>> Image Url",Constants.PERSON_ICON_PIC_URL + StringUtility.removeSpaceFromFirst(name1) + Constants.IMAGE_FORMAT);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public String getDataAt(int position){
        return  items.get(position);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView name;
        private ImageView iv;
        private CardView cv;
        private LinearLayout llParent;

        public CustomViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_name);
            iv = (ImageView) view.findViewById(R.id.iv_list);
            cv = (CardView) view;
            llParent= (LinearLayout) view.findViewById(R.id.ll_parent);
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (rvAdapterClickListener != null)
                rvAdapterClickListener.onItemClick(getAdapterPosition(), v);
        }
    }


    public interface RvAdapterClickListener {
        void onItemClick(int i, View v);
    }

    public void setRvAdapterClickLIstener(RvAdapterClickListener rvAdapterClickListener) {
        this.rvAdapterClickListener = rvAdapterClickListener;
    }

}
