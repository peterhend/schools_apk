package com.hvdoc.schools;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Pete on 10/8/2015.
 */
public class DistrictFragment extends Fragment {

    private static final String TAG = "DistrictFragment";
    private Callbacks mCallbacks;

    private TextView mSuperintendentTextView;
    private TextView mAddressTextView;
    private TextView mPhoneTextView;
    private RecyclerView mDistrictRecyclerView;
    private SchoolAdapter mAdapter;

    private District mDistrict;

    public interface Callbacks {
        void onSchoolSelected(School school);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDistrict = District.get(getActivity());
        if (mDistrict.getSchools().size() == 0) {
            new FetchItemsTask().execute("http://peterhend.pythonanywhere.com/districts/1/JSON");
        }
    }

    private class FetchItemsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return new JSONFetcher().fetchJSON(urls[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            createObjectsFromJSON(s);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_district, container, false);

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

    private void createObjectsFromJSON(String jsonString) {
        try {
            JSONObject json = new JSONObject(jsonString);
            JSONObject district = json.getJSONObject("District");
            mDistrict.setName(district.getString("name"));
            mDistrict.setSuperintendent(district.getString("superintendent"));
            mDistrict.setAddress(district.getString("address"));
            mDistrict.setCity(district.getString("city"));
            mDistrict.setState(district.getString("state"));
            mDistrict.setZip(district.getString("zip"));
            mDistrict.setPhone(district.getString("phone"));
            JSONArray schools = district.getJSONArray("schools");
            for (int i = 0; i < schools.length(); i++) {
                JSONObject jSchool = schools.getJSONObject(i);
                School school = new School(Integer.parseInt(jSchool.getString("id").toString()));
                school.setName(jSchool.getString("name"));
                school.setPrincipal(jSchool.getString("principal"));
                school.setAddress(jSchool.getString("address"));
                school.setCity(jSchool.getString("city"));
                school.setState(jSchool.getString("state"));
                school.setZip(jSchool.getString("zip"));
                school.setPhone(jSchool.getString("phone"));
                mDistrict.addSchool(school);
            }
            updateUI();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateUI() {
        List<School> schools = null;

        if (mDistrict != null) {
            getActivity().setTitle(mDistrict.getName());
            mSuperintendentTextView.setText("Superintendent: " + mDistrict.getSuperintendent());
            mAddressTextView.setText(mDistrict.getAddress() + ", " + mDistrict.getCity() + ", " + mDistrict.getState() + " " + mDistrict.getZip());
            mPhoneTextView.setText(mDistrict.getPhone());
            schools = mDistrict.getSchools();

            if (mAdapter == null) {
                mAdapter = new SchoolAdapter(schools);
                mDistrictRecyclerView.setAdapter(mAdapter);
            }
            else {
                mAdapter.notifyDataSetChanged();
            }
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
            //Intent intent = SchoolPagerActivity.newIntent(getActivity(), mSchool.getId());
            //startActivity(intent);
            mCallbacks.onSchoolSelected(mSchool);
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
