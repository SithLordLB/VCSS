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

public class CourseList {

    private static ArrayList<Course> courseList = new ArrayList<>();    //the list in which the courses are stored
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
        Course tempCourse = new Course(asset_id_base,asset_id_quote, 0, new Date());

        for (Course course : courseList) {    //search the list for a fitting course

            if (asset_id_base.equals(course.getAsset_id_base()) && asset_id_quote.equals(course.getAsset_id_quote()) && date.equals(course.getTime())) {
                tempCourse = course;          //set the fitting course as the temporary course
            }

        }

        if(tempCourse.getRate() != 0) {
            return tempCourse.getRate();  //return the fitting course or 0 if no fitting course was found
        } else{
            return 0;
        }
    }


    public static ArrayList<Course> getCourseList() {
        return courseList;
    }
}
