package com.hvdoc.schools;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pete on 10/8/2015.
 */
public class District {
    private static District sDistrict;

    private List<School> mSchools;

    public static District get(Context context) {
        if (sDistrict == null) {
            sDistrict = new District(context);
        }
        return sDistrict;
    }

    private District(Context context){
        mSchools = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            School school = new School();
            school.setName("Craigside" + i);
            school.setPrincipal("Principal" + i);
            school.setAddress("15 Craigside Drive");
            school.setCity("New York");
            school.setState("NY");
            school.setZip("10002");
            school.setPhone("(646) 784-9254");
            mSchools.add(school);
        }
    }

    public List<School> getSchools() {
        return mSchools;
    }

    public School getSchool(int id) {
        for (School school : mSchools) {
            if (school.getId() == id) {
                return school;
            }
        }
        return null;
    }
}
