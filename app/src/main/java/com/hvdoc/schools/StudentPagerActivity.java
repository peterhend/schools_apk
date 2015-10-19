package com.hvdoc.schools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by Pete on 10/9/2015.
 */
public class StudentPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private static final String EXTRA_SCHOOL_ID = "com.hvdoc.android.schools.school_id";
    private static final String EXTRA_STUDENT_ID = "com.hvdoc.android.schools.student_id";

    private ViewPager mViewPager;
    private School mSchool;
    private List<Student> mStudents;

    public static Intent newIntent(Context packageContext, int schoolId, int studentId) {
        Intent intent = new Intent(packageContext, StudentPagerActivity.class);
        intent.putExtra(EXTRA_SCHOOL_ID, schoolId);
        intent.putExtra(EXTRA_STUDENT_ID, studentId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_pager);

        int schoolId = (int) getIntent().getSerializableExtra(EXTRA_SCHOOL_ID);
        int studentId = (int) getIntent().getSerializableExtra(EXTRA_STUDENT_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_detail_pager_view_pager);

        mSchool = District.get(this).getSchool(schoolId);
        mStudents = mSchool.getStudents();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Student student = mStudents.get(position);
                return StudentFragment.newInstance(mSchool.getId(), student.getId());
            }

            @Override
            public int getCount() {
                return mStudents.size();
            }
        });

        for (int i = 0; i < mStudents.size(); i++) {
            if (mStudents.get(i).getId() == studentId) {
                mViewPager.setCurrentItem(i);
                setTitle(mStudents.get(i).getFirstName() + " " + mStudents.get(i).getLastName());
                break;
            }
        }
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int i) {
        setTitle(mStudents.get(i).getFirstName() + " " + mStudents.get(i).getLastName());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
