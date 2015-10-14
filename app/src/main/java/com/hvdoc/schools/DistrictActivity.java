package com.hvdoc.schools;

import android.support.v4.app.Fragment;

/**
 * Created by Pete on 10/8/2015.
 */
public class DistrictActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new DistrictFragment();
    }
}
