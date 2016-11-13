package com.ncode.mukhyamantriamrutum;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PolicyAndGuidelinesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PolicyAndGuidelinesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PolicyAndGuidelinesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CALL = "CALL";
    private static final String MENU_NAME_LIST = "MENU_NAME_LIST";
    private static final String GR_LIST = "GR_LIST";
    private static final String HOSPITAL_LIST = "HOSPITAL_LIST";
    private static final String ENROLLMENT_LIST = "ENROLLMENT_LIST";
    private static final String HEALTHCAMP_LIST = "HEALTHCAMP_LIST";
    private static final String GR_LIST1 = "GR_LIST1";
    private static final String GR_LIST2 = "GR_LIST2";
    private static final String HOSPITAL_LIST1 = "HOSPITAL_LIST1";
    private static final String HEALTHCAMP_LIST1 = "HEALTHCAMP_LIST1";
    private static final String HEALTHCAMP_LIST2 = "HEALTHCAMP_LIST2";
    static String calltype = "";
    //    private ArrayList<String> paramMenuNameList,paramCallOtherList,paramGRList,paramHospitalList,
//            paramEnrollmentList,paramHealthCampList,paramGRList1,paramGRList2,paramHosptialList1,
//            paramHealthCampList1,paramHealthCampList2;
    static ArrayList<String> NameList, GRLIST, HOSPITALLIST, ENROLLMENTLIST, HEALTHCAMPLIST, GRLIST1,
            GRLIST2, HOSPITALLIST1, HEALTHCAMPLIST1, HEALTHCAMPLIST2;
    static boolean yearAdd = false;
    static WebView wvDateDetail;
    static LinearLayout lvNameList;
    static DialogListAdapter adapter;
    TextView txvHeader;
    ListView lvMenuList;
    // TODO: Rename and change types of parameters
    private String paramCall;
    private OnFragmentInteractionListener mListener;

    public PolicyAndGuidelinesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AboutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PolicyAndGuidelinesFragment newInstance(String calltype, ArrayList<String> NameList,
                                                          ArrayList<String> GRLIST, ArrayList<String> HOSPITALLIST, ArrayList<String> ENROLLMENTLIST,
                                                          ArrayList<String> HEALTHCAMPLIST, ArrayList<String> GRLIST1, ArrayList<String> GRLIST2, ArrayList<String> HOSPITALLIST1
            , ArrayList<String> HEALTHCAMPLIST1, ArrayList<String> HEALTHCAMPLIST2) {

        PolicyAndGuidelinesFragment fragment = new PolicyAndGuidelinesFragment();
        Bundle args = new Bundle();
        args.putString(CALL, calltype);
        args.putStringArrayList(MENU_NAME_LIST, NameList);
        args.putStringArrayList(GR_LIST, GRLIST);
        args.putStringArrayList(HOSPITAL_LIST, HOSPITALLIST);
        args.putStringArrayList(ENROLLMENT_LIST, ENROLLMENTLIST);
        args.putStringArrayList(HEALTHCAMP_LIST, HEALTHCAMPLIST);
        args.putStringArrayList(GR_LIST1, GRLIST1);
        args.putStringArrayList(GR_LIST2, GRLIST2);
        args.putStringArrayList(HOSPITAL_LIST1, HOSPITALLIST1);
        args.putStringArrayList(HEALTHCAMP_LIST1, HEALTHCAMPLIST1);
        args.putStringArrayList(HEALTHCAMP_LIST2, HEALTHCAMPLIST2);
        fragment.setArguments(args);


        return fragment;
    }

    public static void refreshView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);

            calltype = getArguments().getString(CALL);
            NameList = getArguments().getStringArrayList(MENU_NAME_LIST);


            GRLIST = getArguments().getStringArrayList(GR_LIST);
            HOSPITALLIST = getArguments().getStringArrayList(HOSPITAL_LIST);
            ENROLLMENTLIST = getArguments().getStringArrayList(ENROLLMENT_LIST);
            HEALTHCAMPLIST = getArguments().getStringArrayList(HEALTHCAMP_LIST);

            GRLIST1 = getArguments().getStringArrayList(GR_LIST1);
            GRLIST2 = getArguments().getStringArrayList(GR_LIST2);

            HOSPITALLIST1 = getArguments().getStringArrayList(HOSPITAL_LIST1);

            HEALTHCAMPLIST1 = getArguments().getStringArrayList(HEALTHCAMP_LIST1);
            HEALTHCAMPLIST2 = getArguments().getStringArrayList(HEALTHCAMP_LIST2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

        txvHeader = (TextView) view.findViewById(R.id.txvHeader);
        lvMenuList = (ListView) view.findViewById(R.id.lvMenuList);
        lvNameList = (LinearLayout) view.findViewById(R.id.lvNameList);
        wvDateDetail = (WebView) view.findViewById(R.id.wvDateDetail);
        wvDateDetail.getSettings().setJavaScriptEnabled(true);
        txvHeader.setVisibility(View.GONE);


        adapter = new DialogListAdapter(NameList);
        lvMenuList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lvMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;
    }

    public void loadPDF(String PDFname) {

//        WebViewFragment.newInstance("HOME", ApplicationData.serviceURL + "PDF/" + PDFname, "PDF");

       /* Fragment defineDeliveryFragment = new WebViewFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmContentLayout, defineDeliveryFragment);
        Bundle bundle = new Bundle();
        bundle.putString("CALL", "PDF");
        bundle.putString("URL", ApplicationData.serviceURL + "PDF/" + PDFname);
        defineDeliveryFragment.setArguments(bundle);
        fragmentTransaction.commit();*/
        lvNameList.setVisibility(View.GONE);
        wvDateDetail.setVisibility(View.VISIBLE);
        callPDF("", ApplicationData.serviceURL + "PDF/" + PDFname, "PDF");
    }

    public void callPDF(String mParam1, String mParam2, String mParam3) {
        if (mParam3.equalsIgnoreCase("FILE")) {
            if (mParam1.equalsIgnoreCase("District wise Empanelled Hospitals")) {
                wvDateDetail.loadUrl("file:///android_asset/DistrictwiseHospitalEmpanelment.html");
            } else if (mParam1.equalsIgnoreCase("Standalone Dialysis Centers")) {
                wvDateDetail.loadUrl("file:///android_asset/DistrictwiseStandaloneDialysisCenter.html");
            } else if (mParam1.equalsIgnoreCase("Executive Committee")) {
                wvDateDetail.loadUrl("file:///android_asset/Contact-ExeComMem.html");
            } else if (mParam1.equalsIgnoreCase("SNC Members")) {
                wvDateDetail.loadUrl("file:///android_asset/Contact-SNC-Officials.html");
            } else if (mParam1.equalsIgnoreCase("CDHO's")) {
                wvDateDetail.loadUrl("file:///android_asset/Contact-CDHO.html");
            } else if (mParam1.equalsIgnoreCase("THO's")) {
                wvDateDetail.loadUrl("file:///android_asset/Contact-THO.html");
            } else if (mParam1.equalsIgnoreCase("Ma Yojana")) {
                wvDateDetail.loadUrl("file:///android_asset/faq-ma-yojana.html");
            } else if (mParam1.equalsIgnoreCase("Enrollment Process")) {
                wvDateDetail.loadUrl("file:///android_asset/faq-enrollment-process.html");
            } else if (mParam1.equalsIgnoreCase("General")) {
                wvDateDetail.loadUrl("file:///android_asset/faq-general.html");
            } else if (mParam1.equalsIgnoreCase("Service Delivery")) {
                wvDateDetail.loadUrl("file:///android_asset/faq-service-delivery.html");
            } else if (mParam1.equalsIgnoreCase("Service Delivery")) {
                wvDateDetail.loadUrl("file:///android_asset/faq-service-delivery.html");
            } else if (mParam1.equalsIgnoreCase("HOME")) {
                wvDateDetail.loadUrl("file:///android_asset/home.html");
            } else if (mParam1.equalsIgnoreCase("Photos")) {
                wvDateDetail.loadUrl("http://192.95.6.213/new-html/gallery.html");
            }
        } else {
            wvDateDetail.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + mParam2);

        }
    }

    public void fragmentCall(String name) {
        lvNameList.setVisibility(View.GONE);
        wvDateDetail.setVisibility(View.VISIBLE);
        callPDF(name, "", "FILE");
       /* Fragment defineDeliveryFragment = new WebViewFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmContentLayout, defineDeliveryFragment);
        Bundle bundle = new Bundle();
        bundle.putString("CALL", "FILE");
        bundle.putString("FILE_NAME", name);
        defineDeliveryFragment.setArguments(bundle);
        fragmentTransaction.commit();*/
    }

    public void itemSelection(int position) {
        Log.e("data select ", "" + NameList.get(position));
        // if (calltype.equalsIgnoreCase("POLICY AND GUIDELINES")) {
        if (NameList.get(position).equalsIgnoreCase("GR's")) {
            adapter = new DialogListAdapter(GRLIST);
            lvMenuList.setAdapter(adapter);
            yearAdd = false;
        } else if (NameList.get(position).equalsIgnoreCase("Hospital Empanelment")) {
            adapter = new DialogListAdapter(HOSPITALLIST);
            lvMenuList.setAdapter(adapter);
            yearAdd = false;
        } else if (NameList.get(position).equalsIgnoreCase("Enrollment Kiosk")) {
            adapter = new DialogListAdapter(ENROLLMENTLIST);
            lvMenuList.setAdapter(adapter);
            yearAdd = false;
        } else if (NameList.get(position).equalsIgnoreCase("Health Camps")) {
            adapter = new DialogListAdapter(HEALTHCAMPLIST);
            lvMenuList.setAdapter(adapter);
            yearAdd = false;
        } else if (NameList.get(position).equalsIgnoreCase("MA YOJANA")) {
            adapter = new DialogListAdapter(GRLIST1);
            lvMenuList.setAdapter(adapter);
            yearAdd = false;
        } else if (NameList.get(position).equalsIgnoreCase("MAV YOJANA")) {
            adapter = new DialogListAdapter(GRLIST2);
            lvMenuList.setAdapter(adapter);
            yearAdd = false;
        } else if (NameList.get(position).equalsIgnoreCase("RFP of MA Yojana")) {
            adapter = new DialogListAdapter(HOSPITALLIST1);
            lvMenuList.setAdapter(adapter);
            yearAdd = false;
        } else if (NameList.get(position).equalsIgnoreCase("Schedules")) {
            adapter = new DialogListAdapter(HEALTHCAMPLIST1);
            lvMenuList.setAdapter(adapter);
            yearAdd = true;
        } else if (NameList.get(position).equalsIgnoreCase("Mega Health Camp Reports")) {
            adapter = new DialogListAdapter(HEALTHCAMPLIST2);
            lvMenuList.setAdapter(adapter);
            yearAdd = true;
        } else {
//                    Intent i = new Intent();
//                    i.putExtra("CALL_RETURN", calltype);
//                    i.putExtra("HEADER", "" + NameList.get(position));
//                    setResult(10, i);
//                    finish();
            String HEADERCALL = "" + NameList.get(position);
            {
                if (HEADERCALL.equalsIgnoreCase("About MA Yojana (English)")) {
                    loadPDF("AboutMAYojanaEnglish.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("About MA Yojana (Gujarati)")) {
                    loadPDF("AboutMAYojanaGujarati.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("District wise Empanelled Hospitals")) {
                    fragmentCall("District wise Empanelled Hospitals");
                } else if (HEADERCALL.equalsIgnoreCase("Standalone Dialysis Centers")) {
                    fragmentCall("Standalone Dialysis Centers");
                } else if (HEADERCALL.equalsIgnoreCase("Executive Committee")) {
                    fragmentCall("Executive Committee");
                } else if (HEADERCALL.equalsIgnoreCase("SNC Members")) {
                    fragmentCall("SNC Members");
                } else if (HEADERCALL.equalsIgnoreCase("Collectors")) {

                } else if (HEADERCALL.equalsIgnoreCase("DDO's")) {

                } else if (HEADERCALL.equalsIgnoreCase("CDHO's")) {
                    fragmentCall("CDHO's");
                } else if (HEADERCALL.equalsIgnoreCase("THO's")) {
                    fragmentCall("THO's");
                } else if (HEADERCALL.equalsIgnoreCase("Package Rates")) {
                    loadPDF("PackageRatesMain.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("FAQMa Yojana")) {
                    fragmentCall("Ma Yojana");
                } else if (HEADERCALL.equalsIgnoreCase("Enrollment Process")) {
                    fragmentCall("Enrollment Process");
                } else if (HEADERCALL.equalsIgnoreCase("General")) {
                    fragmentCall("General");
                } else if (HEADERCALL.equalsIgnoreCase("Service Delivery")) {
                    fragmentCall("Service Delivery");
                } else if (HEADERCALL.equalsIgnoreCase("Mukhyamantri Amrutum Yojana")) {
                    loadPDF("GR_of_Mukhyamantri_Amrutam_18-04-2012(New2014).pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Exe. Com. and SNC of MA")) {
                    loadPDF("GR_for_Exe._Com.and_SNC_of_MA_2-6-12.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("MA Verifying Authority")) {
                    loadPDF("GR_for_MA_Enrollment.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Grievance Redressal Committee")) {
                    loadPDF("GR_for_Grievance_Redresser_Committee.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("SNC Recruitment")) {
                    loadPDF("GRforSNC.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("District Authorities Approval")) {
                    loadPDF("PRAMANIT_Dakhlo.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("ASHA Incentive Letter")) {
                    loadPDF("ASHA_INCEVTE_2014-2015.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Mukhyamantri Amrutum Vatsalya Yojana")) {
                    loadPDF("13_8_2014_GR.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Mukhyamantri Amrutum Vatsalya Yojana(Extension)")) {
                    loadPDF("MAV_Tharav_26_3_2015.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Updated GR of MAV Yojana for limit of income certificate")) {
                    loadPDF("Updated_GR _MAVYojana_for_limit_of_income_certificate.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Validity of Mukhyamantri Amrutam Vatsalya Card")) {
                    loadPDF("GR_for_Validity_of_Mukhyamantri_Amrutam_Vatsalya_Card.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("RFP of HospitalEmpanelment")) {
                    loadPDF("RFPofHospitalempanelmentunderMAYojana.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("RFP of Dialysis")) {
                    loadPDF("RFPofDialysisCenter.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Hardware and Hospital Kiosk Procurement for Private Hospitals")) {
                    loadPDF("Hardware-and-Hospital-Kiosk-Procurement-for-Private-Empanelled-Hospitals.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Treatment Guidelines for Maintenance Hemodialysis")) {
                    loadPDF("Treatment_Guidlines_for_Maintainance_Hemodialysis.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("MA Card Enrollment Guidelines- Case Scenario")) {

                } else if (HEADERCALL.equalsIgnoreCase("Photos")) {
                    fragmentCall("Photos");
                } else if (HEADERCALL.equalsIgnoreCase("2013- Planning")) {
                    loadPDF("MAAnnualHealthCampPlanning.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2013- February")) {
                    loadPDF("Health_Camps_In_February.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2013- March")) {

                } else if (HEADERCALL.equalsIgnoreCase("2013- June")) {
                    loadPDF("June_Month_Health_Camp.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2013- July")) {
                    loadPDF("July_HealthCamp_Schedule.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2013- August")) {
                    loadPDF("August_Health_Camp_Tracker.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2013- September")) {
                    loadPDF("September_Health_Camp_Tracker.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2013- November")) {
                    loadPDF("November_Health_Camp.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- January")) {
                    loadPDF("January-2014_HealthCamp_Tracker.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- February")) {
                    loadPDF("JFebruary-2014 Health Camp Tracker.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- May")) {
                    loadPDF("May_2014_HealthCamp.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- June")) {
                    loadPDF("June_2014_HealthCamp.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- July")) {
                    loadPDF("July_2014_HealthCamp.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- August")) {
                    loadPDF("August_Healthcamp_2014.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- September")) {
                    loadPDF("Healthcamp_september_2014.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- October")) {
                    loadPDF("Healthcamp_October_2014.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- November")) {
                    loadPDF("Healthcamp_Novemberr_2014.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- December")) {
                    loadPDF("December_HealthCamp_2014.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2015- January")) {
                    loadPDF("HealthCamp_January_2015.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2015- February")) {
                    loadPDF("HealthCamp_February_2015.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2015- March")) {
                    loadPDF("HealthcampSchedule_March_2015.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2013- Bharuch")) {
                    loadPDF("Bharuch_Mega_Health_Camp_Report.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2013- Dangs")) {
                    loadPDF("Dang_Mega_Health_Camp_Report.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2013- Kutch")) {
                    loadPDF("Kutch_Mega_Health_Camp_Report.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2013- Narmada")) {
                    loadPDF("Mega_Health_Camp_Report_Narmada.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2013- Navsari")) {
                    loadPDF("Mega_Health_Camp_Report_Navsari.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2013- Rajkot")) {
                    loadPDF("Rajkot_Report_MAA_Camp.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2013- Sola(Ahd)")) {
                    loadPDF("Sola_Mega_Health_Camp_Report.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- Amreli")) {
                    loadPDF("Amreli_Mega_Health_Camp.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- Navsari")) {
                    loadPDF("Mega_Health_Camp_Report_Navsari.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- Porbandar")) {
                    loadPDF("Porbandar_Mega_HealthCamp_Report.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- Sabarkantha")) {
                    loadPDF("Sabarkatha_MegaCamp_Report.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- Tapi")) {
                    loadPDF("Tapi_Mega_HealthCamp_Report.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- Dahod")) {
                    loadPDF("Tapi_Mega_HealthCamp_Report.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- Panchmahal")) {
                    fragmentCall("2014- Panchmahal");
                } else if (HEADERCALL.equalsIgnoreCase("2014- Surat")) {
                    fragmentCall("2014- Surat");
                } else if (HEADERCALL.equalsIgnoreCase("2014- Valsad")) {
                    fragmentCall("2014- Valsad");
                } else if (HEADERCALL.equalsIgnoreCase("2014- Vadodara")) {
                    fragmentCall("2014- Vadodara");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 1 - BURNS")) {
                    loadPDF("Cluster1BURNS.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 2 - CARDIOLOGY")) {
                    loadPDF("Cluster2CARDIOLOGY.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 3 - CARDIOTHORACIC SURGERY")) {
                    loadPDF("Cluster3CARDIOTHORACICSURGERY.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 4 - CARDIOVASCULAR SURGERY")) {
                    loadPDF("Cluster4CARDIOVASCULARSURGERY.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 5 - RENAL")) {
                    loadPDF("Cluster5RENAL.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 6 - NEUROSURGERY")) {
                    loadPDF("Cluster6NEUROSURGERY.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 7 - PAEDIATRIC SURGERY")) {
                    loadPDF("Cluster7PAEDIATRICSURGERY.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 8 - POLYTRAUMA")) {
                    loadPDF("Cluster8POLYTRAUMA.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 9 - MEDICAL ONCOLOGY")) {
                    loadPDF("Cluster9MEDICALONCOLOGY.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 10 - RADIATION ONCOLOGY")) {
                    loadPDF("Cluster10RADIATIONONCOLOGY.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 11 - SURGICAL ONCOLOGY")) {
                    loadPDF("Cluster11SURGICALONCOLOGY.pdf");
                }

            }
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class DialogListAdapter extends BaseAdapter {
        LayoutInflater inflater;
        Context context;


        public DialogListAdapter(ArrayList<String> namelist) {
            NameList = namelist;
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            else if (NameList.get(position).equalsIgnoreCase("FAQMa Yojana")) {
                holder.txvMenuName.setText("Ma Yojana");
            } else
                holder.txvMenuName.setText(NameList.get(position));

            holder.txvMenuName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemSelection(position);
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView txvMenuName;
        }

    }
}
