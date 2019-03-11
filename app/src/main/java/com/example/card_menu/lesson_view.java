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
            "How Everything Began",
            "Everything Man Needed",
            "Sin Begins",
            "The Results of Sin",
            "Noah, a Righteous Man",
            "The Flood",
            "The Rainbow - God's Promise",
            "The Tower of Babel",
            "Righteous Job",
            "A Time of Great Trouble",
            "Job Suffers",
            "Job is Restored",
            "Abraham Obeys God",
            "Lot's Choice",
            "Abraham Rescues Lot",
            "God's Promise to Abraham",
            "Ishmael is Born",
            "God's Promise Renewed",
            "Abraham Prays for Sodom",
            "God Tests Abraham",
            "A Wife for Isaac",
            "God Sends His Promised Son",
            "Jesus Died for Us",
            "Thomas Meets the Risen Jesus"

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
