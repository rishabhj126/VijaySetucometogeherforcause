package com.example.risha.vijaysetu_cometogeherforcause;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import static com.example.risha.vijaysetu_cometogeherforcause.R.layout.list_item;

public class NGORecyclerAdapter extends RecyclerView.Adapter<NGORecyclerAdapter.ViewHolder> {
   private final Context mContext;
   private final List<NGO> mNGO;
    private final LayoutInflater mlayoutInflater;


    public NGORecyclerAdapter(Context mContext, List<NGO> mNGO) {
        this.mContext = mContext;
        this.mNGO = mNGO;
        mlayoutInflater = LayoutInflater.from(this.mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mlayoutInflater.inflate(list_item ,viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NGO ngo=mNGO.get(i);
        viewHolder.mtextTitle.setText(ngo.getNGOName());
        viewHolder.mtextDescription.setText(ngo.getNGODescription());
        viewHolder.mtextCategory.setText(ngo.getNGOCategory());
        viewHolder.mbuttonPhone.setText(ngo.getNGOPhone());

    }

    @Override
    public int getItemCount() {
        return mNGO.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView mtextTitle;
        private final TextView mtextCategory;
        private final TextView mtextDescription;
        private final Button mbuttonPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtextTitle = itemView.findViewById(R.id.text_title);
            mtextCategory = itemView.findViewById(R.id.text_category);
            mtextDescription = itemView.findViewById(R.id.text_description);
            mbuttonPhone = itemView.findViewById(R.id.button_contact);





































        }
    }
}
