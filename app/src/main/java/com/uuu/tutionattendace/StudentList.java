package com.uuu.tutionattendace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StudentList extends AppCompatActivity {

    String Surl,Sname1,Scontact1;
    ArrayList Slist;
    ListView Slv;
    ArrayAdapter Sadp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Slv=findViewById(R.id.SlistView);
        Slist=new ArrayList<String>();


        Surl="http://13.59.204.100/android/allStudentData.php";

        new StudentList.LoadStudentData().execute();//whenever we done network related task than application has to use AsyncTask class
        Sadp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Slist);//android.R.layout.simple_list_item_1   -->> it shows how item display
        Slv.setAdapter(Sadp);

    }

    public class LoadStudentData extends AsyncTask<Void,Void,String> {


        @Override
        protected String doInBackground(Void... voids) {
            HttpHandlerGet sh = new HttpHandlerGet();
            String jsonstr = sh.makeServiceCall(Surl);

            if (jsonstr != null) {
                try {

                    JSONObject jsonObject = new JSONObject(jsonstr);
                    JSONArray data = jsonObject.getJSONArray(("AllInfo"));//here Result is the name of the object;

                    for(int i=1;i<=data.length();i++) {

                        JSONObject jsonObject1 = data.getJSONObject((i-1));//here Result is the name of the object;
                        JSONObject t = jsonObject1.getJSONObject("Result"+i);
                        Sname1 = t.getString("Name");
                        Slist.add(Sname1);


                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
        }
    }

}
