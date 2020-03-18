package com.uuu.tutionattendace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

            String result = "";

            try {
            URL url = new URL("http://18.218.141.91/android/index.php?etName="+name+"&etClass="+cls+"&etNum="+num+"&etTeacher="+teacher+"&etTime="+time);

            InputStream stream = url.openConnection().getInputStream();

            InputStreamReader ir = new InputStreamReader(stream);

            BufferedReader br = new BufferedReader(ir); //true false  there is a null character at end

            String line = "";

            while ((line = br.readLine())!= null)
            {
                result = result+line+"\n";// t tr tru true then loop exit when null is encountered
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

            return result;  //it is to be passed to onPostExecute
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

        @Override
        protected void onPostExecute(String s) {  //s contains result from doInBackground
            super.onPostExecute(s);

            progressBar.setVisibility(View.INVISIBLE);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String data = s.trim();

            if(data.matches("true"))
            {
                Toast.makeText(getApplicationContext(), "User Registeration Successful", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "User Registeration Failed", Toast.LENGTH_LONG).show();
            }

            etName.setText("");
            etClass.setText("");
            etNum.setText("");
            etTeacher.setText("");
            etTime.setText("");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent login = new Intent(getApplicationContext(),Admin.class);
                    startActivity(login);
                    finish();
                }
            }, 1000);
        }
    }
}
