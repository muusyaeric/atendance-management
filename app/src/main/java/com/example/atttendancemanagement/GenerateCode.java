package com.example.atttendancemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateCode extends AppCompatActivity {

    EditText qrvalue;
    Button generator;
    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_code);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        qrvalue = findViewById(R.id.qrinput);
        generator = findViewById(R.id.btnGenerateCode);
        qrImage = findViewById(R.id.imageView);

        generator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = qrvalue.getText().toString();
                if (data.isEmpty()){
                    qrvalue.setError("Value Required!");
                }else {
                    QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 500);
                    try {
                        // Getting QR-Code as Bitmap
                        Bitmap qrBits = qrgEncoder.getBitmap();
                        // Setting Bitmap to ImageView
                        qrImage.setImageBitmap(qrBits);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }
}