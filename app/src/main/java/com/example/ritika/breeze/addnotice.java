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

public class addnotice extends AppCompatActivity {

    private Button home,queries,profile,help,events,notices,update;
    private EditText notice;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotice);


        notice = (EditText) findViewById(R.id.writenotice);
        home= (Button) findViewById(R.id.addnoticehome);
        queries= (Button) findViewById(R.id.addnoticequeries);
        profile= (Button) findViewById(R.id.addnoticeprofile);
        help= (Button) findViewById(R.id.addnoticehelp);
        events= (Button) findViewById(R.id.addnoticeevents);
        notices= (Button) findViewById(R.id.addnoticenotices);
        update = (Button) findViewById(R.id.update);

        mAuth= FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("notices");
        mDatabase= FirebaseDatabase.getInstance();

        queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addnotice.this,queries.class);
                startActivity(intent);
            }
        });
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addnotice.this,notices.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addnotice.this,profile.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addnotice.this,events.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addnotice.this,MainActivity.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addnotice.this,help.class);
                startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String writenotice=notice.getText().toString();
                 DatabaseReference newnotice=mRef.push();
                 newnotice.child("notice").setValue(writenotice);

                Toast.makeText(addnotice.this,"Updating",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(addnotice.this,notices.class);
                startActivity(intent);

            }
        });
    }
}
