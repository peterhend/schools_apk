package com.hvdoc.schools;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Pete on 10/8/2015.
 */
public class SchoolFragment extends Fragment {
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

        if (mSchool.getTeachers().size() == 0) {
            new HttpAsyncTask().execute("http://peterhend.pythonanywhere.com/districts/1/schools/" + mSchool.getId() + "/teachers/JSON");
        }

        if (mSchool.getStudents().size() == 0) {
            new HttpAsyncTask().execute("http://peterhend.pythonanywhere.com/districts/1/schools/" + mSchool.getId() + "/students/JSON");
        }

        if (mSchool.getSections().size() == 0) {
            new HttpAsyncTask().execute("http://peterhend.pythonanywhere.com/districts/1/schools/" + mSchool.getId() + "/sections/JSON");
        }

        if (fm == null) {
            fm = getActivity().getSupportFragmentManager();
        }
    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

//    public boolean isConnected(){
//        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected())
//            return true;
//        else
//            return false;
//    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            createObjectsFromJSON(result);
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
