package com.example.ritika.breeze;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.EventLog;
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
public class events extends AppCompatActivity {
    private Button home,queries,profile,help,events,notices,add;
    private RecyclerView mEventList;
    private DatabaseReference mdatabase;
    private CardView mcard;
    private FirebaseRecyclerAdapter<newevent, events.EventViewHolder> mnewAdapter;
    private LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        add= (Button) findViewById(R.id.addevent);
        home= (Button) findViewById(R.id.eventshome);
        queries= (Button) findViewById(R.id.eventsqueries);
        profile= (Button) findViewById(R.id.eventsprofile);
        help= (Button) findViewById(R.id.eventshelp);
        events= (Button) findViewById(R.id.eventsevents);
        notices= (Button) findViewById(R.id.eventsnotices);
        mEventList= (RecyclerView) findViewById(R.id.event_list);
        mEventList.setHasFixedSize(true);
        mEventList.setLayoutManager(new LinearLayoutManager(this));
        mdatabase = FirebaseDatabase.getInstance().getReference().child("events");
        mdatabase.keepSynced(true);
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("events");
        Query query = mRef.orderByKey();
        mLayoutManager = new LinearLayoutManager(events.this);
        mLayoutManager.setReverseLayout(true); // THIS ALSO SETS setStackFromBottom to true
        mEventList.setLayoutManager(mLayoutManager);

        queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(events.this,queries.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(events.this,profile.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(events.this,help.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(events.this,MainActivity.class);
                startActivity(intent);
            }
        });
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(events.this,notices.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(events.this,addevent.class);
                startActivity(intent);
            }
        });
        FirebaseRecyclerOptions personOptions = new FirebaseRecyclerOptions.Builder<newevent>().setQuery(query, newevent.class).build();
        mnewAdapter = new FirebaseRecyclerAdapter<newevent, events.EventViewHolder>(personOptions) {
            @Override
            protected void onBindViewHolder(com.example.ritika.breeze.events.EventViewHolder holder, final int position, final newevent model) {
                holder.setTitle(model.getTitle());
                holder.setDate(model.getDate());
                holder.setTime(model.getTime());
                holder.setVenue(model.getVenue());
                holder.setDesc(model.getDesc());
                holder.itemView.setBackgroundColor(Color.parseColor("#fdefe4"));
            }
            @Override
            public events.EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_row, mcard);
                return new events.EventViewHolder(view);
            }
        };
        mEventList.setAdapter(mnewAdapter);
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
    public static class EventViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public EventViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public  void setTitle(String newtitle){
            TextView add_title =(TextView) mView.findViewById(R.id.add_title);
            add_title.setText(newtitle);
        }
        public  void setDate(String newdate){
            TextView add_date =(TextView) mView.findViewById(R.id.add_date);
            add_date.setText(newdate);
        }
        public  void setTime(String newtime){
            TextView add_time =(TextView) mView.findViewById(R.id.add_time);
            add_time.setText(newtime);
        }
        public  void setVenue(String newvenue){
            TextView add_venue =(TextView) mView.findViewById(R.id.add_venue);
            add_venue.setText(newvenue);
        }
        public  void setDesc(String newdesc){
            TextView add_desc =(TextView) mView.findViewById(R.id.add_desc);
            add_desc.setText(newdesc);
        }
    }
}