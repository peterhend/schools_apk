package com.hvdoc.schools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hvdoc.schools.database.SchoolBaseHelper;
import com.hvdoc.schools.database.SchoolCursorWrapper;
import com.hvdoc.schools.database.SchoolDbSchema.SchoolTable;

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

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static District get(Context context) {
        if (sDistrict == null) {
            sDistrict = new District(context);
        }
        return sDistrict;
    }

    private District(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new SchoolBaseHelper(mContext).getWritableDatabase();

        mSchools = new ArrayList<>();
    }

    public List<School> getSchools() {
        List<School> schoolsFromDB = new ArrayList<>();
        SchoolCursorWrapper cursor = querySchools(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                schoolsFromDB.add(cursor.getSchool());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return mSchools;
    }

    public void addSchool(School school) {
        mSchools.add(school);

        ContentValues values = getContentValues(school);
        long returnVal = mDatabase.insert(SchoolTable.NAME, null, values);
    }

    public School getSchool(int id) {
        for (School school : mSchools) {
            if (school.getId() == id) {
                return school;
            }
        }
        return null;
    }

    private static ContentValues getContentValues(School school) {
        ContentValues values = new ContentValues();
        values.put(SchoolTable.Cols.ID, school.getId());
        values.put(SchoolTable.Cols.NAME, school.getName());
        values.put(SchoolTable.Cols.PRINCIPAL, school.getPrincipal());
        values.put(SchoolTable.Cols.ADDRESS, school.getAddress());
        values.put(SchoolTable.Cols.CITY, school.getCity());
        values.put(SchoolTable.Cols.STATE, school.getState());
        values.put(SchoolTable.Cols.ZIP, school.getZip());
        values.put(SchoolTable.Cols.PHONE, school.getPhone());

        return values;
    }

    private SchoolCursorWrapper querySchools(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                SchoolTable.NAME,
                null, //null selects all columns
                whereClause,
                whereArgs,
                null, null, null
        );
        return new SchoolCursorWrapper(cursor);
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
