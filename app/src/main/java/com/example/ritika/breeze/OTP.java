package com.example.ritika.breeze;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OTP extends AppCompatActivity {


    private Button submit;
    private EditText otp;
    private TextView invalid;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        submit=(Button) findViewById(R.id.btnSubmit);
        otp=(EditText) findViewById(R.id.otp);
        invalid=(TextView) findViewById(R.id.invalidtext);
        mAuth=FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference().child("users");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String input = otp.getText().toString();
                if(input.equals("9999"))
                {
                    Bundle bundle = getIntent().getExtras();
                    final String e = bundle.getString("email");
                    final String ps = bundle.getString("password");
                    final String n = bundle.getString("name");
                    final String r = bundle.getString("roll");

                    mAuth.createUserWithEmailAndPassword(e,ps).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                String id= user.getUid();
                                mRef.child(id).child("Name").setValue(n);
                                mRef.child(id).child("Email").setValue(e);
                                mRef.child(id).child("Password").setValue(ps);
                                mRef.child(id).child("RollNo").setValue(r);
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(OTP.this, Login.class);
                                startActivity(intent);

                            }
                            else
                                {
                                Toast.makeText(OTP.this, "ERROR WHILE REGISTERING", Toast.LENGTH_SHORT).show();
                                }
                        }
                    });
                }
                else
                {
                    invalid.setVisibility(View.VISIBLE);
                }

            }
        });
    }
}
