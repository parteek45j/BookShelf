package com.example.parteek.bookshelf;

/**
 * Created by Parteek on 10/13/2017.
 */

public class Bean {
    String title,name,imageUrl;

    public Bean() {
    }

    public Bean(String title, String name, String imageUrl) {
        this.title = title;
        this.name = name;
        this.imageUrl=imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
