package com.uuu.tutionattendace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Login extends AppCompatActivity implements View.OnClickListener {

    ProgressBar p;
    Button go, forgetpass;
    TextInputLayout username,password;
    String user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        username =findViewById(R.id.username);
        password=findViewById(R.id.password);
        go=findViewById(R.id.go);
        forgetpass =findViewById(R.id.forgetPass);

        forgetpass.setOnClickListener(this);
        go.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.forgetPass:
                Toast.makeText(getApplicationContext(), "Link is sended to Your Registered email", Toast.LENGTH_SHORT).show();
                break;

            case R.id.go:
                user= username.getEditText().getText().toString();
                pass=password.getEditText().getText().toString();
                checkDataOfLogin();
//                new LoginDb().execute();
                break;



        }

    }

    public void checkDataOfLogin(){
        if(isEmpty(username)||isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Enter Corret Information", Toast.LENGTH_SHORT).show();
            username.setError("Enter Correct Username");
            password.setError("Enter correct Password");
        }

        else{
            username.setError(null);
            password.setError(null);
        }

    }


    boolean isEmpty(TextInputLayout text){
        //charSequence ia a readable sequence of characters
        CharSequence str=text.getEditText().getText().toString();//it canv read empty string//important
        return TextUtils.isEmpty(str);
    }

    public  class LoginDb extends AsyncTask<Void,Void,String> {
        String result="";

        @Override
        protected void onPreExecute() {
            username.setError(null);
            password.setError(null);
            p=findViewById(R.id.TprogressBar);
            p.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                //Ip ->ip
                URL url = new URL("http:// IP  /android/login.php?email="+user+"&pswd="+pass);
                InputStream stream=url.openConnection().getInputStream();
                InputStreamReader ir = new InputStreamReader(stream);
                BufferedReader br = new BufferedReader(ir);
                String line="";

                while ((line=br.readLine())!=null){
                    result=result+line+"\n";
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                Toast.makeText(getApplicationContext(), "Valid User", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "InValid User !!", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(s);
        }
    }

}
