package sample;
/*
    Author: LB
    Created on: 26.04.2021
    Changed on: 26.04.2021
    Changed from:
    Description: Displays a diagram for a course
 */
public class Diagram {
    private Course course;
    private String name;

    //Constructor
    public Diagram(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    //Getter and Setter
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
