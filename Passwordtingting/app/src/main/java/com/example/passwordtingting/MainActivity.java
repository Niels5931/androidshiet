package com.example.passwordtingting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "checkfile7.txt";

    String pin;
    EditText input;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent password_getter = new Intent(this, passwordGetter.class);
        Intent make_pin = new Intent(this, makePassword.class);

        FileInputStream fis;

        try {
            fis = openFileInput(FILE_NAME);

            pin = get_input_from_file(fis);

            fis.close();
        } catch (FileNotFoundException e) {
            Log.d("Bruh", "Den fandtes ikke dafuq?");
            startActivity(make_pin);

        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        button = findViewById(R.id.button);


        TextView temp = findViewById(R.id.textView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                temp.setText(pin);

                if (1==1) {
                    //

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