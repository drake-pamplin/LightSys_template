package com.example.biblelessonviewer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ImageList extends AppCompatActivity {

    //Lists images related to the current lesson

    //variables
    //RecyclerView to display list of ImageItems
    //ImageAdapter to handle ImageItems
    //List to hold ImageItems
    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private List<ImageItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        //create toolbar
        Toolbar my_toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        //add upwards navigation
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //get the intent of the activity calling this one
        Intent intent = getIntent();
        //retrieve data from intent
        String book = intent.getStringExtra(PdfView.EXTRA_BOOK);
        String[] photos = intent.getStringArrayExtra(PdfView.EXTRA_PHOTOS);

        //set activity title
        getSupportActionBar().setTitle("Pictures");

        //get the recycler view and set layout manager
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initialize list
        listItems = new ArrayList<>();

        //get application context
        Context c = getApplicationContext();

        //generate list of photos, number of items determined by activity calling this one
        for (int i = 0; i < photos.length; i++) {
            /*

            * image
            * title
            * book number
            * photo number

             */

            listItems.add(new ImageItem(
                    c.getResources().getDrawable(c.getResources().getIdentifier("book_" + book + "_lesson_" + (photos[i]), "drawable", c.getPackageName())),
                    "Picture " + photos[i],
                    book,
                    photos[i]));
        }

        //set adapter
        adapter = new ImageAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }
}
