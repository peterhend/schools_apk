package com.hvdoc.schools;

/**
 * Created by Pete on 10/9/2015.
 */
public class Section {
    private int mId;
    private int mSchoolId;
    private int mTeacherId;
    private String mName;

    public Section(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public int getSchoolId() {
        return mSchoolId;
    }

    public void setSchoolId(int schoolId) {
        mSchoolId = schoolId;
    }

    public int getTeacherId() {
        return mTeacherId;
    }

    public void setTeacherId(int teacherId) {
        mTeacherId = teacherId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
