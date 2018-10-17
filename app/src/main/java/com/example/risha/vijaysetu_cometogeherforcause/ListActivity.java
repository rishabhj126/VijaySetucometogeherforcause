package com.example.risha.vijaysetu_cometogeherforcause;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    DatabaseReference NGODatabase;
    List<NGO> listNGO;
    private RecyclerView recyclerNGO;
    private LinearLayoutManager ngOlayoutmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        NGODatabase=FirebaseDatabase.getInstance().getReference("NGO");
        recyclerNGO = findViewById(R.id.list_feilds);
        ngOlayoutmanager = new LinearLayoutManager(this);
        recyclerNGO.setLayoutManager(ngOlayoutmanager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        NGODatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listNGO.clear();
                for(DataSnapshot NGOsnapshot: dataSnapshot.getChildren()){
                    NGO ngo=NGOsnapshot.getValue(NGO.class);
                    listNGO.add(ngo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final NGORecyclerAdapter ngoRecyclerAdapter=new NGORecyclerAdapter(this,listNGO);
        recyclerNGO.setAdapter(ngoRecyclerAdapter);
    }
}
