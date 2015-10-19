package com.hvdoc.schools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Pete on 10/8/2015.
 */
public class StudentFragment extends Fragment {
    private static final String ARG_SCHOOL_ID = "school_id";
    private static final String ARG_STUDENT_ID = "student_id";

    private Student mStudent;

    private TextView mGradeTextView;
    private TextView mPhoneTextView;

    public static StudentFragment newInstance(int schoolId, int studentId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SCHOOL_ID, schoolId);
        args.putSerializable(ARG_STUDENT_ID, studentId);

        StudentFragment fragment = new StudentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int schoolId = (int) getArguments().getSerializable(ARG_SCHOOL_ID);
        int studentId = (int) getArguments().getSerializable(ARG_STUDENT_ID);
        mStudent = District.get(getActivity()).getSchool(schoolId).getStudent(studentId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_student, container, false);
        mPhoneTextView = (TextView)v.findViewById(R.id.student_phone_text_view);
        mPhoneTextView.setText("Phone: " + mStudent.getPhone());
        mGradeTextView = (TextView)v.findViewById(R.id.student_grade_text_view);
        mGradeTextView.setText("Grade: " + mStudent.getGrade());
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
