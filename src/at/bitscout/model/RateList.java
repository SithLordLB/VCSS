package at.bitscout.model;

import at.bitscout.model.CourseTimePeriod;
import at.bitscout.model.Rate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/** Class for the list that stores all the courses
 * @author Helperstorfer
 * @version 1.2
 */

public class RateList {

    private static ArrayList<Rate> rateList = new ArrayList<>();    //the list in which the courses are stored
    private static ArrayList<Double> doubleArrayList = new ArrayList<>();


    /** Method sorts rate list
     * @param list list of rates unsorted
     * @return returns sorted list
     */
    public static ArrayList<Double> sortByRate(List<CourseTimePeriod> list){
        //clears the double array list
        doubleArrayList = new ArrayList<>();
        for(CourseTimePeriod courseTimePeriod: list){
            doubleArrayList.add(Double.parseDouble(courseTimePeriod.getRate_open()));
        }
        Collections.sort(doubleArrayList);
        return doubleArrayList;
    }

    public void update() throws IOException {
        //Filling the list with courses
    }

    /** method for finding a fitting course for a start- and a target currency
     * @param asset_id_base id of asset
     * @param asset_id_quote quote of asset
     * @param date selected date
     * @return returns rate ate date
     */

    public static double getCourse(String asset_id_base, String asset_id_quote, Date date){
        Rate tempRate = new Rate(asset_id_base,asset_id_quote, 0, new Date());

        for (Rate rate : rateList) {    //search the list for a fitting course

            if (asset_id_base.equals(rate.getAsset_id_base()) && asset_id_quote.equals(rate.getAsset_id_quote()) && date.equals(rate.getTime())) {
                tempRate = rate;          //set the fitting course as the temporary course
            }

        }

        if(tempRate.getRate() != 0) {
            return tempRate.getRate();  //return the fitting course or 0 if no fitting course was found
        } else{
            return 0;
        }
    }


    public static ArrayList<Rate> getCourseList() {
        return rateList;
    }
}
