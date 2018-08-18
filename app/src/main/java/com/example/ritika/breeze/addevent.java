package com.example.ritika.breeze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addevent extends AppCompatActivity {

    private Button home,queries,profile,help,events,notices,add;
    private EditText title,date,time,venue,des;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);

        home= (Button) findViewById(R.id.addeventhome);
        queries= (Button) findViewById(R.id.addeventqueries);
        profile= (Button) findViewById(R.id.addeventprofile);
        help= (Button) findViewById(R.id.addeventhelp);
        events= (Button) findViewById(R.id.addeventevents);
        notices= (Button) findViewById(R.id.addeventnotices);
        add = (Button) findViewById(R.id.addevent);
        title = (EditText) findViewById(R.id.eventtitle);
        date = (EditText) findViewById(R.id.eventdate);
        time = (EditText) findViewById(R.id.eventtime);
        venue = (EditText) findViewById(R.id.eventvenue);
        des = (EditText) findViewById(R.id.eventdesc);

        mAuth= FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("events");
        mDatabase= FirebaseDatabase.getInstance();

        queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addevent.this,queries.class);
                startActivity(intent);
            }
        });
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addevent.this,notices.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addevent.this,profile.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addevent.this,events.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addevent.this,MainActivity.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addevent.this,help.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addtitle=title.getText().toString();
                String adddate=date.getText().toString();
                String addtime=time.getText().toString();
                String addvenue=venue.getText().toString();
                String description = des.getText().toString();
                DatabaseReference newnotice = mRef.push();
                newnotice.child("title").setValue(addtitle);
                newnotice.child("date").setValue(adddate);
                newnotice.child("time").setValue(addtime);
                newnotice.child("venue").setValue(addvenue);
                newnotice.child("desc").setValue(description);

                Toast.makeText(addevent.this,"Updating",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(addevent.this,events.class);
                startActivity(intent);

            }
        });
    }
}
