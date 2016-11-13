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

public class SearchDialogActivity extends Activity {
    static ArrayList<String> NameList;
    TextView txvHeader;
    ListView lvMenuList;
    String call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        txvHeader = (TextView) findViewById(R.id.txvHeader);
        lvMenuList = (ListView) findViewById(R.id.lvMenuList);

        Intent i = getIntent();
        call = i.getStringExtra("CALL");
        if (call.equalsIgnoreCase("CLUSTER")) {
            txvHeader.setText("Cluster Wise Empanelled Hospitals");
        }else{
            txvHeader.setText("Search");
        }
        DialogListAdapter adapter = new DialogListAdapter(i.getStringArrayListExtra("MENU_NAME_LIST"));
        lvMenuList.setAdapter(adapter);

        lvMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("data select ", "" + NameList.get(position));
                // if (calltype.equalsIgnoreCase("POLICY AND GUIDELINES")) {
                if (call.equalsIgnoreCase("CLUSTER")) {
                    Intent i = new Intent();
                    i.putExtra("HEADER", "" + NameList.get(position));
                    setResult(15, i);
                    finish();
                } else {
                    Intent i = new Intent();
                    i.putExtra("HEADER", "" + NameList.get(position));
                    setResult(11, i);
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

            holder.txvMenuName.setText(NameList.get(position));

            return convertView;
        }

        class ViewHolder {
            TextView txvMenuName;
        }

    }
}
