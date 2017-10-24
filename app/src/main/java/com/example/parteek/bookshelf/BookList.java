package com.example.parteek.bookshelf;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class BookList extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Bean>>{
    String key;
    String url1="https://www.googleapis.com/books/v1/volumes?q=";
    String url2="+inauthor:";
    String url3="&printType=";
    String url4="&key=";
    ListView listView;
    CustomAdapter customAdapter;
    ArrayList<Bean> beanArrayList;
    String name,title,type,url;
    LoaderManager loaderManager;
    int loaderId=1;
    ProgressDialog progressDialog;
    URI uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("lOading");
        progressDialog.setCancelable(false);
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        title=intent.getStringExtra("title");
        type=intent.getStringExtra("type");
        key=getString(R.string.api_key);
        listView=(ListView)findViewById(R.id.list_item);
        beanArrayList=new ArrayList<>();
        customAdapter=new CustomAdapter(this,R.layout.row,beanArrayList);
        listView.setAdapter(customAdapter);
        if (type.contains("magazines")) {
            url = getUrl(title, type);
        }else if(type.contains("all")){
            url = getUrl(title,type);
        }else{
            url =getUrl(title,name,type);
        }
        try {
            uri=new URI(url.replace(" ","_"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Log.e("url",url);
        loaderManager=getLoaderManager();
//        if(loaderManager==null){
            loaderManager.initLoader(loaderId,null,this).forceLoad();
//        }else {
//            loaderManager.restartLoader(loaderId,null,this).forceLoad();
//        }
    }
    private String getUrl(String title,String name,String type){

        return url1+title+url2+name+url3+type+url4+key;
    }private String getUrl(String title,String type){

        return url1+title+url3+type+url4+key;
    }

    @Override
    public Loader<ArrayList<Bean>> onCreateLoader(int i, Bundle bundle) {
        progressDialog.show();
        Log.e("OnLoaderCreate", String.valueOf(uri));
        return new BackGroundTask(BookList.this,String.valueOf(uri));
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Bean>> loader, ArrayList<Bean> list) {
        Log.e("OnLoaderFinish",list.toString());
        customAdapter.addAll(list);
        progressDialog.dismiss();
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Bean>> loader) {

    }
}
