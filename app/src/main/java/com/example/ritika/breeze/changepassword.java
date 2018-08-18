package com.example.ritika.breeze;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class changepassword extends AppCompatActivity {
    private Button home,queries,profile,help,events,notices,change;
    private EditText currentpassword, newpassword, verifypassword;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        home= (Button) findViewById(R.id.changepasswordhome);
        queries= (Button) findViewById(R.id.changepasswordqueries);
        profile= (Button) findViewById(R.id.changepasswordprofile);
        help= (Button) findViewById(R.id.changepasswordhelp);
        events= (Button) findViewById(R.id.changepasswordevents);
        notices= (Button) findViewById(R.id.changepasswordnotices);
        change= (Button) findViewById(R.id.changepassword);
        currentpassword= (EditText) findViewById(R.id.currentpassword);
        newpassword= (EditText) findViewById(R.id.newpassword);
        verifypassword= (EditText) findViewById(R.id.verifypassword);
        mAuth= FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference().child("users");
        final String ps = currentpassword.getText().toString();
        queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(changepassword.this,queries.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(changepassword.this,profile.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(changepassword.this,help.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(changepassword.this,MainActivity.class);
                startActivity(intent);
            }
        });
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(changepassword.this,notices.class);
                startActivity(intent);
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String ps = currentpassword.getText().toString();
                final String nps = newpassword.getText().toString();
                final String vps = verifypassword.getText().toString();

                final FirebaseUser user = mAuth.getCurrentUser();
                final String id= user.getUid();

                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     String  userpassword = dataSnapshot.child(id).child("Password").getValue(String.class);
                        String mail = dataSnapshot.child(id).child("mail").getValue(String.class);

                        if(!ps.equals(userpassword))
                        {
                            Toast.makeText(changepassword.this, "Password does not match at all.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if (ps.equals("") || nps.equals("") || vps.equals(""))
                        {
                            Toast.makeText(changepassword.this, "Fill all fields",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(!nps.equals(vps)) {
                            Toast.makeText(changepassword.this, "Password does not match.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                                                user1.updatePassword(nps).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            mRef.child(id).child("Password").setValue(nps);
                                                            Intent intent = new Intent(changepassword.this,profile.class);
                                                            startActivity(intent);
                                                        }
                                                        else {
                                                            Toast.makeText(changepassword.this, "Password cant be updated.",
                                                                    Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                }
            });
        }

    }



