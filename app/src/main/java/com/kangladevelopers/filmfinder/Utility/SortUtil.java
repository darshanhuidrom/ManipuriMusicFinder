package com.kangladevelopers.filmfinder.Utility;

import com.kangladevelopers.filmfinder.pogo.Music;
import com.kangladevelopers.filmfinder.utils.StringUtility;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by HUIDROM on 7/31/2016.
 */
public class SortUtil {


    static public class CustomComparator implements Comparator<Music> {// may be it would be Model

        public static final int SORT_BY_TITLE = 1;
        public static final int SORT_BY_DATE = 2;
        public static final int SORT_BY_RATING = 3;
        private int currentSort = 0;
        private boolean ascending=true;

        public CustomComparator(int sortBy) {
            currentSort = sortBy;
        }

        public CustomComparator(int sortBy,boolean ascending) {
            currentSort = sortBy;
            this.ascending = ascending;
        }



        @Override
        public int compare(Music obj1, Music obj2) {
            int status = 0;

            switch (currentSort) {
                case SORT_BY_TITLE:
                    if(ascending==true){
                        status = obj1.getSongName().trim().compareTo(obj2.getSongName().trim());
                    }else{
                        status = obj2.getSongName().trim().compareTo(obj1.getSongName().trim());
                    }

                    break;
                case SORT_BY_DATE:
                    Date date2= StringUtility.convertStringToDate(obj2.getUploadDate());
                    Date date1= StringUtility.convertStringToDate(obj1.getUploadDate());
                    if(ascending==true){
                        status = date2.compareTo(date1);
                    }else{
                        status = date1.compareTo(date2);
                    }

                    //  status = obj1.getUploadDate().compareTo(obj2.getUploadDate());
                    break;
                case SORT_BY_RATING:
                    if(ascending==true){
                        status = obj2.getQuality().compareTo(obj1.getQuality());
                    }else{
                        status = obj1.getQuality().compareTo(obj2.getQuality());
                    }

                   // status = String.valueOf(obj2.getQuality()).compareTo(String.valueOf(obj1.getQuality()));
                    break;
                default:
            }
            return status;

        }
    }


}