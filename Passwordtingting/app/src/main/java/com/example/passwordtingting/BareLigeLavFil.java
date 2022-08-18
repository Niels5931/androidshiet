package com.example.passwordtingting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class BareLigeLavFil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i, go_back;

        i = getIntent();

        Log.d("Bruh", i.getStringExtra("FILE_NAME"));

        try {
            FileOutputStream fos = openFileOutput(i.getStringExtra("FILE_NAME"),MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        go_back = new Intent(this,passwordGetter.class);
        startActivity(go_back);
        finish();
    }
}