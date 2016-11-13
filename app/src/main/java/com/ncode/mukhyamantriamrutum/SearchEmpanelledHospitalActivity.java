package com.ncode.mukhyamantriamrutum;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class SearchEmpanelledHospitalActivity extends AppCompatActivity {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "CALL";
    private static final String ARG_PARAM2 = "TYPE_LIST";
    private static final String ARG_PARAM3 = "DISTRICT_LIST";
    private static final String ARG_PARAM4 = "HOSPITAL_NAME_LIST";
    private static final String ARG_PARAM5 = "ADDRESS_LIST";
    private static final String ARG_PARAM6 = "ADDRESS1_LIST";
    private static final String ARG_PARAM7 = "ADDRESS2_LIST";
    private static final String ARG_PARAM8 = "AROGYAMITRA_LIST";
    private static final String ARG_PARAM9 = "AMNUMBER_LIST";
    private static final String ARG_PARAM10 = "HAM_LIST";
    private static final String ARG_PARAM11 = "HAMNUMBER_LIST";
    private static final String ARG_PARAM12 = "LATITUDE_LIST";
    private static final String ARG_PARAM13 = "LONGITUDE_LIST";

    Spinner spinner1, spinner2;
    ListView lvDetailView;
    TextView  txvClusterHeader, txvSpinner1Header, txvSpinner2Header, txvBack;
    LinearLayout llSelection;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private ArrayList<String> mParam2, mParam3, mParam4, mParam5, mParam6, mParam7, mParam8, mParam9, mParam10, mParam11, mParam12, mParam13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_search);

        Bundle bundle = getIntent().getExtras();
        mParam1 = bundle.getString(ARG_PARAM1);
        mParam2 = bundle.getStringArrayList(ARG_PARAM2);
        mParam3 = bundle.getStringArrayList(ARG_PARAM3);
        mParam4 = bundle.getStringArrayList(ARG_PARAM4);
        mParam5 = bundle.getStringArrayList(ARG_PARAM5);
        mParam6 = bundle.getStringArrayList(ARG_PARAM6);
        mParam7 = bundle.getStringArrayList(ARG_PARAM7);
        mParam8 = bundle.getStringArrayList(ARG_PARAM8);
        mParam9 = bundle.getStringArrayList(ARG_PARAM9);
        mParam10 = bundle.getStringArrayList(ARG_PARAM10);
        mParam11 = bundle.getStringArrayList(ARG_PARAM11);
        mParam12 = bundle.getStringArrayList(ARG_PARAM12);
        mParam13 = bundle.getStringArrayList(ARG_PARAM13);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        lvDetailView = (ListView) findViewById(R.id.listview);
      //  txvTitleHeader = (TextView) findViewById(R.id.txvTitleHeader);
        txvClusterHeader = (TextView) findViewById(R.id.txvClusterHeader);
        txvSpinner1Header = (TextView) findViewById(R.id.txvSpinner1Header);
        txvSpinner2Header = (TextView) findViewById(R.id.txvSpinner2Header);
        llSelection = (LinearLayout) findViewById(R.id.llSelection);
        txvBack = (TextView) findViewById(R.id.txvBack);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (mParam1.equalsIgnoreCase("DIALYSIS")) {
            getSupportActionBar().setTitle("Dialysis Services");
      //      txvTitleHeader.setText("Dialysis Services");
            txvClusterHeader.setVisibility(View.GONE);
        } else if (mParam1.equalsIgnoreCase("EMPANELLED")) {
            getSupportActionBar().setTitle("Hospital with details of Arogya Mitra");
//            txvTitleHeader.setText("Empanelled Hospital");
            txvClusterHeader.setVisibility(View.VISIBLE);
        }
        Set<String> typeUnique = new TreeSet<String>(mParam2);
        ArrayList<String> typeList = new ArrayList<String>();
        typeList.addAll(typeUnique);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchEmpanelledHospitalActivity.this, android.R.layout.simple_spinner_item, typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        txvBack.setVisibility(View.GONE);
        txvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llSelection.setVisibility(View.VISIBLE);
                Set<String> typeUnique = new TreeSet<String>(mParam2);
                ArrayList<String> typeList = new ArrayList<String>();
                typeList.addAll(typeUnique);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchEmpanelledHospitalActivity.this, android.R.layout.simple_spinner_item, typeList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter);
                txvClusterHeader.setVisibility(View.VISIBLE);
                txvBack.setVisibility(View.GONE);
            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> district = new ArrayList<String>();
                String getSelectType = spinner1.getSelectedItem().toString();
                for (int i = 0; i < mParam2.size(); i++) {
                    if (getSelectType.equalsIgnoreCase(mParam2.get(i))) {
                        district.add(mParam3.get(i));
                    }
                }

                Set<String> districtUnique = new TreeSet<String>(district);
                ArrayList<String> districtList = new ArrayList<String>();
                districtList.addAll(districtUnique);
                ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(SearchEmpanelledHospitalActivity.this, android.R.layout.simple_spinner_item, districtList);
                districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(districtAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> typelist = new ArrayList<String>();
                ArrayList<String> hospitalnamelist = new ArrayList<String>();
                ArrayList<String> addresslist = new ArrayList<String>();
                ArrayList<String> address1list = new ArrayList<String>();
                ArrayList<String> address2list = new ArrayList<String>();
                ArrayList<String> arogyamitra = new ArrayList<String>();
                ArrayList<String> amnumber = new ArrayList<String>();
                ArrayList<String> ham = new ArrayList<String>();
                ArrayList<String> hamnumber = new ArrayList<String>();
                ArrayList<String> latitude = new ArrayList<String>();
                ArrayList<String> longitude = new ArrayList<String>();

                String getSelectType = spinner1.getSelectedItem().toString();
                String getSelectedDistrict = spinner2.getSelectedItem().toString();
                for (int i = 0; i < mParam2.size(); i++) {
                    if (getSelectType.equalsIgnoreCase(mParam2.get(i)) && getSelectedDistrict.equalsIgnoreCase(mParam3.get(i))) {
                        typelist.add(mParam2.get(i));
                        hospitalnamelist.add(mParam4.get(i));
                        addresslist.add(mParam5.get(i));
                        address1list.add(mParam6.get(i));
                        address2list.add(mParam7.get(i));
                        arogyamitra.add(mParam8.get(i));
                        amnumber.add(mParam9.get(i));
                        ham.add(mParam10.get(i));
                        hamnumber.add(mParam11.get(i));
                        latitude.add(mParam12.get(i));
                        longitude.add(mParam13.get(i));
                    }
                }
                DetailDataAdapter adapter = new DetailDataAdapter(typelist, hospitalnamelist, addresslist, address1list, address2list, arogyamitra, amnumber, ham, hamnumber, latitude, longitude);
                lvDetailView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        txvClusterHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> search_list = new ArrayList<String>();
                search_list.add("BURNS");
                search_list.add("CARDIOLOGY");
                search_list.add("CARDIOTHORACIC SURGERY");
                search_list.add("CARDIOVASCULAR SURGERY");
                search_list.add("GENITOURINARY SURGERY");
                search_list.add("NEURO SURGERY");
                search_list.add("PEDIATRIC SURGERY");
                search_list.add("POLY TRAUMA");
                search_list.add("MEDICAL ONCOLOGY");
                search_list.add("RADIATION ONCOLOGY");
                search_list.add("SURGICAL ONCOLOGY");

                Intent i = new Intent(SearchEmpanelledHospitalActivity.this, SearchDialogActivity.class);
                i.putExtra("CALL", "CLUSTER");
                i.putExtra("MENU_NAME_LIST", search_list);
                startActivityForResult(i, 15);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 15) {
                getClusteredHospital(data.getStringExtra("HEADER"));
               // txvTitleHeader.setText("Empanelled Hospital");
                getSupportActionBar().setTitle("Hospital with details of Arogya Mitra");
                llSelection.setVisibility(View.GONE);
                txvClusterHeader.setVisibility(View.GONE);
                txvBack.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getClusteredHospital(final String searchType) {
        String tag_json_obj = "json_obj_req";

        String url = ApplicationData.searchURL + "GetClusterHospitalsMA";
        Log.e("url", url + "");
        final ProgressDialog mProgressDialog = new ProgressDialog(SearchEmpanelledHospitalActivity.this);
        mProgressDialog.setTitle("");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e("response", "" + response);
                        ArrayList<String> type = new ArrayList<String>();
                        ArrayList<String> hospitalName = new ArrayList<String>();
                        ArrayList<String> address = new ArrayList<String>();
                        ArrayList<String> address1 = new ArrayList<String>();
                        ArrayList<String> address2 = new ArrayList<String>();
                        ArrayList<String> arogyamitra = new ArrayList<String>();
                        ArrayList<String> amnumber = new ArrayList<String>();
                        ArrayList<String> ham = new ArrayList<String>();
                        ArrayList<String> hamnumber = new ArrayList<String>();
                        ArrayList<String> latitude = new ArrayList<String>();
                        ArrayList<String> longitude = new ArrayList<String>();
                        try {
                            mProgressDialog.dismiss();
                            JSONObject object = new JSONObject(response.toString());

                            String msg = object.getString("msg");
                            if (msg.equalsIgnoreCase("Success")) {
                                JSONArray dataArr = object.getJSONArray("data");
                                for (int i = 0; i < dataArr.length(); i++) {
                                    JSONObject dataOb = dataArr.getJSONObject(i);
                                    type.add(dataOb.getString("Cluster").replace(" ", ""));
                                    hospitalName.add(dataOb.getString("HospitalName"));
                                    address.add(dataOb.getString("Address"));
                                    address1.add(dataOb.getString("Address1"));
                                    address2.add(dataOb.getString("Address2"));
//                                    arogyamitra.add(dataOb.getString("Arogyamitra"));
//                                    amnumber.add(dataOb.getString("AMNumber"));
//                                    ham.add(dataOb.getString("HAM"));
//                                    hamnumber.add(dataOb.getString("HAMNumber"));
//                                    latitude.add(dataOb.getString("Latitude"));
//                                    longitude.add(dataOb.getString("Longitude"));
                                    arogyamitra.add("");
                                    amnumber.add("");
                                    ham.add("");
                                    hamnumber.add("");
                                    latitude.add("");
                                    longitude.add("");
                                }

                                ArrayList<String> typelist1 = new ArrayList<String>();
                                ArrayList<String> hospitalnamelist1 = new ArrayList<String>();
                                ArrayList<String> addresslist1 = new ArrayList<String>();
                                ArrayList<String> address1list1 = new ArrayList<String>();
                                ArrayList<String> address2list1 = new ArrayList<String>();
                                ArrayList<String> arogyamitra1 = new ArrayList<String>();
                                ArrayList<String> amnumber1 = new ArrayList<String>();
                                ArrayList<String> ham1 = new ArrayList<String>();
                                ArrayList<String> hamnumber1 = new ArrayList<String>();
                                ArrayList<String> latitude1 = new ArrayList<String>();
                                ArrayList<String> longitude1 = new ArrayList<String>();

                                for (int i = 0; i < type.size(); i++) {
                                    if (type.get(i).equalsIgnoreCase(searchType)) {
                                        typelist1.add(type.get(i));
                                        hospitalnamelist1.add(hospitalName.get(i));
                                        addresslist1.add(address.get(i));
                                        address1list1.add(address1.get(i));
                                        address2list1.add(address2.get(i));
                                        arogyamitra1.add(arogyamitra.get(i));
                                        amnumber1.add(amnumber.get(i));
                                        ham1.add(ham.get(i));
                                        hamnumber1.add(hamnumber.get(i));
                                        latitude1.add(latitude.get(i));
                                        longitude1.add(longitude.get(i));
                                    }
                                }

                                DetailDataAdapter adapter = new DetailDataAdapter(typelist1, hospitalnamelist1,
                                        addresslist1, address1list1, address2list1, arogyamitra1, amnumber1, ham1, hamnumber1, latitude1, longitude1);
                                lvDetailView.setAdapter(adapter);
                            } else {
                                Toast.makeText(SearchEmpanelledHospitalActivity.this,
                                        "Oopss! Login failure please try again",
                                        Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception error) {
                            mProgressDialog.dismiss();
                            error.printStackTrace();
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(SearchEmpanelledHospitalActivity.this, "Please check your internet connection!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(SearchEmpanelledHospitalActivity.this, "Something is wrong Please try again!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressDialog.dismiss();

                error.getCause();
                error.printStackTrace();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(SearchEmpanelledHospitalActivity.this, "Please check your internet connection!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SearchEmpanelledHospitalActivity.this, "Something is wrong Please try again!", Toast.LENGTH_LONG).show();
                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }//fikabox.se
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        ApplicationData.getInstance().addToRequestQueue(jsonObjReq,
                tag_json_obj);
    }


    public class DetailDataAdapter extends BaseAdapter {

        LayoutInflater inflater;
        ArrayList<String> typelist, hospitalnamelist, address_list, address1_list, address2_list,
                arogyamitra, amnumber, ham, hamnumber, latitude, longitude, selectedlist;
        ViewHolder holder;
        int m = 0;

        public DetailDataAdapter(ArrayList<String> typelist, ArrayList<String> hospitalnamelist, ArrayList<String> Aaddresslist, ArrayList<String> Aaddress1list, ArrayList<String> Aaddress2list,
                                 ArrayList<String> arogyamitra, ArrayList<String> amnumber, ArrayList<String> ham, ArrayList<String> hamnumber,
                                 ArrayList<String> latitude, ArrayList<String> longitude) {
            // TODO Auto-generated constructor stub
            this.typelist = typelist;
            this.hospitalnamelist = hospitalnamelist;
            this.address_list = Aaddresslist;
            this.address1_list = Aaddress1list;
            this.address2_list = Aaddress2list;
            this.arogyamitra = arogyamitra;
            this.amnumber = amnumber;
            this.ham = ham;
            this.hamnumber = hamnumber;
            this.latitude = latitude;
            this.longitude = longitude;
            selectedlist = new ArrayList<String>();
            for (int i = 0; i < address_list.size(); i++) {
                selectedlist.add("false");
            }
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            // TODO Auto-generated method stub
            if (convertView == null) {

                convertView = inflater.inflate(R.layout.row_hospital_search_item_list,
                        parent, false);
                holder = new ViewHolder();
                holder.txvHospitalTitle = (TextView) convertView
                        .findViewById(R.id.txvHospitalTitle);
                holder.txvHospitalValue = (TextView) convertView
                        .findViewById(R.id.txvHospitalValue);
                holder.txvAddressTitle = (TextView) convertView
                        .findViewById(R.id.txvAddressTitle);
                holder.txvAddressValue = (TextView) convertView
                        .findViewById(R.id.txvAddressValue);
                holder.txvArgoyamitraTitle = (TextView) convertView
                        .findViewById(R.id.txvArgoyamitraTitle);
                holder.txvArgoyamitraValue = (TextView) convertView
                        .findViewById(R.id.txvArgoyamitraValue);
                holder.txvAContactTitle = (TextView) convertView
                        .findViewById(R.id.txvAContactTitle);
                holder.txvAContactValue = (TextView) convertView
                        .findViewById(R.id.txvAContactValue);
                holder.txvHAMTitle = (TextView) convertView
                        .findViewById(R.id.txvHAMTitle);
                holder.txvHAMValue = (TextView) convertView
                        .findViewById(R.id.txvHAMValue);
                holder.txvHAMContactTitle = (TextView) convertView
                        .findViewById(R.id.txvHAMContactTitle);
                holder.txvHAMContactValue = (TextView) convertView
                        .findViewById(R.id.txvHAMContactValue);
                holder.llDetailHospital = (LinearLayout) convertView.findViewById(R.id.llDetailHospital);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.txvHospitalValue.setText(hospitalnamelist.get(position) + "(" + typelist.get(position) + ")");
            holder.txvHospitalValue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedlist.get(position).equalsIgnoreCase("true"))
                        selectedlist.set(position, "false");
                    else {
                        selectedlist.set(position, "true");
                    }
                    notifyDataSetChanged();
                }
            });
            holder.txvHospitalTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedlist.get(position).equalsIgnoreCase("true"))
                        selectedlist.set(position, "false");
                    else {
                        selectedlist.set(position, "true");
                    }
                    notifyDataSetChanged();
                }
            });
            if (selectedlist.get(position).equalsIgnoreCase("false")) {
                holder.llDetailHospital.setVisibility(View.GONE);
            } else {
                holder.llDetailHospital.setVisibility(View.VISIBLE);
            }
            if (address1_list.get(position).isEmpty() && address2_list.get(position).isEmpty()) {
                holder.txvAddressValue.setText(address_list.get(position));
            } else if (address1_list.get(position).isEmpty() && !address2_list.get(position).isEmpty()) {
                holder.txvAddressValue.setText(address_list.get(position) + "\n" + address2_list.get(position));
            } else if (!address1_list.get(position).isEmpty() && address2_list.get(position).isEmpty()) {
                holder.txvAddressValue.setText(address_list.get(position) + "\n" + address1_list.get(position));
            } else {
                holder.txvAddressValue.setText(address_list.get(position) + "\n" + address1_list.get(position) + "\n" + address2_list.get(position));
            }
            if (arogyamitra.get(position).isEmpty()) {
                holder.txvArgoyamitraValue.setVisibility(View.GONE);
            } else {
                holder.txvArgoyamitraValue.setVisibility(View.VISIBLE);
                holder.txvArgoyamitraValue.setText(arogyamitra.get(position));
            }
            if (amnumber.get(position).isEmpty()) {
                holder.txvAContactValue.setVisibility(View.GONE);
            } else {
                holder.txvAContactValue.setVisibility(View.VISIBLE);
                holder.txvAContactValue.setText(amnumber.get(position));
            }
            if (ham.get(position).isEmpty()) {
                holder.txvHAMValue.setVisibility(View.GONE);
            } else {
                holder.txvHAMValue.setVisibility(View.VISIBLE);
                holder.txvHAMValue.setText(ham.get(position));
            }
            if (hamnumber.get(position).isEmpty()) {
                holder.txvHAMContactValue.setVisibility(View.GONE);
            } else {
                holder.txvHAMContactValue.setVisibility(View.VISIBLE);
                holder.txvHAMContactValue.setText(hamnumber.get(position));
            }

            return convertView;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return address_list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        class ViewHolder {
            TextView txvHospitalTitle, txvHospitalValue, txvAddressTitle, txvAddressValue, txvArgoyamitraTitle, txvArgoyamitraValue,
                    txvAContactTitle, txvAContactValue, txvHAMTitle, txvHAMValue, txvHAMContactTitle, txvHAMContactValue;
            LinearLayout llDetailHospital;
        }
    }
}
