package sample;

/*
    Author: Julian Helperstorfer
    Created on: 17.05.2021
    Changed on: 21.05.2021
    Changed from:
    Description: Class for the list that stores all the courses
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RateList {

    private static ArrayList<Rate> rateList = new ArrayList<>();    //the list in which the courses are stored
    private static ArrayList<Double> doubleArrayList = new ArrayList<>();

    //Sorts the rates from the lowest to the highest
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

    public static double getCourse(String asset_id_base, String asset_id_quote, Date date){  //method for finding a fitting course for a start- and a target currency
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
