package com.example.swev2.ui.others;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.swev2.R;
import com.example.swev2.model.User;
import com.example.swev2.ui.bookflights.BookFlightFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Register extends AppCompatActivity {

    EditText edfirstname,edlastname,eddateofbirth,edphonenumber,edemail,edpassword;
    private static final String TAG="Register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Calendar calendar = Calendar.getInstance();
        edfirstname = findViewById(R.id.first_name);
        edlastname = findViewById(R.id.last_name);
        eddateofbirth = findViewById(R.id.date_of_birth);
        edphonenumber = findViewById(R.id.phone_number);
        edemail = findViewById(R.id.e_mail);
        edpassword = findViewById(R.id.password);

        Button register = findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Firstname = edfirstname.getText().toString();
                String Lastname = edlastname.getText().toString();
                String Dateofbirth = eddateofbirth.getText().toString();
                String Phonenumber = edphonenumber.getText().toString();
                String Email = edemail.getText().toString();
                String Password = edpassword.getText().toString();

                if(TextUtils.isEmpty(Firstname)){
                    Toast.makeText(Register.this,"Please enter your firstname!",Toast.LENGTH_LONG).show();
                    edfirstname.setError("Firstname is required!");
                    edfirstname.requestFocus();
                }
                else if(TextUtils.isEmpty(Lastname)){
                    Toast.makeText(Register.this,"Please enter your lastname!",Toast.LENGTH_LONG).show();
                    edlastname.setError("Lastname is required");
                    edlastname.requestFocus();
                }
                else if(TextUtils.isEmpty(Dateofbirth)){
                    Toast.makeText(Register.this,"Please enter you passport number!",Toast.LENGTH_LONG).show();
                    eddateofbirth.setError("Date of birth is required");
                    eddateofbirth.requestFocus();
                }
                else if(TextUtils.isEmpty(Phonenumber)){
                    Toast.makeText(Register.this,"Please enter your phone number!",Toast.LENGTH_LONG).show();
                    edphonenumber.setError("Phone number is required");
                    edphonenumber.requestFocus();
                }
                else if (Phonenumber.length()!=10) {
                    Toast.makeText(Register.this,"Phone number must contain 10 digits!",Toast.LENGTH_LONG).show();
                    edphonenumber.setError("Mobile phone should be 10 digits");
                    edphonenumber.requestFocus();
                }
                 else if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(Register.this,"Please enter your email!",Toast.LENGTH_LONG).show();
                    edemail.setError("Email is required");
                    edemail.requestFocus();
                }
                 else if(TextUtils.isEmpty(Password)){
                     Toast.makeText(Register.this,"Please enter your password",Toast.LENGTH_LONG).show();
                     edpassword.setError("Password is required");
                     edpassword.requestFocus();
                } else if (Password.length()<6) {
                     Toast.makeText(Register.this,"Password is short!",Toast.LENGTH_LONG).show();
                     edpassword.setError("Password is short");
                     edpassword.requestFocus();
                }
                 else{
                     registerUser(Firstname,Lastname,Dateofbirth,Phonenumber,Email,Password);
                }


            }
        });


     /*   DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
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
                new DatePickerDialog(Register.this, date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        }); */


    }

    private void registerUser(String firstname, String lastname, String dateofbirth, String phonenumber, String email, String password) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser firebaseUser = auth.getCurrentUser();

                    User user = new User(firstname,lastname,dateofbirth,phonenumber);
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Registered Users");

                    reference.child(firebaseUser.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(Register.this,"User succesfuly registered!",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Register.this,Login.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(Register.this,"User registration failed!",Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                }
                else{
                    try{
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e){
                        edpassword.setError("Your password is too weak!");
                        edpassword.requestFocus();
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        edpassword.setError("Your email is invalid or already in use!");
                        edpassword.requestFocus();
                    } catch (FirebaseAuthUserCollisionException e){
                        edpassword.setError("User is already registered with this email!");
                        edpassword.requestFocus();
                    } catch (Exception e){
                        Toast.makeText(Register.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}