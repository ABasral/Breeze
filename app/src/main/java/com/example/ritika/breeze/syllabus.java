package com.example.ritika.breeze;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class syllabus extends AppCompatActivity {
    private Button home,queries,profile,help,events,notices;
    private Button applied,cse,ece,civil,mech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        home= (Button) findViewById(R.id.syllabushome);
        queries= (Button) findViewById(R.id.syllabusqueries);
        profile= (Button) findViewById(R.id.syllabusprofile);
        help= (Button) findViewById(R.id.syllabushelp);
        events= (Button) findViewById(R.id.syllabusevents);
        notices= (Button) findViewById(R.id.syllabusnotices);
        applied= (Button) findViewById(R.id.appliedscience);
        cse= (Button) findViewById(R.id.cse);
        ece= (Button) findViewById(R.id.ece);
        civil= (Button) findViewById(R.id.civil);
        mech= (Button) findViewById(R.id.mechanical);

        queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(syllabus.this,queries.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(syllabus.this,profile.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(syllabus.this,help.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(syllabus.this,MainActivity.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(syllabus.this,events.class);
                startActivity(intent);
            }
        });
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(syllabus.this,notices.class);
                startActivity(intent);
            }
        });
         applied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(syllabus.this,appliedsyllabus.class);
                startActivity(intent);
            }
        });
         cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(syllabus.this,csesyllabus.class);
                startActivity(intent);
            }
        });
         ece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(syllabus.this,ecesyllabus.class);
                startActivity(intent);
            }
        });
         civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(syllabus.this,civilsyllabus.class);
                startActivity(intent);
            }
        });
         mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(syllabus.this,mechsyllabus.class);
                startActivity(intent);
            }
        });


    }
}
