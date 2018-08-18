package com.example.ritika.breeze;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Post extends AppCompatActivity {

    private Button home,queries,profile,help,events,notices,post;
    private ImageButton image;
    private EditText desc;
    private Uri mImageuri = null;
    private static final int Gallery_Request = 1;
    private StorageReference mStorage;
    private DatabaseReference mDatabase,mRef;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private ProgressDialog mProgress;
    private String poto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mAuth=FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("newsfeed");
        home= (Button) findViewById(R.id.posthome);
        queries= (Button) findViewById(R.id.postqueries);
        profile= (Button) findViewById(R.id.postprofile);
        help= (Button) findViewById(R.id.posthelp);
        events= (Button) findViewById(R.id.postevents);
        notices= (Button) findViewById(R.id.postnotices);
        post = (Button) findViewById(R.id.post);
        image = (ImageButton) findViewById(R.id.postimage);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference().child("users");
        desc = (EditText) findViewById(R.id.postdesc);
        mProgress = new ProgressDialog(this);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
                Intent intent = new Intent(Post.this,MainActivity.class);
                startActivity(intent);
            }


        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent((Intent.ACTION_GET_CONTENT));
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,Gallery_Request);
            }
        });
        queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Post.this,queries.class);
                startActivity(intent);
            }
        });
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Post.this,notices.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Post.this,profile.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Post.this,events.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Post.this,MainActivity.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Post.this,help.class);
                startActivity(intent);
            }
        });
    }
    private void startPosting(){
            final String des = desc.getText().toString().trim();
            if(!TextUtils.isEmpty(des) && mImageuri != null)
            {
                FirebaseUser user = mAuth.getCurrentUser();
                final String id = user.getUid();
                        mRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String name = dataSnapshot.child(id).child("Name").getValue(String.class);
                                DatabaseReference newPost = mDatabase.push();
                                newPost.child("name").setValue(name);
                                newPost.child("userid").setValue(id);
                                newPost.child("desc").setValue(des);
                                newPost.child("image").setValue(poto);
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
            }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == Gallery_Request && resultCode == RESULT_OK)
        {
            mImageuri = data.getData();
            image.setImageURI(mImageuri);
            StorageReference filepath = mStorage.child("images").child(mImageuri.getLastPathSegment());
            filepath.putFile(mImageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    mStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                           poto = uri.toString();
                        }
                    });
                }
            });
        }
    }
}