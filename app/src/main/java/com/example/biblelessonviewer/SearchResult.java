package com.example.biblelessonviewer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.biblelessonviewer.PdfView;

import java.util.ArrayList;
import java.util.List;

public class SearchResult extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LessonAdapter adapter;
    private List<RecyclerItem> listItems;
    public static final String EXTRA_BOOK = "com.example.biblelessonviewer.BOOK";
    public static final String EXTRA_LESSON = "com.example.biblelessonviewer.LESSON";
    public static final String EXTRA_SEARCH = "com.example.biblelessonviewer.SEARCH";
    public static final String EXTRA_PHOTOS = "com.example.biblelessonviewer.PHOTOS";

    private String[][] title = LessonView.title;
    private String[][][] photos = LessonView.photos;

    private int[] bookNumber(String lesson_title) {

        int[] values = new int[2];

        // loop through book
        for (int i = 0; i < 9; i++)
        {
            // loop through lessons
            int l;
            // 34 lessons in Good News
            if (i == 0) {l = 34;}
            // 24 lessons in books 1-8
            else {l = 24;}
            for (int j = 0; j < l; j++)
            {
                if (lesson_title.equals(title[i][j]))
                {

                    values[0] = i;
                    values[1] = j;
                    return values;
                }
            }
        }

        // if it wasn't found; but should never happen
        values[0] = -1;
        values[1] = -1;
        return values;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent name = getIntent();
        String lesson = name.getStringExtra(SearchBar.EXTRA_ITEM);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        //create toolbar
        Toolbar my_toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        //add upwards navigation
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        // gets book number, lesson number
        int[] results = bookNumber(lesson);
        String book = Integer.toString(results[0]);
        int lessonNum = results[1];

        getSupportActionBar().setTitle("Book " + book);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        // chooses correct array for the book
        String[][] photo_array;
        if (book.equals("0"))
            photo_array = photos[0];
        else
            photo_array = photos[1];


        // sends book number, lesson number, and lesson's photo(s) to PdfView
        Intent view = new Intent(this, PdfView.class);
        view.putExtra(EXTRA_BOOK, book);
        view.putExtra(EXTRA_LESSON, Integer.toString(results[1]));
        view.putExtra(EXTRA_SEARCH, "yes");
        view.putExtra(EXTRA_PHOTOS, photo_array[lessonNum-1]);
        startActivity(view);

    }

}
