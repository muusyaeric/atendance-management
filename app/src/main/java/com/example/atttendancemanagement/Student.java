package com.example.atttendancemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class Student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button markAttendance = findViewById(R.id.btnMarkAttendance);
        Button viewCredentials = findViewById(R.id.btnStudentDetails);

        markAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MarkAttendance.class));
            }
        });

        viewCredentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), VeiwCredentials.class));
            }
        });
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();       //logsOut User
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}