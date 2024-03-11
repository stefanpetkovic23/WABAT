package com.example.swev2.ui.others;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.swev2.MainActivity;
import com.example.swev2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText edloginemail,edloginpassword;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login= findViewById(R.id.login);
        edloginemail = findViewById(R.id.E_mail);
        edloginpassword = findViewById(R.id.Password);

        auth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textEmail = edloginemail.getText().toString();
                String textPassword = edloginpassword.getText().toString();

                if(TextUtils.isEmpty(textEmail)){
                    Toast.makeText(Login.this,"Please enter your email!",Toast.LENGTH_LONG).show();
                    edloginemail.setError("Email is required");
                    edloginemail.requestFocus();
                } else if(TextUtils.isEmpty(textPassword)){
                    Toast.makeText(Login.this,"Please enter your password!",Toast.LENGTH_LONG).show();
                    edloginpassword.setError("Password is required");
                    edloginpassword.requestFocus();
                } else{
                    loginuser(textEmail,textPassword);
                }


            }
        });

        Button signup = findViewById(R.id.register);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);

            }
        });
    }

    private void loginuser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    Toast.makeText(Login.this,"Login failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}