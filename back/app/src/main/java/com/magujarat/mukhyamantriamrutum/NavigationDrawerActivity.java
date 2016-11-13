package com.ncode.mukhyamantriamrutum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        WebViewFragment.OnFragmentInteractionListener,
        AboutFragment.OnFragmentInteractionListener,
        NetworkHospitalFragment.OnFragmentInteractionListener,
        PolicyAndGuidelinesFragment.OnFragmentInteractionListener,
        GalleryFragment.OnFragmentInteractionListener,
        FAQFragment.OnFragmentInteractionListener,
        ContactFragment.OnFragmentInteractionListener,
        PackageRatesFragment.OnFragmentInteractionListener,HomeFragment.OnFragmentInteractionListener {
    static MenuItem actionViewItem;
    ViewPager pager;
    SlidingTabLayout slidingTabLayout;
    ViewPagerAdapter adapter;
    private String[] titles = new String[]{"HOME", "ABOUT", "POLICY AND GUIDELINES", "NETWORK HOSPITAL"
            , "GALLERY", "FAQ'S", "CONTACT", "PACKAGE RATES"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ic_app_small_icon);

        pager = (ViewPager) findViewById(R.id.viewpager);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        adapter = new ViewPagerAdapter(this, getSupportFragmentManager(), titles);
        pager.setAdapter(adapter);
        slidingTabLayout.setViewPager(pager);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (pager.getCurrentItem() > 1) {
                    AboutFragment.wvDateDetail.setVisibility(View.GONE);
                    AboutFragment.lvNameList.setVisibility(View.VISIBLE);
                    pager.setCurrentItem(pager.getCurrentItem());
                    adapter.getItem(pager.getCurrentItem());
                }
            }
        });

        toolbar.setBackgroundColor(getResources().getColor(R.color.material_teal));
        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.material_teal));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        navigationView.setItemTextColor(ColorStateList.valueOf(Color.parseColor("#287AA9")));

        //  selectedItem(R.id.nav_home);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pager.setCurrentItem(pager.getCurrentItem());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            //  Toast.makeText(NavigationDrawerActivity.this,"search clicked",Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        actionViewItem = menu.findItem(R.id.action_search);
        View v = MenuItemCompat.getActionView(actionViewItem);
        ImageView search = (ImageView) v.findViewById(R.id.txvSearchAction);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> search_list = new ArrayList<String>();
                search_list.add("Enrollment Kiosk");
                search_list.add("Empanelled Hospital");
                search_list.add("Dialysis Services");
                search_list.add("BPL Family");
                search_list.add("TVA");
                search_list.add("Help for Bulk Printed Cards");

                Intent i = new Intent(NavigationDrawerActivity.this, SearchDialogActivity.class);
                i.putExtra("CALL", "SEARCH");
                i.putExtra("MENU_NAME_LIST", search_list);
                startActivityForResult(i, 11);
                //Toast.makeText(NavigationDrawerActivity.this,"search clicked",Toast.LENGTH_LONG).show();
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 11) {
                if (data.getStringExtra("HEADER").equalsIgnoreCase("Enrollment Kiosk")) {
                    getKisokCenter();
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Empanelled Hospital")) {
                    getEmpanelledHospital();
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Dialysis Services")) {
                    getDialysisHospital();
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("BPL Family")) {

                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("TVA")) {

                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Help for Bulk Printed Cards")) {

                }
            }
            if (requestCode == 10) {
                if (data.getStringExtra("HEADER").equalsIgnoreCase("About MA Yojana (English)")) {
                    loadPDF("AboutMAYojanaEnglish.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("About MA Yojana (Gujarati)")) {
                    loadPDF("AboutMAYojanaGujarati.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("District wise Empanelled Hospitals")) {
                    fragmentCall("District wise Empanelled Hospitals");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Standalone Dialysis Centers")) {
                    fragmentCall("Standalone Dialysis Centers");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Executive Committee")) {
                    fragmentCall("Executive Committee");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("SNC Members")) {
                    fragmentCall("SNC Members");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Collectors")) {

                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("DDO's")) {

                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("CDHO's")) {
                    fragmentCall("CDHO's");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("THO's")) {
                    fragmentCall("THO's");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Package Rates")) {
                    loadPDF("PackageRatesMain.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("FAQMa Yojana")) {
                    fragmentCall("Ma Yojana");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Enrollment Process")) {
                    fragmentCall("Enrollment Process");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("General")) {
                    fragmentCall("General");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Service Delivery")) {
                    fragmentCall("Service Delivery");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Mukhyamantri Amrutum Yojana")) {
                    loadPDF("GR_of_Mukhyamantri_Amrutam_18-04-2012(New2014).pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Exe. Com. and SNC of MA")) {
                    loadPDF("GR_for_Exe._Com.and_SNC_of_MA_2-6-12.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("MA Verifying Authority")) {
                    loadPDF("GR_for_MA_Enrollment.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Grievance Redressal Committee")) {
                    loadPDF("GR_for_Grievance_Redresser_Committee.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("SNC Recruitment")) {
                    loadPDF("GRforSNC.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("District Authorities Approval")) {
                    loadPDF("PRAMANIT_Dakhlo.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("ASHA Incentive Letter")) {
                    loadPDF("ASHA_INCEVTE_2014-2015.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Mukhyamantri Amrutum Vatsalya Yojana")) {
                    loadPDF("13_8_2014_GR.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Mukhyamantri Amrutum Vatsalya Yojana(Extension)")) {
                    loadPDF("MAV_Tharav_26_3_2015.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Updated GR of MAV Yojana for limit of income certificate")) {
                    loadPDF("Updated_GR _MAVYojana_for_limit_of_income_certificate.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Validity of Mukhyamantri Amrutam Vatsalya Card")) {
                    loadPDF("GR_for_Validity_of_Mukhyamantri_Amrutam_Vatsalya_Card.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("RFP of HospitalEmpanelment")) {
                    loadPDF("RFPofHospitalempanelmentunderMAYojana.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("RFP of Dialysis")) {
                    loadPDF("RFPofDialysisCenter.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Hardware and Hospital Kiosk Procurement for Private Hospitals")) {
                    loadPDF("Hardware-and-Hospital-Kiosk-Procurement-for-Private-Empanelled-Hospitals.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Treatment Guidelines for Maintenance Hemodialysis")) {
                    loadPDF("Treatment_Guidlines_for_Maintainance_Hemodialysis.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("MA Card Enrollment Guidelines- Case Scenario")) {

                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Photos")) {
                    fragmentCall("Photos");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2013- Planning")) {
                    loadPDF("MAAnnualHealthCampPlanning.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2013- February")) {
                    loadPDF("Health_Camps_In_February.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2013- March")) {

                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2013- June")) {
                    loadPDF("June_Month_Health_Camp.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2013- July")) {
                    loadPDF("July_HealthCamp_Schedule.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2013- August")) {
                    loadPDF("August_Health_Camp_Tracker.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2013- September")) {
                    loadPDF("September_Health_Camp_Tracker.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2013- November")) {
                    loadPDF("November_Health_Camp.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- January")) {
                    loadPDF("January-2014_HealthCamp_Tracker.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- February")) {
                    loadPDF("JFebruary-2014 Health Camp Tracker.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- May")) {
                    loadPDF("May_2014_HealthCamp.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- June")) {
                    loadPDF("June_2014_HealthCamp.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- July")) {
                    loadPDF("July_2014_HealthCamp.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- August")) {
                    loadPDF("August_Healthcamp_2014.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- September")) {
                    loadPDF("Healthcamp_september_2014.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- October")) {
                    loadPDF("Healthcamp_October_2014.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- November")) {
                    loadPDF("Healthcamp_Novemberr_2014.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- December")) {
                    loadPDF("December_HealthCamp_2014.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2015- January")) {
                    loadPDF("HealthCamp_January_2015.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2015- February")) {
                    loadPDF("HealthCamp_February_2015.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2015- March")) {
                    loadPDF("HealthcampSchedule_March_2015.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2013- Bharuch")) {
                    loadPDF("Bharuch_Mega_Health_Camp_Report.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2013- Dangs")) {
                    loadPDF("Dang_Mega_Health_Camp_Report.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2013- Kutch")) {
                    loadPDF("Kutch_Mega_Health_Camp_Report.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2013- Narmada")) {
                    loadPDF("Mega_Health_Camp_Report_Narmada.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2013- Navsari")) {
                    loadPDF("Mega_Health_Camp_Report_Navsari.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2013- Rajkot")) {
                    loadPDF("Rajkot_Report_MAA_Camp.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2013- Sola(Ahd)")) {
                    loadPDF("Sola_Mega_Health_Camp_Report.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- Amreli")) {
                    loadPDF("Amreli_Mega_Health_Camp.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- Navsari")) {
                    loadPDF("Mega_Health_Camp_Report_Navsari.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- Porbandar")) {
                    loadPDF("Porbandar_Mega_HealthCamp_Report.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- Sabarkantha")) {
                    loadPDF("Sabarkatha_MegaCamp_Report.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- Tapi")) {
                    loadPDF("Tapi_Mega_HealthCamp_Report.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- Dahod")) {
                    loadPDF("Tapi_Mega_HealthCamp_Report.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- Panchmahal")) {
                    fragmentCall("2014- Panchmahal");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- Surat")) {
                    fragmentCall("2014- Surat");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- Valsad")) {
                    fragmentCall("2014- Valsad");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("2014- Vadodara")) {
                    fragmentCall("2014- Vadodara");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Cluster 1 - BURNS")) {
                    loadPDF("Cluster1BURNS.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Cluster 2 - CARDIOLOGY")) {
                    loadPDF("Cluster2CARDIOLOGY.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Cluster 3 - CARDIOTHORACIC SURGERY")) {
                    loadPDF("Cluster3CARDIOTHORACICSURGERY.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Cluster 4 - CARDIOVASCULAR SURGERY")) {
                    loadPDF("Cluster4CARDIOVASCULARSURGERY.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Cluster 5 - RENAL")) {
                    loadPDF("Cluster5RENAL.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Cluster 6 - NEUROSURGERY")) {
                    loadPDF("Cluster6NEUROSURGERY.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Cluster 7 - PAEDIATRIC SURGERY")) {
                    loadPDF("Cluster7PAEDIATRICSURGERY.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Cluster 8 - POLYTRAUMA")) {
                    loadPDF("Cluster8POLYTRAUMA.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Cluster 9 - MEDICAL ONCOLOGY")) {
                    loadPDF("Cluster9MEDICALONCOLOGY.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Cluster 10 - RADIATION ONCOLOGY")) {
                    loadPDF("Cluster10RADIATIONONCOLOGY.pdf");
                } else if (data.getStringExtra("HEADER").equalsIgnoreCase("Cluster 11 - SURGICAL ONCOLOGY")) {
                    loadPDF("Cluster11SURGICALONCOLOGY.pdf");
                }

            }
        }
    }

    public void loadPDF(String PDFname) {
        Fragment defineDeliveryFragment = new WebViewFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmContentLayout, defineDeliveryFragment);
        Bundle bundle = new Bundle();
        bundle.putString("CALL", "PDF");
        bundle.putString("URL", ApplicationData.serviceURL + "PDF/" + PDFname);
        defineDeliveryFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    public void fragmentCall(String name) {
        Fragment defineDeliveryFragment = new WebViewFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmContentLayout, defineDeliveryFragment);
        Bundle bundle = new Bundle();
        bundle.putString("CALL", "FILE");
        bundle.putString("FILE_NAME", name);
        defineDeliveryFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    private void CopyReadAssets(String filename) {
        AssetManager assetManager = getAssets();
        InputStream in = null;
        OutputStream out = null;
        String extStorageDirectory = Environment.getExternalStorageDirectory()
                .toString();
        File folder = new File(extStorageDirectory, "Amrutum");
        folder.mkdir();
        File file = new File(folder, filename);
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            in = assetManager.open(filename);
            out = new FileOutputStream(extStorageDirectory + "/Amrutum/" + filename);
            copyFile(in, out);
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }
        showPDF(filename);
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        in.close();
        in = null;
        out.flush();
        out.close();
        out = null;
    }

    public void showPDF(String strurl) {
        File file = new File(Environment.getExternalStorageDirectory() + "/Amrutum/" + strurl);
        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/pdf");
        startActivity(intent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        selectedItem(id);
        return true;
    }

    public void selectedItem(int id) {
        if (id == R.id.nav_nearby) {
            Intent i = new Intent(NavigationDrawerActivity.this, MapsActivity.class);
            startActivity(i);
//            Fragment defineDeliveryFragment = new MapsFragment();
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frmContentNavigation, defineDeliveryFragment);
//            fragmentTransaction.commit();
        } else if (id == R.id.nav_myaccount) {
            Intent i = new Intent(NavigationDrawerActivity.this, MyAccountActivity.class);
            startActivity(i);
        }/* else if (id == R.id.nav_home) {
            fragmentCall("HOME");
        } else if (id == R.id.nav_about) {
            Intent i = new Intent(NavigationDrawerActivity.this, DialogActivity.class);
            i.putExtra("CALL", "ABOUT");
            ArrayList<String> menu_list = new ArrayList<String>();
            menu_list.add("About MA Yojana (English)");
            menu_list.add("About MA Yojana (Gujarati)");
            i.putStringArrayListExtra("MENU_NAME_LIST", menu_list);
            startActivityForResult(i, 10);
        } else if (id == R.id.nav_policy_guidelines) {
            Intent i = new Intent(NavigationDrawerActivity.this, DialogActivity.class);
            i.putExtra("CALL", "POLICY AND GUIDELINES");
            ///////////////////1/////////////////
            ArrayList<String> menu_list = new ArrayList<String>();
            menu_list.add("GR's");
            menu_list.add("Hospital Empanelment");
            menu_list.add("Enrollment Kiosk");
            menu_list.add("Health Camps");
            i.putStringArrayListExtra("MENU_NAME_LIST", menu_list);
            /////////////////////////2////////////////////////
            ArrayList<String> gr_list = new ArrayList<String>();
            gr_list.add("MA YOJANA");
            gr_list.add("MAV YOJANA");
            i.putStringArrayListExtra("GR_LIST", gr_list);

            ArrayList<String> hospital_list = new ArrayList<String>();
            hospital_list.add("RFP of MA Yojana");
            hospital_list.add("Hardware and Hospital Kiosk Procurement for Private Hospitals");
            hospital_list.add("Treatment Guidelines for Maintenance Hemodialysis");
            i.putStringArrayListExtra("HOSPITAL_LIST", hospital_list);

            ArrayList<String> enrollment_list = new ArrayList<String>();
            enrollment_list.add("MA Card Enrollment Guidelines- Case Scenario");
            i.putStringArrayListExtra("ENROLLMENT_LIST", enrollment_list);

            ArrayList<String> healthcamp_list = new ArrayList<String>();
            healthcamp_list.add("Schedules");
            healthcamp_list.add("Mega Health Camp Reports");
            i.putStringArrayListExtra("HEALTHCAMP_LIST", healthcamp_list);

            ///////////////////3///////////////////////
            ArrayList<String> gr1_list = new ArrayList<String>();
            gr1_list.add("Mukhyamantri Amrutum Yojana");
            gr1_list.add("Exe. Com. and SNC of MA");
            gr1_list.add("MA Verifying Authority");
            gr1_list.add("Grievance Redressal Committee");
            gr1_list.add("SNC Recruitment");
            gr1_list.add("District Authorities Approval");
            gr1_list.add("ASHA Incentive Letter");
            i.putStringArrayListExtra("GR_LIST1", gr1_list);

            ArrayList<String> gr2_list = new ArrayList<String>();
            gr2_list.add("Mukhyamantri Amrutum Vatsalya Yojana");
            gr2_list.add("Mukhyamantri Amrutum Vatsalya Yojana(Extension)");
            gr2_list.add("Updated GR of MAV Yojana for limit of income certificate");
            gr2_list.add("Validity of Mukhyamantri Amrutam Vatsalya Card");
            i.putStringArrayListExtra("GR_LIST2", gr2_list);

            ArrayList<String> hospital1_list = new ArrayList<String>();
            hospital1_list.add("RFP of HospitalEmpanelment");
            hospital1_list.add("RFP of Dialysis");
            i.putStringArrayListExtra("HOSPITAL_LIST1", hospital1_list);

            ArrayList<String> healthcamp1_list = new ArrayList<String>();
            healthcamp1_list.add("-------------2013--------------");
            healthcamp1_list.add("2013- Planning");
            healthcamp1_list.add("2013- February");
            healthcamp1_list.add("2013- March");
            healthcamp1_list.add("2013- June");
            healthcamp1_list.add("2013- July");
            healthcamp1_list.add("2013- August");
            healthcamp1_list.add("2013- September");
            healthcamp1_list.add("2013- November");
            healthcamp1_list.add("-------------2014--------------");
            healthcamp1_list.add("2014- January");
            healthcamp1_list.add("2014- February");
            healthcamp1_list.add("2014- May");
            healthcamp1_list.add("2014- June");
            healthcamp1_list.add("2014- July");
            healthcamp1_list.add("2014- August");
            healthcamp1_list.add("2014- September");
            healthcamp1_list.add("2014- October");
            healthcamp1_list.add("2014- November");
            healthcamp1_list.add("2014- December");
            healthcamp1_list.add("-------------2015--------------");
            healthcamp1_list.add("2015- January");
            healthcamp1_list.add("2015- February");
            healthcamp1_list.add("2015- March");
            i.putStringArrayListExtra("HEALTHCAMP_LIST1", healthcamp1_list);

            ArrayList<String> healthcamp2_list = new ArrayList<String>();
            healthcamp2_list.add("-------------2013--------------");
            healthcamp2_list.add("2013- Bharuch");
            healthcamp2_list.add("2013- Dangs");
            healthcamp2_list.add("2013- Kutch");
            healthcamp2_list.add("2013- Narmada");
            healthcamp2_list.add("2013- Navsari");
            healthcamp2_list.add("2013- Rajkot");
            healthcamp2_list.add("2013- Sola(Ahd)");
            healthcamp2_list.add("-------------2014--------------");
            healthcamp2_list.add("2014- Amreli");
            healthcamp2_list.add("2014- Navsari");
            healthcamp2_list.add("2014- Porbandar");
            healthcamp2_list.add("2014- Sabarkantha");
            healthcamp2_list.add("2014- Tapi");
            healthcamp2_list.add("2014- Dahod");
            healthcamp2_list.add("2014- Panchmahal");
            healthcamp2_list.add("2014- Surat");
            healthcamp2_list.add("2014- Valsad");
            healthcamp2_list.add("2014- Vadodara");
            i.putStringArrayListExtra("HEALTHCAMP_LIST2", healthcamp2_list);

            startActivityForResult(i, 10);
        } else if (id == R.id.nav_network_hospital) {
            Intent i = new Intent(NavigationDrawerActivity.this, DialogActivity.class);
            i.putExtra("CALL", "NETWORK HOSPTIAL");
            ArrayList<String> menu_list = new ArrayList<String>();
            menu_list.add("District wise Empanelled Hospitals");
            menu_list.add("Standalone Dialysis Centers");
            i.putStringArrayListExtra("MENU_NAME_LIST", menu_list);
            startActivityForResult(i, 10);
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(NavigationDrawerActivity.this, DialogActivity.class);
            i.putExtra("CALL", "GALLERY");
            ArrayList<String> menu_list = new ArrayList<String>();
            menu_list.add("Photos");
            menu_list.add("Videos");
            i.putStringArrayListExtra("MENU_NAME_LIST", menu_list);
            startActivityForResult(i, 10);
        } else if (id == R.id.nav_faqs) {
            Intent i = new Intent(NavigationDrawerActivity.this, DialogActivity.class);
            i.putExtra("CALL", "FAQ'S");
            ArrayList<String> menu_list = new ArrayList<String>();
            menu_list.add("FAQMa Yojana");
            menu_list.add("Enrollment Process");
            menu_list.add("General");
            menu_list.add("Service Delivery");
            i.putStringArrayListExtra("MENU_NAME_LIST", menu_list);
            startActivityForResult(i, 10);
        } else if (id == R.id.nav_contact) {
            Intent i = new Intent(NavigationDrawerActivity.this, DialogActivity.class);
            i.putExtra("CALL", "CONTACT");
            ArrayList<String> menu_list = new ArrayList<String>();
            menu_list.add("Executive Committee");
            menu_list.add("SNC Members");
            menu_list.add("Collectors");
            menu_list.add("DDO's");
            menu_list.add("CDHO's");
            menu_list.add("THO's");
            i.putStringArrayListExtra("MENU_NAME_LIST", menu_list);
            startActivityForResult(i, 10);
        } else if (id == R.id.nav_package_rates) {
            Intent i = new Intent(NavigationDrawerActivity.this, DialogActivity.class);
            i.putExtra("CALL", "PACKAGE RATES");
            ArrayList<String> menu_list = new ArrayList<String>();
            menu_list.add("Package Rates");
            menu_list.add("Other Package Rates");
            i.putStringArrayListExtra("MENU_NAME_LIST", menu_list);

            ArrayList<String> other_package_list = new ArrayList<String>();
            other_package_list.add("Cluster 1 - BURNS");
            other_package_list.add("Cluster 2 - CARDIOLOGY");
            other_package_list.add("Cluster 3 - CARDIOTHORACIC SURGERY");
            other_package_list.add("Cluster 4 - CARDIOVASCULAR SURGERY");
            other_package_list.add("Cluster 5 - RENAL");
            other_package_list.add("Cluster 6 - NEUROSURGERY");
            other_package_list.add("Cluster 7 - PAEDIATRIC SURGERY");
            other_package_list.add("Cluster 8 - POLYTRAUMA");
            other_package_list.add("Cluster 9 - MEDICAL ONCOLOGY");
            other_package_list.add("Cluster 10 - RADIATION ONCOLOGY");
            other_package_list.add("Cluster 11 - SURGICAL ONCOLOGY");
            i.putStringArrayListExtra("OTHER_PACKAGE_LIST", other_package_list);
            startActivityForResult(i, 10);
        }
*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void getKisokCenter() {
        String tag_json_obj = "json_obj_req";

        String url = ApplicationData.searchURL + "GetKioskCentersMA";
        Log.e("url", url + "");
        final ProgressDialog mProgressDialog = new ProgressDialog(NavigationDrawerActivity.this);
        mProgressDialog.setTitle("");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e("response", "" + response);
                        ArrayList<String> select = new ArrayList<String>();
                        ArrayList<String> districtName = new ArrayList<String>();
                        ArrayList<String> talukaName = new ArrayList<String>();
                        ArrayList<String> address = new ArrayList<String>();
                        ArrayList<String> address1 = new ArrayList<String>();
                        ArrayList<String> address2 = new ArrayList<String>();
                        ArrayList<String> kisokExecutiveName = new ArrayList<String>();
                        ArrayList<String> mobile = new ArrayList<String>();
                        try {
                            mProgressDialog.dismiss();
                            JSONObject object = new JSONObject(response.toString());

                            String msg = object.getString("msg");
                            if (msg.equalsIgnoreCase("Success")) {
                                JSONArray dataArr = object.getJSONArray("data");
                                for (int i = 0; i < dataArr.length(); i++) {
                                    JSONObject dataOb = dataArr.getJSONObject(i);
                                    select.add(dataOb.getString("Select"));
                                    districtName.add(dataOb.getString("DitrictName"));
                                    talukaName.add(dataOb.getString("TalukaName"));
                                    address.add(dataOb.getString("Address"));
                                    address1.add(dataOb.getString("Address1"));
                                    address2.add(dataOb.getString("Address2"));
                                    kisokExecutiveName.add(dataOb.getString("KioskExecutive"));
                                    mobile.add(dataOb.getString("Mobile"));
                                }
                                searchKisokFragmentCall(select, districtName, talukaName, address, address1, address2, kisokExecutiveName, mobile);
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Oopss! Login failure please try again",
                                        Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception error) {
                            mProgressDialog.dismiss();
                            error.printStackTrace();
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(NavigationDrawerActivity.this, "Please check your internet connection!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(NavigationDrawerActivity.this, "Something is wrong Please try again!", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(NavigationDrawerActivity.this, "Please check your internet connection!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(NavigationDrawerActivity.this, "Something is wrong Please try again!", Toast.LENGTH_LONG).show();
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

    public void getEmpanelledHospital() {
        String tag_json_obj = "json_obj_req";

        String url = ApplicationData.searchURL + "GetEmpanelledHospitalsMA";
        Log.e("url", url + "");
        final ProgressDialog mProgressDialog = new ProgressDialog(NavigationDrawerActivity.this);
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
                        ArrayList<String> districtName = new ArrayList<String>();
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
                                    type.add(dataOb.getString("Type"));
                                    districtName.add(dataOb.getString("DistrictName"));
                                    hospitalName.add(dataOb.getString("HospitalName"));
                                    address.add(dataOb.getString("Address"));
                                    address1.add(dataOb.getString("Address1"));
                                    address2.add(dataOb.getString("Address2"));
                                    arogyamitra.add(dataOb.getString("Arogyamitra"));
                                    amnumber.add(dataOb.getString("AMNumber"));
                                    ham.add(dataOb.getString("HAM"));
                                    hamnumber.add(dataOb.getString("HAMNumber"));
                                    latitude.add(dataOb.getString("Latitude"));
                                    longitude.add(dataOb.getString("Longitude"));
                                }
                                searchEmpanelledHospitalFragmentCall("EMPANELLED", type, districtName, hospitalName, address,
                                        address1, address2, arogyamitra, amnumber, ham, hamnumber, latitude, longitude);
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Oopss! Login failure please try again",
                                        Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception error) {
                            mProgressDialog.dismiss();
                            error.printStackTrace();
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(NavigationDrawerActivity.this, "Please check your internet connection!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(NavigationDrawerActivity.this, "Something is wrong Please try again!", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(NavigationDrawerActivity.this, "Please check your internet connection!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(NavigationDrawerActivity.this, "Something is wrong Please try again!", Toast.LENGTH_LONG).show();
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

    public void getDialysisHospital() {
        String tag_json_obj = "json_obj_req";

        String url = ApplicationData.searchURL + "GetDialysisHospitalsMA";
        Log.e("url", url + "");
        final ProgressDialog mProgressDialog = new ProgressDialog(NavigationDrawerActivity.this);
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
                        ArrayList<String> districtName = new ArrayList<String>();
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
                                    type.add(dataOb.getString("Type"));
                                    districtName.add(dataOb.getString("DistrictName"));
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
                                searchEmpanelledHospitalFragmentCall("DIALYSIS", type, districtName, hospitalName, address,
                                        address1, address2, arogyamitra, amnumber, ham, hamnumber, latitude, longitude);
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Oopss! Login failure please try again",
                                        Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception error) {
                            mProgressDialog.dismiss();
                            error.printStackTrace();
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(NavigationDrawerActivity.this, "Please check your internet connection!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(NavigationDrawerActivity.this, "Something is wrong Please try again!", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(NavigationDrawerActivity.this, "Please check your internet connection!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(NavigationDrawerActivity.this, "Something is wrong Please try again!", Toast.LENGTH_LONG).show();
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

    public void searchKisokFragmentCall(ArrayList<String> select, ArrayList<String> district,
                                        ArrayList<String> taluka, ArrayList<String> address,
                                        ArrayList<String> address1, ArrayList<String> address2,
                                        ArrayList<String> executiveName, ArrayList<String> mobile) {
        Intent i = new Intent(NavigationDrawerActivity.this, SearchEnrollementKisokActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("CALL", "KISOK");
        bundle.putStringArrayList("SELECT_LIST", select);
        bundle.putStringArrayList("DISTRICT_LIST", district);
        bundle.putStringArrayList("TALUKA_LIST", taluka);
        bundle.putStringArrayList("ADDRESS_LIST", address);
        bundle.putStringArrayList("ADDRESS1_LIST", address1);
        bundle.putStringArrayList("ADDRESS2_LIST", address2);
        bundle.putStringArrayList("EXECUTIVENAME_LIST", executiveName);
        bundle.putStringArrayList("MOBILE_LIST", mobile);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void searchEmpanelledHospitalFragmentCall(String call, ArrayList<String> type, ArrayList<String> districtName, ArrayList<String> hospitalName,
                                                     ArrayList<String> address, ArrayList<String> address1, ArrayList<String> address2, ArrayList<String> arogyamitra,
                                                     ArrayList<String> amnumber, ArrayList<String> ham, ArrayList<String> hamnumber, ArrayList<String> latitude, ArrayList<String> longitude) {
        Intent i = new Intent(NavigationDrawerActivity.this, SearchEmpanelledHospitalActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("CALL", call);
        bundle.putStringArrayList("TYPE_LIST", type);
        bundle.putStringArrayList("DISTRICT_LIST", districtName);
        bundle.putStringArrayList("HOSPITAL_NAME_LIST", hospitalName);
        bundle.putStringArrayList("ADDRESS_LIST", address);
        bundle.putStringArrayList("ADDRESS1_LIST", address1);
        bundle.putStringArrayList("ADDRESS2_LIST", address2);
        bundle.putStringArrayList("AROGYAMITRA_LIST", arogyamitra);
        bundle.putStringArrayList("AMNUMBER_LIST", amnumber);
        bundle.putStringArrayList("HAM_LIST", ham);
        bundle.putStringArrayList("HAMNUMBER_LIST", hamnumber);
        bundle.putStringArrayList("LATITUDE_LIST", latitude);
        bundle.putStringArrayList("LONGITUDE_LIST", longitude);
        i.putExtras(bundle);
        startActivity(i);
    }
}
