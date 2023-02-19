package com.isd.gasnow.ItemsDashBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.isd.gasnow.R;

import java.util.ArrayList;

public class DynamicRvAdapter extends RecyclerView.Adapter<DynamicRvAdapter.DynamicRvHolder>{

    public ArrayList<DynamicRvModel> dynamicRvModels;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener mListener){
        this.mListener = mListener;
    }
    public DynamicRvAdapter(ArrayList<DynamicRvModel> dynamicRvModels){
        this.dynamicRvModels = dynamicRvModels;
    }


    public class DynamicRvHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView name,price;
        ConstraintLayout constraintLayout;
        public DynamicRvHolder(@NonNull View itemView, final OnItemClickListener mListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.dynamicImageView);
            name  = itemView.findViewById(R.id.dynamicName);
            price  = itemView.findViewById(R.id.dynamicDetails);

            constraintLayout = itemView.findViewById(R.id.dynamicConstraintLayout1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position = getAbsoluteAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public DynamicRvAdapter.DynamicRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_item,parent,false);
        DynamicRvHolder dynamicRvHolder = new DynamicRvHolder(view, mListener);
        return dynamicRvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRvAdapter.DynamicRvHolder holder, int position) {
        DynamicRvModel currentItem = dynamicRvModels.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.name.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return dynamicRvModels.size();
    }


}