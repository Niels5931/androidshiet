package com.example.passwordtingting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "checkfile.txt";

    EditText input;
    Button button;
    String pin;
    Intent password_getter = new Intent(this, passwordGetter.class);
    Intent make_pin = new Intent(this, makePassword.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        button = findViewById(R.id.button);

        try {
            pin = check_for_file();
        } catch (FileNotFoundException e) {

            startActivity(make_pin);

        } catch (IOException e) {
            e.printStackTrace();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pin.equals(input.getText().toString())) {

                    startActivity(password_getter);

                } else {

                    Toast error = Toast.makeText(getApplicationContext(),"Kode findes ikke", Toast.LENGTH_LONG);
                    error.show();

                    input.setText("");

                }

            }
        });


    }

    private String check_for_file() throws IOException {

        FileInputStream fis = openFileInput(FILE_NAME);

        String fis_content = String.valueOf(fis.read());

        return fis_content;

    }


}