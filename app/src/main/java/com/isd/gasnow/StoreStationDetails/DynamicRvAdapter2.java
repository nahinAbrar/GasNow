package com.isd.gasnow.StoreStationDetails;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.isd.gasnow.R;
import com.isd.gasnow.Transactions.ApplicationMain;
import com.isd.gasnow.Transactions.UpdateSelectedItems;

import java.util.ArrayList;

public class DynamicRvAdapter2 extends RecyclerView.Adapter<DynamicRvAdapter2.DynamicRvHolder2>{
    public ArrayList<DynamicRvModel2> dynamicRvModels;
    Application activity;
    Context context;
    String name;
    int price;
    UpdateSelectedItems updateSelectedItems;
    public DynamicRvAdapter2(ArrayList<DynamicRvModel2> dynamicRvModels, Context context, UpdateSelectedItems updateSelectedItems){
        this.dynamicRvModels = dynamicRvModels;
        this.context = context;
        this.updateSelectedItems = updateSelectedItems;
    }

    public class DynamicRvHolder2 extends RecyclerView.ViewHolder {
        public TextView name,price;
        ConstraintLayout constraintLayout;

        ImageView cart, cart_done;
        public DynamicRvHolder2(@NonNull View itemView) {
            super(itemView);
            name  = itemView.findViewById(R.id.dynamic2Name);
            price  = itemView.findViewById(R.id.dynamic2Price);
            constraintLayout = itemView.findViewById(R.id.dynamic2ConstraintLayout1);
            cart = itemView.findViewById(R.id.add_to_cart);
            cart_done = itemView.findViewById(R.id.add_to_cart_done);


        }
    }
    @NonNull
    @Override
    public DynamicRvAdapter2.DynamicRvHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_item2,parent,false);
        DynamicRvHolder2 dynamicRvHolder = new DynamicRvHolder2(view);
        return dynamicRvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRvAdapter2.DynamicRvHolder2 holder, int position) {
        DynamicRvModel2 currentItem = dynamicRvModels.get(position);
        holder.name.setText(currentItem.getName());

        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = currentItem.getName();
                price = currentItem.getPrice();

                ((UpdateSelectedItems) ApplicationMain.getMyContext()).addItems(name, price);
                holder.cart.setVisibility(View.INVISIBLE);
                holder.cart_done.setVisibility(View.VISIBLE);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dynamicRvModels.size();
    }

}
