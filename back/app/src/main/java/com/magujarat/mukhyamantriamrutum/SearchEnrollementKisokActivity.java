package com.ncode.mukhyamantriamrutum;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;


public class SearchEnrollementKisokActivity extends AppCompatActivity {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "CALL";
    private static final String ARG_PARAM2 = "SELECT_LIST";
    private static final String ARG_PARAM3 = "DISTRICT_LIST";
    private static final String ARG_PARAM4 = "TALUKA_LIST";
    private static final String ARG_PARAM5 = "ADDRESS_LIST";
    private static final String ARG_PARAM6 = "ADDRESS1_LIST";
    private static final String ARG_PARAM7 = "ADDRESS2_LIST";
    private static final String ARG_PARAM8 = "EXECUTIVENAME_LIST";
    private static final String ARG_PARAM9 = "MOBILE_LIST";
    Spinner spinner1, spinner2, spinner3;
    ListView lvDetailView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private ArrayList<String> mParam2, mParam3, mParam4, mParam5, mParam6, mParam7, mParam8, mParam9;

    public SearchEnrollementKisokActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisok_search);
        //  View view = inflater.inflate(R.layout.activity_kisok_search, container, false);
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

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        lvDetailView = (ListView) findViewById(R.id.listview);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Enrollment Kiosk");

        Set<String> selectUnique = new TreeSet<String>(mParam2);
        ArrayList<String> selectList = new ArrayList<String>();
        selectList.addAll(selectUnique);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchEnrollementKisokActivity.this, android.R.layout.simple_spinner_item, selectList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> district = new ArrayList<String>();
                String getSelectKisok = spinner1.getSelectedItem().toString();
                for (int i = 0; i < mParam2.size(); i++) {
                    if (getSelectKisok.equalsIgnoreCase(mParam2.get(i))) {
                        district.add(mParam3.get(i));
                    }
                }

                Set<String> districtUnique = new TreeSet<String>(district);
                ArrayList<String> districtList = new ArrayList<String>();
                districtList.addAll(districtUnique);
                ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(SearchEnrollementKisokActivity.this, android.R.layout.simple_spinner_item, districtList);
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
                ArrayList<String> taluka = new ArrayList<String>();
                String getSelectKisok = spinner1.getSelectedItem().toString();
                String getSelectedDistrict = spinner2.getSelectedItem().toString();
                for (int i = 0; i < mParam4.size(); i++) {
                    if (getSelectKisok.equalsIgnoreCase(mParam2.get(i)) && getSelectedDistrict.equalsIgnoreCase(mParam3.get(i))) {
                        taluka.add(mParam4.get(i));
                    }
                }

                Set<String> talukaUnique = new TreeSet<String>(taluka);
                ArrayList<String> talukaList = new ArrayList<String>();
                talukaList.addAll(talukaUnique);
                ArrayAdapter<String> talukaAdapter = new ArrayAdapter<String>(SearchEnrollementKisokActivity.this, android.R.layout.simple_spinner_item, talukaList);
                talukaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner3.setAdapter(talukaAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> addresslist = new ArrayList<String>();
                ArrayList<String> address1list = new ArrayList<String>();
                ArrayList<String> address2list = new ArrayList<String>();
                ArrayList<String> executiveNameList = new ArrayList<String>();
                ArrayList<String> mobilelist = new ArrayList<String>();

                String getSelectKisok = spinner1.getSelectedItem().toString();
                String getSelectedDistrict = spinner2.getSelectedItem().toString();
                String getSelectedTaluka = spinner3.getSelectedItem().toString();
                for (int i = 0; i < mParam4.size(); i++) {
                    if (getSelectKisok.equalsIgnoreCase(mParam2.get(i)) &&
                            getSelectedDistrict.equalsIgnoreCase(mParam3.get(i)) &&
                            getSelectedTaluka.equalsIgnoreCase(mParam4.get(i))) {
                        addresslist.add(mParam5.get(i));
                        address1list.add(mParam6.get(i));
                        address2list.add(mParam7.get(i));
                        executiveNameList.add(mParam8.get(i));
                        mobilelist.add(mParam9.get(i));
                    }
                }
                DetailDataAdapter adapter = new DetailDataAdapter(addresslist, address1list, address2list, executiveNameList, mobilelist);
                lvDetailView.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //  return view;
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

    public class DetailDataAdapter extends BaseAdapter {

        LayoutInflater inflater;
        ArrayList<String> address_list, address1_list, address2_list, executivename_list, mobile_list;

        int m = 0;

        public DetailDataAdapter(ArrayList<String> address_list,
                                 ArrayList<String> address1_list, ArrayList<String> address2_list,
                                 ArrayList<String> executivename_list, ArrayList<String> mobile_list) {
            // TODO Auto-generated constructor stub
            this.address_list = address_list;
            this.address1_list = address1_list;
            this.address2_list = address2_list;
            this.executivename_list = executivename_list;
            this.mobile_list = mobile_list;
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            // TODO Auto-generated method stub
            // View view = null;

            ViewHolder holder;
            if (convertView == null) {

                convertView = inflater.inflate(R.layout.row_kisok_search_item_list,
                        parent, false);
                holder = new ViewHolder();
                holder.txvAddressTitle = (TextView) convertView
                        .findViewById(R.id.txvAddressTitle);
                holder.txvAddressValue = (TextView) convertView
                        .findViewById(R.id.txvAddressValue);
                holder.txvKisokExecutiveTitle = (TextView) convertView
                        .findViewById(R.id.txvKisokExecutiveTitle);
                holder.txvKisokExecutiveValue = (TextView) convertView
                        .findViewById(R.id.txvKisokExecutiveValue);
                holder.txvContactTitle = (TextView) convertView
                        .findViewById(R.id.txvContactTitle);
                holder.txvContactValue = (TextView) convertView
                        .findViewById(R.id.txvContactValue);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
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
            holder.txvKisokExecutiveValue.setText(executivename_list.get(position));
            holder.txvContactValue.setText(mobile_list.get(position));
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
            TextView txvAddressTitle, txvAddressValue, txvKisokExecutiveTitle, txvKisokExecutiveValue,
                    txvContactTitle, txvContactValue;
        }
    }
}
