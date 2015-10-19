package com.hvdoc.schools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pete on 10/13/2015.
 */
public class StudentListFragment extends Fragment {
    private static final String ARG_SCHOOL_ID = "school_id";

    private School mSchool;
    private List<Student> mStudents;

    private TextView mTextView;
    private RecyclerView mRecyclerView;
    private StudentAdapter mStudentAdapter;

    public static StudentListFragment newInstance(int schoolId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SCHOOL_ID, schoolId);
        StudentListFragment fragment = new StudentListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int schoolId = (int) getArguments().getSerializable(ARG_SCHOOL_ID);
        mSchool = District.get(getActivity()).getSchool(schoolId);
        mStudents = mSchool.getStudents();
        //getActivity().setTitle(mSchool.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.student_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadList();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadList();
    }

    private void loadList() {
        mStudentAdapter = new StudentAdapter(mStudents);
        mRecyclerView.setAdapter(mStudentAdapter);
    }

    private class StudentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mNameTextView;
        private TextView mGradeTextView;
        private Student mStudent;

        public void bindStudent(Student student)
        {
            mStudent = student;
            mNameTextView.setText(student.getLastName() + ", " + student.getFirstName());
            mGradeTextView.setText(student.getGrade());
        }

        public StudentHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_student_name);
            mGradeTextView = (TextView) itemView.findViewById(R.id.list_item_grade);
        }

        @Override
        public void onClick(View v) {
            Intent intent = StudentPagerActivity.newIntent(getActivity(), mSchool.getId(), mStudent.getId());
            startActivity(intent);
        }
    }

    private class StudentAdapter extends RecyclerView.Adapter<StudentHolder> {
        private List<Student> mStudents;

        public StudentAdapter(List<Student> students) {
            mStudents = students;
        }

        @Override
        public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_student, parent, false);
            return new StudentHolder(view);
        }

        @Override
        public void onBindViewHolder(StudentHolder holder, int position) {
            Student student = mStudents.get(position);
            holder.bindStudent(student);
        }

        @Override
        public int getItemCount() {
            return mStudents.size();
        }
    }

}
