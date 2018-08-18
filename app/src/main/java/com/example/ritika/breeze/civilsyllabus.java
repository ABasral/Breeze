package com.example.ritika.breeze;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class civilsyllabus extends AppCompatActivity {
    PDFView book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civilsyllabus);

        book = (PDFView) findViewById(R.id.civilpdf);
        book.fromAsset("civil.pdf").load();
    }
}
