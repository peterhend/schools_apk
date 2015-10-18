package com.hvdoc.schools;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Pete on 10/8/2015.
 */
public class SchoolFragment extends Fragment {

    private static final String TAG = "SchoolFragment";

    private static final String ARG_SCHOOL_ID = "school_id";

    private School mSchool;

    private TextView mPrincipalTextView;
    private TextView mAddressTextView;
    private TextView mPhoneTextView;
    private TabLayout mTabLayout;
    private FrameLayout mFrameLayout;

    private int mSelectedTab = 0;

    private FragmentManager fm;

    public static SchoolFragment newInstance(int schoolId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SCHOOL_ID, schoolId);
        SchoolFragment fragment = new SchoolFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int schoolId = (int) getArguments().getSerializable(ARG_SCHOOL_ID);
        mSchool = District.get(getActivity()).getSchool(schoolId);

        String baseURL = "http://peterhend.pythonanywhere.com/districts/1/schools/";
        if (mSchool.getTeachers().size() == 0) {
            new FetchItemsTask().execute(baseURL + mSchool.getId() + "/teachers/JSON");
            new FetchItemsTask().execute(baseURL + mSchool.getId() + "/students/JSON");
            new FetchItemsTask().execute(baseURL + mSchool.getId() + "/sections/JSON");
        }

        if (fm == null) {
            fm = getActivity().getSupportFragmentManager();
        }
    }

    private class FetchItemsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return new JSONFetcher().fetchJSON(urls[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            createObjectsFromJSON(s);
            loadListFragment(mSelectedTab);
        }
    }

    private void createObjectsFromJSON(String jsonString) {
        try {
            JSONObject json = new JSONObject(jsonString);
            if (jsonString.contains("\"Teachers\"")) {
                JSONArray teachers = json.getJSONArray("Teachers");
                for (int i = 0; i < teachers.length(); i++) {
                    JSONObject jTeacher = teachers.getJSONObject(i);
                    Teacher teacher = new Teacher(Integer.parseInt(jTeacher.getString("id").toString()));
                    teacher.setFirstName(jTeacher.getString("first_name"));
                    teacher.setLastName(jTeacher.getString("last_name"));
                    teacher.setDepartment(jTeacher.getString("department"));
                    teacher.setAddress(jTeacher.getString("address"));
                    teacher.setCity(jTeacher.getString("city"));
                    teacher.setState(jTeacher.getString("state"));
                    teacher.setZip(jTeacher.getString("zip"));
                    teacher.setPhone(jTeacher.getString("phone"));
                    teacher.setEmail(jTeacher.getString("email"));
                    mSchool.addTeacher(teacher);
                }
            }
            else if (jsonString.contains("\"Students\"")) {
                JSONArray students = json.getJSONArray("Students");
                for (int i = 0; i < students.length(); i++) {
                    JSONObject jStudent = students.getJSONObject(i);
                    Student student = new Student(Integer.parseInt(jStudent.getString("id").toString()));
                    student.setFirstName(jStudent.getString("first_name"));
                    student.setLastName(jStudent.getString("last_name"));
                    student.setGrade(jStudent.getString("grade"));
                    student.setAddress(jStudent.getString("address"));
                    student.setCity(jStudent.getString("city"));
                    student.setState(jStudent.getString("state"));
                    student.setZip(jStudent.getString("zip"));
                    student.setPhone(jStudent.getString("phone"));
                    mSchool.addStudent(student);
                }
            }
            else if (jsonString.contains("\"Sections\"")) {
                JSONArray sections = json.getJSONArray("Sections");
                for (int i = 0; i < sections.length(); i++) {
                    JSONObject jSection = sections.getJSONObject(i);
                    Section section = new Section(Integer.parseInt(jSection.getString("id").toString()));
                    section.setName(jSection.getString("name"));
                    mSchool.addSection(section);
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_school, container, false);
        mPrincipalTextView = (TextView)v.findViewById(R.id.school_principal_text_view);
        mAddressTextView = (TextView)v.findViewById(R.id.school_address_text_view);
        mPhoneTextView = (TextView)v.findViewById(R.id.school_phone_text_view);
        mFrameLayout = (FrameLayout)v.findViewById(R.id.list_fragment_container);
        mFrameLayout.setTag(mSchool.getId());

        mPrincipalTextView.setText("Principal: " + mSchool.getPrincipal());
        mAddressTextView.setText(mSchool.getAddress() + ", " + mSchool.getCity() + ", " + mSchool.getState() + " " + mSchool.getZip());
        mPhoneTextView.setText(mSchool.getPhone());

        mTabLayout = (TabLayout)v.findViewById(R.id.school_tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText("TEACHERS"));
        mTabLayout.addTab(mTabLayout.newTab().setText("STUDENTS"));
        mTabLayout.addTab(mTabLayout.newTab().setText("SECTIONS"));

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mSelectedTab = tab.getPosition();
                loadListFragment(mSelectedTab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        loadListFragment(mSelectedTab);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        //loadListFragment(mSelectedTab);
    }

    private void loadListFragment(int tab) {
        Fragment newFragment = null;

        switch (tab) {
            case 0: newFragment = TeacherListFragment.newInstance(mSchool.getId());
                    break;
            case 1: newFragment = StudentListFragment.newInstance(mSchool.getId());
                    break;
            case 2: newFragment = SectionListFragment.newInstance(mSchool.getId());
                break;
            default:
                    ;
        }
        if (newFragment != null) {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.list_fragment_container, newFragment)
                    .commit();
        }
    }
}
