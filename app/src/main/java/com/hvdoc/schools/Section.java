package com.hvdoc.schools;

/**
 * Created by Pete on 10/9/2015.
 */
public class Section {
    private int mId;
    private String mName;

    public Section(int id, String name) {
        mId = id;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

}
