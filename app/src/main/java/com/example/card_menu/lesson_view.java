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
            "Jesus our Teacher",
            "The Two Houses",
            "A Light Should Be Seen",
            "Revenge",
            "Praying To God",
            "Evil In God's World",
            "Children of God",
            "The Lost Sheep",
            "Forgiving Others",
            "God's Reward",
            "Be Prepared",
            "Waiting for Jesus",
            "Jesus is Baptised",
            "Jesus Calls Helpers (Disciples)",
            "A Man is Made Clean",
            "A Paralysed Man Walks",
            "A Withered Hand and Hard Hearts",
            "Jesus Calms the Storm",
            "A Woman is Healed",
            "A Dead Child Lives",
            "The Faith of a Foreigner",
            "A Deaf and Dumb Man Hears and Speaks",
            "Jesus Makes The Blind To See",
            "Jesus' Power Over Satan"

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
