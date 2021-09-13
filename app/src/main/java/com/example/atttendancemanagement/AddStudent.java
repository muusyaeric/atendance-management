package com.example.atttendancemanagement;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddStudent extends AppCompatActivity {

    public static final String TAG = "TAG";

    EditText mFullName, mFaculty, mContact, mEmail;
    Spinner mProgrammes;
    Button mSubmit, mViewStudent;
    TextView mStudentDetails;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;
    DocumentReference documentReference;
    CollectionReference lecturerProfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgrammes = findViewById(R.id.spnProgrammes);
        mFullName = findViewById(R.id.edtAdminFullName);
        mFaculty = findViewById(R.id.edtFaculty);
        mContact = findViewById(R.id.edtContact);
        mEmail = findViewById(R.id.edtAdminEmail);
        mSubmit = findViewById(R.id.btnSubmit);
        //mViewStudent = findViewById(R.id.btnViewTeacher);
        mStudentDetails = findViewById(R.id.txtTeacherDetails);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                String fullName = mFullName.getText().toString();
                String faculty = mFaculty.getText().toString();
                Integer contact = Integer.valueOf(mContact.getText().toString());
                String email = mEmail.getText().toString();
                String program = mProgrammes.getSelectedItem().toString();

                Toast.makeText(AddStudent.this, "User stored successfully", Toast.LENGTH_SHORT).show();
                userID = fAuth.getCurrentUser().getUid(); //Retrieves userID of the current logged in user.
                //DocumentReference documentReference = fStore.collection("students").document(userID); //Creating a document in Firestore to store data.
                CollectionReference studentProfiles = fStore.collection("students");

                Students student = new Students(fullName, faculty, contact, email, program);  //instantiating the object user



                studentProfiles.add(student)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "onSuccess: user profile is created for" + userID);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, e.toString());
                    }
                });

                startActivity(getIntent());
                finish();

            }
        });
    }

}