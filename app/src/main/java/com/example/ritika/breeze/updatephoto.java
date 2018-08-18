package com.example.ritika.breeze;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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
import com.squareup.picasso.Picasso;

public class updatephoto extends AppCompatActivity {

    private Button updatedp;
    private ImageButton changedp;
    private Uri mImageuri = null,duri=null;
    private static final int Gallery_Request = 1;
    private StorageReference mStorage;
    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatephoto);

        updatedp=(Button) findViewById(R.id.changedp);
        changedp=(ImageButton) findViewById(R.id.dp);
        mAuth=FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance().getReference();
        mRef = FirebaseDatabase.getInstance().getReference().child("users");

        updatedp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    StorageReference filepath = mStorage.child("userdp").child(mImageuri.getLastPathSegment());
                    filepath.putFile(mImageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            final Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();

                            FirebaseUser user = mAuth.getCurrentUser();
                            final String id = user.getUid();
                            Picasso.with(updatephoto.this).load(String.valueOf(downloadUrl)).fit().centerCrop()
                                    .into(changedp);
                            mRef.child(id).child("userdp").setValue(downloadUrl.toString());



                           /* mRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    mRef.child(id).child("userdp").setValue(downloadUrl.toString());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });*/
                    }

                                                                     });


                Intent intent=new Intent(updatephoto.this,user.class);
                startActivity(intent);

                    }
            });

        changedp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent((Intent.ACTION_GET_CONTENT));
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,Gallery_Request);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Gallery_Request && resultCode == RESULT_OK)
        {
            mImageuri = data.getData();
            changedp.setImageURI(mImageuri);
        }
    }
}
