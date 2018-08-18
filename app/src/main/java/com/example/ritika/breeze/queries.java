package com.example.ritika.breeze;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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

public class queries extends AppCompatActivity {

    private Button home,profile,help,events,notices,add;
    private RecyclerView mQueryList;
    private DatabaseReference mdatabase;
    private DatabaseReference mdatabasecomments;
    private FirebaseAuth mAuth;
    private boolean mProcesscomments = false;
    private CardView mcard;
    private FirebaseRecyclerAdapter<newquery,QueryViewHolder>mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queries);

        home= (Button) findViewById(R.id.querieshome);
        profile= (Button) findViewById(R.id.queriesprofile);
        help= (Button) findViewById(R.id.querieshelp);
        events= (Button) findViewById(R.id.queriesevents);
        notices= (Button) findViewById(R.id.queriesnotices);
        add = (Button) findViewById(R.id.queryadd);
        mQueryList = (RecyclerView) findViewById(R.id.queries_list);
        mQueryList.setHasFixedSize(true);
        mQueryList.setLayoutManager(new LinearLayoutManager(this));
        mdatabase = FirebaseDatabase.getInstance().getReference().child("queries");
        mdatabase.keepSynced(true);
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("queries");
        Query query1 = mRef.orderByKey();
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(queries.this,notices.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(queries.this,profile.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(queries.this,events.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(queries.this,MainActivity.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(queries.this,help.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(queries.this,addquery.class);
                startActivity(intent);
            }
        });
        FirebaseRecyclerOptions personOptions = new FirebaseRecyclerOptions.Builder<newquery>().setQuery(query1,newquery.class).build();
        mAdapter = new FirebaseRecyclerAdapter<newquery, queries.QueryViewHolder>(personOptions) {
            @Override
            protected void onBindViewHolder(com.example.ritika.breeze.queries.QueryViewHolder holder,final int position,final newquery model) {
                final String query_key = getRef(position).getKey();
                holder.setQuery(model.getQuery());
                holder.itemView.setBackgroundColor(Color.parseColor("#fdefe4"));
                holder.mcommentbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(queries.this,comments.class);
                        startActivity(intent);


                    }
                });

            }

            @Override
            public queries.QueryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.queries_row,mcard);
                return new QueryViewHolder(view);
            }
        };
        mQueryList.setAdapter(mAdapter);
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
    public static class QueryViewHolder extends RecyclerView.ViewHolder {
        View mView;
        Button mcommentbtn;
        public QueryViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mcommentbtn = (Button)mView.findViewById(R.id.cbutton);
        }
        public  void setQuery(String query){
            TextView write_query = (TextView) mView.findViewById(R.id.write_query);
            write_query.setText(query);
        }
    }
}
