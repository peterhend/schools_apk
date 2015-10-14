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
public class TeacherPagerActivity extends AppCompatActivity {
    private static final String EXTRA_SCHOOL_ID = "com.hvdoc.android.schools.school_id";
    private static final String EXTRA_TEACHER_ID = "com.hvdoc.android.schools.teacher_id";

    private ViewPager mViewPager;
    private School mSchool;
    private List<Teacher> mTeachers;

    public static Intent newIntent(Context packageContext, int schoolId, int teacherId) {
        Intent intent = new Intent(packageContext, TeacherPagerActivity.class);
        intent.putExtra(EXTRA_SCHOOL_ID, schoolId);
        intent.putExtra(EXTRA_TEACHER_ID, teacherId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_activity_pager);

        int schoolId = (int) getIntent().getSerializableExtra(EXTRA_SCHOOL_ID);
        int teacherId = (int) getIntent().getSerializableExtra(EXTRA_TEACHER_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_teacher_pager_view_pager);

        mSchool = District.get(this).getSchool(schoolId);
        mTeachers = mSchool.getTeachers();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Teacher teacher = mTeachers.get(position);
                return TeacherFragment.newInstance(mSchool.getId(), teacher.getId());
            }

            @Override
            public int getCount() {
                return mTeachers.size();
            }
        });

        for (int i = 0; i < mTeachers.size(); i++) {
            if (mTeachers.get(i).getId() == teacherId) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
