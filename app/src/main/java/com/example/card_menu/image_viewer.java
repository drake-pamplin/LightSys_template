package com.example.card_menu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

    //create overflow menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mp3_view_menu, menu);
        return true;
    }

    //handle menu item pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(this, mp3_access.class);

        switch (item.getItemId()) {
            case R.id.action_mp3:
                this.startActivity(intent);
                return true;

            default:
                //If we got here, the user's action was not recognized.
                //Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
