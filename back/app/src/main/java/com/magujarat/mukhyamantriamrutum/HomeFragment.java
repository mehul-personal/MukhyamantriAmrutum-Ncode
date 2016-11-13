package com.ncode.mukhyamantriamrutum;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ncode.mukhyamantriamrutum.infiniteindicator.InfiniteIndicatorLayout;
import com.ncode.mukhyamantriamrutum.infiniteindicator.slideview.BaseSliderView;
import com.ncode.mukhyamantriamrutum.infiniteindicator.slideview.DefaultSliderView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<PageInfo> viewInfos;
    InfiniteIndicatorLayout viewPager;
    ForwardUserAdapter adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (InfiniteIndicatorLayout) view.findViewById(R.id.vpHomeSlider);

        viewInfos = new ArrayList<PageInfo>();

        viewInfos.add(new PageInfo("" + 1, R.drawable.img1));
        viewInfos.add(new PageInfo("" + 2, R.drawable.img2));
        viewInfos.add(new PageInfo("" + 3, R.drawable.img3));
        viewInfos.add(new PageInfo("" + 4, R.drawable.img4));
        viewInfos.add(new PageInfo("" + 5, R.drawable.img5));
        viewInfos.add(new PageInfo("" + 6, R.drawable.img6));
        viewInfos.add(new PageInfo("" + 7, R.drawable.img7));
        viewInfos.add(new PageInfo("" + 8, R.drawable.img8));
        viewInfos.add(new PageInfo("" + 9, R.drawable.img9));
        viewInfos.add(new PageInfo("" + 10, R.drawable.img10));
        viewInfos.add(new PageInfo("" + 11, R.drawable.img11));
        viewInfos.add(new PageInfo("" + 12, R.drawable.img12));
        viewInfos.add(new PageInfo("" + 13, R.drawable.img13));
        viewInfos.add(new PageInfo("" + 14, R.drawable.img14));
        viewInfos.add(new PageInfo("" + 15, R.drawable.img15));

        for (PageInfo name : viewInfos) {
            DefaultSliderView textSliderView = new DefaultSliderView(getActivity());

            try {
                textSliderView.image(name.getDrawableRes()).setScaleType(BaseSliderView.ScaleType.Fit);
                textSliderView.getBundle()
                        .putString("extra", name.getData());
                viewPager.addSlider(textSliderView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        viewPager.setIndicatorPosition(InfiniteIndicatorLayout.IndicatorPosition.Center_Bottom);
        viewPager.startAutoScroll();



//        ArrayList<Integer> imagelist = new ArrayList<Integer>();
//        imagelist.add(R.drawable.logo1);
//        ForwardUserAdapter adapter = new ForwardUserAdapter(imagelist);
//        picker.setValues(adapter);
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class ForwardUserAdapter extends BaseAdapter {

        LayoutInflater inflater;
        ArrayList<Integer> Imagelist;

        int m = 0;

        public ForwardUserAdapter(ArrayList<Integer> imagelist) {
            // TODO Auto-generated constructor stub
            Imagelist = imagelist;

            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                        R.layout.row_adapter_image, parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.image.setImageResource(Imagelist.get(position));
            return convertView;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return Imagelist.size();
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
            ImageView image;

        }
    }

}
