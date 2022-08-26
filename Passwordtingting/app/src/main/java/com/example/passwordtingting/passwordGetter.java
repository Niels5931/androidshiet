package com.example.passwordtingting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class passwordGetter extends AppCompatActivity {

    private static final String FILE_NAME = "shhh.txt";

    private LinearLayout liste;

    private ArrayList<String> navne, koder;

    private FileOutputStream fos;

    private FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_getter);

        EditText ny_side = findViewById(R.id.ny_side);
        EditText ny_kode = findViewById(R.id.ny_kode);
        Button ny_ting = findViewById(R.id.tilføj_ny);
        Button skift_kode = findViewById(R.id.Skift);

        ArrayList<TextView> view_list = new ArrayList<>();

        liste = (LinearLayout) findViewById(R.id.liste);
        
        navne = new ArrayList<>();
        koder = new ArrayList<>();


        try {

            load_shit_fra_fil();

            update_scroll_view(view_list);

        } catch (FileNotFoundException e) {
            Log.d("Bruh","Og vi fejler");
            Intent i = new Intent(this,BareLigeLavFil.class);
            i.putExtra("FILE_NAME",FILE_NAME);
            startActivity(i);
            finish();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ny_ting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    add_to_fil(ny_side,ny_kode);
                    ny_side.setText("");
                    ny_kode.setText("");
                    load_shit_fra_fil();
                    update_scroll_view(view_list);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        skift_kode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skift_kode(ny_side,ny_kode);
                ny_side.setText("");
                ny_kode.setText("");
                try {
                    load_shit_fra_fil();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                update_scroll_view(view_list);
            }
        });

        ny_side.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    liste.setVisibility(View.VISIBLE);
                } else {

                    liste.setVisibility(View.INVISIBLE);

                }
            }
        });

        ny_kode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    liste.setVisibility(View.VISIBLE);
                } else {
                    liste.setVisibility(View.INVISIBLE);
                }
            }
        });

        ConstraintLayout test = findViewById(R.id.hele_siden);

        test.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                test.requestFocus();
                close_keyboard();
                return false;
            }
        });

    }

    private void add_to_fil(EditText ny_navn, EditText ny_kode) throws IOException {

        fos = openFileOutput(FILE_NAME,MODE_APPEND);

        String ny_navn_str = ny_navn.getText().toString();
        String ny_kode_str = ny_kode.getText().toString();
        String temp = null;


        if ((!ny_navn_str.equals("")) && (!ny_kode_str.equals(""))) {

            Log.d("Bruuh", ny_kode_str);

            temp = ny_navn_str + "-" + ny_kode_str + "\n";
            fos.write(temp.getBytes());

        } else {

            Toast error = Toast.makeText(getApplicationContext(),"Input må ikke være tomt", Toast.LENGTH_LONG);
            error.show();

        }

        fos.close();

    }

    private void load_shit_fra_fil() throws IOException {

        navne.clear();
        koder.clear();

        fis = openFileInput(FILE_NAME);

        InputStreamReader isr = new InputStreamReader(fis);

        BufferedReader br = new BufferedReader(isr);

        String line;

        String[] temp = null;

        while((line = br.readLine()) != null) {

            Log.d("Bruh", line);

            temp = line.split("-");
            navne.add(temp[0]);
            koder.add(temp[1]);

        }

        fis.close();

    }

    private void update_scroll_view(ArrayList<TextView> list) {

        TextView temp = null;

        TextView t = findViewById(R.id.textView82);

        liste.removeAllViews();

        list.clear();

        for (int i = 0; i < navne.toArray().length; i++) {

            temp = new TextView(this);

            temp.setTextSize(24);

            temp.offsetTopAndBottom(10);

            temp.setText(navne.get(i));

            temp.setClickable(true);

            int finalI = i;
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    t.setText(koder.get(finalI));
                }
            });


            list.add(temp);

        }

        for (TextView tempo : list) {

            liste.addView(tempo);

        }

    }

    private void close_keyboard() {

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }

    }

    private void skift_kode(EditText navn, EditText ny_kode) {

        koder.set(navne.indexOf(navn.getText().toString()), ny_kode.getText().toString());

        File file = new File(getFilesDir(),FILE_NAME);

        String to_write = null;

        if (file.delete()) {

            try {
                fos = openFileOutput(FILE_NAME,MODE_PRIVATE);

                for (int i = 0; i < navne.toArray().length; i++) {

                    to_write = navne.get(i) + "-" + koder.get(i) + "\n";

                    try {
                        fos.write(to_write.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }



        }



    }

}