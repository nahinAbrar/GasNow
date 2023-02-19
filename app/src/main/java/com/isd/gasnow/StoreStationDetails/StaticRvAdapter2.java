package com.isd.gasnow.StoreStationDetails;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isd.gasnow.StoreStationDetails.UpdateRecyclerView2;
import com.isd.gasnow.R;

import java.util.ArrayList;

public class StaticRvAdapter2 extends RecyclerView.Adapter<StaticRvAdapter2.StaticRvViewHolder2> {

    private ArrayList<StaticRvModel2> items;
    int row_index = -1;
    UpdateRecyclerView2 updateRecyclerView;
    Activity activity;
    boolean check =true;
    boolean select = true;
    int p;

    public StaticRvAdapter2(ArrayList<StaticRvModel2> items, Activity activity, UpdateRecyclerView2 updateRecyclerView) {
        this.items = items;
        this.activity = activity;
        this.updateRecyclerView = updateRecyclerView;
    }


    @NonNull
    @Override
    public StaticRvViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item2,parent,false);
        StaticRvViewHolder2 staticRvViewHolder = new StaticRvViewHolder2(view);
        return staticRvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRvViewHolder2 holder, int position) {
        StaticRvModel2 currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        p = currentItem.getPos();




        if(check){
            if(p==0){
                ArrayList<DynamicRvModel2> items = new ArrayList<DynamicRvModel2>();
                items.add(new DynamicRvModel2("LPG Gas Cylinder 35Kg", 3500));
                items.add(new DynamicRvModel2("LPG Gas Cylinder 22Kg", 3500));
                items.add(new DynamicRvModel2("LPG Gas Cylinder 28Kg", 3500));


                updateRecyclerView.callback2(position, items);
            } else if (p==1) {
                ArrayList<DynamicRvModel2> items = new ArrayList<DynamicRvModel2>();
                items.add(new DynamicRvModel2("Octane 1", 3500));
                items.add(new DynamicRvModel2("Octane 2", 3500));
                items.add(new DynamicRvModel2("Octane 3", 80));
                updateRecyclerView.callback2(position, items);
            }else if(p == 2) {

                ArrayList<DynamicRvModel2> items = new ArrayList<DynamicRvModel2>();
                items.add(new DynamicRvModel2("Disel 1", 110));
                items.add(new DynamicRvModel2("Disel 2", 110));
                items.add(new DynamicRvModel2("Disel 3", 110));
                updateRecyclerView.callback2(position, items);
            }
            else if(p == 3) {

                ArrayList<DynamicRvModel2> items = new ArrayList<DynamicRvModel2>();
                items.add(new DynamicRvModel2("Oil 1", 110));
                items.add(new DynamicRvModel2("Oil 2", 110));
                items.add(new DynamicRvModel2("Oil 3", 110));
                updateRecyclerView.callback2(position, items);
            }
            check = false;
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();

                if(position == 0)
                {
                    ArrayList<DynamicRvModel2> items = new ArrayList<DynamicRvModel2>();
                    items.add(new DynamicRvModel2("LPG Gas Cylinder 12Kg", 110));
                    items.add(new DynamicRvModel2("LPG Gas Cylinder 22Kg", 110));
                    items.add(new DynamicRvModel2("LPG Gas Cylinder 28Kg", 110));

                    updateRecyclerView.callback2(position, items);

                }else if(position == 1) {

                    ArrayList<DynamicRvModel2> items = new ArrayList<DynamicRvModel2>();
                    items.add(new DynamicRvModel2("Octane 1", 80));
                    items.add(new DynamicRvModel2("Octane 2", 80));
                    items.add(new DynamicRvModel2("Octane 3", 80));
                    updateRecyclerView.callback2(position, items);
                }else if(position == 2) {

                    ArrayList<DynamicRvModel2> items = new ArrayList<DynamicRvModel2>();
                    items.add(new DynamicRvModel2("Disel 1", 80));
                    items.add(new DynamicRvModel2("Disel 2", 80));
                    items.add(new DynamicRvModel2("Disel 3", 80));
                    updateRecyclerView.callback2(position, items);
                }
                else if(position == 3) {

                    ArrayList<DynamicRvModel2> items = new ArrayList<DynamicRvModel2>();
                    items.add(new DynamicRvModel2("Oil 1", 80));
                    items.add(new DynamicRvModel2("Oil 2", 80));
                    items.add(new DynamicRvModel2("Oil 3", 80));
                    updateRecyclerView.callback2(position, items);
                }
            }
        });

        if(select){
            if(position == 0)
                holder.layout.setBackgroundResource(R.drawable.static_rv_selected);
            select = false;
        }else{
            if(row_index == position)
                holder.layout.setBackgroundResource(R.drawable.static_rv_selected);
            else
                holder.layout.setBackgroundResource(R.drawable.static_rv_bg);
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRvViewHolder2 extends RecyclerView.ViewHolder{

        ImageView imageView;

        LinearLayout layout;
        public StaticRvViewHolder2(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.staticRv2ItemImage);
            layout = itemView.findViewById(R.id.staticRv2LinearLayout);
        }
    }
}

