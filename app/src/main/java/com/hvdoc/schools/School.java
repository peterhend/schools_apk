package com.hvdoc.schools;

/**
 * Created by Pete on 10/8/2015.
 */
public class School {
    private int mId;
    private String mName;
    private String mPrincipal;
    private String mAddress;
    private String mCity;
    private String mState;
    private String mZip;
    private String mPhone;

    public School() {

    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String mCity) {
        this.mCity = mCity;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getPrincipal() {
        return mPrincipal;
    }

    public void setPrincipal(String mPrincipal) {
        this.mPrincipal = mPrincipal;
    }

    public String getState() {
        return mState;
    }

    public void setState(String mState) {
        this.mState = mState;
    }

    public String getZip() {
        return mZip;
    }

    public void setZip(String mZip) {
        this.mZip = mZip;
    }

}
