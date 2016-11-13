package com.ncode.mukhyamantriamrutum;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MyAccountActivity extends AppCompatActivity {
    TextView txvFamilyDetailsValue, txvName, txvAddress, txvFemale, txvDOI, txvAvailableBalance, txvCardStatus;
    LinearLayout llFamilyDetails;
    Button btnSubmit;
    EditText edtDOB, edtUID;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    Spinner spnUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        txvFamilyDetailsValue = (TextView) findViewById(R.id.txvFamilyDetailsValue);
        txvName = (TextView) findViewById(R.id.txvName);
        txvAddress = (TextView) findViewById(R.id.txvAddress);
        txvFemale = (TextView) findViewById(R.id.txvFemale);
        txvDOI = (TextView) findViewById(R.id.txvDOI);
        txvAvailableBalance = (TextView) findViewById(R.id.txvAvailableBalance);
        txvCardStatus = (TextView) findViewById(R.id.txvCardStatus);

        llFamilyDetails = (LinearLayout) findViewById(R.id.llFamilyDetails);

        spnUID = (Spinner) findViewById(R.id.spnUID);
        edtDOB = (EditText) findViewById(R.id.edtDOB);
        edtUID = (EditText) findViewById(R.id.edtUID);
        edtDOB.setInputType(InputType.TYPE_NULL);
        edtDOB.requestFocus();

        llFamilyDetails.setVisibility(View.GONE);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUID.getText().toString().isEmpty()) {
                    Toast.makeText(MyAccountActivity.this, "Please enter UID", Toast.LENGTH_LONG).show();
                } else {
                    llFamilyDetails.setVisibility(View.VISIBLE);
                    getMessages(spnUID.getSelectedItemPosition());
                }
            }
        });


        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Account");


        edtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(MyAccountActivity.this, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);

                        edtDOB.setText(dateFormatter.format(newDate.getTime()));
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        spnUID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                             @Override
                                             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                 if (!edtUID.getText().toString().isEmpty()) {
                                                     if (position < 2)
                                                         getMessages(position);
                                                 }
                                             }

                                             @Override
                                             public void onNothingSelected(AdapterView<?> parent) {

                                             }
                                         }

        );
    }

    public void getMessages(int spnPos) {
        String tag_json_obj = "json_obj_req";
        //http://www.magujarat.com/AndroidMobileWebAPI_UAT/Operations.asmx/GetLocationByLatLong?Lat=23.049260539165445&Long=72.55894
        String url = "";
        if (spnPos == 0)
            url = ApplicationData.searchURL + "GetDataByMAURN?MAURN=" + edtUID.getText().toString();
        else if (spnPos == 1)
            url = ApplicationData.searchURL + "GetDataByUWINNo?UWINNo=" + edtUID.getText().toString();
        Log.e("url", url + "");
        final ProgressDialog mProgressDialog = new ProgressDialog(MyAccountActivity.this);
        mProgressDialog.setTitle("");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e("forward_offer response", "" + response);
                        try {
                            mProgressDialog.dismiss();

                            JSONObject object = new JSONObject(response.toString());

                            String msg = object.getString("msg");

                            if (msg.equalsIgnoreCase("success")) {
                                JSONArray datarray = object.getJSONArray("data");
                                JSONObject dataob = datarray.getJSONObject(0);
                                txvName.setText(dataob.getString("Fullname"));
                                txvAddress.setText(dataob.getString("Door_HouseNo") + dataob.getString("Address"));
                                txvAvailableBalance.setText(dataob.getString("AvailableBalance"));
                                txvCardStatus.setText(dataob.getString("CardStatus"));
                                txvDOI.setText(dataob.getString("CreatedDate"));
                                txvFamilyDetailsValue.setText(dataob.getString("MemberDetails").replace(",", ",\n"));
                                txvFemale.setText(dataob.getString("Gender"));
                            } else {
                                Toast.makeText(MyAccountActivity.this,
                                        "Sorry! we can't get your messages. \n Please try again!",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception error) {
                            mProgressDialog.dismiss();
                            error.printStackTrace();
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(MyAccountActivity.this, "Please check your internet connection!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MyAccountActivity.this, "Something is wrong Please try again!", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(MyAccountActivity.this, "Please check your internet connection!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MyAccountActivity.this, "Something is wrong Please try again!", Toast.LENGTH_LONG).show();
                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        // Adding request to request queue
        ApplicationData.getInstance().addToRequestQueue(jsonObjReq,
                tag_json_obj);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
