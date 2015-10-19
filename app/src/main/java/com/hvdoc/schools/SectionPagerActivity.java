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
public class SectionPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private static final String EXTRA_SCHOOL_ID = "com.hvdoc.android.schools.school_id";
    private static final String EXTRA_SECTION_ID = "com.hvdoc.android.schools.section_id";

    private ViewPager mViewPager;
    private School mSchool;
    private List<Section> mSections;

    public static Intent newIntent(Context packageContext, int schoolId, int sectionId) {
        Intent intent = new Intent(packageContext, SectionPagerActivity.class);
        intent.putExtra(EXTRA_SCHOOL_ID, schoolId);
        intent.putExtra(EXTRA_SECTION_ID, sectionId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_pager);

        int schoolId = (int) getIntent().getSerializableExtra(EXTRA_SCHOOL_ID);
        int sectionId = (int) getIntent().getSerializableExtra(EXTRA_SECTION_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_detail_pager_view_pager);

        mSchool = District.get(this).getSchool(schoolId);
        mSections = mSchool.getSections();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Section section = mSections.get(position);
                return SectionFragment.newInstance(mSchool.getId(), section.getId());
            }

            @Override
            public int getCount() {
                return mSections.size();
            }
        });

        for (int i = 0; i < mSections.size(); i++) {
            if (mSections.get(i).getId() == sectionId) {
                mViewPager.setCurrentItem(i);
                setTitle(mSections.get(i).getName());
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
        setTitle(mSections.get(i).getName());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
