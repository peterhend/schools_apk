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
        mSchools = new ArrayList<>();
    }

    public List<School> getSchools() {
        return mSchools;
    }

    public void addSchool(School school) {
        mSchools.add(school);
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

    public void setName(String name) {
        mName = name;
    }

    public String getSuperintendent() {
        return mSuperintendent;
    }

    public void setSuperintendent(String superintendent) {
        mSuperintendent = superintendent;
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
