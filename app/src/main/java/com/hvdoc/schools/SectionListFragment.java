package com.hvdoc.schools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pete on 10/13/2015.
 */
public class SectionListFragment extends Fragment {
    private static final String ARG_SCHOOL_ID = "school_id";

    private School mSchool;
    private List<Section> mSections;

    private TextView mTextView;
    private RecyclerView mRecyclerView;
    private SectionAdapter mSectionAdapter;

    public static SectionListFragment newInstance(int schoolId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SCHOOL_ID, schoolId);
        SectionListFragment fragment = new SectionListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int schoolId = (int) getArguments().getSerializable(ARG_SCHOOL_ID);
        mSchool = District.get(getActivity()).getSchool(schoolId);
        mSections = mSchool.getSections();
        //getActivity().setTitle(mSchool.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_section_list, container, false);
//        mTextView = (TextView) view.findViewById(R.id.section_list_text_view);
//        mTextView.setText("Section list for " + mSchool.getName());
//        mTextView.setVisibility(View.INVISIBLE);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.section_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadList();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadList();
    }

    private void loadList() {
        mSectionAdapter = new SectionAdapter(mSections);
        mRecyclerView.setAdapter(mSectionAdapter);
    }

    private class SectionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mNameTextView;
        private Section mSection;

        public void bindSection(Section section)
        {
            mSection = section;
            mNameTextView.setText(section.getName());
        }

        public SectionHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_school_name);
        }

        @Override
        public void onClick(View v) {
            //Pete
            //Intent intent = TeacherPagerActivity.newIntent(getActivity(), mSchool.getId(), mSection.getId());
            //startActivity(intent);
        }
    }

    private class SectionAdapter extends RecyclerView.Adapter<SectionHolder> {
        private List<Section> mSections;

        public SectionAdapter(List<Section> sections) {
            mSections = sections;
        }

        @Override
        public SectionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_school, parent, false);
            return new SectionHolder(view);
        }

        @Override
        public void onBindViewHolder(SectionHolder holder, int position) {
            Section section = mSections.get(position);
            holder.bindSection(section);
        }

        @Override
        public int getItemCount() {
            return mSections.size();
        }
    }

}
