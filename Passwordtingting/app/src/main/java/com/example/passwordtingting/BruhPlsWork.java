package com.example.passwordtingting;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BruhPlsWork extends AppCompatActivity {

    LinearLayout liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bruh_pls_work);

        liste = (LinearLayout) findViewById(R.id.Dinmor);

        test();

    }

    private void test() {

        TextView temp = null;

        for (int i = 0; i < 10; i++) {

            temp = new TextView(this);
            temp.setText(String.valueOf(i));
            liste.addView(temp);

        }

    }

}