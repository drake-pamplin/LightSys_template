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
    public static final String EXTRA_BOOK = "com.example.card_menu.BOOK";
    public static final String EXTRA_LESSON = "com.example.card_menu.LESSON";
    public static final String EXTRA_SEARCH = "com.example.card_menu.SEARCH";
//    public static final String EXTRA_PHOTOS = "com.example.biblelessonviewer.PHOTOS";



    private String[][] title = LessonView.title;
//    private String[][][] photos = LessonView.photos;

    private int[] bookNumber(String lesson_title) {

        int[] values = new int[2];

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 24; j++)
            {
                if (lesson_title.equals(title[i][j]))
                {
                    System.out.println(i + " " + j);

                    values[0] = i;
                    values[1] = j+1;
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

        getSupportActionBar().setTitle("Book " + book);

        System.out.println("Book " + book);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        // photo stuff
//        String[][][] photos = LessonView.photos;
//        Intent intent = getIntent();
//        String b = intent.getStringExtra(BookAdapter.EXTRA_BOOK);
//        int array;
//        String[][] photo_array;
//
//        if (b.equals("0")) {
//            array = 0;
//            photo_array = photos[array];
//        }
//        else {
//            array = 1;
//            photo_array = photos[array];
//        }



        Intent view = new Intent(this, PdfView.class);
        view.putExtra(EXTRA_BOOK, book);
        view.putExtra(EXTRA_LESSON, Integer.toString(results[1]));
        view.putExtra(EXTRA_SEARCH, "yes");
//        view.putExtra(EXTRA_PHOTOS, photo_array[0]);
        startActivity(view);

    }

}
