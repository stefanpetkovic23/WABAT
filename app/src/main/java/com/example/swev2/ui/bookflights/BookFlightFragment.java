package com.example.swev2.ui.bookflights;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swev2.R;
import com.example.swev2.ui.others.BaggageFragment;
import com.example.swev2.ui.others.HomeFlightListFragment;
import com.example.swev2.ui.others.Register;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookFlightFragment extends Fragment {

    private BookFlightViewModel mViewModel;

    public static  final String TAG = "BookFlight";

    public static BookFlightFragment newInstance() {
        return new BookFlightFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_flight, container, false);


        EditText startdate= view.findViewById(R.id.Departure);
        EditText enddate=view.findViewById(R.id.Comeback);
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        EditText startdest = view.findViewById(R.id.searchstartdestination);
        EditText enddest = view.findViewById(R.id.searchfinaldestination);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayofmonth) {

                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayofmonth);

                updateCalendar();

            }
            private void updateCalendar() {
                String Format="MM/dd/yy";
                SimpleDateFormat sdf= new SimpleDateFormat(Format, Locale.getDefault());

                startdate.setText(sdf.format(calendar.getTime()));
            }
        };

        DatePickerDialog.OnDateSetListener date1 =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayofmonth) {

                calendar1.set(Calendar.YEAR,year);
                calendar1.set(Calendar.MONTH,month);
                calendar1.set(Calendar.DAY_OF_MONTH,dayofmonth);

                updateCalendar();

            }
            private void updateCalendar() {
                String Format="MM/dd/yy";
                SimpleDateFormat sdf= new SimpleDateFormat(Format, Locale.getDefault());

                enddate.setText(sdf.format(calendar1.getTime()));
            }
        };

        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(BookFlightFragment.this.getContext(), date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        Button search= view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Fragment fragment = HomeFlightListFragment.newInstance();

                Bundle startdestination = new Bundle();
                startdestination.putString("sd",startdest.getText().toString());
                getParentFragmentManager().setFragmentResult("startdestfromsearch",startdestination);

                Bundle finaldestination = new Bundle();
                finaldestination.putString("fd",enddest.getText().toString());
                getParentFragmentManager().setFragmentResult("finaldestfromsearch",finaldestination);

               // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeseachflight,fragment).commit();
                String polazak = startdest.getText().toString();
                String dolazak = enddest.getText().toString();
                if(TextUtils.isEmpty(polazak))
                {
                    Toast.makeText(getContext(),"Please enter start destination!",Toast.LENGTH_LONG).show();
                    startdest.setError("City is required!");
                    startdest.requestFocus();
                }
                else if(TextUtils.isEmpty(dolazak)){
                    Toast.makeText(getContext(),"Please enter final destination!",Toast.LENGTH_LONG).show();
                    enddest.setError("City is required!");
                    enddest.requestFocus();
                }
                else{
                    Fragment fragment = HomeFlightListFragment.newInstance();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeseachflight,fragment).commit();
                }
            }
        });

        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(BookFlightFragment.this.getContext(), date1,calendar1.get(Calendar.YEAR),calendar1.get(Calendar.MONTH),
                        calendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

       /* TextView startdestination = view.findViewById(R.id.searchstartdestination);
        TextView finaldestination = view.findViewById(R.id.searchfinaldestination);
        getParentFragmentManager().setFragmentResultListener("fragment1", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                String data = result.getString("data");
                startdestination.setText(data);
            }
        });
        getParentFragmentManager().setFragmentResultListener("fragment2", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String data1 = result.getString("data");
                finaldestination.setText(data1);
            }
        });



        finaldestination.setOnClickListener(new View.OnClickListener() {
             @Override
                 public void onClick(View view) {
                 Bundle bundle = new Bundle();
                 bundle.putString("position","final");
                 Navigation.findNavController(view).navigate(R.id.action_nav_bookflight_to_searchDestinationsFragment2, bundle);
            }
        });

        startdestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("position","start");
                Navigation.findNavController(view).navigate(R.id.action_nav_bookflight_to_searchDestinationsFragment, bundle);
            }
        }); */



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BookFlightViewModel.class);
        // TODO: Use the ViewModel
    }

}