package com.uuu.tutionattendace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class BatchAdd extends AppCompatActivity {

    EditText etName, etClass, etNum, etTeacher, etTime;
    String name, cls, num, teacher, time;
    Button btnAddBatch;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_add);

        etName = findViewById(R.id.etName);
        etClass = findViewById(R.id.etClass);
        etNum = findViewById(R.id.etNum);
        etTeacher = findViewById(R.id.etTeacher);
        etTime = findViewById(R.id.etTime);
        btnAddBatch = findViewById(R.id.btnAddBatch);

        btnAddBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                cls = etClass.getText().toString();
                num = etNum.getText().toString();
                teacher = etTeacher.getText().toString();
                time = etTime.getText().toString();


                new WebBatch().execute();
            }
        });

    }

    public class WebBatch extends AsyncTask<Void, Void, String> {

        String result = "";

        @Override
        protected String doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPreExecute() {

            etName.setError(null);
            etClass.setError(null);
            etNum.setError(null);
            etTeacher.setError(null);
            etTime.setError(null);
            progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }}