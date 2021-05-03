package sample;
/*
    Author: LB
    Created on: 26.04.2021
    Changed on: 26.04.2021
    Changed from:
    Description: Creates courses for the currencies
 */
import java.util.Date;

public class Course {
    private Currency currency1;
    private Currency currency2;
    private double course;
    private Date date;

    //Constructor
    public Course(Currency currency1, Currency currency2, double course, Date date) {
        this.currency1 = currency1;
        this.currency2 = currency2;
        this.course = course;
        this.date = date;
    }

    //Getter and Setter
    public Currency getCurrency1() {
        return currency1;
    }

    public void setCurrency1(Currency currency1) {
        this.currency1 = currency1;
    }

    public Currency getCurrency2() {
        return currency2;
    }

    public void setCurrency2(Currency currency2) {
        this.currency2 = currency2;
    }

    public double getCourse() {
        return course;
    }

    public void setCourse(double course) {
        this.course = course;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
