package com.example.atttendancemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LecturerSignUp extends AppCompatActivity {

    private EditText mFullName, mRegNo, mEmail, mPassword;
    private Button mSignUp;
    private TextView mSignIn;
    private ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_sign_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFullName = findViewById(R.id.edtLecFullName);
        mRegNo = findViewById(R.id.edtLecRegNo);
        mEmail = findViewById(R.id.edtLecEmail);
        mPassword = findViewById(R.id.edtLecPassword);
        mSignUp =  findViewById(R.id.btnSignUp);
        mSignIn = findViewById(R.id.txtSignIn);
        progressBar =  findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance(); //getting current instance of the database from firebase to perform operations on the database

        //check if the user is currently logged in
        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), Admin.class));
            finish();
        }

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){                  //checks if email field is empty
                    mEmail.setError("Email is required.");      //and sets the error message
                    return;
                }

                if (TextUtils.isEmpty(password)){                   //checks if password field is empty
                    mPassword.setError("Password is required.");     //and sets the error message
                    return;
                }

                if (password.length() < 6){                                                 //checking password length
                    mPassword.setError("Password should be more than 6 characters.");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register user in the firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "User created successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Lecturer.class));
                        }else {
                            Toast.makeText(LecturerSignUp.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LecturerSignIn.class));
            }
        });
    }
}