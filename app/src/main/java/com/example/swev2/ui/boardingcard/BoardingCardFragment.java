package com.example.swev2.ui.boardingcard;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swev2.R;
import com.example.swev2.model.Flight;
import com.example.swev2.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BoardingCardFragment extends Fragment {


    TextView txtpassengername,txtflightnumber,txtgate,txtseat,txtdate,txtboarding,txtdeparture,txtstartdestination, txtfinaldestination;
    DatabaseReference databaseReference;
    DatabaseReference userreference;
    DatabaseReference bookingreference;
    ArrayList<Flight> list;
    Flight flight;
    private BoardingCardViewModel mViewModel;

    public static BoardingCardFragment newInstance() {
        return new BoardingCardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boarding_card, container, false);

        txtpassengername = view.findViewById(R.id.boardingpasspassname);
        txtflightnumber = view.findViewById(R.id.boardpassflight);
        txtgate = view.findViewById(R.id.boardpassgate);
        txtseat = view.findViewById(R.id.boardpassSeat);
        txtdate = view.findViewById(R.id.boardpassdate);
        txtboarding = view.findViewById(R.id.boardpassboardingtime);
        txtdeparture = view.findViewById(R.id.boardpassdeparturetime);
        txtstartdestination = view.findViewById(R.id.boardpassstartdest);
        txtfinaldestination = view.findViewById(R.id.boardpassenddest);
        databaseReference = FirebaseDatabase.getInstance().getReference("Flights");
        Query query = databaseReference.orderByChild("cena").equalTo(90);
        flight = new Flight();
        list = new ArrayList<>();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                     flight = dataSnapshot.getValue(Flight.class);
                     list.add(flight);

                }
                txtstartdestination.setText(flight.getPolazak());
                txtfinaldestination.setText(flight.getDolazak());
                txtflightnumber.setText(flight.getSifra_leta());
                txtgate.setText(flight.getGate());
                txtdate.setText(flight.getDatum_polaska());
                txtboarding.setText(flight.getVreme_polaska());
                txtdeparture.setText(flight.getVreme_dolaska());
                txtseat.setText("B6");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        userreference = FirebaseDatabase.getInstance().getReference("Registered Users");
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        String userid = firebaseUser.getUid();
        userreference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user!=null){
                    String firstname = user.firstname;
                    String lastname = user.lastname;
                    txtpassengername.setText(firstname+" "+lastname
                    );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BoardingCardViewModel.class);
        // TODO: Use the ViewModel
    }

}