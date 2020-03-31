package com.uuu.tutionattendace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class showTeacherList extends AppCompatActivity {
//    String teacherName,teacherPhn;
    Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_teacher_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        Intent i =getIntent();
//        teacherName=i.getStringExtra("TeacherName");
//        teacherPhn=i.getStringExtra("TeacherPhnNo");
//        addTeacherButton();
//
//    }
//
//    private void addTeacherButton() {
//        LinearLayout layout =(LinearLayout) findViewById(R.id.root_Layout);
//        newTeacher=new Button(this);
//        newTeacher.setText("teacherName");
////        newTeacher.setId(Integer.parseInt(teacherPhn));//id of each teacher is its unique phn no.
//        layout.addView(newTeacher);
//    }

       Button bt=findViewById(R.id.newBtn);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton();
            }
        });
    }

    public void addButton(){
        LinearLayout l= findViewById(R.id.root_Layout);
        bt1=new Button(this);
        bt1.setText("Hii");
        l.addView(bt1);
    }


}
