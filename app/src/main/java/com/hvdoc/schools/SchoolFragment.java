package com.hvdoc.schools;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Pete on 10/8/2015.
 */
public class SchoolFragment extends Fragment {
    private static final String ARG_SCHOOL_ID = "school_id";

    private School mSchool;

    private TextView mNameTextView;
    private TextView mPrincipalTextView;
    private TextView mAddressTextView;
    private TextView mPhoneTextView;
    private TabLayout mTabLayout;

    private int mSelectedTab = 0;

    private Fragment mTeacherListFragment;
    private Fragment mStudentListFragment;
    private Fragment mSectionListFragment;

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
        fm = getActivity().getSupportFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_school, container, false);
        mNameTextView = (TextView)v.findViewById(R.id.school_name_text_view);
        mPrincipalTextView = (TextView)v.findViewById(R.id.school_principal_text_view);
        mAddressTextView = (TextView)v.findViewById(R.id.school_address_text_view);
        mPhoneTextView = (TextView)v.findViewById(R.id.school_phone_text_view);

        mNameTextView.setText(mSchool.getName());
        mPrincipalTextView.setText(mSchool.getPrincipal());
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
        loadListFragment(mSelectedTab);
    }

    private void loadListFragment(int tab) {
        Fragment currentFragment = fm.findFragmentById(R.id.list_fragment_container);
        Fragment newFragment = null;

        switch (tab) {
            case 0: if (mTeacherListFragment == null) {
                        mTeacherListFragment = TeacherListFragment.newInstance(mSchool.getId());
                    };
                    newFragment = mTeacherListFragment;
                    break;
            case 1: if (mStudentListFragment == null) {
                        mStudentListFragment = StudentListFragment.newInstance(mSchool.getId());
                    };
                    newFragment = mStudentListFragment;
                    break;
            case 2: if (mSectionListFragment == null) {
                        mSectionListFragment = SectionListFragment.newInstance(mSchool.getId());
                    };
                    newFragment = mSectionListFragment;
                    break;
            default:
                    ;
        }
        if (newFragment != null) {
            fm.beginTransaction()
                    .replace(R.id.list_fragment_container, newFragment)
                    .commit();
        }
    }
}
