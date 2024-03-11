package com.example.swev2.ui.others;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.swev2.R;
import com.example.swev2.adapters.GridAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChooseSeatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChooseSeatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;




    public ChooseSeatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ChooseSeatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChooseSeatFragment newInstance() {
        ChooseSeatFragment fragment = new ChooseSeatFragment();

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
        View view =  inflater.inflate(R.layout.fragment_choose_seat, container, false);

        String[] seatnumber = new String[]{"A1","B1","C1","D1","A2","B2","C2","D2"};
        GridView grid = view.findViewById(R.id.gridview);
        GridAdapter gridAdapter = new GridAdapter(getContext(),seatnumber);
        grid.setAdapter(gridAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for(int j =0;j<grid.getChildCount();j++){
                   // String selectedseat = seatnumber[j];
                    int selectedseat = i;
                    Bundle bundle = new Bundle();
                    bundle.putInt("selectedSeat",selectedseat);
                    //Toast.makeText(getContext(),Integer.toString(selectedseat),Toast.LENGTH_LONG).show();
                    Fragment paymentFragment = new PaymentFragment();
                    paymentFragment.setArguments(bundle);
                    CardView card = view.findViewById(R.id.cardView);
                    card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            card.setCardBackgroundColor(getResources().getColor(R.color.blue));

                        }
                    });
                }
            }
        });




        String[] seatnumberbusiness = new String[]{"A3","B3","C3","D4","A4","B4","C4","D4","A5","B5","C5","D5","A6","B6","C6","D6"};
        GridView gridbusiness = view.findViewById(R.id.gridview2);
        GridAdapter gridAdapter1 = new GridAdapter(getContext(),seatnumberbusiness);
        gridbusiness.setAdapter(gridAdapter1);
        gridbusiness.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for(int j =0;j<grid.getChildCount();j++){
                    int selectedseat = i;
                    Bundle bundle = new Bundle();
                    bundle.putInt("selectedSeat2",selectedseat);
                    //Toast.makeText(getContext(),Integer.toString(selectedseat),Toast.LENGTH_LONG).show();
                    Fragment paymentFragment = new PaymentFragment();
                    paymentFragment.setArguments(bundle);
                    CardView card = view.findViewById(R.id.cardView);
                    card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            card.setCardBackgroundColor(getResources().getColor(R.color.blue));
                        }
                    });
                }
            }
        });

        String[] seatnumberseconomy = new String[]{"A7"
                ,"B7","C7","D7","A8","B8","C8","D8","A9","B9","C9","D9","A10","B10","C10","D10","A11","B11","C11","D11","A12"
                ,"B12","C12","D12","A13","B13","C13","D13","A14","B14","C14","D14","A15","B15","C15","D15","A16","B16","C16","D16","A17"
                ,"B17","C17","D17","A18","B18","C18","D18","A19","B19","C19","D19","A20","B20","C20","D20","A21","B21","C21","D21"
                ,"A22","B22","C22","D22"};
        GridView grideconomy = view.findViewById(R.id.gridview3);
        GridAdapter gridAdapter2 = new GridAdapter(getContext(),seatnumberseconomy);
        grideconomy.setAdapter(gridAdapter2);
        grideconomy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for(int j =0;j<grid.getChildCount();j++){
                    CardView card = view.findViewById(R.id.cardView);
                    card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            card.setCardBackgroundColor(getResources().getColor(R.color.blue));
                        }
                    });
                }
            }
        });

        Button pay = view.findViewById(R.id.plati);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = HomeBaggageFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.chooseseatcontainerID,fragment).commit();

            }
        });

        return view;
    }
}