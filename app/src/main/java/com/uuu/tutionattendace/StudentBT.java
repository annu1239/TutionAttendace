package com.uuu.tutionattendace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class StudentBT extends AppCompatActivity {

    TextView tvName, tvContact, tvAddress, tvAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_b_t);

        tvName = findViewById(R.id.tvName);
        tvContact = findViewById(R.id.tvContact);
        tvAddress = findViewById(R.id.tvAddress);
        tvAttendance = findViewById(R.id.tvAttendance);


    }
}
