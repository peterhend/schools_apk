package com.hvdoc.schools;

import android.support.v4.app.Fragment;

public class SchoolActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SchoolFragment();
    }
}
