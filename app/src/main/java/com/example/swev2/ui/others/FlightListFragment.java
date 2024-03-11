package com.example.swev2.ui.others;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.swev2.R;
import com.example.swev2.adapters.RecycleAdapter;
import com.example.swev2.model.Flight;
import com.example.swev2.ui.boardingcard.BoardingCardFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FlightListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlightListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    RecycleAdapter adapter;
    ArrayList<Flight> list;

    Query query;

    String getstartdest,getfinaldest;

    public FlightListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *


     * @return A new instance of fragment FlightListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FlightListFragment newInstance() {
        FlightListFragment fragment = new FlightListFragment();

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
        View view = inflater.inflate(R.layout.fragment_flight_list, container, false);


        getParentFragmentManager().setFragmentResultListener("startdestfromsearch", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                getstartdest = result.getString("sd");
                String startdest = getstartdest;
                filterFlightsBySearchQuery(getstartdest, getfinaldest);
               // Toast.makeText(getContext(),getstartdest,Toast.LENGTH_LONG).show();
            }
        });


        getParentFragmentManager().setFragmentResultListener("finaldestfromsearch", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                getfinaldest = result.getString("fd");
                filterFlightsBySearchQuery(getstartdest, getfinaldest);
            }
        });
        recyclerView = view.findViewById(R.id.recycle_flights);
        databaseReference = FirebaseDatabase.getInstance().getReference("Flights");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Query query = databaseReference.orderByChild("polazak").equalTo("Beograd");
        list = new ArrayList<>();
        //filteredList = new ArrayList<>();
        adapter = new RecycleAdapter(getContext(),list);
        //adapter.setFilteredList(filteredList);
        //recyclerView.setAdapter(adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                //adapter.notifyDataSetChanged();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Flight flight = dataSnapshot.getValue(Flight.class);
                    list.add(flight);

                }
                if (getstartdest != null && getfinaldest != null) {
                    filterFlightsBySearchQuery(getstartdest, getfinaldest);
                } else {
                   // adapter.resetList();  // Reset the list to the original unfiltered list
                    //adapter.setFilteredList(list);
                    list.clear();
                    Toast.makeText(getContext(),"Curently we dont have flights with entered destinations!",Toast.LENGTH_LONG).show();
                }
                //adapter.setFilteredList(list);


             /*   Bundle bundle = new Bundle();
                bundle.putInt("price",list.get(0).getCena());
                Fragment paymentFragment = PaymentFragment.newInstance();
                paymentFragment.setArguments(bundle);*/
                adapter.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        recyclerView.setAdapter(adapter);


        //recyclerView.setAdapter(adapter);



        return view;
    }

    private void filterFlightsBySearchQuery(String getstartdest,String getfinaldest) {
        ArrayList<Flight> filteredList = new ArrayList<>();

        // Filter the flightList based on the search query
        for (Flight flight : list) {
            if (flight.getPolazak().toLowerCase().contains(getstartdest.toLowerCase()) && flight.getDolazak().toLowerCase().contains(getfinaldest.toLowerCase())) {
                filteredList.add(flight);

            }

            adapter.setFilteredList(filteredList);
            adapter.notifyDataSetChanged();
        }

       // return filteredList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getParentFragmentManager().clearFragmentResultListener("startdestfromsearch");
        getParentFragmentManager().clearFragmentResultListener("finaldestfromsearch");
    }
}