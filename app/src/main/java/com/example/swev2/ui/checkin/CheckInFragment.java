package com.example.swev2.ui.checkin;

import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.Toast;

import com.example.swev2.R;
import com.example.swev2.ui.bookflights.BookFlightFragment;
import com.example.swev2.ui.others.Register;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CheckInFragment extends Fragment {

    private CheckInViewModel mViewModel;

    public static CheckInFragment newInstance() {
        return new CheckInFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_check_in, container, false);

        Button button = view.findViewById(R.id.checkbutton);
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.check_in_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        Button okey = dialog.findViewById(R.id.btn_okay);
        Button cancel = dialog.findViewById(R.id.btn_cancel);

        EditText ednationality,eddateofbirth,eddocumentnumber,edphonenumber;
        ednationality = view.findViewById(R.id.nationalityID);
        eddateofbirth = view.findViewById(R.id.date_of_birthID);
        eddocumentnumber = view.findViewById(R.id.documentID);
        edphonenumber = view.findViewById(R.id.phoneNumberID);

        Calendar calendar = Calendar.getInstance();
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

                eddateofbirth.setText(sdf.format(calendar.getTime()));
            }
        };

        eddateofbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        okey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ednationality.getText().clear();
                eddateofbirth.getText().clear();
                eddocumentnumber.getText().clear();
                edphonenumber.getText().clear();

                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nationality,dateofbirth,documentnumber,phonenumber;
                nationality = ednationality.getText().toString();
                dateofbirth = eddateofbirth.getText().toString();
                documentnumber = eddocumentnumber.getText().toString();
                phonenumber = edphonenumber.getText().toString();

                if(TextUtils.isEmpty(nationality)){
                    Toast.makeText(getContext(),"Please enter your nationality!",Toast.LENGTH_LONG).show();
                    ednationality.setError("Firstname is required!");
                    ednationality.requestFocus();
                } else if(TextUtils.isEmpty(dateofbirth)){
                    Toast.makeText(getContext(),"Please enter your date of birth!",Toast.LENGTH_LONG).show();
                    eddateofbirth.setError("Date of birth is required!");
                    eddateofbirth.requestFocus();
                }else if(TextUtils.isEmpty(documentnumber)){
                    Toast.makeText(getContext(),"Please enter your document number!",Toast.LENGTH_LONG).show();
                    eddocumentnumber.setError("Document number is required!");
                    eddocumentnumber.requestFocus();
                } else if(TextUtils.isEmpty(phonenumber)){
                    Toast.makeText(getContext(),"Please enter your phone number!",Toast.LENGTH_LONG).show();
                    edphonenumber.setError("Phone number is required!");
                    edphonenumber.requestFocus();
                } else{
                    dialog.show();
                }

                Bundle nacionalnost = new Bundle();
                nacionalnost.putString("nacionalnostcheckin",nationality);
                getParentFragmentManager().setFragmentResult("fromchecknationality",nacionalnost);

                Bundle result = new Bundle();
                result.putString("documentfromcheckin",dateofbirth);
                getParentFragmentManager().setFragmentResult("fromcheckfragment",result);

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CheckInViewModel.class);
        // TODO: Use the ViewModel
    }

}