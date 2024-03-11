package com.example.swev2.ui.others;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.swev2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FlightInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlightInfoFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String dolazak, polazak,  gate, sifra_leta, vreme_dolaska, vreme_polaska, trajanje,datum;
    private Integer cena;
    TextView txtpolazak,txtvreme_trajanja,txtdolazak,txtvreme_polaska,txt_vremedolaska,txtgate,txtsifra_leta,txtcena,txtdatum;

    public FlightInfoFragment(int cena, String dolazak, String polazak, String gate, String sifra_leta, String vreme_dolaska, String vreme_polaska, String trajanje, String datum_polaska) {
        // Required empty public constructor
    }

    public FlightInfoFragment(Integer cena , String dolazak, String polazak, String gate, String sifra_leta, String vreme_dolaska, String vreme_polaska, String trajanje,String datum)
    {
            this.cena = cena;
            this.dolazak=dolazak;
            this.polazak = polazak;
            this.gate = gate;
            this.sifra_leta = sifra_leta;
            this.vreme_dolaska = vreme_dolaska;
            this.vreme_polaska = vreme_polaska;
            this.trajanje = trajanje;
            this.datum = datum;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment FlightInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public FlightInfoFragment newInstance() {
       // FlightInfoFragment fragment = new FlightInfoFragment();
        FlightInfoFragment fragment = new FlightInfoFragment(cena ,dolazak, polazak,  gate, sifra_leta, vreme_dolaska, vreme_polaska, trajanje,datum);
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
        View view = inflater.inflate(R.layout.fragment_flight_info, container, false);

        txtpolazak = view.findViewById(R.id.flightinfopolazak);
        txtdolazak = view.findViewById(R.id.flightinfodolazak);
        txtvreme_trajanja = view.findViewById(R.id.infotrajanje);
        txtvreme_polaska = view.findViewById(R.id.flightinfovremepolaska);
        txt_vremedolaska = view.findViewById(R.id.flightinfovremedolaska);
        txtcena = view.findViewById(R.id.flightinfocena);
        txtsifra_leta = view.findViewById(R.id.flightinfosifraleta);
        txtgate = view.findViewById(R.id.flightinfogate);
        txtdatum = view.findViewById(R.id.flightinfodat);

        txtpolazak.setText(polazak);
        txtdolazak.setText(dolazak);
        txtvreme_trajanja.setText(trajanje);
        txtvreme_polaska.setText(vreme_polaska+"h");
        txt_vremedolaska.setText(vreme_dolaska+"h");
        txtcena.setText(cena.toString()+"€");
        txtsifra_leta.setText(sifra_leta);
        txtgate.setText(gate);
        txtdatum.setText(datum);

        Button kupi = view.findViewById(R.id.button2);
        kupi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = HomeChooseSeatFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeflightinfocontainerID,fragment).commit();
            }
        });

        return view;
    }


}