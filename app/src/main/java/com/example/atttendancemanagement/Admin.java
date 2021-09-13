package com.example.atttendancemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button mAddLecturer = findViewById(R.id.btnAddLecturer);
        Button mAddStudent = findViewById(R.id.btnAddStudent);
        Button mViewLecturer = findViewById(R.id.btnViewLecturer);
        Button mViewStudent = findViewById(R.id.btnViewStudent);

        mAddLecturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddLecturer.class));
            }
        });

        mViewLecturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewLecturer.class));
            }
        });

        mAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddStudent.class));
            }
        });

        mViewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ViewStudent.class));
            }
        });
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();       //logsOut User
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}