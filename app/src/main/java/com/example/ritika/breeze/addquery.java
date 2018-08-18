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

public class addquery extends AppCompatActivity {

    private Button home,queries,profile,help,events,notices,update;
    private EditText query;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquery);

        query = (EditText) findViewById(R.id.writequery);
        home= (Button) findViewById(R.id.addqueryhome);
        queries= (Button) findViewById(R.id.addqueryqueries);
        profile= (Button) findViewById(R.id.addqueryprofile);
        help= (Button) findViewById(R.id.addqueryhelp);
        events= (Button) findViewById(R.id.addqueryevents);
        notices= (Button) findViewById(R.id.addquerynotices);
        update = (Button) findViewById(R.id.update);

        mAuth= FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("queries");
        mDatabase= FirebaseDatabase.getInstance();

        queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addquery.this,queries.class);
                startActivity(intent);
            }
        });
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addquery.this,notices.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addquery.this,profile.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addquery.this,events.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addquery.this,MainActivity.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addquery.this,help.class);
                startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String writequery=queries.getText().toString();
                DatabaseReference newquery=mRef.push();
                newquery.child("query").setValue(writequery);

                Toast.makeText(addquery.this,"Updating",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(addquery.this,queries.class);
                startActivity(intent);


            }
        });
    }
}
