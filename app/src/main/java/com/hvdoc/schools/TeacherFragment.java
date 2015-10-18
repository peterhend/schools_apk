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
public class TeacherFragment extends Fragment {
    private static final String ARG_SCHOOL_ID = "school_id";
    private static final String ARG_TEACHER_ID = "teacher_id";

    private Teacher mTeacher;

    private TextView mEmailTextView;
    private TextView mPhoneTextView;

    public static TeacherFragment newInstance(int schoolId, int teacherId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SCHOOL_ID, schoolId);
        args.putSerializable(ARG_TEACHER_ID, teacherId);

        TeacherFragment fragment = new TeacherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int schoolId = (int) getArguments().getSerializable(ARG_SCHOOL_ID);
        int teacherId = (int) getArguments().getSerializable(ARG_TEACHER_ID);
        mTeacher = District.get(getActivity()).getSchool(schoolId).getTeacher(teacherId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_teacher, container, false);
        mPhoneTextView = (TextView)v.findViewById(R.id.teacher_phone_text_view);
        mPhoneTextView.setText("Phone: " + mTeacher.getPhone());
        mEmailTextView = (TextView)v.findViewById(R.id.teacher_email_text_view);
        mEmailTextView.setText("Email: " + mTeacher.getEmail());
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
