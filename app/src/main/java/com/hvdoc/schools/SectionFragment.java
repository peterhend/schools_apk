package com.hvdoc.schools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Pete on 10/8/2015.
 */
public class SectionFragment extends Fragment {
    private static final String ARG_SCHOOL_ID = "school_id";
    private static final String ARG_SECTION_ID = "section_id";

    private Section mSection;

    private TextView mName;

    public static SectionFragment newInstance(int schoolId, int sectionId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SCHOOL_ID, schoolId);
        args.putSerializable(ARG_SECTION_ID, sectionId);

        SectionFragment fragment = new SectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int schoolId = (int) getArguments().getSerializable(ARG_SCHOOL_ID);
        int sectionId = (int) getArguments().getSerializable(ARG_SECTION_ID);
        mSection = District.get(getActivity()).getSchool(schoolId).getSection(sectionId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_section, container, false);
        mName = (TextView)v.findViewById(R.id.section_name_text_view);
        mName.setText("Name: " + mSection.getName());
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
