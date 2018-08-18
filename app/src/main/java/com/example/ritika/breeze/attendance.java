package com.example.ritika.breeze;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class attendance extends AppCompatActivity {
    private Button home,queries,profile,help,events,notices,att;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        home= (Button) findViewById(R.id.attendancehome);
        queries= (Button) findViewById(R.id.attendancequeries);
        profile= (Button) findViewById(R.id.attendanceprofile);
        help= (Button) findViewById(R.id.attendancehelp);
        events= (Button) findViewById(R.id.attendanceevents);
        notices= (Button) findViewById(R.id.attendancenoti);
        att = (Button) findViewById(R.id.attend);

        queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(attendance.this,queries.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(attendance.this,profile.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(attendance.this,help.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(attendance.this,MainActivity.class);
                startActivity(intent);
            }
        });
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(attendance.this,notices.class);
                startActivity(intent);
            }
        });
       att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://docs.google.com/spreadsheets/d/1AIhBkEruEBvPRpcltrfvRHbBhPDQBJ_LwZoRRRpMBw8/edit?ts=5b4f6852#gid=186001220"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
}
