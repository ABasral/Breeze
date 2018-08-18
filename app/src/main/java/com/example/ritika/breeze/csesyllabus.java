package com.example.ritika.breeze;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class csesyllabus extends AppCompatActivity {
    PDFView book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csesyllabus);

        book = (PDFView) findViewById(R.id.csepdf);
        book.fromAsset("cse.pdf").load();
    }
}
