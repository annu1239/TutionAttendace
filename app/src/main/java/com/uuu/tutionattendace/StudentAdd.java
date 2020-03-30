package com.uuu.tutionattendace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class StudentAdd extends AppCompatActivity {
    TextInputLayout e1,e2,e3,e4,e5,e6,e7,e8,e9;
    Button b;
    String name,password,confirmpassword,contact,email,batch,address,fathersname,fatherscontact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_student_add);
        e1=findViewById(R.id.susername);
        e2=findViewById(R.id.spassword);
        e3=findViewById(R.id.sconfirmpassword);
        e4=findViewById(R.id.scontact);
        e5=findViewById(R.id.semail);
        e6=findViewById(R.id.sBatch);
        e7=findViewById(R.id.saddress);
        e8=findViewById(R.id.sfathername);
        e9=findViewById(R.id.sfathercontact);
        b=findViewById(R.id.sadd);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=e1.getEditText().getText().toString();
                password=e2.getEditText().getText().toString();
                confirmpassword=e3.getEditText().getText().toString();
                contact=e4.getEditText().getText().toString();
                email=e5.getEditText().getText().toString();
                batch=e6.getEditText().getText().toString();
                address=e7.getEditText().getText().toString();
                fathersname=e8.getEditText().getText().toString();
                fatherscontact=e9.getEditText().getText().toString();

                scheckDataEntered();

            }
        });
    }


    boolean isEmpty(TextInputLayout text)
    {
        //CharSequence is a readable sequence of characters
        CharSequence str=text.getEditText().getText().toString();

        //TextUtils--> provides set of utility functions to do operations on string
        //all the functiojns TextUtils only returns boolean value
        //isEmpty-->This function is inbuilt function in TextUtils not our maded function

        return TextUtils.isEmpty(str);


    }


    boolean isEmail(TextInputLayout text)
    {
        CharSequence email=text.getEditText().getText().toString();
        return(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isValidPhoneNumber(TextInputLayout text)

    {
        CharSequence phone=text.getEditText().getText().toString();

        if(!TextUtils.isEmpty(phone))
            return(Patterns.PHONE.matcher(phone).matches() && phone.length()==10);
        else
            return false;
    }


    public void scheckDataEntered()
    {if(isEmpty(e1))
    {
        Toast.makeText(getApplicationContext(), "Username can't be empty", Toast.LENGTH_SHORT).show();
    }

    else if(isEmpty(e2))
    {   e1.setError(null);
        e2.setError("Enter Password");
    }

    else if(password.equals(confirmpassword)==false)
    {   e2.setError(null);
        e3.setError("Password does not match");
    }

    else if(isValidPhoneNumber(e4)==false)
    {   e2.setError(null);
        e3.setError(null);
        e4.setError("Enter Valid Phone number");
    }

    else if (isEmail(e5)==false)
    {    e2.setError(null);
        e3.setError(null);
        e4.setError(null);
        e5.setError("Enter Valid Email");
    }
    else if(isEmpty(e6))
    {   e2.setError(null);
        e3.setError(null);
        e4.setError(null);
        e5.setError(null);
        e6.setError("Enter Batch");
    }

    else if(isEmpty(e7))
    {    e2.setError(null);
        e3.setError(null);
        e4.setError(null);
        e5.setError(null);
        e6.setError(null);
        e7.setError("Enter Address");
    }

    else if(isEmpty(e8))
    {   e2.setError(null);
        e3.setError(null);
        e4.setError(null);
        e5.setError(null);
        e6.setError(null);
        e7.setError(null);
        e8.setError("Enter Father's Name");
    }

    else if(isValidPhoneNumber(e9)==false)
    {    e2.setError(null);
        e3.setError(null);
        e4.setError(null);
        e5.setError(null);
        e6.setError(null);
        e7.setError(null);
        e8.setError(null);
        e9.setError("Enter Valid Phone number");
    }




    else
    {
        new WebStudent().execute();
    }




    }

    public class WebStudent extends AsyncTask<Void,Void,String>
    {
        String result="";


        @Override
        protected void onPreExecute() {
            e2.setError(null);
            e3.setError(null);
            e4.setError(null);
            e5.setError(null);
            e6.setError(null);
            e7.setError(null);
            e8.setError(null);
            e9.setError(null);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL("\n" + "http://13.59.204.100/android/sadd.php?username=" + name + "&password=" + password + "&confirmpassword="+ confirmpassword+"&contact="  + contact + "&email=" + email + "&batch=" + batch + "&address=" + address + "&fathersname=" + fathersname + "&fatherscontact=" + fatherscontact);

                //URL is used to fetch data from any server

                InputStream stream=url.openConnection().getInputStream();
                //stream means sequence of bytes
                InputStreamReader ir=new InputStreamReader(stream);
                BufferedReader br=new BufferedReader(ir);
                String line="";
                while ((line=br.readLine())!=null)
                {
                    result=result+line+"\n";
                }
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace(); }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            String data=s.trim();//trim removes white spaces
            if(data.matches("true")){
                Toast.makeText(getApplicationContext(), "User Registeration Successful!!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "User Registeration Failed!!", Toast.LENGTH_SHORT).show();

            }
            e1.getEditText().setText("");
            e2.getEditText().setText("");
            e3.getEditText().setText("");
            e4.getEditText().setText("");
            e5.getEditText().setText("");
            e6.getEditText().setText("");
            e7.getEditText().setText("");
            e8.getEditText().setText("");
            e9.getEditText().setText("");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent login=new Intent(getApplicationContext(),Admin.class);
                    startActivity(login);
                    finish();
                }
            },1000);


        }
    }

}
