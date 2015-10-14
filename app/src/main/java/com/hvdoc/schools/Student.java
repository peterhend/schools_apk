package com.hvdoc.schools;

/**
 * Created by Pete on 10/9/2015.
 */
public class Student {
    private int mId;
    private String mName;

    private String mGrade;
    private String mAddress;
    private String mCity;
    private String mState;
    private String mZip;
    private String mPhone;

    public Student(int id, String name, String grade) {
        mId = id;
        mName = name;
        mGrade = grade;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getGrade() {
        return mGrade;
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
