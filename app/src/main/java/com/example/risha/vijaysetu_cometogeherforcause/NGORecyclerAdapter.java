package com.example.risha.vijaysetu_cometogeherforcause;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;

import static com.example.risha.vijaysetu_cometogeherforcause.ListActivity.mInterstitialAd;
import static com.example.risha.vijaysetu_cometogeherforcause.R.layout.list_item;

public class NGORecyclerAdapter extends RecyclerView.Adapter<NGORecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final List<NGO> mNGO;
    private final LayoutInflater mlayoutInflater;
    private static final int LIST_AD_DELTA = 3;
    private static final int CONTENT = 0;
    private static final int AD = 1;

    public NGORecyclerAdapter(Context mContext, List<NGO> mNGO) {
        this.mContext = mContext;
        this.mNGO = mNGO;
        mlayoutInflater = LayoutInflater.from(this.mContext);

        mInterstitialAd.loadAd(new AdRequest.Builder().build());



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = mlayoutInflater.inflate(list_item, viewGroup, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        NGO ngo = mNGO.get(i);
        viewHolder.mtextTitle.setText(ngo.getNGOName());
        viewHolder.mtextDescription.setText(ngo.getNGODescription());
        viewHolder.mtextCategory.setText(ngo.getNGOCategory());
        if(ngo.getNGOPhone().equals("Phone not Provided")){
        viewHolder.mbuttonPhone.setText(ngo.getNGOPhone());

        viewHolder.mbuttonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterstitialAd.show();
                if (!viewHolder.mbuttonPhone.getText().toString().equals("Phone not Provided")) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + viewHolder.mbuttonPhone.getText().toString()));


                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        mContext.startActivity(intent);
                    }
                }

                }
            });}


    }


    @Override
    public int getItemCount() {
        return mNGO.size();
    }

    @Override
    public int getItemViewType(int position) {
    return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mtextTitle;
        private final TextView mtextCategory;
        private final TextView mtextDescription;
        private final TextView mbuttonPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtextTitle = itemView.findViewById(R.id.text_title);
            mtextCategory = itemView.findViewById(R.id.text_category);
            mtextDescription = itemView.findViewById(R.id.text_description);
            mbuttonPhone = itemView.findViewById(R.id.button_contact);
        }
    }
}
