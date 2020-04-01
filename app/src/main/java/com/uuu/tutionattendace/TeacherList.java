package com.uuu.tutionattendace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TeacherList extends AppCompatActivity {

    String url,Tname1,Tcontact1;
    ArrayList Tlist;
    ListView Tlv;
    ArrayAdapter adp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Tlv=findViewById(R.id.TlistView);
        Tlist=new ArrayList<String>();


        url="http://13.59.204.100/android/allTeacherData.php";

        new LoadTeacherData().execute();//whenever we done network related task than application has to use AsyncTask class
        adp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Tlist);//android.R.layout.simple_list_item_1   -->> it shows how item display
        Tlv.setAdapter(adp);

    }

    public class LoadTeacherData extends AsyncTask<Void,Void,String> {


        @Override
        protected String doInBackground(Void... voids) {
            HttpHandlerGet sh = new HttpHandlerGet();
            String jsonstr = sh.makeServiceCall(url);

            if (jsonstr != null) {
                try {

                    JSONObject jsonObject = new JSONObject(jsonstr);
                    JSONArray data = jsonObject.getJSONArray(("AllInfo"));//here Result is the name of the object;

                    for(int i=1;i<=data.length();i++) {

                        JSONObject jsonObject1 = data.getJSONObject((i-1));//here Result is the name of the object;
                        JSONObject t = jsonObject1.getJSONObject("Result"+i);
                        Tname1 = t.getString("Name");
                        Tlist.add(Tname1);


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



