package com.example.card_menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class pdf_view extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        Intent intent = getIntent();
        String book = intent.getStringExtra(LessonAdapter.EXTRA_BOOK);
        String lesson = intent.getStringExtra(LessonAdapter.EXTRA_LESSON);

        getSupportActionBar().setTitle("Book " + book + " Lesson " + lesson);

        Toast.makeText(getApplicationContext(), "book " + book + ", lesson " + lesson, Toast.LENGTH_LONG).show();

        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("book_" + book + ".pdf")
                .pages(Integer.parseInt(lesson) + 3)
                .load();
    }
}
