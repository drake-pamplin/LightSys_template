package com.example.biblelessonviewer;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;

public class InfoView extends AppCompatActivity {

    //activity that displays information designated by user on the MainActivity screen

    //initialize variable to hold PDFView
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

        //get intent from activity calling this one
        Intent intent = getIntent();

        //get the data from the intent set by MainActivity
        String item = intent.getStringExtra(MainActivity.EXTRA_ITEM);

        //set title in toolbar
        getSupportActionBar().setTitle(item);

        //attach variable to PDFView in layout
        pdfView = findViewById(R.id.pdfView);

        //check item requested by MainActivity, display either about page or instructions page
        if (item.equals("About")) {
            pdfView.fromAsset("about.pdf")
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                            pdfView.fitToWidth();
                        }
                    })
                    .load();
        }
        else { //Instructions
            pdfView.fromAsset("instruction.pdf")
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                            pdfView.fitToWidth();
                        }
                    })
                    .load();
        }
    }
}
