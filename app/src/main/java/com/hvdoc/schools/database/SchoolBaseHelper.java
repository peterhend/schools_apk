package com.hvdoc.schools.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hvdoc.schools.database.SchoolDbSchema.SchoolTable;
import com.hvdoc.schools.database.SchoolDbSchema.SectionTable;
import com.hvdoc.schools.database.SchoolDbSchema.StudentTable;
import com.hvdoc.schools.database.SchoolDbSchema.TeacherTable;

/**
 * Created by Pete on 10/19/2015.
 */
public class SchoolBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "schoolbase.db";

    public SchoolBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + SchoolTable.NAME + "(" +
                        " _id integer primary key autoincrement, " +
                        SchoolTable.Cols.ID + ", " +
                        SchoolTable.Cols.NAME + ", " +
                        SchoolTable.Cols.PRINCIPAL + ", " +
                        SchoolTable.Cols.ADDRESS + ", " +
                        SchoolTable.Cols.CITY + ", " +
                        SchoolTable.Cols.STATE + ", " +
                        SchoolTable.Cols.ZIP + ", " +
                        SchoolTable.Cols.PHONE + ")"
        );

        db.execSQL("create table " + TeacherTable.NAME + "(" +
                        " _id integer primary key autoincrement, " +
                        TeacherTable.Cols.ID + ", " +
                        TeacherTable.Cols.SCHOOL_ID + ", " +
                        TeacherTable.Cols.FIRST_NAME + ", " +
                        TeacherTable.Cols.LAST_NAME + ", " +
                        TeacherTable.Cols.DEPARTMENT + ", " +
                        TeacherTable.Cols.ADDRESS + ", " +
                        TeacherTable.Cols.CITY + ", " +
                        TeacherTable.Cols.STATE + ", " +
                        TeacherTable.Cols.ZIP + ", " +
                        TeacherTable.Cols.PHONE + ", " +
                        TeacherTable.Cols.EMAIL + ")"
        );

        db.execSQL("create table " + StudentTable.NAME + "(" +
                        " _id integer primary key autoincrement, " +
                        StudentTable.Cols.ID + ", " +
                        StudentTable.Cols.SCHOOL_ID + ", " +
                        StudentTable.Cols.FIRST_NAME + ", " +
                        StudentTable.Cols.LAST_NAME + ", " +
                        StudentTable.Cols.GRADE + ", " +
                        StudentTable.Cols.ADDRESS + ", " +
                        StudentTable.Cols.CITY + ", " +
                        StudentTable.Cols.STATE + ", " +
                        StudentTable.Cols.ZIP + ", " +
                        StudentTable.Cols.PHONE + ", " +
                        StudentTable.Cols.EMAIL + ")"
        );

        db.execSQL("create table " + SectionTable.NAME + "(" +
                        " _id integer primary key autoincrement, " +
                        SectionTable.Cols.ID + ", " +
                        SectionTable.Cols.SCHOOL_ID + ", " +
                        SectionTable.Cols.TEACHER_ID + ", " +
                        SectionTable.Cols.NAME + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
