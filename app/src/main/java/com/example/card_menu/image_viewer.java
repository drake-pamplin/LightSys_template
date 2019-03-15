package com.example.card_menu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class image_viewer extends AppCompatActivity {

    ImageView imgDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        //create toolbar
        Toolbar my_toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        //add upwards navigation
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String book = intent.getStringExtra(ImageAdapter.EXTRA_BOOK);
        String lesson = intent.getStringExtra(ImageAdapter.EXTRA_LESSON);

        getSupportActionBar().setTitle("Picture " + lesson);

        Context c = getApplicationContext();

        imgDisplay = findViewById(R.id.imgDisplay);
        imgDisplay.setImageDrawable(c.getResources().getDrawable(c.getResources().getIdentifier("book_" + book + "_lesson_" + lesson, "drawable", c.getPackageName())));
    }
}
