package com.example.card_menu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class lesson_view extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LessonAdapter adapter;
    private List<RecyclerItem> listItems;

    private String[] title = {
            "The Birth of Jesus",
            "Jesus Turns Water into Wine",
            "Jesus Speaks to Nicodemus",
            "A Ruler Kneels Before Jesus",
            "The Sick Man at the Pool",
            "Jesus Feeds Five Thousand People",
            "Jesus Walks on the Water",
            "Jesus Heals a Blind Man",
            "Jesus Calls Lazarus from Death",
            "Jesus Dies on the Cross",
            "Mary and Jesus at the Tomb",
            "Jesus Appears to His Friends",
            "Jesus Teaches Two Friends",
            "The Son Among the Pigs",
            "The Lost Son Comes Home",
            "The Wealth of a Rich Man",
            "The Beggar and the Rich Man",
            "The Friend at the Door",
            "Two Men in God's House",
            "A Man Sows his Seed",
            "The Seed Grows",
            "Help for an Injured Man",
            "The House Owner Comes Home",
            "The Man Up a Tree"
    };

    private String[] title_book7 = {
            "The Birth of Jesus",
            "Jesus Turns Water into Wine",
            "Jesus Speaks to Nicodemus",
            "A Ruler Kneels Before Jesus",
            "The Sick Man at the Pool",
            "Jesus Feeds Five Thousand People",
            "Jesus Walks on the Water",
            "Jesus Heals a Blind Man",
            "Jesus Calls Lazarus from Death",
            "Jesus Dies on the Cross",
            "Mary and Jesus at the Tomb",
            "Jesus Appears to His Friends",
            "Jesus Teaches Two Friends",
            "The Son Among the Pigs",
            "The Lost Son Comes Home",
            "The Wealth of a Rich Man",
            "The Beggar and the Rich Man",
            "The Friend at the Door",
            "Two Men in God's House",
            "A Man Sows his Seed",
            "The Seed Grows",
            "Help for an Injured Man",
            "The House Owner Comes Home",
            "The Man Up a Tree"

    };

    private String[] title_book8 = {
            "The Lord Jesus Returns to Heaven",
            "The Coming of the Holy Spirit",
            "Peter Begins to Preach",
            "The First Church",
            "Power to Heal",
            "Giving to God",
            "Stephen, Faithful unto Death",
            "he Good News Spreads to Sudan",
            "Peter's Vision”, “Peter Visits the House of Cornelius",
            "Peter in Prison",
            "Power Through Prayer",
            "Paul is Changed",
            "Ananias Visits Paul",
            "The Church Sends Missionaries",
            "The Missionary Message",
            "God Guides Paul",
            "Paul and Silas in Trouble at Philippi",
            "Paul and the Unknown God",
            "Conflicts at Corinth",
            "Paul Faces Death",
            "A Message for Kings",
            "With God in Danger",
            "Paul in Rome"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_view);

        Intent intent = getIntent();
        String book = intent.getStringExtra(BookAdapter.EXTRA_BOOK);

        getSupportActionBar().setTitle("Book " + book);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        Context c = getApplicationContext();

        /* INPUT YOUR CODE BELOW
         * List items need the following:
         * Lesson <NUMBER> ( let the current code handle that )
         * Title ( populate "title" array above )
         * Image ( Download from website, follow naming convention!!! -> book 1 lesson 1 photo = "book_1_lesson_1.jpg"
         * Book number ( let current code handle this )
         * Lesson number ( let current code handle this )
         */

        for (int i = 0; i < 24; i++) {
            listItems.add(new RecyclerItem(
                    "Lesson " + (i + 1),
                    title[i],
                    c.getResources().getDrawable(c.getResources().getIdentifier("book_" + book + "_lesson_" + (i + 1), "drawable", c.getPackageName())),
                    book,
                    "" + (i + 1)));
        }

        //set adapter
        adapter = new LessonAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }
}
