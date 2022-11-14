package com.example.micdbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    StudentGradeDB sgdb;
    Student s;
    EditText edttxtv_roll,edttxtv_sname,edttxtv_avg,edttxtv_grade;
    int roll;
    String sname,grade;
    float avg;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sgdb=new StudentGradeDB(getApplicationContext());

        edttxtv_roll=findViewById(R.id.edttxtv_roll);
        edttxtv_sname=findViewById(R.id.edttxt_name);
        edttxtv_avg=findViewById(R.id.edttxt_avg);
        edttxtv_grade=findViewById(R.id.edttxt_grade);
    }

    public void insertStudent(View v)
    {

       roll=Integer.parseInt(edttxtv_roll.getText().toString());
        sname=edttxtv_sname.getText().toString();
        avg=Float.parseFloat(edttxtv_avg.getText().toString());
       grade=edttxtv_grade.getText().toString();
        s=new Student(roll,sname,avg,grade);

        sgdb.addStudent(s);
        Toast.makeText(this, "Insertion Successful", Toast.LENGTH_SHORT).show();
    }
    public void delete_student(View v)
    {
        try {
            roll = Integer.parseInt(edttxtv_roll.getText().toString());
            sgdb.deleteStudent(roll);
            //Toast.makeText(this, "Deletion is successful", Toast.LENGTH_SHORT).show();
            Log.d("MICHAEL good", "delete_student: Successful");
        }
        catch (Exception ex)
        {
            Log.d("PROBLEM MICHAEL:", "delete_student: "+ex.getMessage());
        }

    }
    public void get_student(View v)
    {
        try {
            roll = Integer.parseInt(edttxtv_roll.getText().toString());
            String c= sgdb.getStudent(roll);
            Toast.makeText(this, c, Toast.LENGTH_SHORT).show();
            Log.d("MICHAEL good", "select_student: Successful"+c);
        }
        catch (Exception ex)
        {
            Log.d("PROBLEM MICHAEL:", "select_student: "+ex.getMessage());
        }

    }
    public void updateStudent(View v)
    {
        try {
                roll = Integer.parseInt(edttxtv_roll.getText().toString());
                sname = edttxtv_sname.getText().toString();
                avg = Float.parseFloat(edttxtv_avg.getText().toString());
                grade = edttxtv_grade.getText().toString();
                s = new Student(roll, sname, avg, grade);

                sgdb.updateStudent(s);
                Toast.makeText(this, "updation Successful", Toast.LENGTH_SHORT).show();
                Log.d("MICHAEL good", "update_student: Successful");
                }
        catch (Exception ex)
        {
            Log.d("MICHAEL problem", "update_student:"+ex.getMessage());
        }
    }
}