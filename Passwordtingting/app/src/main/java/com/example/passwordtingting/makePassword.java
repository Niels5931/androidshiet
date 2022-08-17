package com.example.passwordtingting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class makePassword extends AppCompatActivity {

    private static final String FILE_NAME = "checkfile7.txt";

    EditText ny_pin;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_password);

        ny_pin = findViewById(R.id.lavkode);
        button = findViewById(R.id.button2);

        Intent main = new Intent(this, MainActivity.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pin_kode = ny_pin.getText().toString();

                FileOutputStream fos = null;

                try {
                    fos = openFileOutput(FILE_NAME,MODE_PRIVATE);

                    fos.write(pin_kode.getBytes());

                    startActivity(main);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            Log.d("Bruh", "Den vil ikke lukke");
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

    }



}