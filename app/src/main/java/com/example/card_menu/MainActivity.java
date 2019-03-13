package com.example.card_menu;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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

        //create toolbar
        Toolbar my_toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

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

        //book 1
        listItems.add(new RecyclerItem(
                "Book 1",
                "Beginning with GOD",
                 c.getResources().getDrawable(c.getResources().getIdentifier("book_1", "drawable", c.getPackageName())),
                "1",
                "0"));

        //book 2
        listItems.add(new RecyclerItem(
                "Book 2",
                "Mighty Men GOD",
                c.getResources().getDrawable(c.getResources().getIdentifier("book_2", "drawable", c.getPackageName())),
                "2",
                "0"));

        //book 3
        listItems.add(new RecyclerItem(
                "Book 3",
                "Victory through GOD",
                c.getResources().getDrawable(c.getResources().getIdentifier("book_3", "drawable", c.getPackageName())),
                "3",
                "0"));

        //book 4
        listItems.add(new RecyclerItem(
                "Book 4",
                "Servants of GOD",
                c.getResources().getDrawable(c.getResources().getIdentifier("book_4", "drawable", c.getPackageName())),
                "4",
                "0"));

        //book 5
        listItems.add(new RecyclerItem(
                "Book 5",
                "On Trial For GOD",
                c.getResources().getDrawable(c.getResources().getIdentifier("book_5", "drawable", c.getPackageName())),
                "5",
                "0"));

        //book 6
        listItems.add(new RecyclerItem(
                "Book 6",
                "JESUS – Teacher & Healer",
                c.getResources().getDrawable(c.getResources().getIdentifier("book_6", "drawable", c.getPackageName())),
                "6",
                "0"));

        //book 7
        listItems.add(new RecyclerItem(
                "Book 7",
                "JESUS – Lord & Saviour",
                c.getResources().getDrawable(c.getResources().getIdentifier("book_7", "drawable", c.getPackageName())),
                "7",
                "0"));

        //book 8
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

    //create overflow menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //handle menu item pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                //user chose the "Settings" item, show the app settings UI
                return true;

            case R.id.action_instructions:
                //user chose the "Favorite" action, mark the current item as a favorite
                return true;

            default:
                //If we got here, the user's action was not recognized.
                //Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
