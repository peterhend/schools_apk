package com.hvdoc.schools.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.hvdoc.schools.School;
import com.hvdoc.schools.database.SchoolDbSchema.SchoolTable;

/**
 * Created by Pete on 10/20/2015.
 */
public class SchoolCursorWrapper extends CursorWrapper {
    public SchoolCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public School getSchool() {
        String id = getString(getColumnIndex(SchoolTable.Cols.ID));
        String name = getString(getColumnIndex(SchoolTable.Cols.NAME));
        String principal = getString(getColumnIndex(SchoolTable.Cols.PRINCIPAL));
        String address = getString(getColumnIndex(SchoolTable.Cols.ADDRESS));
        String city = getString(getColumnIndex(SchoolTable.Cols.CITY));
        String state = getString(getColumnIndex(SchoolTable.Cols.STATE));
        String zip = getString(getColumnIndex(SchoolTable.Cols.ZIP));
        String phone = getString(getColumnIndex(SchoolTable.Cols.PHONE));

        School school = new School(Integer.parseInt(id));
        school.setName(name);
        school.setPrincipal(principal);
        school.setAddress(address);
        school.setCity(city);
        school.setState(state);
        school.setZip(zip);
        school.setPhone(phone);

        return school;
    }
}
