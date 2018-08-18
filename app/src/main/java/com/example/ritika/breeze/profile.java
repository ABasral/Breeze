package com.example.ritika.breeze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends AppCompatActivity {

    private Button home,queries,profile,help,events,notices;
    private Button signout,user,syllabus,attendance,changepassword,support;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        signout=(Button) findViewById(R.id.signout);
        user=(Button) findViewById(R.id.profileprofile);
        syllabus=(Button) findViewById(R.id.profilesyllabus);
        attendance=(Button) findViewById(R.id.profileattendance);
        changepassword=(Button) findViewById(R.id.profilechangepassword);
        support=(Button) findViewById(R.id.profilehelp);
        home= (Button) findViewById(R.id.profilehome);
        queries= (Button) findViewById(R.id.profilequeries);
        profile= (Button) findViewById(R.id.profileprofile);
        help= (Button) findViewById(R.id.profilehelp);
        events= (Button) findViewById(R.id.profileevents);
        notices= (Button) findViewById(R.id.profilenotices);


        queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this,queries.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this,profile.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this,help.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this,MainActivity.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this,events.class);
                startActivity(intent);
            }
        });
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this,notices.class);
                startActivity(intent);
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(profile.this,Login.class);
                startActivity(intent);
                FirebaseAuth.getInstance().signOut();

            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(profile.this,user.class);
                startActivity(intent);

            }
        });
        syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(profile.this,syllabus.class);
                startActivity(intent);

            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(profile.this,attendance.class);
                startActivity(intent);

            }
        });
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(profile.this,changepassword.class);
                startActivity(intent);

            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(profile.this,helpsupport.class);
                startActivity(intent);

            }
        });




    }
}
