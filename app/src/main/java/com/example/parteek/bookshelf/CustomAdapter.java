package com.example.parteek.bookshelf;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Parteek on 10/13/2017.
 */

public class CustomAdapter extends ArrayAdapter<Bean> {
    Context context;
    int resource;
    ArrayList<Bean> list;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Bean> list) {
        super(context, resource, list);
        this.context=context;
        this.resource=resource;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Bean bean=getItem(position);
        View view=null;
        view= LayoutInflater.from(context).inflate(resource,parent,false);
        TextView textTitle=(TextView)view.findViewById(R.id.bookTitle);
        TextView textAuthor=(TextView)view.findViewById(R.id.bookAuthor);
        ImageView imageView=(ImageView)view.findViewById(R.id.image);
        textTitle.setText(bean.getTitle());
        textAuthor.setText(bean.getName());
        Glide.with(context).load(bean.getImageUrl()).into(imageView);
        return view;
    }
}
