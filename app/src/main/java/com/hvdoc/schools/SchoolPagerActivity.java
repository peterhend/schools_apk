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
public class SchoolPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private static final String EXTRA_SCHOOL_ID = "com.hvdoc.android.schools.school_id";

    private ViewPager mViewPager;
    private List<School> mSchools;

    public static Intent newIntent(Context packageContext, int schoolId) {
        Intent intent = new Intent(packageContext, SchoolPagerActivity.class);
        intent.putExtra(EXTRA_SCHOOL_ID, schoolId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_activity_pager);

        int schoolId = (int) getIntent().getSerializableExtra(EXTRA_SCHOOL_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_school_pager_view_pager);

        mSchools = District.get(this).getSchools();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                School school = mSchools.get(position);
                return SchoolFragment.newInstance(school.getId());
            }

            @Override
            public int getCount() {
                return mSchools.size();
            }
        });

        for (int i = 0; i < mSchools.size(); i++) {
            if (mSchools.get(i).getId() == schoolId) {
                mViewPager.setCurrentItem(i);
                setTitle(mSchools.get(i).getName());
                break;
            }
        }

        mViewPager.addOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTitle(mSchools.get(position).getName());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
