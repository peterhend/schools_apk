package com.hvdoc.schools;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pete on 10/8/2015.
 */
public class District {
    private static District sDistrict;

    private String mName;
    private String mSuperintendent;
    private String mAddress;
    private String mCity;
    private String mState;
    private String mZip;
    private String mPhone;
    private List<School> mSchools;

    public static District get(Context context) {
        if (sDistrict == null) {
            sDistrict = new District(context);
        }
        return sDistrict;
    }

    private District(Context context){
        mName = "Craigside School District";
        mSuperintendent = "Superintendent";
        mAddress = "15 Craigside Drive";
        mCity = "New York";
        mState = "NY";
        mZip = "10002";
        mPhone = "(646) 784-9254";
        mSchools = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            School school = new School();
            school.setId(i);
            school.setName("Craigside " + i);
            school.setPrincipal("Principal " + i);
            school.setAddress("15 Craigside Drive");
            school.setCity("New York");
            school.setState("NY");
            school.setZip("10002");
            school.setPhone("(646) 784-9254");
            for (int j = 1; j <= 100; j++) {
                Teacher teacher = new Teacher(j, "Teacher " + j, "English");
                school.addTeacher(teacher);
            }
            for (int j = 1; j <= 100; j++) {
                Student student = new Student(j, "Student " + j, "6");
                school.addStudent(student);
            }
            for (int j = 1; j <= 100; j++) {
                Section section = new Section(j, "Section " + j);
                school.addSection(section);
            }
            mSchools.add(school);
        }
    }

    public List<School> getSchools() {
        return mSchools;
    }

    public School getSchool(int id) {
        for (School school : mSchools) {
            if (school.getId() == id) {
                return school;
            }
        }
        return null;
    }

    public String getName() {
        return mName;
    }

    public String getSuperintendent() {
        return mSuperintendent;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getCity() {
        return mCity;
    }

    public String getState() {
        return mState;
    }

    public String getZip() {
        return mZip;
    }

    public String getPhone() {
        return mPhone;
    }

}
