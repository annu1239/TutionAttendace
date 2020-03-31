package com.uuu.tutionattendace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class TeacherAdd extends AppCompatActivity {

    ProgressBar p;
    Button Tregister, Tbatcheslist;
    TextInputLayout Tfullname, Temail, Tcontactno,Taddress, Tpassword, Tconfirmpassword;
    String Tname, Tmail, Tcontact, Tadd, Tbatches, Tpass,Tconfirmpass;
    String[] Blist;//Batches list
    boolean[] checkedBatches;
    String url="";
    ArrayList<Integer> b =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add);

                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


                Tfullname=findViewById(R.id.Tfullname);
                Temail =findViewById(R.id.Temail);
                Tcontactno =findViewById(R.id.Tcontact);
                Taddress=findViewById(R.id.Taddress);
                Tbatcheslist =findViewById(R.id.Tbatches);
                Tpassword =findViewById(R.id.Tpassword);
                Tconfirmpassword =findViewById(R.id.Tconfirmword);
                Tregister =findViewById(R.id.Tregister);


//        Blist = getResources().getStringArray(R.array.batchesList);
//        checkedBatches = new boolean[Blist.length];
//
//        Tbatcheslist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder Bbuilder= new AlertDialog.Builder(getApplicationContext());
//                Bbuilder.setTitle("Batches");
//                Bbuilder.setMultiChoiceItems(Blist, checkedBatches, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
//                        if(isChecked){
//                           if(!b.contains(position)) {
//                               b.add(position);
//                           }}
////                           else{
////                               b.remove(position);
////                           }
//
//                           else if(b.contains(position)){
//                                b.remove(position);
//                            }
//
//                        }
//
//
//                });
//
//                Bbuilder.setCancelable(false);
//                Bbuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String batch="";
//                        for(int i=0;i<b.size();i++){
//                            batch=batch+Blist[b.get(i)];
//                        }
//                    }
//                });
//
//                Bbuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//                Bbuilder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        for(int i=0;i<checkedBatches.length;i++){
//                            checkedBatches[i]=false;
//                            b.clear();
//                        }
//                    }
//                });
//
//                AlertDialog bDialog= Bbuilder.create();
//                bDialog.show();
//
//            }
//        });


                Tregister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Tname =Tfullname.getEditText().getText().toString();
                        Tmail = Temail.getEditText().getText().toString();
                        Tcontact = Tcontactno.getEditText().getText().toString();
                        Tadd =Taddress.getEditText().getText().toString();
                        Tpass = Tpassword.getEditText().getText().toString();
                        Tconfirmpass= Tconfirmpassword.getEditText().getText().toString();
                       // Tbatches=Tbatcheslist.getEditText().getText().toString();//jb button pe click krke chrck box bnenge ye unke text ko access krega
                        checkDataEntered();
                    }
                });


            }

            public void checkDataEntered(){

                if(Tfullname.getEditText().getText().toString().trim().length()>1){
                    Tfullname.setError(null);
                }

                if(Taddress.getEditText().getText().toString().trim().length()>1){
                    Taddress.setError(null);
                }

                if(isValidPhoneNumber(Tcontactno)){
                    Tcontactno.setError(null);
                }

                if(Tpassword.getEditText().getText().toString().trim().length()>1){
                    Tpassword.setError(null);
                }

                if(Tconfirmpassword.getEditText().getText().toString().trim().length()>1){
                    Tconfirmpassword.setError(null);
                }

                if(Taddress.getEditText().getText().toString().trim().length()>1){
                    Taddress.setError(null);
                }


                //setError
                if(isEmpty(Tfullname)){
                    Tfullname.setError("fullname is required");
                }

                if(isEmpty(Tpassword)){
                    Tpassword.setError("Password is required");
                }
                if(isEmpty(Tconfirmpassword)){
                    Tconfirmpassword.setError("Enter Password to confirm");
                }
                if(isEmpty(Taddress)){
                    Taddress.setError("Address is required");
                }
//                if(isEmpty(Tcontactno)){
//                    Tcontactno.setError("Contact Number is required");
//                }
                if(!isValidPhoneNumber(Tcontactno)){
                    Tcontactno.setError("Enter Valid Contact Number");
                }

                if(!isEmail(Temail)){
                    Temail.setError("Enter valid Email");
                }

                if(isEmpty(Tfullname)&&isEmpty(Temail)&&isEmpty(Tconfirmpassword)&&isEmpty(Tcontactno)&&isEmpty(Tpassword)&&isEmpty(Taddress)) {
                    Toast.makeText(getApplicationContext(), "Please fill all Information", Toast.LENGTH_SHORT).show();
                }
                if(!isValidPass()){
                Tpassword.setError("Enter Valid Pass");
                Tconfirmpassword.setError("Enter Valid Pass");}

                //setError null
                else{
                            new TeacherDb().execute();
                    }

                }

                boolean isEmail(TextInputLayout text){
                CharSequence email= text.getEditText().getText().toString();
                //dont use third party validations
                return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());//patterns can use for contact ip and other inputs

            }

            boolean isValidPhoneNumber(TextInputLayout text){
                CharSequence phn = text.getEditText().getText().toString();
                if(!TextUtils.isEmpty(phn)){
                    if(text.getEditText().getText().toString().trim().length()<10){
                        return false;
                    }
                    return Patterns.PHONE.matcher(phn).matches();
                }
                return false;
            }

            boolean isEmpty(TextInputLayout text){
                //charSequence ia a readable sequence of characters

                CharSequence str=text.getEditText().getText().toString();//it canv read empty string//important

                //TextUtils --> provide set of utility function to do operations on string alwz return true or false
                //isEmpty(inbuilt function)

                return TextUtils.isEmpty(str);

            }
            public boolean isValidPass(){
                if(Tconfirmpass.equals(Tpass)){
                    return true;
                }else{
                    return false;

                }
            }

            public  class TeacherDb extends AsyncTask<Void,Void,String> {

                String result="";

                @Override
                protected void onPreExecute() {
                    Tfullname.setError(null);
                    Tpassword.setError(null);
                    Tconfirmpassword.setError(null);
                    Taddress.setError(null);
                    Tcontactno.setError(null);
                    Temail.setError(null);
                    p=findViewById(R.id.TprogressBar);
                    p.setVisibility(View.VISIBLE);
                    super.onPreExecute();
                }

                @Override
                protected String doInBackground(Void... voids) {
                    url = "http://13.59.204.100/android/tadd.php?username="+Tname+"&password="+Tpass+"&email="+Tmail+"&contact="+Tcontact+"&address="+Tadd+"&batch=6";
                    HttpHandlerPost hp =new HttpHandlerPost();
                    result=hp.makeServiceCall(url);

                    return result;

                }

                @Override
                protected void onPostExecute(String s) {
                    p.setVisibility(View.INVISIBLE);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String data=s.trim();
                    if(data.matches("true")){


                        Intent i= new Intent(getApplicationContext(),showTeacherList.class);
                        i.putExtra("TeacherName",Tname);
                        i.putExtra("TeacherPhnNo",Tcontact);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "Teacher Registred ", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "InValid Information !!", Toast.LENGTH_SHORT).show();
                    }
                    Tfullname.getEditText().setText("");
                    Temail.getEditText().setText("");
                    Tcontactno.getEditText().setText("");
                    Taddress.getEditText().setText("");
                    Tpassword.getEditText().setText("");
                    Tconfirmpassword.getEditText().setText("");

                    super.onPostExecute(s);
                }
            }
        }





