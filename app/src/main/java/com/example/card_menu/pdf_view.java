package com.example.card_menu;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;

public class pdf_view extends AppCompatActivity {

    PDFView pdfView;
    String book;
    String lesson;
    public static final String EXTRA_BOOK = "com.example.card_menu.BOOK";
    public static final String EXTRA_PDF = "com.example.card_menu.PDF";
    public static final String EXTRA_PHOTOS = "com.example.card_menu.PHOTOS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        //create toolbar
        Toolbar my_toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        //add upwards navigation
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent.getStringExtra(search_result.EXTRA_SEARCH) != null)
        {
            book = intent.getStringExtra(search_result.EXTRA_BOOK);
            lesson = intent.getStringExtra(search_result.EXTRA_LESSON);
        }
        else
        {
            book = intent.getStringExtra(LessonAdapter.EXTRA_BOOK);
            lesson = intent.getStringExtra(LessonAdapter.EXTRA_LESSON);
        }
        //String[] photos = intent.getStringArrayExtra(LessonAdapter.EXTRA_PHOTOS);

        if (book.equals("0")) {
            getSupportActionBar().setTitle("Good News Lesson " + lesson);
        }
        else {
            getSupportActionBar().setTitle("Book " + book + " Lesson " + lesson);
        }

        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("book_" + book + ".pdf")
                .pages(Integer.parseInt(lesson) + 3)
                .onRender(new OnRenderListener() {
                    @Override
                    public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                        pdfView.fitToWidth();
                    }
                })
                .load();
    }

    //create overflow menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pdf_menu, menu);
        return true;
    }

    //handle menu item pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = getIntent();
        String book = intent.getStringExtra(LessonAdapter.EXTRA_BOOK);
        String[] photos = intent.getStringArrayExtra(LessonAdapter.EXTRA_PHOTOS);

        intent = new Intent(this, image_list.class);

        switch (item.getItemId()) {
            case R.id.action_picture:
                //user chose the "about" item, open the about view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra(EXTRA_PHOTOS, photos);
                this.startActivity(intent);
                return true;

            default:
                //If we got here, the user's action was not recognized.
                //Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    // make OS back button go to lesson_view
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(pdf_view.this, lesson_view.class);
            intent.putExtra(EXTRA_BOOK, book);
            intent.putExtra(EXTRA_PDF, "yes");
            startActivity(intent);
            return true;
        }
        return false;
    }
}
