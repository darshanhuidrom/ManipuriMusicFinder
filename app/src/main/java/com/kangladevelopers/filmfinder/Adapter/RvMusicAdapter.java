package com.kangladevelopers.filmfinder.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.Activity.Player;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.pogo.Movie;
import com.kangladevelopers.filmfinder.pogo.Music;
import com.kangladevelopers.filmfinder.utils.Config;
import com.kangladevelopers.filmfinder.utils.StringUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by HUIDROM on 7/9/2016.
 */
public class RvMusicAdapter extends RecyclerView.Adapter<RvMusicAdapter.CustomViewHolder> {
    private RvAdapterClickListener rvAdapterClickListener;
    private List<Music> musicList;
    private Context context;

    public RvMusicAdapter(Context context,List<Music> musicList){
        this.musicList=musicList;
        this.context=context;
    }

    public void setNotifyChange(List<Music> musicList){
        this.musicList=musicList;
        notifyDataSetChanged();
    }
    public List<Music> getData() {
        return musicList;
    }
    @Override
    public RvMusicAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_block2, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RvMusicAdapter.CustomViewHolder holder, int position) {
        final Music music = musicList.get(position);
        holder.tvSinger.setText(music.getSingers());
        holder.tvComposer.setText(music.getComposer());
        holder.tvDirector.setText(music.getDirector());
        holder.tv_name.setText(music.getSongName());
        holder.tv_rating.setText(music.getQuality()+"");
        holder.tvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "onclick", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(context, Player.class);
                intent.putExtra("url",music.getUrl());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
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
        imageLoader.displayImage(Constants.YOUTUBE_IMAGE_URL+ StringUtility.extractYouTubeCode(music.getUrl())+"/0.jpg", holder.moveIcon, options);

        if(music.getType().equals("movie")){
            holder.tv_name.setTextColor(context.getResources().getColor(R.color.dirty_yellow));
        }
        else{
            holder.tv_name.setTextColor(context.getResources().getColor(R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvSinger;
        private TextView tvComposer;
        private LinearLayout tvPlay;
        private CardView cv;
        private ImageView moveIcon;
        private TextView tvDirector;
        private TextView tv_name;
        private TextView tv_rating;

        public CustomViewHolder(View view) {
            super(view);
            cv = (CardView) view.findViewById(R.id.cardView);
            moveIcon = (ImageView) view.findViewById(R.id.iv_moveIcon);
            tvSinger = (TextView) view.findViewById(R.id.tv_singerD);
            tvDirector = (TextView) view.findViewById(R.id.tv_directorD);
            tvComposer = (TextView) view.findViewById(R.id.tv_composerD);
            tvPlay = (LinearLayout) view.findViewById(R.id.tv_play);
            tv_name= (TextView) view.findViewById(R.id.tv_name);
            tv_rating= (TextView) view.findViewById(R.id.tv_rating);

            cv = (CardView) view;
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(rvAdapterClickListener!=null)
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
