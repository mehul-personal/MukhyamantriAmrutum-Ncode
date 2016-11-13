package com.ncode.mukhyamantriamrutum;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnCameraChangeListener, LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    public static final int UPDATE_INTERVAL_IN_SECONDS = 5;
    private static final int MILLISECONDS_PER_SECOND = 1000;
    private static final long UPDATE_INTERVAL = MILLISECONDS_PER_SECOND
            * UPDATE_INTERVAL_IN_SECONDS;
    private static final int FASTEST_INTERVAL_IN_SECONDS = 1;
    private static final long FASTEST_INTERVAL = MILLISECONDS_PER_SECOND
            * FASTEST_INTERVAL_IN_SECONDS;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 1;
    private static final int REQUEST_CODE_PERMISSION_LOCATION2 = 2;
    private static final int REQUEST_CODE_PERMISSION_LOCATION3 = 3;
    public static Location mLastLocation = null;
    static LatLng currentLatLang;
    ListView messageList, lvMenuList;
    GPSTracker gpsTracker;
    LinearLayout mapLayout;
    int count = 1;
    static int selectedLocation;
    private GoogleMap googleMap;
    private GoogleApiClient mLocationClient = null;
    private LocationRequest mLocationRequest = null;

    private boolean servicesConnected() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(MapsActivity.this);
        if (ConnectionResult.SUCCESS == resultCode) {
            Log.d("Location Updates", "Google Play services is available.");
            return true;
        } else {
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
                    resultCode, MapsActivity.this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            if (errorDialog != null) {
                errorDialog.show();
            }
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Hospital Near By Me");
        Log.e("oncreate", "oncreate");
        messageList = (ListView) findViewById(R.id.lvMessageList);
        lvMenuList = (ListView) findViewById(R.id.lvMenuList);
        mapLayout = (LinearLayout) findViewById(R.id.llMapLayout);
        mapLayout.setVisibility(View.GONE);
        lvMenuList.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}
                        , REQUEST_CODE_PERMISSION_LOCATION3);
            } else {
                gpsTracker = new GPSTracker(MapsActivity.this);

                ArrayList<String> typeOfHospital = new ArrayList<String>();
                typeOfHospital.add("Empanelled Hospital");
                typeOfHospital.add("Dialysis Center");
                // typeOfHospital.add("Enrollment Kiosk");
                DialogListAdapter dialogAdapter = new DialogListAdapter(typeOfHospital);
                lvMenuList.setAdapter(dialogAdapter);
            }
        } else {
            gpsTracker = new GPSTracker(MapsActivity.this);

            ArrayList<String> typeOfHospital = new ArrayList<String>();
            typeOfHospital.add("Empanelled Hospital");
            typeOfHospital.add("Dialysis Center");
            // typeOfHospital.add("Enrollment Kiosk");
            DialogListAdapter dialogAdapter = new DialogListAdapter(typeOfHospital);
            lvMenuList.setAdapter(dialogAdapter);
        }

        mLocationClient = new GoogleApiClient.Builder(MapsActivity.this)
                .addApi(LocationServices.API).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        servicesConnected();
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

//        ArrayList<String> name = new ArrayList<String>();
//        name.add("Civil Hospital");
//        name.add("Vadilal sarabhai hospital");
//        name.add("HCG");
//        ArrayList<String> latitude = new ArrayList<String>();
//        latitude.add("23.049739");
//        latitude.add("23.020765");
//        latitude.add("23.028786");
//        ArrayList<String> longitude = new ArrayList<String>();
//        longitude.add("72.603172");
//        longitude.add("72.571714");
//        longitude.add("72.564279");


        lvMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                count = 2;
                lvMenuList.setVisibility(View.GONE);
                mapLayout.setVisibility(View.VISIBLE);
            }
        });

        // getMessages(0);
    }

    @Override
    public void onBackPressed() {
        if (count == 2) {
            lvMenuList.setVisibility(View.VISIBLE);
            mapLayout.setVisibility(View.GONE);
            count = 1;
        } else if (count == 1) {
            this.finish();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                if (count == 2) {
                    lvMenuList.setVisibility(View.VISIBLE);
                    mapLayout.setVisibility(View.GONE);
                    count = 1;
                } else if (count == 1) {
                    this.finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.e("onMapReady", "onMapReady");
        this.googleMap = googleMap;
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

//                ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}
//                        , REQUEST_CODE_PERMISSION_LOCATION);

            } else {
                this.googleMap.setMyLocationEnabled(true);
            }
        } else {
            this.googleMap.setMyLocationEnabled(true);
        }
        this.googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {


                // Getting the position from the marker
                LatLng latLng = arg0.getPosition();


                return null;
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case REQUEST_CODE_PERMISSION_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                            ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        googleMap.setMyLocationEnabled(true);
                    }
                }
                break;
            case REQUEST_CODE_PERMISSION_LOCATION2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                            ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                        gpsTracker = new GPSTracker(MapsActivity.this);

//                        ArrayList<String> typeOfHospital = new ArrayList<String>();
//                        typeOfHospital.add("Empanelled Hospital");
//                        typeOfHospital.add("Dialysis Center");
//                        typeOfHospital.add("Enrollment Kiosk");
//                        DialogListAdapter dialogAdapter = new DialogListAdapter(typeOfHospital);
//                        lvMenuList.setAdapter(dialogAdapter);

                        mLastLocation = LocationServices.FusedLocationApi
                                .getLastLocation(mLocationClient);
                    }
                }

                break;
            case REQUEST_CODE_PERMISSION_LOCATION3:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                            ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        gpsTracker = new GPSTracker(MapsActivity.this);

                        ArrayList<String> typeOfHospital = new ArrayList<String>();
                        typeOfHospital.add("Empanelled Hospital");
                        typeOfHospital.add("Dialysis Center");
                        //  typeOfHospital.add("Enrollment Kiosk");
                        DialogListAdapter dialogAdapter = new DialogListAdapter(typeOfHospital);
                        lvMenuList.setAdapter(dialogAdapter);
                    }
                }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMessages(selectedLocation);
    }

    @Override
    public void onConnected(Bundle bundle) {
        try {
            Log.e("onconnected", "onconnected");
            if (Build.VERSION.SDK_INT >= 23) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}
//                            , REQUEST_CODE_PERMISSION_LOCATION2);

                } else {
                    mLastLocation = LocationServices.FusedLocationApi
                            .getLastLocation(mLocationClient);
                    takeToLocation(convertLocationtoLatLang(mLastLocation));
                }
            } else {
//                gpsTracker = new GPSTracker(MapsActivity.this);
//
//                ArrayList<String> typeOfHospital = new ArrayList<String>();
//                typeOfHospital.add("Empanelled Hospital");
//                typeOfHospital.add("Dialysis Center");
//                typeOfHospital.add("Enrollment Kiosk");
//                DialogListAdapter dialogAdapter = new DialogListAdapter(typeOfHospital);
//                lvMenuList.setAdapter(dialogAdapter);

                mLastLocation = LocationServices.FusedLocationApi
                        .getLastLocation(mLocationClient);
                takeToLocation(convertLocationtoLatLang(mLastLocation));
            }

        } catch (Exception e) {
            Log.e("error current location", e + "");
        }
    }

    public void takeToLocation(LatLng toBeLocationLatLang) {
        if (toBeLocationLatLang != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(
                    toBeLocationLatLang, 16);
            googleMap.animateCamera(update);

        }
    }

    private LatLng convertLocationtoLatLang(Location location) {
        currentLatLang = new LatLng(location.getLatitude(),
                location.getLongitude());

        Log.e("CURRENT LATLONG", currentLatLang.toString());

        return currentLatLang;

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        getMessages(selectedLocation);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onStart() {
        super.onStart();
        mLocationClient.connect();
    }

    @Override
    public void onStop() {
        mLocationClient.disconnect();
        super.onStop();
    }

    public void getMessages(int select) {
        String tag_json_obj = "json_obj_req";
        //http://www.magujarat.com/AndroidMobileWebAPI_UAT/Operations.asmx/GetLocationByLatLong?Lat=23.049260539165445&Long=72.55894
        String url = "";
        if (gpsTracker.getLatitude() < 0) {
            Toast.makeText(MapsActivity.this,
                    "Sorry! we are troubling to get your location \n Please start your GPS!",
                    Toast.LENGTH_SHORT).show();
        } else {
            if (select == 0)
                url = ApplicationData.searchURL + "GetLocationByLatLong?Lat=" + gpsTracker.getLatitude() + "&Long=" + gpsTracker.getLongitude();
            else
                url = ApplicationData.searchURL + "GetDialysisCenterLocationByLatLong?Lat=" + gpsTracker.getLatitude() + "&Long=" + gpsTracker.getLongitude();
            Log.e("url", url + "");
            final ProgressDialog mProgressDialog = new ProgressDialog(MapsActivity.this);
            mProgressDialog.setTitle("");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage("Please Wait...");
            mProgressDialog.show();
            StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            Log.e("forward_offer response", ":>" + response);
                            try {
                                mProgressDialog.dismiss();

                                ArrayList<String> name = new ArrayList<String>();
                                ArrayList<String> lat = new ArrayList<String>();
                                ArrayList<String> lng = new ArrayList<String>();
                                ArrayList<String> address1 = new ArrayList<String>();
                                ArrayList<String> address2 = new ArrayList<String>();
                                ArrayList<String> address3 = new ArrayList<String>();

                                JSONObject object = new JSONObject(response.toString());

                                String msg = object.getString("msg");

                                if (msg.equalsIgnoreCase("success")) {
                                    JSONArray datarray = object.getJSONArray("data");
                                    // Log.e("array length", "" + datarray.length());
                                    for (int i = 0; i < datarray.length(); i++) {
                                        JSONObject dataOb = datarray.getJSONObject(i);
                                        name.add(dataOb.getString("HospitalName"));
                                        lat.add(dataOb.getString("Lat"));
                                        lng.add(dataOb.getString("Long"));
                                        address1.add(dataOb.getString("Address"));
                                        address2.add(dataOb.getString("Address1"));
                                        address3.add(dataOb.getString("Address2"));
                                    }
                                    mapLayout.setVisibility(View.VISIBLE);
                                    lvMenuList.setVisibility(View.GONE);

                                    MessageLocationAdapter adapter = new MessageLocationAdapter(name, lat, lng);
                                    messageList.setAdapter(adapter);
                                } else {
                                    Toast.makeText(MapsActivity.this,
                                            "Sorry! we are troubling to get list of hospitals \n Please try again!",
                                            Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception error) {
                                mProgressDialog.dismiss();
                                error.printStackTrace();
                                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                    Toast.makeText(MapsActivity.this, "Please check your internet connection!", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(MapsActivity.this, "Something is wrong Please try again!", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(MapsActivity.this, "Please check your internet connection!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MapsActivity.this, "Something is wrong Please try again!", Toast.LENGTH_LONG).show();
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

    }

    public class DialogListAdapter extends BaseAdapter {
        LayoutInflater inflater;
        Context context;
        ArrayList<String> NameList;

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
            holder.txvMenuName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 1) {
                        selectedLocation = 1;
                        getMessages(1);
                    } else {
                        selectedLocation = 0;
                        getMessages(0);
                    }
                }
            });

            return convertView;
        }

        class ViewHolder {
            TextView txvMenuName;
        }

    }

    public class MessageLocationAdapter extends BaseAdapter {

        LayoutInflater inflater;
        ArrayList<String> Hospitalname, shareLatitude, shareLongitude;

        public MessageLocationAdapter(ArrayList<String> hosptialName,
                                      ArrayList<String> shareLatitude,
                                      ArrayList<String> shareLongitude
        ) {
            // TODO Auto-generated constructor stub
            Hospitalname = hosptialName;
            this.shareLatitude = shareLatitude;
            this.shareLongitude = shareLongitude;
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void refreshList() {
            super.notifyDataSetChanged();
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            // TODO Auto-generated method stub
            // View view = null;

            ViewHolder holder;
            if (convertView == null) {

                convertView = inflater.inflate(
                        R.layout.row_message_item, parent, false);
                holder = new ViewHolder();
                holder.txvShareDesc = (TextView) convertView
                        .findViewById(R.id.txvShareLocationDescription);
                holder.messageIcon = (ImageView) convertView.findViewById(R.id.imvMessageIcon);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
//            if (shareType.get(position).equalsIgnoreCase("NAVIGATE")) {
//                holder.txvShareDesc.setText(shareUserName.get(position)
//                        + " shared location with you");
//                holder.messageIcon.setImageResource(R.drawable.ic_white_navigate);
//            } else {
//                holder.txvShareDesc.setText(shareUserName.get(position)
//                        + " shared Tracking location with you");
//                holder.messageIcon.setImageResource(R.drawable.ic_white_tracking);
//            }
            holder.txvShareDesc.setText(Hospitalname.get(position).toUpperCase());
            holder.txvShareDesc.setTextColor(getResources().getColor(R.color.colorPrimary));
            holder.txvShareDesc.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    // if (shareType.get(position).equalsIgnoreCase("NAVIGATE")) {
                    Intent intent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr="
                                    + currentLatLang.latitude + ","
                                    + currentLatLang.longitude + "&daddr="
                                    + shareLatitude.get(position) + ","
                                    + shareLongitude.get(position)));
                    intent.setClassName("com.google.android.apps.maps",
                            "com.google.android.maps.MapsActivity");
                    startActivity(intent);
//                    } else {
//                        Intent i = new Intent(getActivity(),
//                                TrackingMapActivity.class);
//                        i.putExtra("LATITUDE", shareLatitude.get(position) + "");
//                        i.putExtra("LONGITUDE", shareLongitude.get(position) + "");
//                        i.putExtra("USER_ID", shareUserID.get(position) + "");
//                        i.putExtra("TRIP_ID", shareTripId.get(position) + "");
//                        startActivity(i);
//                    }
                }
            });
            return convertView;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return Hospitalname.size();
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
            TextView txvShareDesc;
            ImageView messageIcon;
        }
    }
}
