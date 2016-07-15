package  com.kangladevelopers.filmfinder.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.R;


/**
 * Created by DARSHAN HUIDROJM on 7/15/2016.
 */
public class AutocompleteCustomArrayAdapter extends ArrayAdapter<String> {


    final String TAG = "AutocompleteCustomArrayAdapter.java";

    Context mContext;
    int layoutResourceId;
    String[] data;
    String[] displayData;

    public AutocompleteCustomArrayAdapter(Context mContext, int layoutResourceId,String[] data,String[] displayData) {
        super(mContext, layoutResourceId, displayData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data =data ;
        this.displayData =displayData;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try {

            /*
             * The convertView argument is essentially a "ScrapView" as described is Lucas post
             * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
             * It will have a non-null value when ListView is asking you recycle the row layout.
             * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
             */
            if (convertView == null) {
                // inflate the layout

                convertView = LayoutInflater.from(mContext).inflate(layoutResourceId, parent, false);
            }

            // object item based on the position
            String objectItem = displayData[position];

            // get the TextView and then set the text (item name) and tag (item ID) values
            TextView textViewItem = (TextView) convertView.findViewById(R.id.textView1);
            textViewItem.setText(objectItem);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;

    }



    public String[] getdata() {
        return data;
    }


}