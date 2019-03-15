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

    public static final String EXTRA_BOOK = "com.example.biblelessonviewer.BOOK";
    public static final String EXTRA_PHOTOS = "com.example.biblelessonviewer.PHOTOS";

    PDFView pdfView;

    String book;
    String lesson;
    String[] photos;

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

        if (intent.getStringExtra(LessonView.EXTRA_ACTIVITY) != null) {
            book = intent.getStringExtra(LessonView.EXTRA_BOOK);
            lesson = intent.getStringExtra(LessonView.EXTRA_LESSON);
            photos = intent.getStringArrayExtra(LessonView.EXTRA_PHOTOS);
        }
        else {
            book = intent.getStringExtra(LessonAdapter.EXTRA_BOOK);
            lesson = intent.getStringExtra(LessonAdapter.EXTRA_LESSON);
            photos = intent.getStringArrayExtra(LessonAdapter.EXTRA_PHOTOS);
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
        Intent intent = new Intent(this, ImageList.class);

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
}
