package com.example.passwordtingting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class makePassword extends AppCompatActivity {

    EditText ny_pin = findViewById(R.id.lavkode);
    Button button = findViewById(R.id.button2);

    Intent main = new Intent(this, MainActivity.class);

    private static final String FILE_NAME = "checkfile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);

                    String pin_kode = ny_pin.getText().toString();

                    fos.write(pin_kode.getBytes());

                    startActivity(main);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }



}