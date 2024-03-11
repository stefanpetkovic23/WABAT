package com.example.swev2.ui.others;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.swev2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BaggageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaggageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BaggageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BaggageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BaggageFragment newInstance() {
        BaggageFragment fragment = new BaggageFragment();

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
        View view = inflater.inflate(R.layout.fragment_baggage, container, false);
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Baggage");
        Button luggage = view.findViewById(R.id.choosehand);
        Button luggageto10 = view.findViewById(R.id.chooseto10);
        Button luggageto24 = view.findViewById(R.id.chooseto24);
        TextView txtluggage = view.findViewById(R.id.handluggage);
        TextView txtluggageto10 = view.findViewById(R.id.luggage10);
        TextView txtluggageto24 = view.findViewById(R.id.luggage24);
        luggage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedBaggageType = 0;
                Bundle result = new Bundle();
                result.putInt("selectedBaggageType",selectedBaggageType);
                Fragment fragment = PaymentFragment.newInstance();
                fragment.setArguments(result);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homebaggage,fragment).commit();
            }
        });
        luggageto10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedBaggageType = 1;
                Bundle result = new Bundle();
                result.putInt("selectedBaggageType",selectedBaggageType);
                Fragment fragment = PaymentFragment.newInstance();
                fragment.setArguments(result);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homebaggage,fragment).commit();
            }
        });
        luggageto24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedBaggageType = 2;
                Bundle result = new Bundle();
                result.putInt("selectedBaggageType",selectedBaggageType);
                Fragment fragment = PaymentFragment.newInstance();
                fragment.setArguments(result);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homebaggage,fragment).commit();
            }
        });

        return view;
    }
}