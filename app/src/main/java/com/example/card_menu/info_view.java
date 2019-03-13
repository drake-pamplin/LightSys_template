package com.example.card_menu;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;

public class info_view extends AppCompatActivity {

    PDFView pdfView;

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
        String item = intent.getStringExtra(MainActivity.EXTRA_ITEM);

        getSupportActionBar().setTitle(item);

        pdfView = findViewById(R.id.pdfView);

        if (item.equals("About")) {
            pdfView.fromAsset("about.pdf")
                    //.pages(Integer.parseInt(lesson) + 3)
                    .load();
        }
        else { //Instructions
            pdfView.fromAsset("instruction.pdf")
                    //.pages(Integer.parseInt(lesson) + 3)
                    .load();
        }
    }
}
