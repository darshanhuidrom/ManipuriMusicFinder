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

        public CustomComparator(int sortBy) {
            currentSort = sortBy;

        }

        @Override
        public int compare(Music obj1, Music obj2) {
            int status = 0;

            switch (currentSort) {
                case SORT_BY_TITLE:
                    status = obj1.getSongName().trim().compareTo(obj2.getSongName().trim());
                    break;
                case SORT_BY_DATE:
                    Date date2= StringUtility.convertStringToDate(obj2.getUploadDate());
                    Date date1= StringUtility.convertStringToDate(obj1.getUploadDate());
                    status = date2.compareTo(date1);
                    //  status = obj1.getUploadDate().compareTo(obj2.getUploadDate());
                    break;
                case SORT_BY_RATING:
                    status = obj2.getQuality().compareTo(obj1.getQuality());
                   // status = String.valueOf(obj2.getQuality()).compareTo(String.valueOf(obj1.getQuality()));
                    break;
                default:
            }
            return status;

        }
    }


}