package sample;

/*
    Author: Julian Helperstorfer
    Created on: 17.05.2021
    Changed on: 17.05.2021
    Changed from:
    Description: Class for the list that stores all the courses
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class CourseList {
    public void update() throws IOException {
        //Filling the list with courses
    }

    public static double getKurswert(Currency startCurrency, Currency targetCurrency, ArrayList<Course> list){  //method for finding a fitting course for a start- and a target currency
        Course tempCourse = new Course(startCurrency,targetCurrency, 0, new Date());    //temporary Course

        for (Course course : list) {    //search the list for a fitting course

            if (startCurrency.equals(course.getCurrency1()) && targetCurrency.equals(course.getCurrency2())) {
                tempCourse = course;    //set the fitting course as the temporary course
            }

        }

        if(tempCourse.getCourse() != 0) {
            return tempCourse.getCourse();  //return the fitting course or 0 if no fitting course was found
        } else{
            return 0;
        }
    }
}
