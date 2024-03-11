package com.example.swev2.ui.others;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swev2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFlightInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFlightInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    FlightInfoFragment flightInfoFragment;

    public HomeFlightInfoFragment() {
        // Required empty public constructor
    }

    public HomeFlightInfoFragment(FlightInfoFragment flightInfoFragment) {
        this.flightInfoFragment = flightInfoFragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFlightInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFlightInfoFragment newInstance(String param1, String param2) {
        HomeFlightInfoFragment fragment = new HomeFlightInfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_home_flight_info, container, false);

        Fragment fragment = flightInfoFragment.newInstance();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeflightinfocontainerID,fragment).commit();

        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Flight info");

        return view;
    }
}