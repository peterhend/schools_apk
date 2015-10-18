package com.hvdoc.schools;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Pete on 10/8/2015.
 */
public class DistrictFragment extends Fragment {

    private TextView mSuperintendentTextView;

    private TextView mAddressTextView;
    private TextView mPhoneTextView;
    private RecyclerView mDistrictRecyclerView;
    private SchoolAdapter mAdapter;

    private District mDistrict;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDistrict = District.get(getActivity());
        if (mDistrict.getSchools().size() == 0) {
            new HttpAsyncTask().execute("http://peterhend.pythonanywhere.com/districts/1/JSON");
        }
    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

//    public boolean isConnected(){
//        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected())
//            return true;
//        else
//            return false;
//    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            createObjectsFromJSON(result);
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
