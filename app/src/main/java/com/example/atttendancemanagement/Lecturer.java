package com.example.atttendancemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Lecturer extends AppCompatActivity {

    private Button mGenerate, mStudentsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mGenerate = findViewById(R.id.btnGenerate);
        mStudentsView = findViewById(R.id.btnStudentsView);

        mGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GenerateCode.class));
            }
        });

        mStudentsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisteredStudents.class));
            }
        });

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();       //logsOut User
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

}