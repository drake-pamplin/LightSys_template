package com.example.card_menu;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private List<RecyclerItem> listItems;
    //private String test = "location_bg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Books");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        Context c = getApplicationContext();

        /* INPUT YOUR CODE BELOW
         * List items need the following:
         * "Book ( BOOK NUMBER )"
         * Description ( pulled from website )
         * Image name ( Download from website, follow naming convention!!! -> "book_<NUMBER>" )
         * Book number ( in string format -> "x" )
         * Lesson number is not used in the book view, set it to "0"
         */


        listItems.add(new RecyclerItem(
                "Book 7",
                "JESUS – Lord & Saviour",
                c.getResources().getDrawable(c.getResources().getIdentifier("book_7", "drawable", c.getPackageName())),
                "7",
                "0"));

        listItems.add(new RecyclerItem(
                "Book 8",
                "Acts of the HOLY SPIRIT",
                c.getResources().getDrawable(c.getResources().getIdentifier("book_8", "drawable", c.getPackageName())),
                "8",
                "0"));

        //set adapter
        adapter = new BookAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }
}
