package com.example.ritika.breeze;

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

import static android.app.ProgressDialog.show;

public class Signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button signup;

    private EditText name,email,newps,confirmps,rollno;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        signup = (Button) findViewById(R.id.btnSignup);

        name= (EditText) findViewById(R.id.Name);
        email= (EditText) findViewById(R.id.email);
        newps= (EditText) findViewById(R.id.NewPassword);
        confirmps= (EditText) findViewById(R.id.ConfirmPassword);
        rollno = (EditText) findViewById(R.id.Rollno);

        mAuth = FirebaseAuth.getInstance();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String n = name.getText().toString();
                final String e = email.getText().toString();
                final String ps = newps.getText().toString();
                String cps = confirmps.getText().toString();
                final String r = rollno.getText().toString();
                if (n.equals("") || e.equals("") || ps.equals("") || cps.equals("") || r.equals(""))
                {
                    Toast.makeText(Signup.this, "Incomplete Fields",
                            Toast.LENGTH_SHORT).show();
                }
                else if (!ps.equals(cps)) {

                    Toast.makeText(Signup.this, "Password does not match.",
                            Toast.LENGTH_SHORT).show();
                }
                else
                    {


                                            Intent intent = new Intent(Signup.this, OTP.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("name",n);
                                            bundle.putString("email",e);
                                            bundle.putString("password",ps);
                                            bundle.putString("roll",r);

                                            intent.putExtras(bundle);
                                            startActivity(intent);
                    }

            }
        });


    }
}
