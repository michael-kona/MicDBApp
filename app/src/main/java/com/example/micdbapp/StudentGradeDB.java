package com.example.micdbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.micdbapp.Student;

import java.util.List;

public class StudentGradeDB extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "students";
    private static final String TABLE_NAME = "StudentGrade";
    private static final String KEY_ID = "Roll";
    private static final String KEY_NAME = "sname";
    private static final String KEY_AVG = "average";
    private static final String KEY_GRADE = "grade";

    public StudentGradeDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_StudentS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_AVG + " FLOAT,"
                + KEY_GRADE + " TEXT" + ")";
        db.execSQL(CREATE_StudentS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    // code to add the new Student
    void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getSname()); // Student Name
        values.put(KEY_ID, student.getRoll()); // Student Roll
        values.put(KEY_GRADE,student.getGrade());
        values.put(KEY_AVG,student.getAverage());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    String getStudent(int roll)
    {
        SQLiteDatabase db = this.getReadableDatabase();
         Cursor c=db.query("StudentGrade",new String[]{"Roll","sname","average","grade"},"Roll=?",new String[]{Integer.toString(roll)},null,null,null);
         if(c.moveToFirst()) {
             int r = c.getInt(0);
             String sname = c.getString(1);
             float avg = c.getFloat(2);
             String grade = c.getString(3);
             String print = "Roll=" + r + "\nName=" + sname + "\nAverage=" + avg + "\nGrade=" + grade;
             return print;
         }
         else
         {
             return "No record available";
         }

    }
    void deleteStudent(int roll)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.rawQuery("delete from StudentGrade where roll="+roll,null);
        db.delete("StudentGrade","Roll=?",new String[]{Integer.toString(roll)});
        db.close();
    }
    void updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getSname()); // Student Name
        values.put(KEY_ID, student.getRoll()); // Student Roll
        values.put(KEY_GRADE,student.getGrade());
        values.put(KEY_AVG,student.getAverage());

        // Inserting Row
        db.update(TABLE_NAME,values,"Roll=?",new String[]{Integer.toString(student.getRoll())});
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
}
