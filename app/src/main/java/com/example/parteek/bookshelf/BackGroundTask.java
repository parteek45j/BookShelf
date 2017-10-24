package com.example.parteek.bookshelf;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Parteek on 10/13/2017.
 */

public class BackGroundTask extends AsyncTaskLoader<ArrayList<Bean>> {
    String mUrl;
    public BackGroundTask(Context context,String url) {
        super(context);
        mUrl=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Bean> loadInBackground() {
        ArrayList<Bean> arrayList=NetworkCall.getArrayList(mUrl);
        return arrayList;
    }
}
