package com.example.biblelessonviewer;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;

public class PdfView extends AppCompatActivity {

    //activity to handle viewing PDFs for books 1-8 and "Good News" module

    //strings used to pack data for the ImageList activity
    public static final String EXTRA_BOOK = "com.example.biblelessonviewer.BOOK";
    public static final String EXTRA_PHOTOS = "com.example.biblelessonviewer.PHOTOS";
    public static final String EXTRA_PDF = "com.example.biblelessonviewer.PDF";


    //variable to hold PDFView in layout
    PDFView pdfView;

    //strings to house book number, lesson number, and photo array from activity calling this one
    String book;
    String lesson;
    String[] photos;

    // handles behavior of app's back button if this activity is called via search functionality
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(PdfView.this, LessonView.class);
        intent.putExtra(EXTRA_BOOK, book);
        intent.putExtra(EXTRA_PDF, "yes");
        startActivity(intent);
    }

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

        //get intent from activity calling this one
        Intent intent = getIntent();

        //determine which activity called this one; LessonView has additional EXTRA info called
        //EXTRA_ACTIVITY that can be used to determine is LessonView called this activity
        //otherwise, handle as if LessonAdapter called this activity

        //get extra data from intent that called this activity
        if (intent.getStringExtra(SearchResult.EXTRA_SEARCH) != null) {
            book = intent.getStringExtra(SearchResult.EXTRA_BOOK);
            lesson = intent.getStringExtra(SearchResult.EXTRA_LESSON);
            photos = intent.getStringArrayExtra(SearchResult.EXTRA_PHOTOS);

        } else if (intent.getStringExtra(LessonView.EXTRA_ACTIVITY) != null) {
            book = intent.getStringExtra(LessonView.EXTRA_BOOK);
            lesson = intent.getStringExtra(LessonView.EXTRA_LESSON);
            photos = intent.getStringArrayExtra(LessonView.EXTRA_PHOTOS);

        } else {
            book = intent.getStringExtra(LessonAdapter.EXTRA_BOOK);
            lesson = intent.getStringExtra(LessonAdapter.EXTRA_LESSON);
            photos = intent.getStringArrayExtra(LessonView.EXTRA_PHOTOS);
        }

        //determine the book the user is in and set title in toolbar accordingly
        if (book.equals("0")) {
            getSupportActionBar().setTitle("Good News Lesson " + lesson);
        }
        else {
            getSupportActionBar().setTitle("Book " + book + " Lesson " + lesson);
        }

        //get pdfView from layout and set pdf to display
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
        Intent intent = new Intent(this, ImageList.class);

        // handles app's back button behavior
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_picture:
                //user chose the "picture" item, open the ImageList activity
                //pack extra data into intent for ImageList to use
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
}
