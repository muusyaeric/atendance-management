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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StudentSignIn extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private Button mSignIn;
    private TextView mSignUp;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_in);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEmail = findViewById(R.id.edtAdminEmail);
        mPassword = findViewById(R.id.edtLecPassword);
        mSignIn =findViewById(R.id.btnSignIn);
        mSignUp = findViewById(R.id.txtSignUp);
        fAuth = FirebaseAuth.getInstance();

        mSignIn.setOnClickListener(new View.OnClickListener() {
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


                //authenticate the user

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(StudentSignIn.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Student.class));
                        }
                        else {
                            Toast.makeText(StudentSignIn.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), StudentSignUp.class));
            }
        });

    }
}