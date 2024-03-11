package com.example.swev2.ui.others;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.swev2.R;
import com.example.swev2.model.Booking;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Bundle bundleseat;
    Integer cena;
    Integer seatposition;
    DatabaseReference databaseReference;
    DatabaseReference userref;
    Integer selectedBaggageType;

    public PaymentFragment() {
        // Required empty public constructor
    }



    public PaymentFragment(int price) {
        this.cena = price;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentFragment newInstance() {
        PaymentFragment fragment = new PaymentFragment();
        Bundle args = new Bundle();
        args.putInt("cena",90);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            cena = getArguments().getInt("cena");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Payment");
        EditText cardnumber = view.findViewById(R.id.paycardnumber);
        EditText cardmmyy = view.findViewById(R.id.paycardmm);
        EditText cardname = view.findViewById(R.id.paycardname);
        EditText cardcvc = view.findViewById(R.id.paycardcvc);
        EditText zip = view.findViewById(R.id.paycardzip);
        cardnumber.addTextChangedListener(new TextWatcher() {
            // Change this to what you want... ' ', '-' etc..
            private static final char space = ' ';

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Remove spacing char
                if (s.length() > 0 && (s.length() % 5) == 0) {
                    final char c = s.charAt(s.length() - 1);
                    if (space == c) {
                        s.delete(s.length() - 1, s.length());
                    }
                }
                // Insert char where needed.
                if (s.length() > 0 && (s.length() % 5) == 0) {
                    char c = s.charAt(s.length() - 1);
                    // Only if its a digit where there should be a space we insert a space
                    if (Character.isDigit(c) && TextUtils.split(s.toString(), String.valueOf(space)).length <= 3) {
                        s.insert(s.length() - 1, String.valueOf(space));
                    }
                }
            }
        });

        cardmmyy.addTextChangedListener(new TextWatcher() {
            private static final String DATE_FORMAT_REGEX = "^(0[1-9]|1[0-2])[0-9]{0,2}$";
            private boolean isValidFormat = true;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();

                if (input.length() == 4) {
                    String month = input.substring(0, 2);
                    String year = input.substring(2);

                    input = month + "/" + year;
                    cardmmyy.setText(input);
                    cardmmyy.setSelection(input.length());
                    return;
                }

                isValidFormat = input.matches(DATE_FORMAT_REGEX);

            }
        });

        Bundle args = getArguments();
        if (args != null) {
            selectedBaggageType = args.getInt("selectedBaggageType", -1);
            String tip = Integer.toString(selectedBaggageType);
            if (selectedBaggageType == 0) {
             //   Toast.makeText(getContext(),tip,Toast.LENGTH_LONG).show();
            } else if (selectedBaggageType == 1) {
             //   Toast.makeText(getContext(),tip,Toast.LENGTH_LONG).show();
            } else if (selectedBaggageType == 2) {
             //   Toast.makeText(getContext(),tip,Toast.LENGTH_LONG).show();
            }
        }


        Bundle bundle = getArguments();
        if (bundle != null) {
            /*cena = bundle.getInt("price")+30;
            String cena1 = Integer.toString(cena);
            // Use the price value as needed (e.g., set text in a TextView)
            Toast.makeText(getContext(),cena1,Toast.LENGTH_LONG).show();*/
            int price = bundle.getInt("price", -1); // Provide a default value if "price" is not found
            if (price != -1) {
                cena = price + 110;
                String cena1 = Integer.toString(cena);
             //   Toast.makeText(getContext(), cena1, Toast.LENGTH_LONG).show();
            } else {
              //  Toast.makeText(getContext(), "Price value not found in the bundle.", Toast.LENGTH_LONG).show();
            }
        } else {
           // Toast.makeText(getContext(), "Bundle is null.", Toast.LENGTH_LONG).show();
        }
        String sediste;
        bundleseat = getArguments();
        if (bundleseat != null) {
            int selectedSeatGridView1 = bundleseat.getInt("selectedSeat",-1);
            //String sediste = Integer.toString(selectedSeatGridView1);
              //  Toast.makeText(getContext(),sediste,Toast.LENGTH_LONG).show();
            if(selectedSeatGridView1==0){
                sediste="A1";
            } else if (selectedSeatGridView1==1) {
                sediste="B1";
            } else if (selectedSeatGridView1==2) {
                sediste="C1";
            } else if (selectedSeatGridView1==3) {
                sediste="D1";
            } else if (selectedSeatGridView1==4) {
                sediste="A2";
            } else if (selectedSeatGridView1==5) {
                sediste="B2";
            } else if (selectedSeatGridView1==6) {
                sediste="C2";
            } else
                sediste="D2";
        }

        bundleseat = getArguments();
        if (bundleseat != null) {
            int selectedSeatGridView1 = bundleseat.getInt("selectedSeat2",-1);
            //String sediste = Integer.toString(selectedSeatGridView1);
            //  Toast.makeText(getContext(),sediste,Toast.LENGTH_LONG).show();
            if(selectedSeatGridView1==0){
                sediste="A3";
            } else if (selectedSeatGridView1==1) {
                sediste="B3";
            } else if (selectedSeatGridView1==2) {
                sediste="C3";
            } else if (selectedSeatGridView1==3) {
                sediste="D3";
            } else if (selectedSeatGridView1==4) {
                sediste="A4";
            } else if (selectedSeatGridView1==5) {
                sediste="B4";
            } else if (selectedSeatGridView1==6) {
                sediste="C4";
            } else if (selectedSeatGridView1==7) {
                sediste="D4";
            } else if (selectedSeatGridView1==8) {
                sediste ="A5";
            } else if (selectedSeatGridView1==9) {
                sediste="B5";
            } else if (selectedSeatGridView1==10) {
                sediste="C5";
            } else if (selectedSeatGridView1==11) {
                sediste="D5";
            } else if (selectedSeatGridView1==12) {
                sediste="A6";
            } else if (selectedSeatGridView1==13) {
                sediste="B6";
            } else if (selectedSeatGridView1==14) {
                sediste="C6";
            } else
                sediste="D6";

        }

        Button bookflight = view.findViewById(R.id.bookflight);
        bookflight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userref = FirebaseDatabase.getInstance().getReference("Registered Users");
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = auth.getCurrentUser();
                String userid = firebaseUser.getUid();
                String name = cardname.getText().toString();
                String number = cardnumber.getText().toString();
                String mmyy = cardmmyy.getText().toString();
                String cvc = cardcvc.getText().toString();
                String kartica = name+" "+number+" "+mmyy+" "+cvc;

                String scardname = cardname.getText().toString();
                String scardnumber = cardnumber.getText().toString();
                String scardmmyy = cardmmyy.getText().toString();
                String scvc = cardcvc.getText().toString();
                String szip = zip.getText().toString();

                if(TextUtils.isEmpty(scardname)){
                    Toast.makeText(getContext(),"Please enter your cardname!",Toast.LENGTH_LONG).show();
                    cardname.setError("Cardname is required!");
                    cardname.requestFocus();
                } else if(TextUtils.isEmpty(scardnumber)){
                    Toast.makeText(getContext(),"Please enter your card number!",Toast.LENGTH_LONG).show();
                    cardnumber.setError("Card number is required!");
                    cardnumber.requestFocus();
                }else if(TextUtils.isEmpty(scardmmyy)){
                    Toast.makeText(getContext(),"Please enter your date of card!",Toast.LENGTH_LONG).show();
                    cardmmyy.setError("Card date is required!");
                    cardmmyy.requestFocus();
                } else if(TextUtils.isEmpty(scvc)){
                    Toast.makeText(getContext(),"Please enter your cvc number!",Toast.LENGTH_LONG).show();
                    cardcvc.setError("CVC number is required!");
                    cardcvc.requestFocus();
                } else if(TextUtils.isEmpty(szip)){
                    Toast.makeText(getContext(),"Please enter your ZIP!",Toast.LENGTH_LONG).show();
                    zip.setError("Zip is required!");
                    zip.requestFocus();}
                else{
                Booking booking = new Booking("0",kartica,"B6",userid,selectedBaggageType,110);

                databaseReference = FirebaseDatabase.getInstance().getReference("Bookings");
                String key = databaseReference.push().getKey();
                databaseReference.child(key).setValue(booking).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(),"You are succesfuly booked flight!",Toast.LENGTH_LONG).show();
                             cardnumber.getText().clear();
                             cardmmyy.getText().clear();
                             cardname.getText().clear();
                             cardcvc.getText().clear();
                             zip.getText().clear();
                        }
                        else{
                            Toast.makeText(getContext(),"Booking failed!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
            }
        });


        return view;
    }
    private void BuyBooking(String idleta,String kartica,String sediste, String korisnikid,Integer prtljag,Integer ukcena){

        Booking booking = new Booking(idleta,kartica,sediste,korisnikid,prtljag,ukcena);
    }
}