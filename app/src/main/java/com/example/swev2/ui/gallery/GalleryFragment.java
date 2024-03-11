package com.example.swev2.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swev2.R;
import com.example.swev2.adapters.MyFlightsAdapter;
import com.example.swev2.adapters.RecycleAdapter;
import com.example.swev2.databinding.FragmentGalleryBinding;
import com.example.swev2.model.Flight;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyFlightsAdapter recycleAdapter;
    ArrayList<Flight> list;

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        View view = inflater.inflate(R.layout.fragment_gallery,container,false);

        recyclerView = view.findViewById(R.id.recycle_myflights);
        databaseReference = FirebaseDatabase.getInstance().getReference("Flights");
        Query query = databaseReference.limitToFirst(3);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        //filteredList = new ArrayList<>();
        recycleAdapter = new MyFlightsAdapter(getContext(),list);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                //adapter.notifyDataSetChanged();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Flight flight = dataSnapshot.getValue(Flight.class);
                    list.add(flight);

                }
                recycleAdapter.notifyDataSetChanged();
                // Toast.makeText(getContext(),getfinaldest,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView.setAdapter(recycleAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}