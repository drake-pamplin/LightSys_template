package com.example.card_menu;

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

public class image_list extends AppCompatActivity {

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

        Intent intent = getIntent();
        String book = intent.getStringExtra(pdf_view.EXTRA_BOOK);
        String[] photos = intent.getStringArrayExtra(pdf_view.EXTRA_PHOTOS);

        getSupportActionBar().setTitle("Pictures");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        Context c = getApplicationContext();

        for (int i = 0; i < photos.length; i++) {
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
