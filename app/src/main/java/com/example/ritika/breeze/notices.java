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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class notices extends AppCompatActivity {

    private Button home,queries,profile,help,events,notices,add;
    private RecyclerView mNoticeList;
    private DatabaseReference mdatabase;
    private CardView mcard;
    private FirebaseRecyclerAdapter<newnotice, notices.NoticeViewHolder> mnewAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);

        add=(Button) findViewById(R.id.noticesadd);
        home= (Button) findViewById(R.id.noticeshome);
        queries= (Button) findViewById(R.id.noticesqueries);
        profile= (Button) findViewById(R.id.noticesprofile);
        help= (Button) findViewById(R.id.noticeshelp);
        events= (Button) findViewById(R.id.noticesevents);
        notices= (Button) findViewById(R.id.noticesnotices);

        mNoticeList= (RecyclerView) findViewById(R.id.notice_list);
        mNoticeList.setHasFixedSize(true);
        mNoticeList.setLayoutManager(new LinearLayoutManager(this));
        mdatabase = FirebaseDatabase.getInstance().getReference().child("notices");
        mdatabase.keepSynced(true);
        //mLayoutManager = new LinearLayoutManager(notices.this);
        //mLayoutManager.setReverseLayout(true); // THIS ALSO SETS setStackFromBottom to true
        //mNoticeList.setLayoutManager(mLayoutManager);

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("notices");

        Query query = mRef.orderByKey();

        queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notices.this,queries.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notices.this,profile.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notices.this,events.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notices.this,MainActivity.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notices.this,help.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notices.this,addnotice.class);
                startActivity(intent);
            }
        });


        FirebaseRecyclerOptions personOptions = new FirebaseRecyclerOptions.Builder<newnotice>().setQuery(query,newnotice.class).build();
        mnewAdapter = new FirebaseRecyclerAdapter<newnotice, notices.NoticeViewHolder>(personOptions) {
            @Override
            protected void onBindViewHolder(com.example.ritika.breeze.notices.NoticeViewHolder holder,final int position,final newnotice model) {
                holder.setNotice(model.getNotice());
                holder.itemView.setBackgroundColor(Color.parseColor("#fdefe4"));
            }

            @Override
            public notices.NoticeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notice_row,mcard);
                return new NoticeViewHolder(view);
            }
        };
        mNoticeList.setAdapter(mnewAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mnewAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        mnewAdapter.stopListening();
    }
    public static class NoticeViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public NoticeViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }
        public  void setNotice(String notice){
            TextView write_notice =(TextView) mView.findViewById(R.id.write_notice);
            write_notice.setText(notice);

        }
    }
}
