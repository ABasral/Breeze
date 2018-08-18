package com.example.ritika.breeze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class help extends AppCompatActivity {

    private Button home,queries,profile,help,events,notices,search;
    private EditText searchhere;
    private TextView describe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        home= (Button) findViewById(R.id.helphome);
        queries= (Button) findViewById(R.id.helpqueries);
        profile= (Button) findViewById(R.id.helpprofile);
        help= (Button) findViewById(R.id.helphelp);
        events= (Button) findViewById(R.id.helpevents);
        notices= (Button) findViewById(R.id.helpnotices);
        search= (Button) findViewById(R.id.search);
        searchhere= (EditText) findViewById(R.id.searchhere);
        describe=(TextView) findViewById(R.id.describe);


        queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(help.this,queries.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(help.this,profile.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(help.this,events.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(help.this,MainActivity.class);
                startActivity(intent);
            }
        });
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(help.this,notices.class);
                startActivity(intent);
            }
        });

    }
}
