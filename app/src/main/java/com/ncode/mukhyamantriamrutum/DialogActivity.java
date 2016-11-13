package com.ncode.mukhyamantriamrutum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class DialogActivity extends Activity {
    TextView txvHeader;
    ListView lvMenuList;
    static String calltype = "";
    static ArrayList<String> NameList, GRLIST, HOSPITALLIST, ENROLLMENTLIST, HEALTHCAMPLIST, GRLIST1,
            GRLIST2, HOSPITALLIST1, HEALTHCAMPLIST1, HEALTHCAMPLIST2 ,OTHER_PACKAGE_LIST;
    static boolean yearAdd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        txvHeader = (TextView) findViewById(R.id.txvHeader);
        lvMenuList = (ListView) findViewById(R.id.lvMenuList);

        Intent i = getIntent();
        calltype = i.getStringExtra("CALL");
        if (calltype.equalsIgnoreCase("ABOUT")) {
            txvHeader.setText("ABOUT");
        } else if (calltype.equalsIgnoreCase("NETWORK HOSPTIAL")) {
            txvHeader.setText("NETWORK HOSPTIAL");
        } else if (calltype.equalsIgnoreCase("CONTACT")) {
            txvHeader.setText("CONTACT");
        } else if (calltype.equalsIgnoreCase("PACKAGE RATES")) {
            txvHeader.setText("PACKAGE RATES");
            OTHER_PACKAGE_LIST=i.getStringArrayListExtra("OTHER_PACKAGE_LIST");
        } else if (calltype.equalsIgnoreCase("FAQ'S")) {
            txvHeader.setText("FAQ'S");
        } else if (calltype.equalsIgnoreCase("GALLERY")) {
            txvHeader.setText("GALLERY");
        } else if (calltype.equalsIgnoreCase("POLICY AND GUIDELINES")) {
            txvHeader.setText("POLICY AND GUIDELINES");

            GRLIST = i.getStringArrayListExtra("GR_LIST");
            HOSPITALLIST = i.getStringArrayListExtra("HOSPITAL_LIST");
            ENROLLMENTLIST = i.getStringArrayListExtra("ENROLLMENT_LIST");
            HEALTHCAMPLIST = i.getStringArrayListExtra("HEALTHCAMP_LIST");

            GRLIST1 = i.getStringArrayListExtra("GR_LIST1");
            GRLIST2 = i.getStringArrayListExtra("GR_LIST2");

            HOSPITALLIST1 = i.getStringArrayListExtra("HOSPITAL_LIST1");

            HEALTHCAMPLIST1 = i.getStringArrayListExtra("HEALTHCAMP_LIST1");
            HEALTHCAMPLIST2 = i.getStringArrayListExtra("HEALTHCAMP_LIST2");
        }


        DialogListAdapter adapter = new DialogListAdapter(i.getStringArrayListExtra("MENU_NAME_LIST"));
        lvMenuList.setAdapter(adapter);

        lvMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("data select ", "" + NameList.get(position));
                // if (calltype.equalsIgnoreCase("POLICY AND GUIDELINES")) {
                if (NameList.get(position).equalsIgnoreCase("GR's")) {
                    DialogListAdapter adapter = new DialogListAdapter(GRLIST);
                    lvMenuList.setAdapter(adapter);
                    yearAdd = false;
                } else if (NameList.get(position).equalsIgnoreCase("Hospital Empanelment")) {
                    DialogListAdapter adapter = new DialogListAdapter(HOSPITALLIST);
                    lvMenuList.setAdapter(adapter);
                    yearAdd = false;
                } else if (NameList.get(position).equalsIgnoreCase("Enrollment Kiosk")) {
                    DialogListAdapter adapter = new DialogListAdapter(ENROLLMENTLIST);
                    lvMenuList.setAdapter(adapter);
                    yearAdd = false;
                } else if (NameList.get(position).equalsIgnoreCase("Health Camps")) {
                    DialogListAdapter adapter = new DialogListAdapter(HEALTHCAMPLIST);
                    lvMenuList.setAdapter(adapter);
                    yearAdd = false;
                } else if (NameList.get(position).equalsIgnoreCase("MA YOJANA")) {
                    DialogListAdapter adapter = new DialogListAdapter(GRLIST1);
                    lvMenuList.setAdapter(adapter);
                    yearAdd = false;
                } else if (NameList.get(position).equalsIgnoreCase("MAV YOJANA")) {
                    DialogListAdapter adapter = new DialogListAdapter(GRLIST2);
                    lvMenuList.setAdapter(adapter);
                    yearAdd = false;
                } else if (NameList.get(position).equalsIgnoreCase("RFP of MA Yojana")) {
                    DialogListAdapter adapter = new DialogListAdapter(HOSPITALLIST1);
                    lvMenuList.setAdapter(adapter);
                    yearAdd = false;
                } else if (NameList.get(position).equalsIgnoreCase("Schedules")) {
                    DialogListAdapter adapter = new DialogListAdapter(HEALTHCAMPLIST1);
                    lvMenuList.setAdapter(adapter);
                    yearAdd = true;
                } else if (NameList.get(position).equalsIgnoreCase("Mega Health Camp Reports")) {
                    DialogListAdapter adapter = new DialogListAdapter(HEALTHCAMPLIST2);
                    lvMenuList.setAdapter(adapter);
                    yearAdd = true;
                }else if (NameList.get(position).equalsIgnoreCase("Other Package Rates")) {
                    DialogListAdapter adapter = new DialogListAdapter(OTHER_PACKAGE_LIST);
                    lvMenuList.setAdapter(adapter);
                    yearAdd = false;
                } else {
                    Intent i = new Intent();
                    i.putExtra("CALL_RETURN", calltype);
                    i.putExtra("HEADER", "" + NameList.get(position));
                    setResult(10, i);
                    finish();
                }
            }
        });
    }

    public class DialogListAdapter extends BaseAdapter {
        LayoutInflater inflater;
        Context context;


        public DialogListAdapter(ArrayList<String> namelist) {
            NameList = namelist;
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return NameList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.row_menu_list, null);
                holder = new ViewHolder();
                holder.txvMenuName = (TextView) convertView.findViewById(R.id.txvMenuName);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (NameList.get(position).substring(0, 3).equalsIgnoreCase("2013") ||
                    NameList.get(position).substring(0, 3).equalsIgnoreCase("2014") ||
                    NameList.get(position).substring(0, 3).equalsIgnoreCase("2015"))
                holder.txvMenuName.setText(NameList.get(position).substring(4, NameList.get(position).length()));
            else if(NameList.get(position).equalsIgnoreCase("FAQMa Yojana")){
                holder.txvMenuName.setText("Ma Yojana");
            }else
                holder.txvMenuName.setText(NameList.get(position));

            return convertView;
        }

        class ViewHolder {
            TextView txvMenuName;
        }

    }
}
