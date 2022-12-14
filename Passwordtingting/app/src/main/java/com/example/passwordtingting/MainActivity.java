package com.example.passwordtingting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "pin.txt";

    String pin;
    Button button;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent password_getter = new Intent(this, passwordGetter.class);
        Intent make_pin = new Intent(this, makePassword.class);

        FileInputStream fis;

        try {
            fis = openFileInput(FILE_NAME);

            pin = get_input_from_file(fis);

            fis.close();
        } catch (FileNotFoundException e) {

            startActivity(make_pin);

        } catch (IOException e) {
            e.printStackTrace();
        }

        input = findViewById(R.id.input);
        button = findViewById(R.id.button);

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

    private String get_input_from_file(FileInputStream fis) throws IOException {

        InputStreamReader isr = new InputStreamReader(fis);

        BufferedReader br = new BufferedReader(isr);

        StringBuilder sb = new StringBuilder();

        String text;

        while ((text=br.readLine()) != null) {

            sb.append(text);

        }

        return sb.toString();

    }

}