package com.ncode.mukhyamantriamrutum;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 8;
    FragmentManager fm;
    AppCompatActivity mcontext;
    private String[] titles;

    public ViewPagerAdapter(AppCompatActivity context, FragmentManager fm, String[] titles2) {
        super(fm);
        mcontext = context;
        this.fm = fm;
        titles = titles2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            // Open FragmentTab1.java
//            case 0:
//                return MapsFragment.newInstance();

            case 0:
                //fragmentCall("HOME");
                return HomeFragment.newInstance("HOME", "");

            case 1:
                ArrayList<String> menu_list = new ArrayList<String>();
                menu_list.add("About MA Yojana (English)");
                menu_list.add("About MA Yojana (Gujarati)");
                return AboutFragment.newInstance("ABOUT", menu_list);
            case 2:
                ///////////////////1/////////////////
                ArrayList<String> menu_list1 = new ArrayList<String>();
                menu_list1.add("GR's");
                menu_list1.add("Hospital Empanelment");
                menu_list1.add("Enrollment Kiosk");
                menu_list1.add("Health Camps");
                /////////////////////////2////////////////////////
                ArrayList<String> gr_list = new ArrayList<String>();
                gr_list.add("MA YOJANA");
                gr_list.add("MAV YOJANA");

                ArrayList<String> hospital_list = new ArrayList<String>();
                hospital_list.add("RFP of MA Yojana");
                hospital_list.add("Hardware and Hospital Kiosk Procurement for Private Hospitals");
                hospital_list.add("Treatment Guidelines for Maintenance Hemodialysis");

                ArrayList<String> enrollment_list = new ArrayList<String>();
                enrollment_list.add("MA Card Enrollment Guidelines- Case Scenario");

                ArrayList<String> healthcamp_list = new ArrayList<String>();
                healthcamp_list.add("Schedules");
                healthcamp_list.add("Mega Health Camp Reports");

                ///////////////////3///////////////////////
                ArrayList<String> gr1_list = new ArrayList<String>();
                gr1_list.add("Mukhyamantri Amrutum Yojana");
                gr1_list.add("Exe. Com. and SNC of MA");
                gr1_list.add("MA Verifying Authority");
                gr1_list.add("Grievance Redressal Committee");
                gr1_list.add("SNC Recruitment");
                gr1_list.add("District Authorities Approval");
                gr1_list.add("ASHA Incentive Letter");

                ArrayList<String> gr2_list = new ArrayList<String>();
                gr2_list.add("Mukhyamantri Amrutum Vatsalya Yojana");
                gr2_list.add("Mukhyamantri Amrutum Vatsalya Yojana(Extension)");
                gr2_list.add("Updated GR of MAV Yojana for limit of income certificate");
                gr2_list.add("Validity of Mukhyamantri Amrutam Vatsalya Card");

                ArrayList<String> hospital1_list = new ArrayList<String>();
                hospital1_list.add("RFP of HospitalEmpanelment");
                hospital1_list.add("RFP of Dialysis");

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

                Fragment fragment = PolicyAndGuidelinesFragment.newInstance("POLICY AND GUIDELINES", menu_list1, gr_list,
                        hospital_list, enrollment_list, healthcamp_list, gr1_list, gr2_list, hospital1_list
                        , healthcamp1_list, healthcamp2_list);

                return fragment;

            case 3:
                ArrayList<String> menu_list2 = new ArrayList<String>();
                menu_list2.add("District wise Empanelled Hospitals");
                menu_list2.add("Standalone Dialysis Centers");

                return NetworkHospitalFragment.newInstance("NETWORK HOSPTIAL", menu_list2);
            case 4:
                ArrayList<String> menu_list3 = new ArrayList<String>();
                menu_list3.add("Photos");
                menu_list3.add("Videos");
                return GalleryFragment.newInstance("GALLERY", menu_list3);
            case 5:
                ArrayList<String> menu_list4 = new ArrayList<String>();
                menu_list4.add("FAQMa Yojana");
                menu_list4.add("Enrollment Process");
                menu_list4.add("General");
                menu_list4.add("Service Delivery");

                return FAQFragment.newInstance("FAQ'S", menu_list4);
            case 6:

                ArrayList<String> menu_list5 = new ArrayList<String>();
                menu_list5.add("Executive Committee");
                menu_list5.add("SNC Members");
                menu_list5.add("Collectors");
                menu_list5.add("DDO's");
                menu_list5.add("CDHO's");
                menu_list5.add("THO's");
                return ContactFragment.newInstance("CONTACT", menu_list5);
            case 7:
                ArrayList<String> menu_list6 = new ArrayList<String>();
                menu_list6.add("Package Rates");
                menu_list6.add("Other Package Rates");

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

                return PackageRatesFragment.newInstance("PACKAGE RATES", menu_list6, other_package_list);
        }
        return null;
    }

    /*
    * newInstance(String calltype, ArrayList<String> NameList, ArrayList<String> OTHER_PACKAGE_LIST,
                                                 ArrayList<String> GRLIST, ArrayList<String> HOSPITALLIST, ArrayList<String> ENROLLMENTLIST,
                                                 ArrayList<String> HEALTHCAMPLIST, ArrayList<String> GRLIST1, ArrayList<String> GRLIST2,
                                                  ArrayList<String> HOSPITALLIST1, ArrayList<String> HEALTHCAMPLIST1, ArrayList<String> HEALTHCAMPLIST2)
    * */
    public void fragmentCall(String name) {
        Fragment defineDeliveryFragment = new WebViewFragment();
        FragmentManager fragmentManager = mcontext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmContentLayout, defineDeliveryFragment);
        Bundle bundle = new Bundle();
        bundle.putString("CALL", "FILE");
        bundle.putString("FILE_NAME", name);
        defineDeliveryFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}