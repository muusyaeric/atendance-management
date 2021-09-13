package com.example.atttendancemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class StudentSignUp extends AppCompatActivity {

    public static final String TAG = "TAG";
    private EditText mFullName, mRegNo, mEmail, mPassword;
    private Button mSignUp;
    private TextView mSignIn;
    private ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFullName = findViewById(R.id.edtAdminFullName);
        mRegNo = findViewById(R.id.edtAdminRegNo);
        mEmail = findViewById(R.id.edtAdminEmail);
        mPassword = findViewById(R.id.edtLecPassword);
        mSignUp =  findViewById(R.id.btnSignUp);
        mSignIn = findViewById(R.id.txtSignIn);
        progressBar =  findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance(); //getting current instance of the database from firebase to perform operations on the database
        fStore = FirebaseFirestore.getInstance();

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
                String fullname = mFullName.getText().toString();
                String regNo = mRegNo.getText().toString();

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
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("fName", fullname);
                            user.put("regNo", regNo);
                            user.put("email", email);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: user profile is created for " + userID);
                                }
                            });

                            startActivity(new Intent(getApplicationContext(), Student.class));
                        }else {
                            Toast.makeText(StudentSignUp.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), StudentSignIn.class));
            }
        });

    }
}