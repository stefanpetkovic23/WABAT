package com.example.swev2.ui.others;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swev2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFlightListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFlightListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFlightListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment HomeFlightListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFlightListFragment newInstance() {
        HomeFlightListFragment fragment = new HomeFlightListFragment();

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
        View view =  inflater.inflate(R.layout.fragment_home_flight_list, container, false);

        Fragment fragment = FlightListFragment.newInstance();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerID,fragment).commit();

        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Flights");

        return view;
    }
}