package com.hvdoc.schools.database;

/**
 * Created by Pete on 10/19/2015.
 */
public class SchoolDbSchema {
    public static final class SchoolTable {
        public static final String NAME = "schools";

        public static final class Cols {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String PRINCIPAL = "principal";
            public static final String ADDRESS = "address";
            public static final String CITY = "city";
            public static final String STATE = "state";
            public static final String ZIP = "zip";
            public static final String PHONE = "phone";
            }
    }

    public static final class TeacherTable {
        public static final String NAME = "teachers";

        public static final class Cols {
            public static final String ID = "id";
            public static final String SCHOOL_ID = "school_id";
            public static final String FIRST_NAME = "first_name";
            public static final String LAST_NAME = "last_name";
            public static final String DEPARTMENT = "department";
            public static final String ADDRESS = "address";
            public static final String CITY = "city";
            public static final String STATE = "state";
            public static final String ZIP = "zip";
            public static final String PHONE = "phone";
            public static final String EMAIL = "email";
        }
    }

    public static final class StudentTable {
        public static final String NAME = "students";

        public static final class Cols {
            public static final String ID = "id";
            public static final String SCHOOL_ID = "school_id";
            public static final String FIRST_NAME = "first_name";
            public static final String LAST_NAME = "last_name";
            public static final String GRADE = "grade";
            public static final String ADDRESS = "address";
            public static final String CITY = "city";
            public static final String STATE = "state";
            public static final String ZIP = "zip";
            public static final String PHONE = "phone";
            public static final String EMAIL = "email";
        }
    }

    public static final class SectionTable {
        public static final String NAME = "sections";

        public static final class Cols {
            public static final String ID = "id";
            public static final String SCHOOL_ID = "school_id";
            public static final String TEACHER_ID = "teacher_id";
            public static final String NAME = "name";
        }
    }
}
