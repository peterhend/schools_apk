package com.hvdoc.schools;

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
public class TeacherListFragment extends Fragment {
    private static final String ARG_SCHOOL_ID = "school_id";

    private School mSchool;
    private List<Teacher> mTeachers;

    private TextView mTextView;
    private RecyclerView mRecyclerView;
    private TeacherAdapter mTeacherAdapter;

    public static TeacherListFragment newInstance(int schoolId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SCHOOL_ID, schoolId);
        TeacherListFragment fragment = new TeacherListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int schoolId = (int) getArguments().getSerializable(ARG_SCHOOL_ID);
        mSchool = District.get(getActivity()).getSchool(schoolId);
        mTeachers = mSchool.getTeachers();
        //getActivity().setTitle(mSchool.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_list, container, false);
//        mTextView = (TextView) view.findViewById(R.id.teacher_list_text_view);
//        mTextView.setText("Teacher list for " + mSchool.getName());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.teacher_recycler_view);
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
        mTeacherAdapter = new TeacherAdapter(mTeachers);
        mRecyclerView.setAdapter(mTeacherAdapter);
    }

    private class TeacherHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mNameTextView;
        private TextView mDepartmentTextView;
        private Teacher mTeacher;

        public void bindTeacher(Teacher teacher)
        {
            mTeacher = teacher;
            mNameTextView.setText(teacher.getLastName() + ", " + teacher.getFirstName());
            mDepartmentTextView.setText(teacher.getDepartment());
        }

        public TeacherHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_teacher_name);
            mDepartmentTextView = (TextView) itemView.findViewById(R.id.list_item_department);
        }

        @Override
        public void onClick(View v) {
            //Intent intent = TeacherPagerActivity.newIntent(getActivity(), mSchool.getId(), mTeacher.getId());
            //startActivity(intent);
        }
    }

    private class TeacherAdapter extends RecyclerView.Adapter<TeacherHolder> {
        private List<Teacher> mTeachers;

        public TeacherAdapter(List<Teacher> teachers) {
            mTeachers = teachers;
        }

        @Override
        public TeacherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_teacher, parent, false);
            return new TeacherHolder(view);
        }

        @Override
        public void onBindViewHolder(TeacherHolder holder, int position) {
            Teacher teacher = mTeachers.get(position);
            holder.bindTeacher(teacher);
        }

        @Override
        public int getItemCount() {
            return mTeachers.size();
        }
    }

}
