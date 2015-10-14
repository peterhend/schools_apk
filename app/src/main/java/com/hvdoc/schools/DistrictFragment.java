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
public class DistrictFragment extends Fragment {

    private TextView mNameTextView;
    private TextView mSuperintendentTextView;
    private TextView mAddressTextView;
    private TextView mPhoneTextView;
    private RecyclerView mDistrictRecyclerView;
    private SchoolAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_district, container, false);

        mNameTextView = (TextView) v.findViewById(R.id.name_text_view);
        mSuperintendentTextView = (TextView) v.findViewById(R.id.superintendent_text_view);
        mAddressTextView = (TextView) v.findViewById(R.id.address_text_view);
        mPhoneTextView = (TextView) v.findViewById(R.id.phone_text_view);
        mDistrictRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mDistrictRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        District district = District.get(getActivity());

        mNameTextView.setText(district.getName());
        mSuperintendentTextView.setText("Superintendent: " + district.getSuperintendent());
        mAddressTextView.setText("Address: " + district.getAddress() + ", " + district.getCity() + ", " + district.getState() + " " + district.getZip());
        mPhoneTextView.setText("Phone: " + district.getPhone());

        List<School> schools = district.getSchools();

        if (mAdapter == null) {
            mAdapter = new SchoolAdapter(schools);
            mDistrictRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class SchoolHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mNameTextView;
        private School mSchool;

        public void bindSchool(School school)
        {
            mSchool = school;
            mNameTextView.setText(mSchool.getName());
        }
        public SchoolHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_school_name);
        }

        @Override
        public void onClick(View v) {
            Intent intent = SchoolPagerActivity.newIntent(getActivity(), mSchool.getId());
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
