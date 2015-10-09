package com.hvdoc.schools;

import android.content.Intent;
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
 * Created by Pete on 10/8/2015.
 */
public class SchoolListFragment extends Fragment {

    private RecyclerView mSchoolRecyclerView;
    private SchoolAdapter mAdapter;
    private School mSchool;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_district, container, false);

        mSchoolRecyclerView = (RecyclerView) v.findViewById(R.id.school_recycler_view);
        mSchoolRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return v;
    }

    private void updateUI() {
        District district = District.get(getActivity());
        List<School> schools = district.getSchools();

        mAdapter = new SchoolAdapter(schools);
        mSchoolRecyclerView.setAdapter(mAdapter);
    }

    private class SchoolHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mNameTextView;
        private TextView mPrincipalTextView;

        private School mSchool;

        public void bindSchool(School school)
        {
            mSchool = school;
            mNameTextView.setText(mSchool.getName());
            mPrincipalTextView.setText(mSchool.getPrincipal());
        }
        public SchoolHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_school_name);
            mPrincipalTextView = (TextView) itemView.findViewById(R.id.list_item_school_principal);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), SchoolActivity.class);
            startActivity(intent);
        }

    }

    private class SchoolAdapter extends RecyclerView.Adapter<SchoolHolder> {

        private List<School> mSchools;

        public SchoolAdapter(List<School> schools) {
            mSchools = schools;
        }

        @Override
        public SchoolHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_school, parent, false);
            return new SchoolHolder(view);
        }

        @Override
        public void onBindViewHolder(SchoolHolder holder, int position) {
            School school = mSchools.get(position);
            holder.bindSchool(school);
        }

        @Override
        public int getItemCount() {
            return mSchools.size();
        }
    }
}
