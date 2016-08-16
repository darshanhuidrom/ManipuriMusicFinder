package com.kangladevelopers.filmfinder.Utility;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.R;

/**
 * Created by DELL PC on 8/16/2016.
 */
public class SortStatus {

    public static final String TAG = "SortStatus";

    public static final int SORT_NO_NO = 0;
    public static final int SORT_BY_TITLE = 1;
    public static final int SORT_BY_DATE = 2;
    public static final int SORT_BY_RATING = 3;

    public static final int ORDER_ASCENDING = 1;
    public static final int ORDER_DESCENDING =2;

    private int selectedSortType = 0;  //IT MEANS NOTHING IS SELECTED
    public int orderStatus = 0;

    ImageView displaySortView;

    public void reset(){
        this.selectedSortType = 0;
        this.orderStatus = 0;
        if(displaySortView!=null){
            displaySortView.setVisibility(View.INVISIBLE);
        }
    }

    public void setSortType(int sortType){

        selectedSortType = sortType;
        orderStatus = ORDER_ASCENDING;
    }

    public void setSortType(int sortType,ImageView incomingSortIcon){
        selectedSortType = sortType;
        orderStatus = ORDER_ASCENDING;
        displaySortView = incomingSortIcon;
        displaySortView.setVisibility(View.VISIBLE);
        displaySortView.setImageResource(R.drawable.ts_1);
    }

    public void reverseOrder(){

    }

    public boolean isItSorted(){
        if(selectedSortType==0){
            return true;
        }
        return false;
    }

    public int getSelectedSortType(){
        return this.selectedSortType;
    }

    //------------

    public boolean getSETsortType(int sortType,ImageView incomingSortIcon){

        //if true is return the list will sort in ascending order else decending order
        if(selectedSortType == SORT_NO_NO){ //CASE 1 :if nothing is selected before
            Log.i(TAG,"nothing selected before");
            selectedSortType = sortType;
            orderStatus = ORDER_ASCENDING;
            displaySortView = incomingSortIcon;
            displaySortView.setVisibility(View.VISIBLE);
            displaySortView.setImageResource(R.drawable.arrow_down);

            return true;
        }else if (selectedSortType != sortType){  //CASE 2: something has been selected
            Log.i(TAG,"something selected before");
            displaySortView.setVisibility(View.INVISIBLE);

            selectedSortType = sortType;
            orderStatus = ORDER_ASCENDING;
            displaySortView = incomingSortIcon;
            displaySortView.setVisibility(View.VISIBLE);
            displaySortView.setImageResource(R.drawable.arrow_down);
            return true;
        }else if(selectedSortType == sortType){  //CASE 3: it has been selected before

            if(orderStatus == ORDER_ASCENDING){
                Log.i(TAG,"it has been selected before 1");
                displaySortView.setImageResource(R.drawable.arrow_up);
                orderStatus = SortStatus.ORDER_DESCENDING;
                return false;
            }else{
                Log.i(TAG,"it has been selected before 2");
                displaySortView.setImageResource(R.drawable.arrow_down);
                orderStatus = SortStatus.ORDER_ASCENDING;
                return true;
            }


        }else{
            Log.i("BURN","IT SHOULD NOT HAPPEN");
            return true;  // NO MORE CONDITION but to avoid syntex error..

        }
    }


}
