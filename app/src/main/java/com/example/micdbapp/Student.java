package com.example.micdbapp;

public class Student
{
    private int roll;
    private String sname;
    private float average;
    private String grade;

    public Student(int roll,String sname,float average,String grade)
    {
        this.sname=sname;
        this.roll=roll;
        this.average=average;
        this.grade=grade;
    }
    public int getRoll() {
        return roll;
    }

    public String getSname() {
        return sname;
    }

    public float getAverage() {
        return average;
    }

    public String getGrade() {
        return grade;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAverage(float average) {
        this.average = average;
    }
}
