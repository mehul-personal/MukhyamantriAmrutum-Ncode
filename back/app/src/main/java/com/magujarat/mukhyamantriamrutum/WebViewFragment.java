package com.ncode.mukhyamantriamrutum;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WebViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WebViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "FILE_NAME";
    private static final String ARG_PARAM2 = "URL";
    private static final String ARG_PARAM3 = "CALL";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;

    private OnFragmentInteractionListener mListener;

    public WebViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WebViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WebViewFragment newInstance(String param1, String param2, String param3) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            mParam3 = getArguments().getString(ARG_PARAM3);
            if(mParam3.equalsIgnoreCase("FILE")){
                mParam1 = getArguments().getString(ARG_PARAM1);
            }else if(mParam3.equalsIgnoreCase("PDF")){
                mParam2 = getArguments().getString(ARG_PARAM2);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        WebView pdfopen = (WebView) view.findViewById(R.id.wvDateDetail);
        pdfopen.getSettings().setJavaScriptEnabled(true);
        if (mParam3.equalsIgnoreCase("FILE")) {
            if (mParam1.equalsIgnoreCase("District wise Empanelled Hospitals")) {
                pdfopen.loadUrl("file:///android_asset/DistrictwiseHospitalEmpanelment.html");
            } else if (mParam1.equalsIgnoreCase("Standalone Dialysis Centers")) {
                pdfopen.loadUrl("file:///android_asset/DistrictwiseStandaloneDialysisCenter.html");
            } else if (mParam1.equalsIgnoreCase("Executive Committee")) {
                pdfopen.loadUrl("file:///android_asset/Contact-ExeComMem.html");
            } else if (mParam1.equalsIgnoreCase("SNC Members")) {
                pdfopen.loadUrl("file:///android_asset/Contact-SNC-Officials.html");
            } else if (mParam1.equalsIgnoreCase("CDHO's")) {
                pdfopen.loadUrl("file:///android_asset/Contact-CDHO.html");
            } else if (mParam1.equalsIgnoreCase("THO's")) {
                pdfopen.loadUrl("file:///android_asset/Contact-THO.html");
            } else if (mParam1.equalsIgnoreCase("Ma Yojana")) {
                pdfopen.loadUrl("file:///android_asset/faq-ma-yojana.html");
            } else if (mParam1.equalsIgnoreCase("Enrollment Process")) {
                pdfopen.loadUrl("file:///android_asset/faq-enrollment-process.html");
            } else if (mParam1.equalsIgnoreCase("General")) {
                pdfopen.loadUrl("file:///android_asset/faq-general.html");
            } else if (mParam1.equalsIgnoreCase("Service Delivery")) {
                pdfopen.loadUrl("file:///android_asset/faq-service-delivery.html");
            } else if (mParam1.equalsIgnoreCase("Service Delivery")) {
                pdfopen.loadUrl("file:///android_asset/faq-service-delivery.html");
            }else if(mParam1.equalsIgnoreCase("HOME")){
                pdfopen.loadUrl("file:///android_asset/home.html");
            }else if(mParam1.equalsIgnoreCase("Photos")){
                pdfopen.loadUrl("http://192.95.6.213/new-html/gallery.html");
            }
        } else {
            pdfopen.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + mParam2);

        }
        return view;
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
}
