package com.uuu.tutionattendace;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandlerGet {

    //it's a class httphandler-> it is used to process our http requests

    private static final String TAG = HttpHandlerGet.class.getName();//used in logcat for debugging purpose

    public HttpHandlerGet(){ }

    public String makeServiceCall(String reqUrl){
        String response=null;
        try {
            URL url=new URL(reqUrl);
           // HttpURLConnection => it is used to make single request
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");//it tells connection is of type get

            //read the sequesnce
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response=convertStreamToString(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String convertStreamToString(InputStream in) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        StringBuilder sb =new StringBuilder();
        //String is an immutable class or Unmodifiable or unchangeable
        //StringBuilder are mutable classes
        String line="";
        try {
            while ((line = reader.readLine()) != null){
             sb.append(line).append("\n"); // indecates result=result+"\n"
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
            in.close();}
            catch(IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


}
