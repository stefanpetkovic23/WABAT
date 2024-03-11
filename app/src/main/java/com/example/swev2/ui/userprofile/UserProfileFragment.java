package com.example.swev2.ui.userprofile;

import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swev2.R;
import com.example.swev2.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileFragment extends Fragment {

    private UserProfileViewModel mViewModel;
    private String firstname,lastname,email,password,dateofbirth,phonenumber;
    TextView txtusername,txtemail,txtpassword,txtdateofbirth,txtphonenumber,txtdocnumber,txtnationality;

    public static UserProfileFragment newInstance() {
        return new UserProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

         txtusername = view.findViewById(R.id.profileusername);
         txtemail = view.findViewById(R.id.profileemail);
         txtpassword = view.findViewById(R.id.profilepassword);
         txtdateofbirth = view.findViewById(R.id.profilebirth);
         txtphonenumber = view.findViewById(R.id.profilephone);
         txtdocnumber = view.findViewById(R.id.searchdocument);
         txtnationality = view.findViewById(R.id.profilenationality);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();

        if(firebaseUser==null){
            Toast.makeText(UserProfileFragment.this.getContext(),"Something went wrong!",Toast.LENGTH_LONG).show();
        }else {
            showUserProfile(firebaseUser);
        }

        getParentFragmentManager().setFragmentResultListener("fromchecknationality", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String data = result.getString("nacionalnostcheckin");
                txtnationality.setText(data);

            }
        });

        getParentFragmentManager().setFragmentResultListener("fromcheckfragment", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String data = result.getString("documentfromcheckin");
                txtdateofbirth.setText(data);
            }
        });

        return  view;
    }

    private void showUserProfile(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();

        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user!=null){
                    firstname = user.firstname;
                    lastname = user.lastname;
                    email = firebaseUser.getEmail();
                    dateofbirth = user.broj_pasosa;
                    phonenumber = user.phonenumber;

                    txtusername.setText(firstname+" "+lastname);
                    txtemail.setText(email);
                    txtdocnumber.setText(dateofbirth);
                    //txtpassword.setText(password);
                    txtphonenumber.setText(phonenumber);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfileFragment.this.getContext(),"Something went wrong!",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UserProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}