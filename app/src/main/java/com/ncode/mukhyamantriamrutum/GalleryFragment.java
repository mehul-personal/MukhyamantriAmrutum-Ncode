package com.ncode.mukhyamantriamrutum;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;


public class GalleryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CALL = "CALL";
    private static final String MENU_NAME_LIST = "MENU_NAME_LIST";

    static String calltype = "";
    //    private ArrayList<String> paramMenuNameList,paramCallOtherList,paramGRList,paramHospitalList,
//            paramEnrollmentList,paramHealthCampList,paramGRList1,paramGRList2,paramHosptialList1,
//            paramHealthCampList1,paramHealthCampList2;
    static ArrayList<String> NameList;
    static boolean yearAdd = false;
    static WebView wvDateDetail;
    static LinearLayout lvNameList;
    static DialogListAdapter adapter;
    TextView txvHeader;
    ListView lvMenuList;
    ExpandableHeightGridView ehgGallery;
    ScrollView svGallery;
    // TODO: Rename and change types of parameters
    private String paramCall;
    private OnFragmentInteractionListener mListener;

    public GalleryFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static GalleryFragment newInstance(String calltype, ArrayList<String> NameList) {

        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        args.putString(CALL, calltype);
        args.putStringArrayList(MENU_NAME_LIST, NameList);

        fragment.setArguments(args);
        return fragment;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);

            calltype = getArguments().getString(CALL);
            NameList = getArguments().getStringArrayList(MENU_NAME_LIST);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        txvHeader = (TextView) view.findViewById(R.id.txvHeader);
        lvMenuList = (ListView) view.findViewById(R.id.lvMenuList);
        lvNameList = (LinearLayout) view.findViewById(R.id.lvNameList);
        wvDateDetail = (WebView) view.findViewById(R.id.wvDateDetail);
        svGallery = (ScrollView) view.findViewById(R.id.svGallery);
        wvDateDetail.getSettings().setJavaScriptEnabled(true);
        txvHeader.setVisibility(View.GONE);
        ehgGallery = (ExpandableHeightGridView) view.findViewById(R.id.ehgGallery);
        ehgGallery.setExpanded(true);
        svGallery.setVisibility(View.GONE);

        adapter = new DialogListAdapter(NameList);
        lvMenuList.setAdapter(adapter);

        ArrayList<Integer> ImageList = new ArrayList<Integer>();
        ArrayList<String> ImageNameList = new ArrayList<String>();
        ImageList.add(R.drawable.a1);
        ImageNameList.add("Treatment \n(Ahemdabad)");
        ImageList.add(R.drawable.b1);
        ImageNameList.add("Health Camp \n(Ahwa - Dang)");
        ImageList.add(R.drawable.c1);
        ImageNameList.add("Mega Health Camp \n(Ahemdabad(sola))");
        ImageList.add(R.drawable.d1);
        ImageNameList.add("Health Camp \nNarmada");
        ImageList.add(R.drawable.e1);
        ImageNameList.add("Health Camp \nNavsari");
        ImageList.add(R.drawable.f1);
        ImageNameList.add("Health Camp \nBhuj");
        ImageList.add(R.drawable.g1);
        ImageNameList.add("Health Camp \nBharuch");
        ImageList.add(R.drawable.h1);
        ImageNameList.add("Health Camp \nRajkot");
        ImageList.add(R.drawable.i1);
        ImageNameList.add("Health Conference \nGandhinagar");
        ImageList.add(R.drawable.j1);
        ImageNameList.add("Mega Health Camp \nAmreli");
        ImageList.add(R.drawable.k1);
        ImageNameList.add("Mega Health Camp \nPorbandar");
        ImageList.add(R.drawable.l1);
        ImageNameList.add("Mega Health Camp \nSabarkantha");
        ImageList.add(R.drawable.m1);
        ImageNameList.add("Mega Health Camp \nTapi");
        ImageList.add(R.drawable.n1);
        ImageNameList.add("Mega Health Camp \nJamnagar");
        ImageList.add(R.drawable.o1);
        ImageNameList.add("Mega Health Camp \nBhavnagar");
        ImageList.add(R.drawable.p1);
        ImageNameList.add("Mega Health Camp \nPanchmahal");
        ImageList.add(R.drawable.q1);
        ImageNameList.add("Mega Health Camp \nValsad");
        ImageList.add(R.drawable.r1);
        ImageNameList.add("Mega Health Camp \nGandhinagar");
        ImageList.add(R.drawable.s1);
        ImageNameList.add("Mega Health Camp \nNavsari-2014");
        ImageList.add(R.drawable.t1);
        ImageNameList.add("Mega Health Camp \nDahod");
        ImageList.add(R.drawable.u1);
        ImageNameList.add("Mega Health Camp \nSurat");
        ImageList.add(R.drawable.v1);
        ImageNameList.add("Mega Health Camp \nVadodara");
        ImageList.add(R.drawable.w1);
        ImageNameList.add("SKOCH Award \n2014");
        ImageList.add(R.drawable.x1);
        ImageNameList.add("Mobile Kisok \nAnnouncement");

        UploagImageListAdapter ImageAdapter = new UploagImageListAdapter(ImageList,ImageNameList);
        ehgGallery.setAdapter(ImageAdapter);
        return view;
    }

    public void callPDF(String mParam1, String mParam2, String mParam3) {
        if (mParam3.equalsIgnoreCase("FILE")) {
            if (mParam1.equalsIgnoreCase("Photos")) {
                wvDateDetail.loadUrl("http://192.95.6.213/new-html/gallery.html");
            }
        }
    }

    public void fragmentCall(String name) {
        svGallery.setVisibility(View.VISIBLE);
        lvNameList.setVisibility(View.GONE);
        wvDateDetail.setVisibility(View.GONE);
        callPDF(name, "", "FILE");

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
                    if (NameList.get(position).equalsIgnoreCase("Photos")) {
                        fragmentCall("Photos");

                    }
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView txvMenuName;
        }

    }

    public class UploagImageListAdapter extends BaseAdapter {
        LayoutInflater inflater;
        Context context;
        ArrayList<Integer> ImageList;
        ArrayList<String> ImageNameList;

        public UploagImageListAdapter(ArrayList<Integer> imagelist,ArrayList<String> ImageNameList) {
            ImageList = imagelist;
            this.ImageNameList=ImageNameList;
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return ImageList.size();
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
                convertView = inflater.inflate(R.layout.row_gridview_image, null);
                holder = new ViewHolder();
                holder.imageGridview = (ImageView) convertView.findViewById(R.id.imvGridImage);
                holder.txvImageName=(TextView) convertView.findViewById(R.id.txvImageName);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            try {
//                Picasso.with(getActivity())
//                        .load(ImageList.get(position))
//                        .into(holder.imageGridview);
                holder.imageGridview.setImageBitmap(decodeSampledBitmapFromResource(getResources(), ImageList.get(position), 200, 200));
                holder.txvImageName.setText(ImageNameList.get(position));

                holder.imageGridview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(getActivity(),PhotoActivity.class);
                        i.putExtra("IMAGENAME",ImageNameList.get(position));
                        i.putExtra("POSITION",position+"");
                        startActivity(i);
                    }
                });
                holder.txvImageName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            return convertView;
        }

        class ViewHolder {
            ImageView imageGridview;
            TextView txvImageName;
        }

    }
}
