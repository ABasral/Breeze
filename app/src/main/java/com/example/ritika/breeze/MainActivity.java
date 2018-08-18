package com.example.ritika.breeze;

import android.content.Intent;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
public class MainActivity extends AppCompatActivity {
    private Button home,queries,profile,help,events,notices,add;
    private RecyclerView mPostList;
    private DatabaseReference mdatabase;
    private DatabaseReference mdatabaselike;
    private CardView mcard;
    private FirebaseAuth mAuth;
    private FirebaseRecyclerAdapter<blog, MainActivity.BlogViewHolder> mAdapter;
    private boolean mProcessLike = false;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=(Button) findViewById(R.id.hmadd);
        queries= (Button) findViewById(R.id.homequeries);
        profile= (Button) findViewById(R.id.homeprofile);
        help= (Button) findViewById(R.id.homehelp);
        events= (Button) findViewById(R.id.homeevents);
        notices= (Button) findViewById(R.id.homenotices);
        mPostList= (RecyclerView) findViewById(R.id.post_list);

        mPostList.setHasFixedSize(true);
        mPostList.setLayoutManager(new LinearLayoutManager(this));
        mdatabase = FirebaseDatabase.getInstance().getReference().child("newsfeed");
        mdatabase.keepSynced(true);
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("newsfeed");
        Query query = mRef.orderByKey();
        mdatabaselike = FirebaseDatabase.getInstance().getReference().child("likes");
        mdatabaselike.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mLayoutManager.setReverseLayout(true); // THIS ALSO SETS setStackFromBottom to true
        mPostList.setLayoutManager(mLayoutManager);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Post.class);
                startActivity(intent);
            }
        });
        queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,queries.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,profile.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,help.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,events.class);
                startActivity(intent);
            }
        });
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,notices.class);
                startActivity(intent);
            }
        });




        FirebaseRecyclerOptions personOptions = new FirebaseRecyclerOptions.Builder<blog>().setQuery(query, blog.class).build();
        mAdapter = new FirebaseRecyclerAdapter<blog, MainActivity.BlogViewHolder>(personOptions) {
            @Override
            protected void onBindViewHolder(MainActivity.BlogViewHolder holder,final int position, final blog model) {
                    final String post_key = getRef(position).getKey();
                holder.setName(model.getName());
                holder.setDesc(model.getDesc());
                holder.setImage(getApplicationContext(),model.getImage());
                holder.setlikebtn(post_key);
                holder.itemView.setBackgroundColor(Color.parseColor("#fdefe4"));
                holder.mlikebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mProcessLike = true;

                            mdatabaselike.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (mProcessLike) {
                                        if (dataSnapshot.child(post_key).hasChild(mAuth.getCurrentUser().getUid())) {
                                            mdatabaselike.child(post_key).child(mAuth.getCurrentUser().getUid()).removeValue();
                                            mProcessLike = false;
                                        } else {
                                            mdatabaselike.child(post_key).child(mAuth.getCurrentUser().getUid()).setValue("bla bla");

                                            mProcessLike = false;
                                        }

                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                    }
                });
            }
            @Override
            public MainActivity.BlogViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_row,mcard);
                return new BlogViewHolder(view);
            }

        };

        mPostList.setAdapter(mAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder {
            View mView;
            ImageButton mlikebtn;
            DatabaseReference mdatabaselike;
            FirebaseAuth mAuth;
            public BlogViewHolder(View itemView) {
                super(itemView);
                mView = itemView;
                mlikebtn = (ImageButton)mView.findViewById(R.id.likebtn);
                mdatabaselike = FirebaseDatabase.getInstance().getReference().child("likes");
                mAuth = FirebaseAuth.getInstance();
                mdatabaselike.keepSynced(true);
            }
        public void setlikebtn(final String post_key){
            mdatabaselike.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(post_key).hasChild(mAuth.getCurrentUser().getUid())){
                          mlikebtn.setImageResource(R.drawable.blue_like);
                    }
                    else{
                        mlikebtn.setImageResource(R.drawable.thumb);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        public  void setName(String name){
            TextView user_name =(TextView) mView.findViewById(R.id.user_name);
            user_name.setText(name);
        }
        public void setDesc(String desc){
            TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }
        public void setImage(Context ctx, String image){
            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).fit().centerCrop()
                    .into(post_image);
        }
    }



}
