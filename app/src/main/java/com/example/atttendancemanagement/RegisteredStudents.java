package com.example.atttendancemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class RegisteredStudents extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextView mStudentDetails;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_students);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mStudentDetails = findViewById(R.id.txtStudentDetails);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        userID = fAuth.getCurrentUser().getUid(); //Retrieves userID of the current logged in user.
        DocumentReference documentReference = fStore.collection("students").document(userID);
        final CollectionReference studentProfiles = fStore.collection("students");

        studentProfiles.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }
                String data = "";
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Students student = documentSnapshot.toObject(Students.class);

                    String fullName = student.getFullname();       //getting fullname from the user document
                    String faculty = student.getFaculty();
                    Long contact = Long.valueOf(student.getContact());
                    String email = student.getEmail();
                    String program = student.getProgram();

                    data += "Fullname: " + fullName + "\n" +
                            "Faculty: " + faculty + "\n" +
                            "Contact: " + contact + "\n" +
                            "Email: " + email + "\n" +
                            "Program:  " + program + "\n\n";
                }
                mStudentDetails.setText(data);    //displays the details
            }
        });
    }

}