package com.example.atttendancemanagement;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddLecturer extends AppCompatActivity {

    public static final String TAG = "TAG";

    EditText mFullName, mFaculty, mAddress, mContact, mEmail;
    Button mSubmit, mViewTeacher;
    TextView mTeacherDetails;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;
    DocumentReference documentReference;
    CollectionReference lecturerProfiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecturer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFullName = findViewById(R.id.edtAdminFullName);
        mFaculty = findViewById(R.id.edtFaculty);
        mAddress = findViewById(R.id.edtAddress);
        mContact = findViewById(R.id.edtContact);
        mEmail = findViewById(R.id.edtAdminEmail);
        mSubmit = findViewById(R.id.btnSubmit);
        //mViewTeacher = findViewById(R.id.btnViewTeacher);
        mTeacherDetails = findViewById(R.id.txtTeacherDetails);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = mFullName.getText().toString();
                String faculty = mFaculty.getText().toString();
                String address = mAddress.getText().toString();
                Integer contact = Integer.valueOf(mContact.getText().toString());
                String email = mEmail.getText().toString();

                Toast.makeText(AddLecturer.this, "User stored successfully", Toast.LENGTH_SHORT).show();
                userID = fAuth.getCurrentUser().getUid(); //Retrieves userID of the current logged in user.
                //DocumentReference documentReference = fStore.collection("users").document(userID); //Creating a document in Firestore to store data.
                CollectionReference lecturerProfiles = fStore.collection("lecturers");

                Lecturers user = new Lecturers(fullName, faculty, address, contact, email);  //instantiating the object user

                lecturerProfiles.add(user)
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
    /*
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {   //documentSnapshot contains all the documents data if the document in the reference exists.
                        if (documentSnapshot.exists()){                          //checks if the document exists
                            Lecturers user = documentSnapshot.toObject(Lecturers.class);

                            String fullName = user.getFullname();       //getting fullname from the user document
                            String faculty = user.getFaculty();
                            String address = user.getAddress();
                            Long contact = Long.valueOf(user.getContact());
                            String email = user.getEmail();

                            mTeacherDetails.setText("Fullname: " + fullName + "\n" +
                                    "Faculty: " + faculty + "\n" +
                                    "Address: " + address + "\n" +
                                    "Contact: " + contact + "\n" +
                                    "Email: " + email);
                        }
                        else {
                            Toast.makeText(AddLecturer.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddLecturer.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
         */

}