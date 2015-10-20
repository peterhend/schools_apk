package com.hvdoc.schools.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.hvdoc.schools.School;
import com.hvdoc.schools.Teacher;
import com.hvdoc.schools.database.SchoolDbSchema.SchoolTable;
import com.hvdoc.schools.database.SchoolDbSchema.TeacherTable;

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
    public Teacher getTeacher() {
        String id = getString(getColumnIndex(TeacherTable.Cols.ID));
        String school_id = getString(getColumnIndex(TeacherTable.Cols.SCHOOL_ID));
        String first_name = getString(getColumnIndex(TeacherTable.Cols.FIRST_NAME));
        String last_name = getString(getColumnIndex(TeacherTable.Cols.LAST_NAME));
        String department = getString(getColumnIndex(TeacherTable.Cols.DEPARTMENT));
        String address = getString(getColumnIndex(TeacherTable.Cols.ADDRESS));
        String city = getString(getColumnIndex(TeacherTable.Cols.CITY));
        String state = getString(getColumnIndex(TeacherTable.Cols.STATE));
        String zip = getString(getColumnIndex(TeacherTable.Cols.ZIP));
        String phone = getString(getColumnIndex(TeacherTable.Cols.PHONE));
        String email = getString(getColumnIndex(TeacherTable.Cols.EMAIL));

        Teacher teacher = new Teacher(Integer.parseInt(id));
        teacher.setSchoolId(Integer.parseInt(school_id));
        teacher.setFirstName(first_name);
        teacher.setLastName(last_name);
        teacher.setDepartment(department);
        teacher.setAddress(address);
        teacher.setCity(city);
        teacher.setState(state);
        teacher.setZip(zip);
        teacher.setPhone(phone);
        teacher.setEmail(email);

        return teacher;
    }
}
