package com.example.parteek.bookshelf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {


    EditText editText1,editText2;
    Spinner spinner;
    Button button;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    String query1,query2,query3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1=(EditText)findViewById(R.id.edit_Title);
        editText2=(EditText)findViewById(R.id.edit_Author);
        button=(Button)findViewById(R.id.submit);
        button.setOnClickListener(this);
        spinner=(Spinner)findViewById(R.id.spinner);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        adapter.add("Books and Magazines");
        adapter.add("Books");
        adapter.add("Magazines");
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i==0){
            query3="all";
        }else if(i==1){
            query3="books";
        }else if (i==2){
            query3="magazines";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        query1=editText1.getText().toString();
        query2=editText2.getText().toString();
        Intent intent=new Intent(MainActivity.this,BookList.class);
        intent.putExtra("title",query1);
        intent.putExtra("name",query2);
        intent.putExtra("type",query3);
        startActivity(intent);
    }

}
