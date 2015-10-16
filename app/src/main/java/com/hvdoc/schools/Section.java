package com.hvdoc.schools;

/**
 * Created by Pete on 10/9/2015.
 */
public class Section {
    private int mId;
    private String mName;

    public Section(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
