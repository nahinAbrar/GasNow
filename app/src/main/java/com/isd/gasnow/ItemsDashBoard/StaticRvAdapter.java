package com.isd.gasnow.ItemsDashBoard;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isd.gasnow.R;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRvViewHolder> {

    private ArrayList<StaticRvModel> items;
    int row_index = -1;
    UpdateRecyclerView updateRecyclerView;
    Activity activity;
    boolean check =true;
    boolean select = true;

    public StaticRvAdapter(ArrayList<StaticRvModel> items, Activity activity, UpdateRecyclerView updateRecyclerView) {
        this.items = items;
        this.activity = activity;
        this.updateRecyclerView = updateRecyclerView;
    }


    @NonNull
    @Override
    public StaticRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent,false);
        StaticRvViewHolder staticRvViewHolder = new StaticRvViewHolder(view);
        return staticRvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRvViewHolder holder, int position) {
        StaticRvModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        if(check){
            ArrayList<DynamicRvModel> items = new ArrayList<DynamicRvModel>();
            items.add(new DynamicRvModel("Bashundhara LP 12Kg", R.drawable.lpg,0));
            items.add(new DynamicRvModel("Bashundhara LP 22Kg", R.drawable.lpg,0));
            items.add(new DynamicRvModel("Bashundhara LP 30Kg", R.drawable.lpg,0));
            items.add(new DynamicRvModel("Beximco LP 12Kg", R.drawable.lpg,0));
            items.add(new DynamicRvModel("Beximco LP 22Kg", R.drawable.lpg,0));

            updateRecyclerView.callback(position, items);
            check = false;
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();

                if(position == 0)
                {
                    ArrayList<DynamicRvModel> items = new ArrayList<DynamicRvModel>();
                    items.add(new DynamicRvModel("Bashundhara LP 12Kg", R.drawable.lpg,0));
                    items.add(new DynamicRvModel("Bashundhara LP 22Kg", R.drawable.lpg,0));
                    items.add(new DynamicRvModel("Bashundhara LP 30Kg", R.drawable.lpg,0));
                    items.add(new DynamicRvModel("Beximco LP 12Kg", R.drawable.lpg,0));
                    items.add(new DynamicRvModel("Beximco LP 22Kg", R.drawable.lpg,0));

                    updateRecyclerView.callback(position, items);

                }else if(position == 1) {

                    ArrayList<DynamicRvModel> items = new ArrayList<DynamicRvModel>();
                    items.add(new DynamicRvModel("Disel 1", R.drawable.fire,1));
                    items.add(new DynamicRvModel("Disel 2", R.drawable.fire,1));
                    items.add(new DynamicRvModel("Disel 3", R.drawable.fire,1));
                    updateRecyclerView.callback(position, items);
                }else if(position == 2) {

                    ArrayList<DynamicRvModel> items = new ArrayList<DynamicRvModel>();
                    items.add(new DynamicRvModel("Station 1 Octane", R.drawable.octane,2));
                    items.add(new DynamicRvModel("Station 2 Octane", R.drawable.octane,2));
                    items.add(new DynamicRvModel("Station 3 Octane", R.drawable.octane,2));
                    updateRecyclerView.callback(position, items);
                }
                else if(position == 3) {

                    ArrayList<DynamicRvModel> items = new ArrayList<DynamicRvModel>();
                    items.add(new DynamicRvModel("Oil 5Kg", R.drawable.oil,3));
                    items.add(new DynamicRvModel("Oil 10Kg", R.drawable.oil,3));
                    items.add(new DynamicRvModel("Oil 15Kg", R.drawable.oil,3));
                    updateRecyclerView.callback(position, items);
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

    public static class StaticRvViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;

        LinearLayout layout;
        public StaticRvViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.staticRvItemImage);
            textView = itemView.findViewById(R.id.staticRvItemText);
            layout = itemView.findViewById(R.id.staticRvLinearLayout);
        }
    }
}
