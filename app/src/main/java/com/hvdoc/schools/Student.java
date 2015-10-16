package com.hvdoc.schools;

/**
 * Created by Pete on 10/9/2015.
 */
public class Student {
    private int mId;
    private String mFirstName;
    private String mLastName;
    private String mGrade;
    private String mAddress;
    private String mCity;
    private String mState;
    private String mZip;
    private String mPhone;

    public Student(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String name) {
        mFirstName = name;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String name) {
        mLastName = name;
    }

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getZip() {
        return mZip;
    }

    public void setZip(String zip) {
        mZip = zip;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }
}
