package com.example.parteek.bookshelf;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Parteek on 10/13/2017.
 */

public class NetworkCall {

    public static ArrayList<Bean> getArrayList(String mUrl)  {
        String response="";
        ArrayList<Bean> arrayList=null;
        URL url = createUrl(mUrl);
        response=makeHttpConnection(url);
        Log.e("Response",response);
        arrayList=makeJSonRequest(response);
        return arrayList;
    }




    private static URL createUrl(String mUrl){
        URL url=null;
        try {
            url=new URL(mUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpConnection(URL url)  {
        String response="";
        HttpURLConnection connection=null;
        InputStream inputStream=null;
        try {
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.connect();
            inputStream=connection.getInputStream();
            response=writeIntoStream(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return response;
    }

    private static String writeIntoStream(InputStream inputStream) throws IOException {
        StringBuilder output=new StringBuilder();
        if (inputStream!=null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line=reader.readLine();
            }
        }
        return output.toString();
    }
    private static ArrayList<Bean> makeJSonRequest(String response)  {
        ArrayList<Bean> arrayList=new ArrayList<>();
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("items");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                JSONObject object1=object.getJSONObject("volumeInfo");
                String title=object1.getString("title");
                JSONArray jsonArray1=object1.getJSONArray("authors");
                String name=jsonArray1.optString(0);
                JSONObject jsonObject1=object1.getJSONObject("imageLinks");
                String imageUrl=jsonObject1.getString("smallThumbnail");
                arrayList.add(new Bean(title,name,imageUrl));
                Log.e("url",imageUrl);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }
}
