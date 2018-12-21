package com.example.risha.vijaysetu_cometogeherforcause;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenu;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    DatabaseReference NGODatabase;
    List<NGO> listNGO;
    private RecyclerView recyclerNGO;
    public static InterstitialAd mInterstitialAd;

    private ProgressBar progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        NGODatabase=FirebaseDatabase.getInstance().getReference("NGO");
        NGODatabase.keepSynced(true);
        recyclerNGO = findViewById(R.id.list_feilds);
        LinearLayoutManager ngOlayoutmanager = new LinearLayoutManager(this);
        recyclerNGO.setLayoutManager(ngOlayoutmanager);
        progressDialog= findViewById(R.id.progressBar);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9044422816522157/3931606693");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        listNGO=new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(ListActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ListActivity.this,new String[] {Manifest.permission.CALL_PHONE} ,1);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
         return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.button_add_new:
                mInterstitialAd.show();
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.button_contact:
                mInterstitialAd.show();
                startActivity(new Intent(this,ContactUsActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final NGORecyclerAdapter ngoRecyclerAdapter=new NGORecyclerAdapter(ListActivity.this,listNGO);
        recyclerNGO.setAdapter(ngoRecyclerAdapter);
        progressDialog.setVisibility(View.VISIBLE);
        NGODatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listNGO.clear();
                for(DataSnapshot NGOsnapshot: dataSnapshot.getChildren()){
                    NGO ngo=new NGO((NGOsnapshot.child("NGOName").getValue()==null)?null:NGOsnapshot.child("NGOName").getValue().toString(),
                                    (NGOsnapshot.child("NGOCategory").getValue()==null)?null:NGOsnapshot.child("NGOCategory").getValue().toString(),
                                    (NGOsnapshot.child("NGODescription").getValue()==null)?null:NGOsnapshot.child("NGODescription").getValue().toString(),
                                    (NGOsnapshot.child("NGOPhone").getValue()==null)?null:NGOsnapshot.child("NGOPhone").getValue().toString());
                    listNGO.add(ngo);

                }
                ngoRecyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        progressDialog.setVisibility(View.GONE);
    }
}
