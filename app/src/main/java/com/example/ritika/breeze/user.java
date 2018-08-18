package com.example.ritika.breeze;

import android.content.Intent;

        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import com.squareup.picasso.Picasso;

        public class user extends AppCompatActivity {

            private Button home,queries,profile,help,events,notices;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mRef;
    private TextView name,roll,email;
    private Button edit;
    private ImageView photo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        name = (TextView) findViewById(R.id.name);
        roll = (TextView) findViewById(R.id.rollnumber);
        email = (TextView) findViewById(R.id.emailid);
        mAuth=FirebaseAuth.getInstance();
        edit = (Button) findViewById(R.id.edit);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference().child("users");
        photo = (ImageView) findViewById(R.id.profile_image);
        home= (Button) findViewById(R.id.userhome);
        queries= (Button) findViewById(R.id.userqueries);
        profile= (Button) findViewById(R.id.userprofile);
        help= (Button) findViewById(R.id.userhelp);
        events= (Button) findViewById(R.id.userevents);
        notices= (Button) findViewById(R.id.usernotices);


        queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user.this,queries.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user.this,profile.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user.this,help.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user.this,MainActivity.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user.this,events.class);
                startActivity(intent);
            }
        });
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user.this,notices.class);
                startActivity(intent);
            }
        });

        final FirebaseUser user = mAuth.getCurrentUser();
        final String id= user.getUid();

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child(id).child("Name").getValue(String.class);
                String userroll = dataSnapshot.child(id).child("RollNo").getValue(String.class);
                String useremail = dataSnapshot.child(id).child("Email").getValue(String.class);
                String image = dataSnapshot.child(id).child("userdp").getValue(String.class);
                Picasso.with(user.this).load(image).fit().centerCrop()
                        .into(photo);

                name.setText(username);
                roll.setText(userroll);
                email.setText(useremail);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user.this,updatephoto.class);
                startActivity(intent);
            }
        });






    }
}
