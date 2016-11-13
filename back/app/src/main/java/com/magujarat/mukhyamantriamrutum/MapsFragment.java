package com.ncode.mukhyamantriamrutum;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.HashMap;

public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnCameraChangeListener, LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap googleMap;
    ExpandableListView offerList;
    ListView messageList;
    ArrayList<String> offerId, offerName, offerDesc, offerDistance, offerInvolvePeople,
            latList, longList, sellerid, offerType, category_id, imageCount;
    HashMap<String, ArrayList<String>> offerImage;

    GPSTracker gpsTracker;

    private static final int MILLISECONDS_PER_SECOND = 1000;
    public static final int UPDATE_INTERVAL_IN_SECONDS = 5;
    private static final long UPDATE_INTERVAL = MILLISECONDS_PER_SECOND
            * UPDATE_INTERVAL_IN_SECONDS;
    private static final int FASTEST_INTERVAL_IN_SECONDS = 1;
    private static final long FASTEST_INTERVAL = MILLISECONDS_PER_SECOND
            * FASTEST_INTERVAL_IN_SECONDS;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mLocationClient = null;
    private LocationRequest mLocationRequest = null;
    static LatLng currentLatLang;
    ArrayList<String> shareUserID, shareLatitude, shareLongitude, shareComment, shareUserName, shareTripID, sharing_type;

    private boolean servicesConnected() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getActivity());
        if (ConnectionResult.SUCCESS == resultCode) {
            Log.d("Location Updates", "Google Play services is available.");
            return true;
        } else {
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
                    resultCode, getActivity(), CONNECTION_FAILURE_RESOLUTION_REQUEST);
            if (errorDialog != null) {
                errorDialog.show();
            }
            return false;
        }
    }
    public static MapsFragment newInstance() {

        MapsFragment fragment = new MapsFragment();
//        Bundle args = new Bundle();
//        args.putString(CALL, calltype);
//        args.putStringArrayList(MENU_NAME_LIST, NameList);
//        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_maps, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        messageList = (ListView) v.findViewById(R.id.lvMessageList);
        gpsTracker = new GPSTracker(getActivity());


        mLocationClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        servicesConnected();
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        ArrayList<String> name = new ArrayList<String>();
        name.add("Civil Hospital");
        name.add("Vadilal sarabhai hospital");
        name.add("HCG");
        ArrayList<String> latitude = new ArrayList<String>();
        latitude.add("23.049739");
        latitude.add("23.020765");
        latitude.add("23.028786");
        ArrayList<String> longitude = new ArrayList<String>();
        longitude.add("72.603172");
        longitude.add("72.571714");
        longitude.add("72.564279");
        MessageLocationAdapter adapter = new MessageLocationAdapter(
                name, latitude, longitude
        );
        messageList.setAdapter(adapter);


        // getMessages();
        return v;

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
        this.googleMap = googleMap;
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        this.googleMap.setMyLocationEnabled(true);

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
    public void onConnected(Bundle bundle) {
        try {
            Location mLastLocation = LocationServices.FusedLocationApi
                    .getLastLocation(mLocationClient);
            takeToLocation(convertLocationtoLatLang(mLastLocation));

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

/*
    public void getMessages() {
        String tag_json_obj = "json_obj_req";
        String url = ApplicationData.serviceURL + "get_seller_trip_location.php";
        Log.e("url", url + "");
        final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle("");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        StringRequest jsonObjReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e("forward_offer response", "" + response);
                        try {
                            mProgressDialog.dismiss();


                            shareUserID = new ArrayList<String>();
                            shareLatitude = new ArrayList<String>();
                            shareLongitude = new ArrayList<String>();
                            shareComment = new ArrayList<String>();
                            shareUserName = new ArrayList<String>();
                            shareTripID = new ArrayList<String>();
                            sharing_type = new ArrayList<String>();

                            JSONObject object = new JSONObject(response.toString());

                            String msg = object.getString("msg");

                            if (msg.equalsIgnoreCase("Success")) {
                                JSONArray datarray = object.getJSONArray("data");
                                // Log.e("array length", "" + datarray.length());
                                for (int i = 0; i < datarray.length(); i++) {
                                    JSONObject dataOb = datarray.getJSONObject(i);
                                    shareUserID.add(dataOb.getString("userid"));
                                    shareLatitude.add(dataOb.getString("latitude"));
                                    shareLongitude.add(dataOb.getString("longitude"));
                                    shareComment.add(dataOb.getString("comments"));
                                    shareUserName.add(dataOb.getString("username"));
                                    shareTripID.add(dataOb.getString("share_trip_id"));
                                    sharing_type.add(dataOb.getString("sharing_type"));
                                }
                                MessageLocationAdapter adapter = new MessageLocationAdapter(
                                        shareUserID, shareLatitude, shareLongitude,
                                        shareComment, shareUserName, shareTripID,
                                        sharing_type);
                                messageList.setAdapter(adapter);
                            } else {
                                Toast.makeText(getActivity(),
                                        "Sorry! we can't get your messages. \n Please try again!",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception error) {
                            mProgressDialog.dismiss();
                            error.printStackTrace();
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(getActivity(), "Please check your internet connection!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getActivity(), "Something is wrong Please try again!", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getActivity(), "Please check your internet connection!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Something is wrong Please try again!", Toast.LENGTH_LONG).show();
                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences mPrefs = getActivity().getSharedPreferences("LOGIN_DETAIL",
                        getActivity().MODE_PRIVATE);
                String user_id = mPrefs.getString("USER_ID", "");
                Map<String, String> params = new HashMap<String, String>();
                params.put("userid", "" + user_id);
                return params;
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        // Adding request to request queue
        ApplicationData.getInstance().addToRequestQueue(jsonObjReq,
                tag_json_obj);


    }
*/

    public class MessageLocationAdapter extends BaseAdapter {

        LayoutInflater inflater;
        ArrayList<String> Hospitalname,shareLatitude, shareLongitude;
        //shareUserID, shareComment, shareUserName, shareTripId, shareType;

        int m = 0;

        public MessageLocationAdapter(ArrayList<String> hosptialName,
                                      ArrayList<String> shareLatitude,
                                      ArrayList<String> shareLongitude
        ) {
            // TODO Auto-generated constructor stub
            Hospitalname=hosptialName;
            this.shareLatitude = shareLatitude;
            this.shareLongitude = shareLongitude;
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        class ViewHolder {
            TextView txvShareDesc;
            ImageView messageIcon;
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
    }
}
