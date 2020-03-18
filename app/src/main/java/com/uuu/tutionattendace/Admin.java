package com.uuu.tutionattendace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class Admin extends AppCompatActivity implements View.OnClickListener {

    Button btnBatch, btnTeacher, btnStudent;
    //TextView tvAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_admin);

        btnBatch = findViewById(R.id.btnBatch);
        btnStudent = findViewById(R.id.btnStudent);
        btnTeacher = findViewById(R.id.btnTeacher);

        btnBatch.setOnClickListener(this);
        btnStudent.setOnClickListener(this);
        btnTeacher.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnBatch:
          //      Intent intentB = new Intent(getApplicationContext(), );
            //    startActivity(intentB);
                finish();
                break;

            case R.id.btnStudent:
              //  Intent intentS = new Intent(getApplicationContext(), );
                //startActivity(intentS);
                //finish();
                break;

            case R.id.btnTeacher:
              //  Intent intentT = new Intent(getApplicationContext(), );
                //startActivity(intentT);
               // finish();
                //break;


        }
    }
}
