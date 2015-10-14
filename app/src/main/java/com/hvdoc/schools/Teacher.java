package com.hvdoc.schools;

/**
 * Created by Pete on 10/9/2015.
 */
public class Teacher {
    private int mId;
    private String mName;
    private String mDepartment;
    private String mAddress;
    private String mCity;
    private String mState;
    private String mZip;
    private String mPhone;

    public Teacher(int id, String name, String department) {
        mId = id;
        mName = name;
        mDepartment = department;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDepartment() {
        return mDepartment;
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
