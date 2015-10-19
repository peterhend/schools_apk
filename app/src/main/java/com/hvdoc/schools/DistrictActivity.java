package com.hvdoc.schools;

import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Pete on 10/8/2015.
 */
public class DistrictActivity extends SingleFragmentActivity
    implements DistrictFragment.Callbacks {
    @Override
    protected Fragment createFragment() {
        return new DistrictFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onSchoolSelected(School school) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = SchoolPagerActivity.newIntent(this, school.getId());
            startActivity(intent);
        } else {
            Fragment newDetail = SchoolFragment.newInstance(school.getId());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }
}
