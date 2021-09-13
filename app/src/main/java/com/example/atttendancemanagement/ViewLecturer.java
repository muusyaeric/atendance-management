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

public class ViewLecturer extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextView mTeacherDetails;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lecturer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTeacherDetails = findViewById(R.id.txtTeacherDetails);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        userID = fAuth.getCurrentUser().getUid(); //Retrieves userID of the current logged in user.
        DocumentReference documentReference = fStore.collection("lecturers").document(userID);
        CollectionReference lecturerProfiles = fStore.collection("lecturers");

        lecturerProfiles.addSnapshotListener(this,new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null){
                    return;
                }
                String data = "";
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    Lecturers user =  documentSnapshot.toObject(Lecturers.class);

                    String fullName = user.getFullname();       //getting fullname from the user document
                    String faculty = user.getFaculty();
                    String address = user.getAddress();
                    Integer contact = user.getContact();
                    String email = user.getEmail();

                    data += "fullname: " + fullName + "\n" +
                            "faculty: " + faculty + "\n" +
                            "address: " + address + "\n" +
                            "contact: " + contact + "\n" +
                            "email: " + email + "\n\n";
                }
                mTeacherDetails.setText(data);    //displays the details
            }
        });
/*
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {   //documentSnapshot contains all the documents data if the document in the reference exists.
                        if (documentSnapshot.exists()) {                          //checks if the document exists
                            String fullName = documentSnapshot.getString("fullname");
                            String faculty = documentSnapshot.getString("faculty");
                            String address = documentSnapshot.getString("address");
                            Long contact = documentSnapshot.getLong("contact");
                            String email = documentSnapshot.getString("email");

                            mTeacherDetails.setText("Fullname: " + fullName + "\n" +
                                    "Faculty: " + faculty + "\n" +
                                    "Address: " + address + "\n" +
                                    "Contact: " + contact + "\n" +
                                    "Email: " + email);
                        } else {
                            Toast.makeText(ViewLecturer.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ViewLecturer.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
 */


        /*
        lecturerProfiles.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {      //QuerySnapshot contains multiple document snapshots
                        //loop to get single document snapshots out of the QuerySnapshot and turn them to the user object
                        String data = "";
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            Lecturers user =  documentSnapshot.toObject(Lecturers.class);

                            String fullName = user.getFullname();       //getting fullname from the user document
                            String faculty = user.getFaculty();
                            String address = user.getAddress();
                            Long contact = Long.valueOf(user.getContact());
                            String email = user.getEmail();

                            data += "Fullname: " + fullName + "\n" +
                                    "Faculty: " + faculty + "\n" +
                                    "Address: " + address + "\n" +
                                    "Contact: " + contact + "\n" +
                                    "Email: " + email + "\n\n";
                        }
                        mTeacherDetails.setText(data);    //displays the details
                    }
                });

         */


    }


}