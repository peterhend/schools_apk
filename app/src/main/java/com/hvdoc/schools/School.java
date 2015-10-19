package com.hvdoc.schools;

import java.util.ArrayList;
import java.util.List;

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
    private List<Teacher> mTeachers = new ArrayList<Teacher>();
    private List<Student> mStudents = new ArrayList<Student>();
    private List<Section> mSections = new ArrayList<Section>();
    private int mListFragmentContainer;

    public School(int id) {
        mId = id;
    }

    public int getListFragmentContainer() {
        return mListFragmentContainer;
    }

    public void setListFragmentContainer(int listFragmentContainer) {
        this.mListFragmentContainer = listFragmentContainer;
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

    public List<Teacher> getTeachers() {
        return mTeachers;
    }

    public void addTeacher(Teacher teacher) {
        mTeachers.add(teacher);
    }

    public Teacher getTeacher(int id) {
        for (Teacher teacher : mTeachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }

    public Section getSection(int id) {
        for (Section section : mSections) {
            if (section.getId() == id) {
                return section;
            }
        }
        return null;
    }

    public Student getStudent(int id) {
        for (Student student : mStudents) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getStudents() {
        return mStudents;
    }

    public void addStudent(Student student) {
        mStudents.add(student);
    }

    public List<Section> getSections() { return mSections; }

    public void addSection(Section section) { mSections.add(section); }

}
