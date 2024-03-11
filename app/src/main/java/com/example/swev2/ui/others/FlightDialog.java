package com.example.swev2.ui.others;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import com.example.swev2.R;
import com.example.swev2.ui.bookflights.BookFlightFragment;

public class FlightDialog  extends DialogFragment {

    public static final String TAG = "FlightDialog";

    Button buy;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_flight_info,container,false);


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookFlightFragment fragment = (BookFlightFragment) getActivity().getSupportFragmentManager().findFragmentByTag("BookFlight");
            }
        });
        return view;
    }
}
