package com.example.ritika.breeze;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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




public class comments extends AppCompatActivity {


    private RecyclerView mCommentList;
    private CardView mcard;
    private Button add;
    private EditText cmnt;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    private FirebaseRecyclerAdapter<doubt,CommentViewHolder>mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        add = (Button)findViewById(R.id.addcomment);
        cmnt = (EditText)findViewById(R.id.typecomment);
        mAuth= FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("comment");
        mDatabase= FirebaseDatabase.getInstance();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newcmnt = cmnt.getText().toString();
                DatabaseReference newquery = mRef.push();
                newquery.child("comment").setValue(newcmnt);
            }
        });


        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("comment");
        Query query = mRef.orderByKey();
        mCommentList = (RecyclerView) findViewById(R.id.comment_list);
        FirebaseRecyclerOptions personOptions = new FirebaseRecyclerOptions.Builder<blog>().setQuery(query, blog.class).build();
        mAdapter = new FirebaseRecyclerAdapter<doubt,comments.CommentViewHolder>(personOptions) {
            @Override
            protected void onBindViewHolder(comments.CommentViewHolder holder, int position, @NonNull doubt model) {
                final String query_key = getRef(position).getKey();
                holder.setComment(model.getComment());

            }
            @Override
            public CommentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comments_row,mcard);
                return new CommentViewHolder(view);
            }

        };
        mCommentList.setAdapter(mAdapter);
    }
    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        View mView;
        ImageButton mcmntbtn;
        DatabaseReference mdatabaselike;
        FirebaseAuth mAuth;
        public CommentViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mcmntbtn = (ImageButton)mView.findViewById(R.id.likebtn);
            mdatabaselike = FirebaseDatabase.getInstance().getReference().child("likes");
            mAuth = FirebaseAuth.getInstance();
            mdatabaselike.keepSynced(true);

        }
        public  void setComment(String comment){
            TextView add_comment =(TextView) mView.findViewById(R.id.add_comment);
            add_comment.setText(comment);
        }




    }



}
