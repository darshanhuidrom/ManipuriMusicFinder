package com.kangladevelopers.filmfinder.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.R;

/**
 * Created by DELL PC on 8/21/2016.
 */
public class TutorialActivity extends BaseActivity implements ViewPager.OnPageChangeListener{

    private static final String TAG = "TutorialActivity";
    ViewPager viewPager;
    PagerAdapter adapter;

    TextView tvSkip,tvNext;
    View[] tDots = new View[5];
    //View d1,d2,d3,d4,d5;

    int[] t_page = {
            R.drawable.z1_intro,
            R.drawable.z2_filter,
            R.drawable.z3_filter,
            R.drawable.z4_multiple,
            R.drawable.z5_sorting};

    int previousPagerIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);



        // Locate the ViewPager in viewpager_main.xml
        viewPager = (ViewPager) findViewById(R.id.pager);
        // Pass results to ViewPagerAdapter Class
        adapter = new CustomPagerAdapter(this);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(this);

        tvSkip = (TextView) findViewById(R.id.tv_skip);
        tvNext = (TextView) findViewById(R.id.tv_next);

        tDots[0] = findViewById(R.id.d1);
        tDots[1] = findViewById(R.id.d2);
        tDots[2] = findViewById(R.id.d3);
        tDots[3] = findViewById(R.id.d4);
        tDots[4] = findViewById(R.id.d5);

        tDots[0].setBackgroundResource(R.drawable.dote_dark);
    }


    public void tutorial_click(View v){
        switch(v.getId()){
            case R.id.tv_next:
                int currentIndex = viewPager.getCurrentItem();
                //Log.i(TAG,String.valueOf(currentIndex));
                if(currentIndex != t_page.length-1){
                    viewPager.setCurrentItem(currentIndex+1);
                }else{
                    goToNextAct();
                }

                break;

            case R.id.tv_skip:
                goToNextAct();
                break;
        }
    }

    private void goToNextAct(){
        Log.i(TAG,"Activity Finish");

    }


    //------------------------------

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
       // Log.w(TAG,"On PageScrolled: "+String.valueOf(position));
    }

    @Override
    public void onPageSelected(int position) {
        //Log.i(TAG,"On onPageSelected: "+String.valueOf(position));
        tDots[position].setBackgroundResource(R.drawable.dote_dark);
        if(position!=0){
            tDots[position - 1].setBackgroundResource(R.drawable.dote_light);
        }
        if(position != t_page.length-1){
            tDots[position + 1].setBackgroundResource(R.drawable.dote_light);
            tvNext.setText("NEXT");
        }else{
            tvNext.setText("FINISH");
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //Log.v(TAG,"On PageScrollState:  "+String.valueOf(state));
        if(state==1 && viewPager.getCurrentItem() == t_page.length-1){
            goToNextAct();
        }
    }

    //---------------------------------------------------------------------------------------------

    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return t_page.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           View itemView = mLayoutInflater.inflate(R.layout.tutorial_slide_block, container, false);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(t_page[position]);
            container.addView(itemView);
            //container.setBackgroundResource(t_page[position]);
            //tDots[position].setBackgroundResource(R.drawable.dote_dark);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
            //tDots[position].setBackgroundResource(R.drawable.dote_light);
        }


    }
}
