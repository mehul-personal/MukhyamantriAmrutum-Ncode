package com.ncode.mukhyamantriamrutum;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PackageRatesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CALL = "CALL";
    private static final String MENU_NAME_LIST = "MENU_NAME_LIST";
    private static final String CALL_OTHER_PACKAGE_LIST = "OTHER_PACKAGE_LIST";

    static String calltype = "";
    //    private ArrayList<String> paramMenuNameList,paramCallOtherList,paramGRList,paramHospitalList,
//            paramEnrollmentList,paramHealthCampList,paramGRList1,paramGRList2,paramHosptialList1,
//            paramHealthCampList1,paramHealthCampList2;
    static ArrayList<String> MenuListData, NameList, OTHER_PACKAGE_LIST;
    static boolean yearAdd = false;
    static WebView wvDateDetail;
    static LinearLayout lvNameList;
    static DialogListAdapter adapter;
    TextView txvHeader;
    ListView lvMenuList;
    ProgressDialog mProgressDialog;
    FrameLayout frmWVDetail;

    ImageView back;
    // TODO: Rename and change types of parameters
    private String paramCall;
    private OnFragmentInteractionListener mListener;

    public PackageRatesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PackageRatesFragment newInstance(String calltype, ArrayList<String> NameList, ArrayList<String> OTHER_PACKAGE_LIST) {

        PackageRatesFragment fragment = new PackageRatesFragment();
        Bundle args = new Bundle();
        args.putString(CALL, calltype);
        args.putStringArrayList(MENU_NAME_LIST, NameList);
        args.putStringArrayList(CALL_OTHER_PACKAGE_LIST, OTHER_PACKAGE_LIST);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);

            calltype = getArguments().getString(CALL);
            MenuListData = getArguments().getStringArrayList(MENU_NAME_LIST);
            OTHER_PACKAGE_LIST = getArguments().getStringArrayList(CALL_OTHER_PACKAGE_LIST);
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
        frmWVDetail=(FrameLayout) view.findViewById(R.id.frmWVDetail);
        back=(ImageView) view.findViewById(R.id.imvBack);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Loading...");
        wvDateDetail.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        });
        wvDateDetail.getSettings().setJavaScriptEnabled(true);
        txvHeader.setVisibility(View.GONE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvNameList.setVisibility(View.VISIBLE);
                frmWVDetail.setVisibility(View.GONE);
            }
        });
        initCall();
        return view;
    }

    @Override
    public void onResume() {
        initCall();
        super.onResume();
    }

    public void initCall() {
        calltype = getArguments().getString(CALL);
        MenuListData = getArguments().getStringArrayList(MENU_NAME_LIST);
        OTHER_PACKAGE_LIST = getArguments().getStringArrayList(CALL_OTHER_PACKAGE_LIST);

        if (calltype.equalsIgnoreCase("ABOUT")) {
            txvHeader.setText("ABOUT");
        } else if (calltype.equalsIgnoreCase("NETWORK HOSPTIAL")) {
            txvHeader.setText("NETWORK HOSPTIAL");
        } else if (calltype.equalsIgnoreCase("CONTACT")) {
            txvHeader.setText("CONTACT");
        } else if (calltype.equalsIgnoreCase("PACKAGE RATES")) {
            txvHeader.setText("PACKAGE RATES");
        } else if (calltype.equalsIgnoreCase("FAQ'S")) {
            txvHeader.setText("FAQ'S");
        } else if (calltype.equalsIgnoreCase("GALLERY")) {
            txvHeader.setText("GALLERY");
        } else if (calltype.equalsIgnoreCase("POLICY AND GUIDELINES")) {
            txvHeader.setText("POLICY AND GUIDELINES");
        }

        adapter = new DialogListAdapter(MenuListData);
        lvMenuList.setAdapter(adapter);
    }

    public void loadPDF(String PDFname) {
        lvNameList.setVisibility(View.GONE);
        frmWVDetail.setVisibility(View.VISIBLE);
//        if (PDFname.contains("http:"))
//            callPDF("", PDFname, "PDF");
//        else
//            callPDF("", ApplicationData.serviceURL + "PDF/" + PDFname, "PDF");
        callPDF("", "http://eighttech.com/new_pdf/" + PDFname, "PDF");
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
            mProgressDialog.show();
            Log.e("load url", "" + mParam2);
            wvDateDetail.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + mParam2);

        }
    }

    public void fragmentCall(String name) {
        lvNameList.setVisibility(View.GONE);
        frmWVDetail.setVisibility(View.VISIBLE);
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
        if (NameList.get(position).equalsIgnoreCase("Other Package Rates")) {
            adapter = new DialogListAdapter(OTHER_PACKAGE_LIST);
            lvMenuList.setAdapter(adapter);
            yearAdd = false;
        } else {
            if (NameList.get(position).equalsIgnoreCase("2BACK1")) {
                adapter = new DialogListAdapter(MenuListData);
                lvMenuList.setAdapter(adapter);
                yearAdd = false;
            } else {
                String HEADERCALL = "" + NameList.get(position);

                if (HEADERCALL.equalsIgnoreCase("About MA Yojana (English)")) {
                    loadPDF("About%20MA%20Yojana%20English.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("About MA Yojana (Gujarati)")) {
                    loadPDF("About%20MA%20Yojana%20Gujarati.pdf");
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
                    loadPDF("ALL%20MA%20544%20procedure%20rate.pdf");
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
                    loadPDF("MAV_Tharav_26_3_2015.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Validity of Mukhyamantri Amrutam Vatsalya Card")) {
                    loadPDF("GR_for_Validity_of_Mukhyamantri_Amrutam_Vatsalya_Card.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("RFP of HospitalEmpanelment")) {
                    loadPDF("RFP%20of%20Hospital%20empanelment%20under%20MA%20Yojana.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("RFP of Dialysis")) {
                    loadPDF("RFPofDialysisCenter.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Hardware and Hospital Kiosk Procurement for Private Hospitals")) {
                    loadPDF("Hardware-and-Hospital-Kiosk-Procurement-for-Private-Empanelled-Hospitals.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Treatment Guidelines for Maintenance Hemodialysis")) {
                    loadPDF("Treatment_Guidlines_for_Maintainance_Hemodialysis.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("MA Card Enrollment Guidelines- Case Scenario")) {
                    loadPDF("Enrollment%20Guideline.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Photos")) {
                    fragmentCall("Photos");
                } else if (HEADERCALL.equalsIgnoreCase("2013- Planning")) {
                    loadPDF("MAAnnualHealthCampPlanning.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2013- February")) {
                    loadPDF("Health_Camps_In_February.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2013- March")) {
                    loadPDF("http://www.magujarat.com/gr3_3.html");
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
                    loadPDF("February-2014%20Health%20Camp%20Tracker.pdf");
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
                    loadPDF("Dahod_Mega_HealthCamp.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("2014- Panchmahal")) {
                    fragmentCall("2014- Panchmahal");
                } else if (HEADERCALL.equalsIgnoreCase("2014- Surat")) {
                    fragmentCall("2014- Surat");
                } else if (HEADERCALL.equalsIgnoreCase("2014- Valsad")) {
                    fragmentCall("2014- Valsad");
                } else if (HEADERCALL.equalsIgnoreCase("2014- Vadodara")) {
                    fragmentCall("2014- Vadodara");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 1 - BURNS")) {
                    loadPDF("Cluster%201%20-%20BURNS.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 2 - CARDIOLOGY")) {
                    loadPDF("Cluster%202%20-%20CARDIOLOGY.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 3 - CARDIOTHORACIC SURGERY")) {
                    loadPDF("Cluster%203%20-%20CARDIOTHORACIC%20SURGERY.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 4 - CARDIOVASCULAR SURGERY")) {
                    loadPDF("Cluster%204%20-%20CARDIOVASCULAR%20SURGERY.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 5 - RENAL")) {
                    loadPDF("Cluster%205%20-%20RENAL.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 6 - NEUROSURGERY")) {
                    loadPDF("Cluster%206%20-%20NEUROSURGERY.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 7 - PAEDIATRIC SURGERY")) {
                    loadPDF("Cluster%207%20-%20PAEDIATRIC%20SURGERY.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 8 - POLYTRAUMA")) {
                    loadPDF("Cluster%208%20-%20POLYTRAUMA.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 9 - MEDICAL ONCOLOGY")) {
                    loadPDF("Cluster%209%20-%20MEDICAL%20ONCOLOGY.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 10 - RADIATION ONCOLOGY")) {
                    loadPDF("Cluster%2010%20-%20RADIATION%20ONCOLOGY.pdf");
                } else if (HEADERCALL.equalsIgnoreCase("Cluster 11 - SURGICAL ONCOLOGY")) {
                    loadPDF("Cluster%2011%20-%20SURGICAL%20ONCOLOGY.pdf");
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
     * <p>
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
            } else if (NameList.get(position).contains("BACK")) {
                holder.txvMenuName.setText(Html.fromHtml("<u>BACK</u>"));
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
